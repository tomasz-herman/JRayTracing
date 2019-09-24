package com.therman;

import com.therman.raytracing.*;
import com.therman.math.Vector3;
import com.therman.raytracing.Window;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Raytracer raytracer = new Raytracer(new Window("Raytracer", 1280, 720));
        World world = new World();
        world.add(new Sphere(new Vector3(-2.5, 0, 0), 2, Color.RED));
        world.add(new Sphere(new Vector3(2.5, 0, 0), 2, Color.GREEN));
        world.add(new Sphere(new Vector3(0, 0, 2.5), 2, Color.BLUE));
        Camera camera = new Perspective(new Vector3(0, 0, -5), new Vector3(0, 0, 0), new Vector3(0, 1, 0), 1, 16.0 / 9.0);
        raytracer.raytrace(world, camera);
        raytracer.repaint();
    }
}
