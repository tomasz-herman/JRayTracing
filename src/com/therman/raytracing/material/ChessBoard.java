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
    public Color shade(Raytracer rt, Hit hit) {
        int x = (int)Math.round(hit.hit.X() * scale);
        int y = (int)Math.round(hit.hit.Z() * scale);
        if(((x ^ y) & 1) == 0){
            return a.shade(rt, hit);
        } else return b.shade(rt, hit);
    }
}