package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

import java.util.Random;

public class NRooks implements SampleGenerator {

    public NRooks(){
        this.random = new Random();
    }

    public NRooks(int seed) {
        this.random = new Random(seed);
    }

    private Random random;

    @Override
    public Vector2[] generate(int count) {
        Vector2[] samples = new Vector2[count];
        for (int i = 0; i < count; i++) {
            samples[i] = new Vector2((i + random.nextDouble())/ count, (i + random.nextDouble())/ count);
        }
        shuffle(samples);
        return samples;
    }

    private void shuffle(Vector2[] samples){
        for (int i = 1; i < samples.length; i++) {
            int to = random.nextInt() % i;
            double temp = samples[i].x;
            samples[i].x = samples[to].x;
            samples[to].x = temp;
        }
    }
}
