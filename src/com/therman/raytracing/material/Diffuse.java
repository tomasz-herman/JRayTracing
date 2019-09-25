package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.light.Light;

public class Diffuse implements Material {

    private Color color;

    public Diffuse(Color color){
        this.color = color;
    }

    @Override
    public Color Radiance(Light light, Hit hit) {
        Vector3 direction = Vector3.sub(light.position(), hit.hit).normalized();
        double diffuse = Vector3.dot(direction, hit.normal);
        if(diffuse < 0) return Color.BLACK;
        return Color.mul(Color.mul(color, diffuse), light.color());
    }
}
