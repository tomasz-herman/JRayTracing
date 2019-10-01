package com.therman;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.Window;
import com.therman.raytracing.World;
import com.therman.raytracing.camera.Camera;
import com.therman.raytracing.camera.Realistic;
import com.therman.raytracing.light.AmbientLight;
import com.therman.raytracing.light.Light;
import com.therman.raytracing.material.ChessBoard;
import com.therman.raytracing.material.Materials;
import com.therman.raytracing.objects.Plane;
import com.therman.raytracing.objects.Sphere;

public class Main {

    public static void main(String[] args) {
        Raytracer raytracer = new Raytracer(new Window("Raytracer", 1280, 720), 4);
        World world = new World();
        world.add(new Sphere(new Vector3(-4, 0, 0), 2, Materials.GOLD));
        world.add(new Sphere(new Vector3(4, 0, 0), 2, Materials.GLASS));
        world.add(new Sphere(new Vector3(0, 0, 2.5), 2, Materials.COPPER));
        world.add(new Plane(new Vector3(0, -2, 0), new Vector3(0,1,0), new ChessBoard(Materials.WHITE_CERAMIC, Materials.BLACK_CERAMIC, 4)));
        world.add(new Light(new Vector3(0, 4000, 0), Color.WHITE, 500, 16));
        world.add(new Sphere(new Vector3(0, 4000, 0), 500, Materials.LIGHT_BULB));
        world.add(new AmbientLight(Color.SKY));
        Camera camera = new Realistic(new Vector3(0, 2, -6), new Vector3(0, 0, 0), new Vector3(0, 1, 0), 1, 16.0 / 9.0, 16, 0.1, 8);
        raytracer.raytrace(world, camera, 8);
        raytracer.repaint();
        System.out.println("done");
    }
}
