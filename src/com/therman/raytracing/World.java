package com.therman.raytracing;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.raytracing.objects.Geometric;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Geometric> objects = new ArrayList<>();
    private Color color = Color.SKY;

    public void add(Geometric obj){
        objects.add(obj);
    }

    Hit raytrace(Ray ray){
        Hit hit = new Hit();
        hit.color = color;
        for (Geometric obj : objects) {
            obj.test(ray, hit);
        }
        return hit;
    }
}
