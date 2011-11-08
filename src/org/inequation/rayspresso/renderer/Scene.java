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
    }
    
    /** Retrieves a reference to the traceables list. */
    public ArrayList<Traceable> getTraceables() {return m_traceables;}
    
    /** Singleton instance reference. */
    private static volatile Scene m_instance = null;
    
    private ArrayList<Traceable> m_traceables;
}
