package org.inequation.rayspresso.renderer;

/**
 * Debug material that displays surface normals.
 * @author inequation
 * @version 22.11.2011
 */
public class DebugNormalsMaterial extends Material {
    @Override
    public Colour sample(Vec3 point, Vec3 normal, Vec3 view, RenderContext ctx) {
        Vec3 screen = worldNormalToScreenNormal(normal, view, ctx);
        return new Colour(
            screen.getX() * 0.5f + 0.5f,
            screen.getY() * 0.5f + 0.5f,
            screen.getZ() * 0.5f + 0.5f
        );
    }
}
