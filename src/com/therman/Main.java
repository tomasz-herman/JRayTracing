package com.therman;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.Window;
import com.therman.raytracing.World;
import com.therman.raytracing.camera.Camera;
import com.therman.raytracing.camera.Perspective;
import com.therman.raytracing.light.Light;
import com.therman.raytracing.material.Diffuse;
import com.therman.raytracing.material.Material;
import com.therman.raytracing.material.Phong;
import com.therman.raytracing.objects.Plane;
import com.therman.raytracing.objects.Sphere;

public class Main {

    public static void main(String[] args) {
        Raytracer raytracer = new Raytracer(new Window("Raytracer", 1280, 720));
        World world = new World();
        world.add(new Sphere(new Vector3(-2.5, 0, 0), 2, Material.GOLD));
        world.add(new Sphere(new Vector3(2.5, 0, 0), 2, Material.COPPER));
        world.add(new Sphere(new Vector3(0, 0, 2.5), 2, Material.STEEL));
        world.add(new Plane(new Vector3(0, -2, 0), new Vector3(0,1,0), Material.SOLID_GREEN));
        world.add(new Light(new Vector3(0, 10, 0), Color.WHITE));
        Camera camera = new Perspective(new Vector3(0, 2, -4), new Vector3(0, 0, 0), new Vector3(0, 1, 0), 1, 16.0 / 9.0);
        raytracer.raytrace(world, camera);
        raytracer.repaint();
    }
}
