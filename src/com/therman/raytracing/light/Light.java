package com.therman.raytracing.light;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;

public class Light {

    private Vector3 position;
    private Color color;

    public Light(Vector3 position, Color color){
        this.color = color;
        this.position = position;
    }

    public Color color() {
        return color;
    }

    public Vector3 position() {
        return position;
    }
}
