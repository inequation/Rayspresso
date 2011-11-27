package org.inequation.rayspresso.renderer;

/**
 * Sphere traceable class.
 * @author inequation
 */
public class Sphere implements Traceable {
    /**
     * @param   origin  sphere origin
     * @param   radius  sphere radius
     * @param   mat     surface material
     */
    public Sphere(Vec3 origin, float radius, Material mat) {
        m_origin = new Vec3(origin);
        m_radius = radius;
        m_mat = mat;
    }
    
    /** Implementation of the intersect method from the Traceable interface. */
    @Override
    public IntersectionResult intersect(Ray r) {
        float det, b, d2, dist;
	Vec3 p = r.getStart().sub(m_origin);
	b = -p.dot(r.getDir());
	det = b * b - p.lengthSquared() + m_radius * m_radius;
	if (det < 0)
            // no intersection
            return new IntersectionResult();
        det = (float)Math.sqrt(det);
	dist = b - det;
	d2 = b + det;
	if (d2 < 0.f || d2 > r.getLength() - m_radius)
            // no intersection
            return new IntersectionResult();
        Vec3 n;
	if (dist < 0.f) {
            dist = d2;
            p = r.getStart().add(r.getDir().mult(dist));
            try {
                n = m_origin.sub(p).normalized();
            } catch (ZeroVectorNormalizationException ex) {
                n = r.getDir().mult(-1.f);
            }
        } else {
            p = r.getStart().add(r.getDir().mult(dist));
            try {
                n = p.sub(m_origin).normalized();
            } catch (ZeroVectorNormalizationException ex) {
                n = new Vec3(r.getDir());
            }
	}
	return new IntersectionResult(true, dist, p, n, m_mat);
    }
    
    /** Sphere origin. */
    private Vec3 m_origin;
    /** Sphere radius. */
    private float m_radius;
    /** Surface shader. */
    private Material m_mat;
}
