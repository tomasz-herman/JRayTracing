package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public class Reflective extends MaterialDecorator {

    private Color color;

    public Reflective(Material material, Color color) {
        super(material);
        this.color = color;
    }

    @Override
    public Color shade(Raytracer rt, Hit hit) {
        throw new UnsupportedOperationException();
    }
}
