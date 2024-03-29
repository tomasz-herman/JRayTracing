package com.therman.raytracing;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.light.AmbientLight;
import com.therman.raytracing.light.Light;
import com.therman.raytracing.material.Ambient;
import com.therman.raytracing.material.Emissive;
import com.therman.raytracing.objects.Geometric;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Geometric> objects = new ArrayList<>();
    private List<Light> lights = new ArrayList<>();
    private AmbientLight ambientLight = new AmbientLight(Color.WHITE);

    public void add(Geometric object){
        objects.add(object);
    }
    public void add(Light light){
        lights.add(light);
    }
    public void add(AmbientLight light){
        ambientLight = light;
    }

    Hit raytrace(Ray ray){
        Hit hit = new Hit();
        hit.world = this;
        for (Geometric obj : objects) {
            obj.test(ray, hit);
        }
        return hit;
    }

    public boolean isObstacleBetween(Vector3 a, Vector3 b){
        Vector3 ab = Vector3.sub(b, a);
        double length = ab.length();
        Hit hit = new Hit();
        Ray ray = new Ray(a, ab);
        for (Geometric object : objects) {
            if(object.material() instanceof Emissive) continue;
            object.test(ray, hit);
            if(hit.distance < length) return true;
        }
        return false;
    }

    public Color getColor() {
        return ambientLight.color();
    }

    public List<Light> getLights() {
        return lights;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }
}
