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
    public Color radiance(Light light, Hit hit) {
        return Color.BLACK;
    }

    @Override
    public Color ambient() {
        return color;
    }
}
