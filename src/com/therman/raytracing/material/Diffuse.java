package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.light.Light;

public class Diffuse implements Material {

    private Color color;
    private double ambient;

    public Diffuse(Color color, double ambient){
        this.color = color;
        this.ambient = ambient;
    }

    @Override
    public Color shade(Raytracer rt, Hit hit) {
        Color result = ambient();
        for (Light light : hit.world.getLights()) {
            Vector3 direction = Vector3.sub(light.position(), hit.hit).normalized();
            double diffuse = Vector3.dot(direction, hit.normal);
            if(diffuse < 0 || hit.world.isObstacleBetween(hit.hit, light.position())) continue;
            result = Color.add(result, Color.mul(light.color(), Color.mul(color, diffuse)));
        }
        return result;
    }

    @Override
    public Color ambient() {
        return Color.mul(color, ambient);
    }
}
