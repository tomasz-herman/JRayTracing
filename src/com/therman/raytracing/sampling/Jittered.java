package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

import java.util.Random;

public class Jittered implements SampleGenerator {

    public Jittered(){
        this.random = new Random();
    }

    public Jittered(int seed) {
        this.random = new Random(seed);
    }

    private Random random;

    @Override
    public Vector2[] generate(int count) {
        int sqrt = (int)Math.sqrt(count);
        Vector2[] result = new Vector2[count];
        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                result[i * sqrt + j] = new Vector2((i + random.nextDouble()) / sqrt,(j + random.nextDouble()) / sqrt);
            }
        }
        return result;
    }
}
