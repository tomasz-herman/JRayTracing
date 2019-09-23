package com.therman;

import com.therman.raytracing.Raytracer;
import com.therman.raytracing.Window;
import com.therman.math.Vector3;
import com.therman.raytracing.Camera;
import com.therman.raytracing.OrthographicCamera;
import com.therman.raytracing.Sphere;
import com.therman.raytracing.World;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Raytracer raytracer = new Raytracer(new Window("Raytracer", 1280, 720));
        World world = new World();
        world.add(new Sphere(new Vector3(-2.5, 0, 0), 2, Color.RED));
        world.add(new Sphere(new Vector3(2.5, 0, 0), 2, Color.GREEN));
        world.add(new Sphere(new Vector3(0, 0, 2.5), 2, Color.BLUE));
        Camera camera = new OrthographicCamera(new Vector3(0, 0, -5), 0, 16, 9);
        raytracer.raytrace(world, camera);
        raytracer.repaint();
    }
}
