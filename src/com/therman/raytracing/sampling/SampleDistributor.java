package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

public interface SampleDistributor {
    Vector2 map(Vector2 sample);
}
