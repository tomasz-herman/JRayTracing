package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

import java.util.Random;
import java.util.stream.IntStream;

public class Randomized implements SampleGenerator {

    private Random random = new Random();

    @Override
    public Vector2[] generate(int count) {
        return IntStream.range(0, count).mapToObj(i -> new Vector2(random.nextDouble(), random.nextDouble())).toArray(Vector2[]::new);
    }
}
