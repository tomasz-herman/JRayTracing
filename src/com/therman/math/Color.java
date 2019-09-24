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
    public static final Color SKY = new Color(0.5, 0.8, 0.9);

    private Vector3 rgb;

    public Color(double red, double green, double blue){
        rgb = new Vector3(clamp(red), clamp(green), clamp(blue));
    }

    public int value(){
        return (int)(0xff * rgb.x) << 16 | (int)(0xff * rgb.y) << 8 | (int)(0xff * rgb.z);
    }

    private double clamp(double val){
        return val > 1 ? 1 : val < 0 ? 0 : val;
    }
}
