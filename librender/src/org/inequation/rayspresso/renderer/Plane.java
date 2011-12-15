package org.inequation.rayspresso.renderer;

/**
 * Infinite plane, traceable.
 * @author inequation
 * @version 22.11.2011
 */
public class Plane implements Traceable {
    /**
     * Basic plane constructor.
     * @param normal                plane normal
     * @param distanceFromOrigin    distance between the plane and the origin of the world space
     * @param m                     surface material
     */
    public Plane(Vec3 normal, float distanceFromOrigin, Material m) {
	m_n = new Vec3(normal);
        m_d = distanceFromOrigin;
        m_m = m;
    }

    /** See Traceable.intersect(). */
    @Override
    public IntersectionResult intersect(Ray r) {
        float d1, d2;

	d1 = m_n.dot(r.getStart()) - m_d;
	d2 = m_n.dot(r.getEnd()) - m_d;
	if (d1 * d2 > 0.f)
            return new IntersectionResult();
        Vec3 point = r.getStart().add(
            r.getDir().mult(r.getLength() * -d1 / (d2 - d1))
        );
	return new IntersectionResult(true, point.sub(r.getStart()).length(),
            point, m_n, m_m);
    }
    
    private Vec3 m_n;
    private float m_d;
    private Material m_m;
}
