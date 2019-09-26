package com.therman.raytracing.objects;

import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.material.Material;

public class Plane implements Geometric{

    private Vector3 point;
    private Vector3 normal;
    private Material material;

    public Plane(Vector3 point, Vector3 normal, Material material){
        this.point = point;
        this.normal = normal;
        this.material = material;
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public Hit test(Ray ray, Hit hit) {
        double t = Vector3.dot(Vector3.sub(point, ray.getOrigin()), normal) / Vector3.dot(ray.getDirection(), normal);
        if(t > 0.000001){
            if(hit.distance > t) {
                hit.distance = t;
                hit.object = this;
                hit.normal = normal;
                hit.point = Vector3.add(Vector3.mul(ray.getDirection(), t), ray.getOrigin());
                hit.ray = ray;
            }
        }
        return hit;
    }
}
