package org.inequation.rayspresso.renderer;

/**
 * Debug material that displays depth in grayscale.
 * @author inequation
 * @version 22.11.2011
 */
public class DebugDepthMaterial extends Material {
    @Override
    public Colour sample(Vec3 point, Vec3 normal, Vec3 view, RenderContext ctx) {
        float dist = point.sub(ctx.getCamera().getLocation()).length();
        float frac = 1.f - (dist - ctx.getCamera().getZNear())
            / (ctx.getCamera().getZFar() - ctx.getCamera().getZNear());
        return new Colour(frac, frac, frac, 1.f);
    }
}
