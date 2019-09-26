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
    public Color shade(Raytracer rt, Hit hit) {
        Vector3 toCamera = Vector3.reverse(hit.ray.getDirection());
        Vector3 reflection = Vector3.reflect(toCamera, hit.normal);
        Ray reflected = new Ray(hit.point, reflection);
        Color result = material.shade(rt, hit);
        result = Color.add(result, Color.mul(rt.shade(hit.world, reflected, hit.depth), color));
        return result;
    }
}
