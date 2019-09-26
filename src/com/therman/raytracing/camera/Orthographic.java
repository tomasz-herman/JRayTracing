package com.therman.raytracing.camera;

import com.therman.math.Ray;
import com.therman.math.Vector3;

public class Orthographic implements Camera {

    private Vector3 position;
    private Vector3 direction;
    private Vector3 right;
    private Vector3 up;
    private double scale;
    private double aspect;

    public Orthographic(Vector3 position, Vector3 at, Vector3 up, double scale, double aspect) {
        this.position = position;
        this.direction = Vector3.sub(at, position).normalized();
        this.up = up.normalized();
        this.right = Vector3.cross(up, direction);
        this.scale = scale;
        this.aspect = aspect;
    }

    @Override
    public Ray getRay(double x, double y) {
        Vector3 origin = Vector3.add(position, Vector3.add(Vector3.mul(right, x * scale * aspect), Vector3.mul(up, - y * scale)));
        return new Ray(origin, direction);
    }
}
