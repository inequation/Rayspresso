package org.inequation.rayspresso.renderer;

/**
 * Interface for all ray-traceable objects in the scene (e.g. shapes).
 * @author inequation
 * @version 08.11.2011
 */
public interface Traceable {
    /**
     * Performs actual intersection with the ray.
     * @param in_r          ray to intersect with
     * @param out_distance  distance (normalized) at which intersection occurs (1.f if no intersection)
     * @param out_point     point at which intersection occurs (unchanged if no intersection)
     * @param out_normal    intersection normal (unchanged if no intersection)
     * @return true if there was intersection, false otherwise
     */
    boolean intersect(Ray in_r, float out_distance, Vec3 out_point, Vec3 out_normal);
    
    /** Retrieves the treaceable's surface shader. */
    Shader getShader();
}
