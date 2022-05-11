package com.p6majo.objects;

import com.p6majo.raytracing.Ray;

public interface Hittable {
    public HitRecord hit(Ray ray, double tMin, double tMax);
}
