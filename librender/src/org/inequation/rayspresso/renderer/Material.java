package org.inequation.rayspresso.renderer;

/**
 * Surface shader abstract class. Subclass to implement different algorithms for
 * colouring surface fragments.
 * @author inequation
 * @version 22.11.2011
 */
public abstract class Material {
    /**
     * Samples the material at the given point.
     * @param   point   world-space location of the sampling point
     * @param   normal  world-space normal of the sampling point
     * @param   view    world-space view vector (i.e. from point to camera POV)
     * @param   ctx     render context
     * @return colour sample as calculated by the material shader
     */
    abstract public Colour sample(Vec3 point, Vec3 normal, Vec3 view,
        RenderContext ctx);
    
    /**
     * Transforms a world normal to screen normal.
     * @param normal    world-space normal
     * @param view      world-space view vector
     * @param ctx       render context
     * @return 
     */
    protected Vec3 worldNormalToScreenNormal(Vec3 normal, Vec3 view, RenderContext ctx) {
        Vec3 R, U;  // local right and up vectors for the given view vector
        try {
            R = view.cross(ctx.getCamera().getUpVector()).normalized();
        } catch (ZeroVectorNormalizationException ex) {
            R = ctx.getCamera().getRightVector();
        }
        try {
            U = R.cross(view).normalized();
        } catch (ZeroVectorNormalizationException ex) {
            U = ctx.getCamera().getUpVector();
        }
       
        return new Vec3(
            -normal.dot(R),
            -normal.dot(U),
            -normal.dot(view)
	);
    }
}
