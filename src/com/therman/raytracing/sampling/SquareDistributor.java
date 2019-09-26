package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

public class SquareDistributor implements SampleDistributor {
    @Override
    public Vector2 map(Vector2 sample) {
        return sample;
    }
}
