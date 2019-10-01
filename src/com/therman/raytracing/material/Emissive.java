package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public class Emissive extends MaterialDecorator {

    private Color color;

    public Emissive(Material material, Color color){
        super(material);
        this.color = color;
    }

    @Override
    public Color shade(Raytracer raytracer, Hit hit, int thread) {
        return Color.add(color, material.shade(raytracer, hit, thread));
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        return Color.add(color, material.fastshade(raytracer, hit));
    }
}
