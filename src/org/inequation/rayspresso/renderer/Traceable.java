package org.inequation.rayspresso.renderer;

/**
 * Interface for all ray-traceable objects in the scene (e.g. shapes).
 * @author inequation
 * @version 09.11.2011
 */
public interface Traceable {
    /**
     * Performs actual intersection with the ray.
     * @return intersection result
     */
    IntersectionResult intersect(Ray r);
}
