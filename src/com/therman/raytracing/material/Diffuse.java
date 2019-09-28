package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;
import com.therman.raytracing.light.Light;

public class Diffuse extends MaterialDecorator {

    private Color color;

    public Diffuse(Material material, Color color){
        super(material);
        this.color = color;
    }

    @Override
    public Color shade(Raytracer rt, Hit hit) {
        Color result = material.shade(rt, hit);
        for (Light light : hit.world.getLights()) {
            Color diffuseColor = Color.BLACK;
            for (int i = 0; i < light.getSamples(); i++) {
                Vector3 lightPos = light.sample();
                Vector3 direction = Vector3.sub(lightPos, hit.point).normalized();
                double diffuse = Vector3.dot(direction, hit.normal);
                if(diffuse < 0 || hit.world.isObstacleBetween(hit.point, lightPos)) continue;
                diffuseColor = Color.add(diffuseColor, Color.mul(light.color(), Color.mul(color, diffuse/light.getSamples())));
            }
            result = Color.add(result, diffuseColor);
        }
        return result;
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        Color result = material.fastshade(raytracer, hit);
        for (Light light : hit.world.getLights()) {
            Vector3 lightPos = light.position();
            Vector3 direction = Vector3.sub(lightPos, hit.point).normalized();
            double diffuse = Vector3.dot(direction, hit.normal);
            if(diffuse < 0 || hit.world.isObstacleBetween(hit.point, lightPos)) continue;
            result = Color.add(result, Color.mul(light.color(), Color.mul(color, diffuse)));
        }
        return result;
    }
}
