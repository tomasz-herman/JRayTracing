package com.therman.raytracing.camera;

import com.therman.math.Matrix3;
import com.therman.math.Ray;
import com.therman.math.Vector2;
import com.therman.math.Vector3;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.sampling.*;

public class Realistic implements Camera {

    private Vector3 position;
    private Matrix3 view;
    private double scale;
    private double aspect;
    private Sampler[] samplers;
    private double lens;
    private double focal;
    private int samples;

    public Realistic(Vector3 position, Vector3 at, Vector3 up, double scale, double aspect, int samples, double lens, double focal) {
        this.position = position;
        this.view = new Matrix3().view(position, at, up);
        this.scale = scale;
        this.aspect = aspect;
        this.lens = lens;
        this.focal = focal;
        this.samplers = new Sampler[Raytracer.THREADS];
        for (int i = 0; i < samplers.length; i++) {
            this.samplers[i] = new Sampler(new Jittered(), new DiskDistributor(), 8, samples);
        }
        this.samples = samples;
    }


    @Override
    public Ray getRay(double x, double y, int thread) {
        Ray original = getPreviewRay(x, y);
        Vector3 aimed = Vector3.add(position, Vector3.mul(original.getDirection(), focal));
        Vector2 sample = samplers[thread].getSample();
        double lensX = sample.x * lens;
        double lensY = sample.y * lens;
        Vector3 origin = Vector3.add(position, view.transform(new Vector3(lensX, lensY, 0)));
        Vector3 direction = Vector3.sub(aimed, origin).normalized();
        return new Ray(origin, direction);
    }

    @Override
    public int samples() {
        return samples;
    }

    @Override
    public Ray getPreviewRay(double x, double y) {
        return new Ray(position, view.transform(new Vector3(x * aspect * scale, -y * scale, -1)));
    }
}
