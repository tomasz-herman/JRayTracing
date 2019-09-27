package com.therman.raytracing.light;

import com.therman.math.Color;
import com.therman.math.Vector2;
import com.therman.math.Vector3;
import com.therman.raytracing.sampling.Jittered;
import com.therman.raytracing.sampling.Randomized;
import com.therman.raytracing.sampling.Sampler;
import com.therman.raytracing.sampling.SquareDistributor;

public class Light {

    private Vector3 position;
    private Color color;
    private Sampler sampler;
    private double radius;
    private int samples;

    public Light(Vector3 position, Color color){
        this(position, color, 0, 0);
    }

    public Light(Vector3 position, Color color, double radius, int samples){
        this.color = color;
        this.position = position;
        this.radius = radius;
        sampler = radius > 0 ? new Sampler(new Randomized(), new SquareDistributor(), 1024, samples) : null;
        this.samples = samples;
    }

    public Color color() {
        return color;
    }

    public Vector3 position() {
        return radius == 0 ? position : sample();
    }

    private Vector3 sample(){
        Vector2 sample = sampler.getSample();
        double z = 2 * sample.x - 1;
        double t = 2 * sample.y * Math.PI;
        double r = Math.sqrt(1 - z * z);
        return Vector3.add(new Vector3(r * Math.cos(t) * radius, r * Math.sin(t) * radius, z * radius), position);
    }

    public int getSamples() {
        return samples;
    }
}
