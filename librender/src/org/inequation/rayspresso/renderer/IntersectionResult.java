package org.inequation.rayspresso.renderer;

/**
 * Class containing the result of a Traceable intersection with Ray (see
 * Traceable.intersect method).
 * @author inequation
 * @version 22.11.2011
 */
public class IntersectionResult {
    /**
     * Full constructor, defining all properties.
     * @param intersects    intersection result: true means there was intersection
     * @param distance      distance (normalized - in the [0..1] range, from ray start to end) of intersection
     * @param point         point of intersection
     * @param normal        intersection normal
     * @param m             reference to the surface material at the point of intersection (valid only if intersects == true)
     */
    public IntersectionResult(boolean intersects, float distance, Vec3 point,
        Vec3 normal, Material m) {
        m_result = intersects;
        m_dist = distance;
        m_p = point;
        m_n = normal;
        m_m = m;
    }
    
    /**
     * Copy constructor.
     * @param   other   instance to copy
     */
    public IntersectionResult(IntersectionResult other) {
        m_result = other.m_result;
        m_dist = other.m_dist;
        m_p = other.m_p;
        m_n = other.m_n;
        m_m = other.m_m;
    }
    
    /** Shortcut constructor for a no-intersection result. */
    public IntersectionResult() {
        m_result = false;
    }
    
    /** Retrieves boolean intersection result. */
    public boolean intersects() {return m_result;}
    public float getDistance() {return m_dist;}
    public Vec3 getPoint() {return m_p;}
    public Vec3 getNormal() {return m_n;}
    public Material getMaterial() {return m_m;}
    
    /** Intersection result: true means there was intersection. */
    private boolean m_result;
    /** Distance (normalized - in the [0..1] range, from ray start to end) of intersection. */
    private float m_dist;
    /** Point of intersection. */
    private Vec3 m_p;
    /** Intersection normal. */
    private Vec3 m_n;
    /** Surface material. */
    private Material m_m;
}
