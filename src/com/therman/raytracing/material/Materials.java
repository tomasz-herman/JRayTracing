package com.therman.raytracing.material;

import com.therman.math.Color;

public class Materials {

    private final static Color copper = new Color(1.0, 0.25, 0.05);
    public final static Material COPPER = new Reflective(new Specular(new Diffuse(new Ambient(Color.mul(copper, 0.15)), copper), copper, 60), Color.mul(copper, 0.02));
    private final static Color steel = new Color(0.6, 0.65, 0.75);
    public final static Material STEEL = new Reflective(new Specular(new Diffuse(new Ambient(Color.mul(steel, 0.25)), steel), steel, 30), Color.mul(steel, 0.18));
    private final static Color gold = new Color(1, 0.75, 0.15);
    public final static Material GOLD = new Reflective(new Specular(new Diffuse(new Ambient(Color.mul(gold, 0.25)), gold), gold, 20), Color.mul(gold, 0.15));

    public final static Material SOLID_RED = new Diffuse(new Ambient(Color.mul(Color.RED, 0.25)), Color.RED);
    public final static Material SOLID_GREEN = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.25)), Color.GREEN);
    public final static Material SOLID_BLUE = new Diffuse(new Ambient(Color.mul(Color.BLUE, 0.25)), Color.BLUE);
    public final static Material SOLID_GRAY = new Diffuse(new Ambient(Color.mul(Color.GRAY, 0.25)), Color.GRAY);

    public final static Material SOLID_DARK_RED = new Diffuse(new Ambient(Color.mul(Color.RED, 0.15)), Color.mul(Color.RED, 0.65));
    public final static Material SOLID_DARK_GREEN = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.15)), Color.mul(Color.GREEN, 0.65));
    public final static Material SOLID_DARK_BLUE = new Diffuse(new Ambient(Color.mul(Color.BLUE, 0.15)), Color.mul(Color.BLUE, 0.65));
    public final static Material SOLID_DARK_GRAY = new Diffuse(new Ambient(Color.mul(Color.GRAY, 0.15)), Color.mul(Color.GRAY, 0.65));
    public final static Material SOLID_BLACK = new Ambient(Color.BLACK);
    public final static Material MIRROR = new Specular(new Reflective(new Ambient(Color.BLACK), Color.WHITE), Color.WHITE, 10);
    public final static Material BLACK_MIRROR = new Specular(new Reflective(new Ambient(Color.BLACK), Color.mul(Color.WHITE, 0.15)), Color.WHITE, 100);
}
