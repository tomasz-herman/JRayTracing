package com.therman.raytracing.camera;

import com.therman.math.Matrix3;
import com.therman.math.Ray;
import com.therman.math.Vector3;

public class Perspective implements Camera {

    private Vector3 position;
    private Matrix3 view;
    private double scale;
    private double aspect;

    public Perspective(Vector3 position, Vector3 at, Vector3 up, double scale, double aspect) {
        this.position = position;
        this.view = new Matrix3().view(position, at, up);
        this.scale = 1 / scale;
        this.aspect = aspect;
    }

    @Override
    public Ray getRay(double x, double y) {
        return new Ray(position, view.transform(new Vector3(x * aspect * scale, -y * scale, -1)));
    }

    @Override
    public int samples() {
        return 1;
    }

    @Override
    public Ray getPreviewRay(double x, double y) {
        return getRay(x, y);
    }
}
