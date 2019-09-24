package com.therman.raytracing.objects;

import com.therman.math.Ray;
import com.therman.raytracing.Hit;

import java.awt.*;

public interface Geometric {
    Color color();
    Hit test(Ray ray, Hit hit);
}
