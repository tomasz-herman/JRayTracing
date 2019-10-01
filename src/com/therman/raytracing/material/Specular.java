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
    public Color shade(Raytracer raytracer, Hit hit, int thread) {
        Color result = material.shade(raytracer, hit, thread);
        for (Light light : hit.world.getLights()) {
            Color specularColor = Color.BLACK;
            for (int i = 0; i < light.getSamples(); i++) {
                Vector3 lightPos = light.sample(thread);
                Vector3 direction = Vector3.sub(lightPos, hit.point).normalized();
                double specular = specular(direction, hit.normal, Vector3.reverse(hit.ray.getDirection()));
                if (specular < 0 || hit.world.isObstacleBetween(hit.point, lightPos)) continue;
                specularColor = Color.add(specularColor, Color.mul(color, specular/light.getSamples()));
            }
            result = Color.add(result, specularColor);
        }
        return result;
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        Color result = material.fastshade(raytracer, hit);
        for (Light light : hit.world.getLights()) {
            Vector3 lightPos = light.position();
            Vector3 direction = Vector3.sub(light.position(), hit.point).normalized();
            double specular = specular(direction, hit.normal, Vector3.reverse(hit.ray.getDirection()));
            if (specular < 0 || hit.world.isObstacleBetween(hit.point, lightPos)) continue;
            result = Color.add(result, Color.mul(color, specular));
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
