package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.light.Light;

public class Specular extends MaterialDecorator {

    private Color color;
    private double exp;

    public Specular(Material material, Color color, double exp) {
        super(material);
        this.color = color;
        this.exp = exp;
    }

    @Override
    public Color shade(Raytracer rt, Hit hit) {
        Color result = material.shade(rt, hit);
        for (Light light : hit.world.getLights()) {
            Vector3 direction = Vector3.sub(light.position(), hit.hit).normalized();
            double specular = specular(direction, hit.normal, Vector3.reverse(hit.ray.getDirection()));
            if(specular > 0) result = Color.add(result, Color.mul(color, specular));
        }
        return result;
    }

    private double specular(Vector3 direction, Vector3 normal, Vector3 toCamera){
        Vector3 reflected = Vector3.reflect(direction, normal);
        double cos = Vector3.dot(reflected, toCamera);
        if(cos < 0) return 0;
        return Math.pow(cos, exp);
    }
}
