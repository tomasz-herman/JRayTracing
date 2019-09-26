package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

import java.util.Random;

public class Sampler {
    private Random random = new Random();
    private Vector2[][] samples;
    private int count;
    private int i;
    private int j;

    public Sampler(SampleGenerator generator, SampleDistributor distributor, int sets, int count){
        this.samples = new Vector2[sets][];
        this.count = count;
        for (int i = 0; i < sets; i++) {
            samples[i] = generator.generate(count);
            for (int j = 0; j < samples[i].length; j++) {
                samples[i][j] = distributor.map(samples[i][j]);
            }
        }
    }

    public Vector2 getSample(){
        Vector2 result = samples[i][j++];
        if(j > count - 1) {
            j = 0;
            i = random.nextInt(samples.length);
        }
        return result;
    }

    public int getCount() {
        return count;
    }
}
