package com.therman.raytracing.material;

import com.therman.math.Color;

public class Materials {

    private final static Color copper = new Color(1.0, 0.35, 0.05);
    public final static Material COPPER = new Specular(new Diffuse(new Ambient(Color.mul(copper, 0.15)), copper), copper, 60);
    private final static Color steel = Color.LIGHT_GRAY;
    public final static Material STEEL = new Specular(new Diffuse(new Ambient(Color.mul(steel, 0.25)), steel), steel, 40);
    private final static Color gold = new Color(1, 0.8, 0);
    public final static Material GOLD = new Specular(new Diffuse(new Ambient(Color.mul(gold, 0.25)), gold), gold, 20);

    public final static Material SOLID_RED = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.25)), Color.RED);
    public final static Material SOLID_GREEN = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.25)), Color.GREEN);
    public final static Material SOLID_BLUE = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.25)), Color.BLUE);
    public final static Material SOLID_GRAY = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.25)), Color.GRAY);

    public final static Material SOLID_DARK_RED = new Diffuse(new Ambient(Color.mul(Color.RED, 0.15)), Color.mul(Color.RED, 0.65));
    public final static Material SOLID_DARK_GREEN = new Diffuse(new Ambient(Color.mul(Color.GREEN, 0.15)), Color.mul(Color.GREEN, 0.65));
    public final static Material SOLID_DARK_BLUE = new Diffuse(new Ambient(Color.mul(Color.BLUE, 0.15)), Color.mul(Color.BLUE, 0.65));
    public final static Material SOLID_DARK_GRAY = new Diffuse(new Ambient(Color.mul(Color.GRAY, 0.15)), Color.mul(Color.GRAY, 0.65));
    public final static Material SOLID_BLACK = new Ambient(Color.BLACK);

}
