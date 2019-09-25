package com.therman.raytracing.objects;

import com.therman.math.Ray;
import com.therman.raytracing.Hit;
import com.therman.raytracing.material.Material;

public interface Geometric {
    Material material();
    Hit test(Ray ray, Hit hit);
}
