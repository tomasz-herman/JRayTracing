package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.light.Light;

public class Ambient implements Material {

    private Color color;

    public Ambient(Color color){
        this.color = color;
    }

    @Override
    public Color Radiance(Light light, Hit hit) {
        return color;
    }
}
