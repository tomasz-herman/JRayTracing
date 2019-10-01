package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

import java.util.Random;
import java.util.stream.IntStream;

public class Randomized implements SampleGenerator {

    public Randomized(){
        this.random = new Random();
    }

    public Randomized(int seed) {
        this.random = new Random(seed);
    }

    private Random random;

    @Override
    public Vector2[] generate(int count) {
        return IntStream.range(0, count).mapToObj(i -> new Vector2(random.nextDouble(), random.nextDouble())).toArray(Vector2[]::new);
    }
}
