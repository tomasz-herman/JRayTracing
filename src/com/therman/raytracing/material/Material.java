package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public interface Material {

    Material COPPER = new Phong(new Color(1.0, 0.35, 0.05), 1.0, 0.8, 60, 0.1);
    Material STEEL = new Phong(Color.LIGHT_GRAY, 0.95, 1, 40, 0.15);
    Material GOLD = new Phong(new Color(1, 0.8, 0), 1, 1, 20, 0.15);
    Material SOLID_RED = new Diffuse(Color.RED, 0.2);
    Material SOLID_GREEN = new Diffuse(Color.GREEN, 0.2);
    Material SOLID_BLUE = new Diffuse(Color.BLUE, 0.2);
    Material SOLID_GRAY = new Diffuse(Color.GRAY, 0.2);

    Color shade(Raytracer rt, Hit hit);
    Color ambient();
}
