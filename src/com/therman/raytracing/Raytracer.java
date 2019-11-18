package com.therman.raytracing;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector2;
import com.therman.raytracing.camera.Camera;
import com.therman.raytracing.material.Material;
import com.therman.raytracing.sampling.Regular;
import com.therman.raytracing.sampling.Sampler;
import com.therman.raytracing.sampling.SquareDistributor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.text.DecimalFormat;
import java.util.concurrent.*;

public class Raytracer extends JComponent {

    private final int width;
    private final int height;
    private final BufferedImage image;
    private final int[] pixels;
    private final Sampler[] samplers;
    private final static int MAX_DEPTH = 5;
    private final static int MAX_PREVIEW_DEPTH = 2;
    public final static int THREADS = Runtime.getRuntime().availableProcessors();

    public Raytracer(Window window, int antialiasing) {
        window.add(this);
        width = window.getWidth();
        height = window.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        samplers = new Sampler[THREADS];
        for (int i = 0; i < THREADS; i++) {
            samplers[i] = new Sampler(new Regular(), new SquareDistributor(), 1, antialiasing * antialiasing);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, width, height, null);
    }

    public void raytrace(World world, Camera camera) {
        prewiev(world, camera);
        System.out.println("Rendering...");
        DecimalFormat format = new DecimalFormat("0.00");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Color result = Color.BLACK;
                for (int k = 0; k < samplers[0].getCount(); k++) {
                    Vector2 sample = samplers[0].getSample();
                    double x = (((i + sample.x) / width)) * 2 - 1;
                    double y = (((j + sample.y) / height)) * 2 - 1;
                    for (int l = 0; l < camera.samples(); l++) {
                        Ray ray = camera.getRay(x, y, 0);
                        result = Color.add(result, Color.div(shade(world, ray, 0, 0), samplers[0].getCount() * camera.samples()));
                    }
                }
                pixels[j * width + i] = result.value();
            }
            repaint(i, 0, 1, height);
            System.out.println(format.format((double)(i+1) / width * 100) + "%");
        }
        System.out.println("Rendering done...");
    }

    public void raytrace(World world, Camera camera, int threads) {
        final int threads_num = Math.min(threads, THREADS);
        prewiev(world, camera);
        DecimalFormat format = new DecimalFormat("0.00");
        System.out.println("Rendering...");
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threads_num);
        final int[] count = {0};
        for (int i = 0; i < width; i++) {
            executor.submit(new Runner(i) {
                @Override
                public void run() {
                    for (int j = 0; j < height; j++) {
                        int thread = (int) (Thread.currentThread().getId() % threads_num);
                        Color result = Color.BLACK;
                        for (int k = 0; k < samplers[thread].getCount(); k++) {
                            Vector2 sample = samplers[thread].getSample();
                            double x = (((i + sample.x) / width)) * 2 - 1;
                            double y = (((j + sample.y) / height)) * 2 - 1;
                            for (int l = 0; l < camera.samples(); l++) {
                                Ray ray = camera.getRay(x, y, thread);
                                result = Color.add(result, Color.div(shade(world, ray, 0, thread), samplers[thread].getCount() * camera.samples()));
                            }
                        }
                        pixels[j * width + i] = result.value();
                    }
                    synchronized (count){
                        count[0]++;
                        System.out.println(format.format((double)(count[0]) / width * 100) + "%");
                    }
                    repaint(i, 0, 1, height);
                }
            });
        }
        try {
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static abstract class Runner implements Runnable {
        int i;
        Runner(int i) { this.i = i; }
        public abstract void run();
    }

    private void prewiev(World world, Camera camera){
        System.out.println("Rendering preview...");
        DecimalFormat format = new DecimalFormat("0.00");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Color result = Color.BLACK;
                double x = ((double)i  / width) * 2 - 1;
                double y = ((double)j / height) * 2 - 1;
                Ray ray = camera.getPreviewRay(x, y);
                result = Color.add(result, fastshade(world, ray, 0));
                pixels[j * width + i] = result.value();
            }
            repaint(i, 0, 1, height);
            System.out.println(format.format((double)(i+1) / width * 100) + "%");
        }
        System.out.println("Preview rendering done...");
    }

    public Color shade(World world, Ray ray, int depth, int thread){
        if(depth > MAX_DEPTH) return Color.BLACK;
        Hit hit = world.raytrace(ray);
        hit.depth = depth + 1;
        if(hit.object == null) return world.getColor();
        Color result = Color.mul(hit.object.material().ambient(hit), world.getAmbientLight().color());
        Material material = hit.object.material();
        return Color.add(result, material.shade(this, hit, thread));
    }

    public Color fastshade(World world, Ray ray, int depth){
        if(depth > MAX_PREVIEW_DEPTH) return Color.BLACK;
        Hit hit = world.raytrace(ray);
        hit.depth = depth + 1;
        if(hit.object == null) return world.getColor();
        Color result = Color.mul(hit.object.material().ambient(hit), world.getAmbientLight().color());
        Material material = hit.object.material();
        return Color.add(result, material.fastshade(this, hit));
    }
}
