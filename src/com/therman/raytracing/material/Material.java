package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.raytracing.Hit;
import com.therman.raytracing.light.Light;

public interface Material {
    Color Radiance(Light light, Hit hit);
}
