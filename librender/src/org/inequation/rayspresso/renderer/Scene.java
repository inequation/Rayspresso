package org.inequation.rayspresso.renderer;

import java.util.ArrayList;

/**
 * Scene class - a singleton holding all of the traceables and lights.
 * @author inequation
 */
public class Scene {
    public static Scene getInstance() {
        if (m_instance == null) {
            synchronized (Scene.class) {
                if (m_instance == null) {
                    m_instance = new Scene();
                }
            }
        }
        return m_instance;
    }
 
    /**
     * A private, argument-less constructor to avoid generating the default,
     * public one.
     */
    private Scene() {
        // reserve some memory up-front
        m_traceables = new ArrayList<Traceable>(32);
        m_lights = new ArrayList<Light>(8);
    }
    
    /** Retrieves a reference to the traceables list. */
    public ArrayList<Traceable> getTraceables() {return m_traceables;}
    /** Retrieves a reference to the light list. */
    public ArrayList<Light> getLights() {return m_lights;}
    /** Retrieves ray recursion level limit. */
    public int getRayRecursionLimit() { return m_rayLimit; }
    /** Sets ray recursion level limit. */
    public void setRayRecursionLimit(int rayLimit) { m_rayLimit = rayLimit; }
    /** Retrieves the scene clear colour. */
    public Colour getClearColour() { return m_clearColour; }
    /** Sets scene clear colour. */
    public void setClearColour(Colour c) { m_clearColour = new Colour(c); }
    
    /**
     * Intersects the given ray with all the geometry in the scene.
     * @param   r       ray to check
     * @param   omit    optional traceable that the ray should not collide against
     * @return intersection data
     */
    public SceneIntersectionResult intersect(Ray r, Traceable omit) {
        float d = Float.MAX_VALUE;
        IntersectionResult result = null, isr;
        Traceable hit = null;
	for (Traceable t : m_traceables) {
            if (t == null || t == omit)
                continue;
            isr = t.intersect(r);
            if (isr.intersects() && isr.getDistance() < d) {
		hit = t;
		result = isr;
                d = isr.getDistance();
            }
        }
        if (result == null)
            return new SceneIntersectionResult();
	return new SceneIntersectionResult(hit, result);
    }
    
    /**
     * Intersects the given ray with all the geometry in the scene, without omitting any shapes.
     * @param   r       ray to check
     * @return intersection data
     */
    public SceneIntersectionResult intersect(Ray r) {
        return intersect(r, null);
    }
    
    /** List of shapes residing in the scene. */
    private ArrayList<Traceable> m_traceables;
    /** List of lights residing in the scene. */
    private ArrayList<Light> m_lights;
    /** Maximum recursion level when casting rays. */
    private int m_rayLimit = 10;
    /** Scene clear colour. */
    private Colour m_clearColour = new Colour(0, 0, 0, 1);
    
    /** Singleton instance reference. */
    private static volatile Scene m_instance = null;
}
