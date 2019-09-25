package com.therman.raytracing.objects;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.material.Material;

public class Sphere implements Geometric {

    private Material material;
    private Vector3 center;
    private double radius;

    public Sphere(Vector3 center, double radius, Material material){
        this.material = material;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public Hit test(Ray ray, Hit hit) {
        double t;
        Vector3 distance = Vector3.sub(ray.getOrigin(), center);
        double b = Vector3.dot(Vector3.mul(distance, 2), ray.getDirection());
        double c = distance.lengthSquared() - radius * radius;
        double delta = b * b - 4 * c;
        if (delta < 0) return hit;
        double deltaSq = Math.sqrt(delta);
        t = (-b - deltaSq) / 2;
        if (t < 0.00001) t = (-b + deltaSq) / 2;
        if (t >= 0.00001) {
            if(hit.distance > t) {
                hit.distance = t;
                hit.object = this;
                hit.hit = Vector3.add(Vector3.mul(ray.getDirection(), t), ray.getOrigin());
                hit.normal = Vector3.sub(hit.hit, center).normalized();
                hit.ray = ray;
            }
        }
        return hit;


    }
}
