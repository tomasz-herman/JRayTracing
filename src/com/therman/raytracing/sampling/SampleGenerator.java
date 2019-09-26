package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

public interface SampleGenerator {
    Vector2[] generate(int count);
}
