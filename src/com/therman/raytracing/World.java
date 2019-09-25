package com.therman.raytracing;

import com.therman.math.Color;
import com.therman.math.Ray;
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

    public Color getColor() {
        return color;
    }

    public List<Light> getLights() {
        return lights;
    }
}
