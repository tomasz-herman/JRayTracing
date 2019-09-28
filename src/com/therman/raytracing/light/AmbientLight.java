package com.therman.raytracing.light;

import com.therman.math.Color;

public class AmbientLight {

    private Color color;

    public AmbientLight(Color color){
        this.color = color;
    }

    public Color color() {
        return color;
    }
}
