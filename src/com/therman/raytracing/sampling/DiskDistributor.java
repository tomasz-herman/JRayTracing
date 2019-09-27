package com.therman.raytracing.sampling;

import com.therman.math.Vector2;

public class DiskDistributor implements SampleDistributor {
    @Override
    public Vector2 map(Vector2 sample) {
        sample.x = sample.x * 2 - 1;
        sample.y = sample.y * 2 - 1;
        double r, phi;
        if(sample.x > -sample.y)
            if(sample.x > sample.y){
                r = sample.x;
                phi = sample.y/sample.x;
            }else {
                r = sample.y;
                phi = 2 - sample.x/sample.y;
            }
        else {
            if(sample.x < sample.y){
                r = -sample.x;
                phi = 4 + sample.y/sample.x;
            }else {
                r = -sample.y;
                phi = 6 + sample.x/sample.y;
            }
        }
        phi *= Math.PI / 4;
        return new Vector2(r * Math.cos(phi), r * Math.sin(phi));
    }
}
