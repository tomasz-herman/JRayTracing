package com.therman.raytracing.camera;

import com.therman.math.Ray;

public interface Camera {
    Ray getRay(double x, double y, int thread);
    int samples();
    Ray getPreviewRay(double x, double y);
}
