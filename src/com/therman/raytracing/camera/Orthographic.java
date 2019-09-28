package com.therman.raytracing.camera;

import com.therman.math.Matrix3;
import com.therman.math.Ray;
import com.therman.math.Vector3;

public class Orthographic implements Camera {

    private Matrix3 view;
    private Vector3 position;
    private Vector3 direction;
    private double scale;
    private double aspect;

    public Orthographic(Vector3 position, Vector3 at, Vector3 up, double scale, double aspect) {
        this.position = position;
        this.view = new Matrix3().view(position, at, up);
        this.direction = Vector3.sub(at, position).normalized();
        this.scale = scale;
        this.aspect = aspect;
    }

    @Override
    public Ray getRay(double x, double y) {
        Vector3 origin = Vector3.add(position, view.transform(new Vector3(x * scale * aspect, - y * scale, 0)));
        return new Ray(origin, direction);
    }

    @Override
    public int samples() {
        return 1;
    }
}
