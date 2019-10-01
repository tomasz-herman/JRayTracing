package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public abstract class MaterialDecorator extends Material {

    protected Material material;

    public MaterialDecorator(Material material){
        this.material = material;
    }

    @Override
    public abstract Color shade(Raytracer raytracer, Hit hit, int thread);

    public Color ambient(Hit hit){
        return material.ambient(hit);
    }
}
