package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public abstract class Material {
    public abstract Color shade(Raytracer rt, Hit hit);
    public abstract Color fastshade(Raytracer raytracer, Hit hit);
    public abstract Color ambient(Hit hit);
}
