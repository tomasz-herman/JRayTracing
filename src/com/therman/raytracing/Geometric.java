package com.therman.raytracing;

import com.therman.math.Ray;

import java.awt.*;

public interface Geometric {
    Color color();
    Hit test(Ray ray, Hit hit);
}
