package com.therman.raytracing;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.raytracing.camera.Camera;
import com.therman.raytracing.material.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Raytracer extends JComponent {

    private final int width;
    private final int height;
    private final BufferedImage image;
    private final int[] pixels;
    private final static int MAX_DEPTH = 5;

    public Raytracer(Window window) {
        window.add(this);
        width = window.getWidth();
        height = window.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, width, height, null);
    }

    public void raytrace(World world, Camera camera) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                double x = (((double)i / width)) * 2 - 1;
                double y = (((double)j / height)) * 2 - 1;
                Ray ray = camera.getRay(x, y);
                pixels[j * width + i] = shade(world, ray, 0).value();
            }
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
