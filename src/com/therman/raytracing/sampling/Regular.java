package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

public class Regular implements SampleGenerator {
    @Override
    public Vector2[] generate(int count) {
        int sqrt = (int)Math.sqrt(count);
        Vector2[] result = new Vector2[count];
        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                result[i * sqrt + j] = new Vector2((i + 0.5) / sqrt,(j + 0.5) / sqrt);
            }
        }
        return result;
    }
}
