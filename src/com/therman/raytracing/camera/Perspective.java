package com.therman.raytracing.camera;

import com.therman.math.Matrix3;
import com.therman.math.Ray;
import com.therman.math.Vector3;

public class Perspective implements Camera {

    private Vector3 position;
    private Matrix3 view;
    private double near;
    private double aspect;

    public Perspective(Vector3 position, Vector3 at, Vector3 up, double near, double aspect) {
        this.position = position;
        this.view = new Matrix3().view(position, at, up);
        this.near = near;
        this.aspect = aspect;
    }

    @Override
    public Ray getRay(double x, double y) {
        return new Ray(position, view.transform(new Vector3(x * aspect, -y, -near)));
    }
}
