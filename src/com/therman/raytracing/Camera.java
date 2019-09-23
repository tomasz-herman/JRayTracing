package com.therman.raytracing;

import com.therman.math.Ray;

public interface Camera {
    Ray getRay(double x, double y);
}
