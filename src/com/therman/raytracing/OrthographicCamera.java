package com.therman.raytracing;

import com.therman.math.Ray;
import com.therman.math.Vector3;

public class OrthographicCamera implements Camera {

    private Vector3 position;
    private Vector3 direction;
    private double width;
    private double height;

    public OrthographicCamera(Vector3 position, double yaw, double width, double height) {
        this.position = position;
        this.direction = new Vector3(Math.sin(yaw), 0, Math.cos(yaw)).normalized();
        this.width = width;
        this.height = height;
    }

    @Override
    public Ray getRay(double x, double y) {
        x*=width;
        y*=height;
        Vector3 origin = new Vector3(position.x + x * direction.z, position.y + y, position.z + x * direction.x);
        return new Ray(origin, direction);
    }
}
