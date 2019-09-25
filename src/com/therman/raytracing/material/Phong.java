package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.light.Light;

public class Phong implements Material {

    private Color color;
    private double diffuse;
    private double specular;
    private double exp;
    private double ambient;

    public Phong(Color color, double diffuse, double specular, double exp, double ambient) {
        this.color = color;
        this.diffuse = diffuse;
        this.specular = specular;
        this.exp = exp;
        this.ambient = ambient;
    }

    @Override
    public Color radiance(Light light, Hit hit) {
        Vector3 direction = Vector3.sub(light.position(), hit.hit).normalized();
        double diffuse = Vector3.dot(direction, hit.normal);
        if(diffuse < 0) return Color.BLACK;
        Color result = Color.mul(Color.mul(light.color(), color), diffuse * this.diffuse);
        double specular = specular(direction, hit.normal, Vector3.reverse(hit.ray.getDirection()));
        if(specular > 0) result = Color.add(result, Color.mul(color, this.specular * specular));
        return result;
    }

    private double specular(Vector3 direction, Vector3 normal, Vector3 toCamera){
        Vector3 reflected = Vector3.reflect(direction, normal);
        double cos = Vector3.dot(reflected, toCamera);
        if(cos < 0) return 0;
        return Math.pow(cos, exp);
    }

    @Override
    public Color ambient() {
        return Color.mul(color, ambient);
    }
}
