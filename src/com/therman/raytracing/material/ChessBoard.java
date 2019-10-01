package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public class ChessBoard extends Material {

    private Material a;
    private Material b;
    private double scale;

    public ChessBoard(Material a, Material b, double scale){
        this.a = a;
        this.b = b;
        this.scale = 1 / scale;
    }

    @Override
    public Color shade(Raytracer raytracer, Hit hit, int thread) {
        int x = (int)Math.round(hit.point.x * scale);
        int y = (int)Math.round(hit.point.z * scale);
        if(((x ^ y) & 1) == 0){
            return a.shade(raytracer, hit, thread);
        } else return b.shade(raytracer, hit, thread);
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        int x = (int)Math.round(hit.point.x * scale);
        int y = (int)Math.round(hit.point.z * scale);
        if(((x ^ y) & 1) == 0){
            return a.fastshade(raytracer, hit);
        } else return b.fastshade(raytracer, hit);
    }

    @Override
    public Color ambient(Hit hit) {
        int x = (int)Math.round(hit.point.x * scale);
        int y = (int)Math.round(hit.point.z * scale);
        if(((x ^ y) & 1) == 0){
            return a.ambient(hit);
        } else return b.ambient(hit);
    }
}
