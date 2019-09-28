package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public class Ambient extends Material {

    private Color color;

    public Ambient(Color color){
        this.color = color;
    }

    @Override
    public Color shade(Raytracer rt, Hit hit) {
        return color;
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        return color;
    }
}
