package com.therman.raytracing.material;

import com.therman.math.Color;

public class Materials {

    public final static Material COPPER;
    public final static Material STEEL;
    public final static Material GOLD;

    public final static Material SOLID_RED;
    public final static Material SOLID_GREEN;
    public final static Material SOLID_BLUE;
    public final static Material SOLID_GRAY;
    public final static Material SOLID_WHITE;

    public final static Material SOLID_DARK_RED;
    public final static Material SOLID_DARK_GREEN;
    public final static Material SOLID_DARK_BLUE;
    public final static Material SOLID_DARK_GRAY;
    public final static Material SOLID_BLACK;

    public final static Material MIRROR;
    public final static Material BLACK_MIRROR;
    public final static Material BLACK_CERAMIC;
    public final static Material WHITE_CERAMIC;

    static {
        Material mirror = new Ambient(Color.BLACK);
        mirror = new Reflective(mirror, Color.WHITE);
        mirror = new Specular(mirror, Color.WHITE, 10);
        MIRROR = mirror;

        Material black_mirror = new Ambient(Color.BLACK);
        black_mirror = new Reflective(black_mirror, Color.mul(Color.WHITE, 0.15));
        black_mirror = new Specular(black_mirror, Color.WHITE, 10);
        BLACK_MIRROR = black_mirror;

        Material black_ceramic = new Ambient(Color.BLACK);
        black_ceramic = new Diffuse(black_ceramic, Color.mul(Color.WHITE, 0.1));
        black_ceramic = new Specular(black_ceramic, Color.WHITE, 100);
        black_ceramic = new Reflective(black_ceramic, Color.mul(Color.WHITE, 0.1));
        BLACK_CERAMIC = black_ceramic;

        Material white_ceramic = new Ambient(Color.DARK_GRAY);
        white_ceramic = new Diffuse(white_ceramic, Color.LIGHT_GRAY);
        white_ceramic = new Specular(white_ceramic, Color.WHITE, 100);
        white_ceramic = new Reflective(white_ceramic, Color.mul(Color.WHITE, 0.1));
        WHITE_CERAMIC = white_ceramic;

        Material copper = new Ambient(Color.mul(Color.COPPER, 0.15));
        copper = new Diffuse(copper, Color.COPPER);
        copper = new Specular(copper, Color.COPPER, 60);
        copper = new Reflective(copper, Color.mul(Color.COPPER, 0.02));
        COPPER = copper;

        Material steel = new Ambient(Color.mul(Color.STEEL, 0.15));
        steel = new Diffuse(steel, Color.STEEL);
        steel = new Specular(steel, Color.STEEL, 60);
        steel = new Reflective(steel, Color.mul(Color.STEEL, 0.02));
        STEEL = steel;

        Material gold = new Ambient(Color.mul(Color.GOLD, 0.15));
        gold = new Diffuse(gold, Color.GOLD);
        gold = new Specular(gold, Color.GOLD, 60);
        gold = new Reflective(gold, Color.mul(Color.GOLD, 0.02));
        GOLD = gold;

        Material solid_red = new Ambient(Color.mul(Color.RED, 0.1));
        solid_red = new Diffuse(solid_red, Color.RED);
        SOLID_RED = solid_red;

        Material solid_green = new Ambient(Color.mul(Color.GREEN, 0.1));
        solid_green = new Diffuse(solid_green, Color.GREEN);
        SOLID_GREEN = solid_green;

        Material solid_blue = new Ambient(Color.mul(Color.BLUE, 0.1));
        solid_blue = new Diffuse(solid_blue, Color.BLUE);
        SOLID_BLUE = solid_blue;

        Material solid_gray = new Ambient(Color.mul(Color.GRAY, 0.1));
        solid_gray = new Diffuse(solid_gray, Color.GRAY);
        SOLID_GRAY = solid_gray;

        Material solid_white = new Ambient(Color.mul(Color.WHITE, 0.1));
        solid_white = new Diffuse(solid_white, Color.WHITE);
        SOLID_WHITE = solid_white;

        SOLID_BLACK = new Ambient(Color.BLACK);

        Material solid_dark_red = new Ambient(Color.mul(Color.RED, 0.05));
        solid_dark_red = new Diffuse(solid_dark_red, Color.mul(Color.RED, 0.65));
        SOLID_DARK_RED = solid_dark_red;

        Material solid_dark_green = new Ambient(Color.mul(Color.GREEN, 0.05));
        solid_dark_green = new Diffuse(solid_dark_green, Color.mul(Color.GREEN, 0.65));
        SOLID_DARK_GREEN = solid_dark_green;

        Material solid_dark_blue = new Ambient(Color.mul(Color.BLUE, 0.05));
        solid_dark_blue = new Diffuse(solid_dark_blue, Color.mul(Color.BLUE, 0.65));
        SOLID_DARK_BLUE = solid_dark_blue;

        Material solid_dark_gray = new Ambient(Color.mul(Color.GRAY, 0.05));
        solid_dark_gray = new Diffuse(solid_dark_gray, Color.mul(Color.GRAY, 0.65));
        SOLID_DARK_GRAY = solid_dark_gray;

    }

}
