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

public class Raytracer extends JComponent {

    private final int width;
    private final int height;
    private final BufferedImage image;
    private final int[] pixels;
    private final Sampler sampler;
    private final static int MAX_DEPTH = 5;

    public Raytracer(Window window, int antialiasing) {
        window.add(this);
        width = window.getWidth();
        height = window.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        sampler = new Sampler(new Regular(), new SquareDistributor(), 1, antialiasing * antialiasing);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, width, height, null);
    }

    public void raytrace(World world, Camera camera) {
        DecimalFormat format = new DecimalFormat("0.00");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                Color result = Color.BLACK;
                for (int k = 0; k < sampler.getCount(); k++) {
                    Vector2 sample = sampler.getSample();
                    double x = (((i + sample.x) / width)) * 2 - 1;
                    double y = (((j + sample.y) / height)) * 2 - 1;
                    Ray ray = camera.getRay(x, y);
                    result = Color.add(result, Color.div(shade(world, ray, 0), sampler.getCount()));
                }
                pixels[j * width + i] = result.value();
            }
            repaint(i, 0, 1, height);
            System.out.println(format.format((double)(i+1) / width * 100) + "%");
        }
    }

    public Color shade(World world, Ray ray, int depth){
        if(depth > MAX_DEPTH) return Color.BLACK;
        Hit hit = world.raytrace(ray);
        hit.depth = depth + 1;
        if(hit.object == null) return world.getColor();
        Material material = hit.object.material();
        return material.shade(this, hit);
    }
}
