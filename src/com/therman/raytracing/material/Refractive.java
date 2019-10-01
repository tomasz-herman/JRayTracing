package com.therman.raytracing.material;

import com.therman.math.Color;
import com.therman.math.Ray;
import com.therman.math.Vector3;
import com.therman.raytracing.Hit;
import com.therman.raytracing.Raytracer;

public class Refractive extends MaterialDecorator {

    private Color color;
    private double refraction;
    private double transmission;

    public Refractive(Material material, Color color, double refraction, double transmission){
        super(material);
        this.transmission = transmission;
        this.refraction = refraction;
        this.color = color;
    }

    @Override
    public Color shade(Raytracer raytracer, Hit hit, int thread) {
        Color result = material.shade(raytracer, hit, thread);
        Vector3 toCamera = Vector3.reverse(hit.ray.getDirection());
        double cos = Vector3.dot(hit.normal, toCamera);
        double eta = cos > 0 ? refraction : 1.0 / refraction;
        double coeff = 1.0 - (1.0 - cos * cos) / (eta * eta);
        Ray reflected = new Ray(hit.point, Vector3.reflect(toCamera, hit.normal));
        Color reflection = Color.mul(color, 1 - transmission);

        if(coeff < 0){
            result = Color.add(result, raytracer.shade(hit.world, reflected, hit.depth, thread));
        } else {
            Ray transmitted = transmitted(hit.point, toCamera, hit.normal, eta, coeff, cos);
            Color transmission = Color.mul(Color.WHITE, this.transmission / (eta * eta));
            result = Color.add(result, Color.mul(reflection, raytracer.shade(hit.world, reflected, hit.depth, thread)));
            result = Color.add(result, Color.mul(transmission, raytracer.shade(hit.world, transmitted, hit.depth, thread)));
        }
        return result;
    }

    private Ray transmitted(Vector3 point, Vector3 toCamera, Vector3 normal, double eta, double coeff, double cos){
        double cos2 = Math.sqrt(coeff);
        if(cos < 0){
            normal = Vector3.reverse(normal);
            cos = -cos;
        }
        Vector3 direction = Vector3.sub(Vector3.reverse(Vector3.div(toCamera, eta)), Vector3.mul(normal, cos2 - cos / eta));
        return new Ray(point, direction);
    }

    @Override
    public Color fastshade(Raytracer raytracer, Hit hit) {
        Color result = material.fastshade(raytracer, hit);
        Vector3 toCamera = Vector3.reverse(hit.ray.getDirection());
        double cos = Vector3.dot(hit.normal, toCamera);
        double eta = cos > 0 ? refraction : 1.0 / refraction;
        double coeff = 1.0 - (1.0 - cos * cos) / (eta * eta);
        Ray reflected = new Ray(hit.point, Vector3.reflect(toCamera, hit.normal));
        Color reflection = Color.mul(color, 1 - transmission);

        if(coeff < 0){
            result = Color.add(result, raytracer.fastshade(hit.world, reflected, hit.depth));
        } else {
            Ray transmitted = transmitted(hit.point, toCamera, hit.normal, eta, coeff, cos);
            Color transmission = Color.mul(Color.WHITE, this.transmission / (eta * eta));
            result = Color.add(result, Color.mul(reflection, raytracer.fastshade(hit.world, reflected, hit.depth)));
            result = Color.add(result, Color.mul(transmission, raytracer.fastshade(hit.world, transmitted, hit.depth)));
        }
        return result;
    }

}
