package com.therman.raytracing;

import com.therman.math.Ray;
import com.therman.math.Vector3;

import java.awt.*;

public class Sphere implements Geometric {

    private Color color;
    private Vector3 center;
    private double radius;

    public Sphere(Vector3 center, double radius, Color color){
        this.color = color;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Color color() {
        return color;
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
                hit.color = color;
            }
        }
        return hit;


    }
}
