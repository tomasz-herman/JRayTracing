package com.therman.raytracing.objects;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;

public class Plane implements Geometric{

    private Vector3 point;
    private Vector3 normal;
    private Color color;

    public Plane(Vector3 point, Vector3 normal, Color color){
        this.point = point;
        this.normal = normal;
        this.color = color;
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public Hit test(Ray ray, Hit hit) {
        double t = Vector3.dot(Vector3.sub(point, ray.getOrigin()), normal) / Vector3.dot(ray.getDirection(), normal);
        if(t > 0.000001){
            if(hit.distance > t) {
                hit.distance = t;
                hit.color = color;
            }
        }
        return hit;
    }
}
