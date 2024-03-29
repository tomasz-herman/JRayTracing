package com.therman.raytracing.light;

import com.therman.math.Color;
import com.therman.math.Vector2;
import com.therman.math.Vector3;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.sampling.Randomized;
import com.therman.raytracing.sampling.Sampler;
import com.therman.raytracing.sampling.SquareDistributor;

public class Light {

    private Vector3 position;
    private Color color;
    private Sampler[] samplers;
    private double radius;
    private int samples;

    public Light(Vector3 position, Color color){
        this(position, color, 0, 0);
    }

    public Light(Vector3 position, Color color, double radius, int samples){
        this.color = color;
        this.position = position;
        this.radius = radius;
        this.samplers = new Sampler[Raytracer.THREADS];
        for (int i = 0; i < samplers.length; i++) {
            samplers[i] = radius > 0 ? new Sampler(new Randomized(0), new SquareDistributor(), 1024, samples) : null;
        }
        this.samples = samples;
    }

    public Color color() {
        return color;
    }

    public Vector3 position(){
        return position;
    }

    public Vector3 sample(int thread) {
        return radius == 0 ? position : getSample(thread);
    }

    private Vector3 getSample(int thread){
        Vector2 sample = samplers[thread].getSample();
        double z = 2 * sample.x - 1;
        double t = 2 * sample.y * Math.PI;
        double r = Math.sqrt(1 - z * z);
        return Vector3.add(new Vector3(r * Math.cos(t) * radius, r * Math.sin(t) * radius, z * radius), position);
    }

    public int getSamples() {
        return samples;
    }
}
