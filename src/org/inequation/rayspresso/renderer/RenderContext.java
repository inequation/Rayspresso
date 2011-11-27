package org.inequation.rayspresso.renderer;

/**
 * Class that holds information specific to the current render in process.
 * @author inequation
 * @version 22.11.2011
 */
public class RenderContext {
    /**
     * Basic constructor.
     * @param c                 camera used in the current render
     * @param rayRecursionLimit maximum level of raytracing recursion
     */
    public RenderContext(Camera c, int rayRecursionLimit) {
        m_cam = c;
        m_rayLimit = rayRecursionLimit;
    }
    
    public Camera getCamera() { return m_cam; }
    public int getRayRecursionLimit() { return m_rayLimit; }
    public void setRayRecursionLimit(int limit) { m_rayLimit = limit; }
    
    private Camera m_cam;
    private int m_rayLimit;
}
