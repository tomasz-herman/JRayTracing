package com.therman.raytracing;

import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.objects.Geometric;

public class Hit {
    public double distance = Double.MAX_VALUE;
    public Geometric object;
    public World world;
    public Vector3 normal;
    public Vector3 hit;
    public Ray ray;
}
