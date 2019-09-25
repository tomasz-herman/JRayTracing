package com.therman.raytracing;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.light.Light;
import com.therman.raytracing.objects.Geometric;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Geometric> objects = new ArrayList<>();
    private List<Light> lights = new ArrayList<>();
    private Color color = Color.SKY;

    public void add(Geometric object){
        objects.add(object);
    }
    public void add(Light light){
        lights.add(light);
    }

    Hit raytrace(Ray ray){
        Hit hit = new Hit();
        for (Geometric obj : objects) {
            obj.test(ray, hit);
        }
        return hit;
    }

    boolean isObstacleBetween(Vector3 a, Vector3 b){
        Vector3 ab = Vector3.sub(b, a);
        double length = ab.length();
        Hit hit = new Hit();
        Ray ray = new Ray(a, ab);
        for (Geometric object : objects) {
            object.test(ray, hit);
            if(hit.distance < length) return true;
        }
        return false;
    }

    public Color getColor() {
        return color;
    }

    public List<Light> getLights() {
        return lights;
    }
}
