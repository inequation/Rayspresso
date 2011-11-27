package org.inequation.rayspresso.renderer;

/**
 * Scene-wide intersection result. Contains a reference to the traceable hit.
 * @author inequation
 */
public class SceneIntersectionResult extends IntersectionResult {
    /**
     * Full constructor, defining all properties.
     * @param intersects    intersection result: true means there was intersection
     * @param t             the traceable hit by ray
     * @param distance      distance (normalized - in the [0..1] range, from ray start to end) of intersection
     * @param point         point of intersection
     * @param normal        intersection normal
     * @param m             reference to the surface material at the point of intersection (valid only if intersects == true)
     */
    public SceneIntersectionResult(boolean intersects, Traceable t,
        float distance, Vec3 point, Vec3 normal, Material m) {
        super(intersects, distance, point, normal, m);
        m_t = t;
    }
    
    /** Shortcut constructor for a no-intersection result. */
    public SceneIntersectionResult() {
        super();
        m_t = null;
    }
    
    /** Shortcut constructor for deriving from an IntersectionResult. */
    public SceneIntersectionResult(Traceable t, IntersectionResult r) {
        super(r);
        m_t = t;
    }
    
    public Traceable getHitTraceable() { return m_t; }
    
    /** Traceable hit by ray. */
    private Traceable m_t;
}
