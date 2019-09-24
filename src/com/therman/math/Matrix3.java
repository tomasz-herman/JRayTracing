package com.therman.math;

public class Matrix3 {
    private double m00, m01, m02;
    private double m10, m11, m12;
    private double m20, m21, m22;

    public Matrix3(){
        m00 = m11 = m22 = 1.0;
    }

    public Matrix3 view(Vector3 position, Vector3 at, Vector3 up){
        Vector3 z = Vector3.sub(position, at).normalized();
        Vector3 x = Vector3.cross(z, up).normalized();
        Vector3 y = Vector3.cross(z, x);
        m00 = x.x;
        m01 = y.x;
        m02 = z.x;
        m10 = x.y;
        m11 = y.y;
        m12 = z.y;
        m20 = x.z;
        m21 = y.z;
        m22 = z.z;
        return this;
    }

    public Vector3 transform(Vector3 v){
        return new Vector3(
                m00 * v.x + m10 * v.y + m20 * v.z,
                m01 * v.x + m11 * v.y + m21 * v.z,
                m02 * v.x + m12 * v.y + m22 * v.z);
    }
}
