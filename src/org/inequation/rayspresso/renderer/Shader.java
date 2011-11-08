package org.inequation.rayspresso.renderer;

/**
 * Surface shader abstract class. Subclass to implement different algorithms for
 * colouring surface fragments.
 * @author inequation
 * @version 08.11.2011
 */
public abstract class Shader {    
    abstract public Colour Sample(Vec3 point, Vec3 normal, Vec3 view, int rayCounter);
    
    /** Transforms a world normal to screen normal. */
    protected Vec3 WorldNormalToScreenNormal(Vec3 in) {
        return new Vec3(
            // TODO
            /*in.dot(Scene.GetInstance().Cam.GetRightVector()),
            in.dot(Scene.GetInstance().Cam.GetUpVector()),
            in.dot(Scene.GetInstance().Cam.GetForwardVector())*/
	);
    }
}
