package com.therman.math;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 sub(Vector3 a, Vector3 b){
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 mul(Vector3 a, double v){
        return new Vector3(a.x * v, a.y * v, a.z * v);
    }

    public static Vector3 div(Vector3 a, double v){
        return new Vector3(a.x / v, a.y / v, a.z / v);
    }

    public static double dot(Vector3 a, Vector3 b){
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vector3 cross(Vector3 a, Vector3 b){
        return new Vector3(a.y * b.z - a.z * b.y,a.z * b.x - a.x * b.z,a.x * b.y - a.y * b.x);
    }

    public double lengthSquared(){
        return x * x + y * y + z * z;
    }

    public double length(){
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalized(){
        return div(this, length());
    }
}
