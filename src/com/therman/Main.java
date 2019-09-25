package com.therman;

import com.therman.math.Color;
import com.therman.raytracing.*;
import com.therman.math.Vector3;
import com.therman.raytracing.Window;
import com.therman.raytracing.camera.Camera;
import com.therman.raytracing.camera.Perspective;
import com.therman.raytracing.light.Light;
import com.therman.raytracing.material.Ambient;
import com.therman.raytracing.material.Diffuse;
import com.therman.raytracing.objects.Plane;
import com.therman.raytracing.objects.Sphere;

public class Main {

    public static void main(String[] args) {
        Raytracer raytracer = new Raytracer(new Window("Raytracer", 1280, 720));
        World world = new World();
        world.add(new Sphere(new Vector3(-2.5, 0, 0), 2, new Diffuse(Color.RED, 0.2)));
        world.add(new Sphere(new Vector3(2.5, 0, 0), 2, new Diffuse(Color.GREEN, 0.2)));
        world.add(new Sphere(new Vector3(0, 0, 2.5), 2, new Diffuse(Color.BLUE, 0.2)));
        world.add(new Plane(new Vector3(0, -2, 0), new Vector3(0,1,0), new Diffuse(Color.GRAY, 0.2)));
        world.add(new Light(new Vector3(0, 10, 0), Color.LIGHT_GRAY));
        Camera camera = new Perspective(new Vector3(0, 2, -4), new Vector3(0, 0, 0), new Vector3(0, 1, 0), 1, 16.0 / 9.0);
        raytracer.raytrace(world, camera);
        raytracer.repaint();
    }
}
