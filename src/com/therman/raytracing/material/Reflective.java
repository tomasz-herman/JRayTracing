package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public class Reflective extends MaterialDecorator {

    private Color color;

    public Reflective(Material material, Color color) {
        super(material);
        this.color = color;
    }

    @Override
    public Color shade(Raytracer raytracer, Hit hit, int thread) {
        Vector3 toCamera = Vector3.reverse(hit.ray.getDirection());
        Vector3 reflection = Vector3.reflect(toCamera, hit.normal);
        Ray reflected = new Ray(hit.point, reflection);
        Color result = material.shade(raytracer, hit, thread);
        result = Color.add(result, Color.mul(raytracer.shade(hit.world, reflected, hit.depth, thread), color));
        return result;
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        Vector3 toCamera = Vector3.reverse(hit.ray.getDirection());
        Vector3 reflection = Vector3.reflect(toCamera, hit.normal);
        Ray reflected = new Ray(hit.point, reflection);
        Color result = material.fastshade(raytracer, hit);
        result = Color.add(result, Color.mul(raytracer.fastshade(hit.world, reflected, hit.depth), color));
        return result;
    }
}
