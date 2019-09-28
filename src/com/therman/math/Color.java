package com.therman.math;

public class Color {

    public static final Color WHITE = new Color(1, 1, 1);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color GRAY = new Color(0.5, 0.5, 0.5);
    public static final Color LIGHT_GRAY = new Color(0.75, 0.75, 0.75);
    public static final Color DARK_GRAY = new Color(0.25, 0.25, 0.25);
    public static final Color RED = new Color(0.75, 0, 0);
    public static final Color GREEN = new Color(0, 0.75, 0);
    public static final Color BLUE = new Color(0, 0, 0.75);
    public static final Color SKY = new Color(0.8, 0.9, 1);
    public final static Color COPPER = new Color(1.0, 0.25, 0.05);
    public final static Color STEEL = new Color(0.6, 0.65, 0.75);
    public final static Color GOLD = new Color(1, 0.75, 0.15);


    private Vector3 rgb;

    public Color(double red, double green, double blue){
        rgb = new Vector3(clamp(red), clamp(green), clamp(blue));
    }

    public Color(Vector3 rgb){
        this.rgb = new Vector3(clamp(rgb.x), clamp(rgb.y), clamp(rgb.z));
    }

    public static Color add(Color a, Color b){
        return new Color(Vector3.add(a.rgb, b.rgb));
    }

    public static Color sub(Color a, Color b){
        return new Color(Vector3.sub(a.rgb, b.rgb));
    }

    public static Color mul(Color a, Color b){
        return new Color(a.rgb.x * b.rgb.x, a.rgb.y * b.rgb.y, a.rgb.z * b.rgb.z);
    }

    public static Color mul(Color a, double v){
        return new Color(Vector3.mul(a.rgb, v));
    }

    public static Color div(Color a, double v){
        return new Color(Vector3.div(a.rgb, v));
    }

    public static Color mix(Color a, Color b, double v){
        return new Color(a.rgb.x * v + b.rgb.x * (1-v), a.rgb.y * v + b.rgb.y * (1-v), a.rgb.z * v + b.rgb.z * (1-v));
    }

    public int value(){
        return (int)(0xff * rgb.x + 0.5) << 16 | (int)(0xff * rgb.y + 0.5) << 8 | (int)(0xff * rgb.z + 0.5);
    }

    private double clamp(double val){
        return val > 1 ? 1 : val < 0 ? 0 : val;
    }
}
