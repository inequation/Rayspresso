package org.inequation.rayspresso.renderer;

import java.awt.image.BufferedImage;

/**
 * Camera through which we look into the scene.
 * @author inequation
 * @version 22.11.2011
 */
public class Camera {
    /** Full constructor.
     * @param location  point of view location
     * @param forward   local coordinate system - forward vector
     * @param right     local coordinate system - right vector
     * @param up        local coordinate system - up vector
     * @param FOV       horizontal field of view in radians (vertical is derived from viewport aspect ratio)
     * @param zNear     near plane depth
     * @param zFar      far plane depth
     */
    public Camera(Vec3 location, Vec3 forward, Vec3 right, Vec3 up, float FOV,
        float zNear, float zFar) {
        m_loc = location;
        m_fwd = forward;
        m_right = right;
        m_up = up;
        m_FOV = FOV;
        m_zNear = zNear;
        m_zFar = zFar;
    }
    
    /** Shortcut constructor that assumes defaults for FOV and depth. */
    public Camera(Vec3 location, Vec3 forward, Vec3 right, Vec3 up) {
        this(location, forward, right, up, 90.f / 180.f * (float)Math.PI, 0.1f, 32.f);
    }
    
    /**
     * Calculates view vector for the given normalized viewport coordinates.
     * @param px    normalized X viewport coordinate
     * @param py    normalized X viewport coordinate
     * @return the view vector
     */
    public Vec3 calcViewVector(float px, float py)
        throws ZeroVectorNormalizationException {
        return m_fwd.mult(m_zNear).add(
            m_right.mult(px).add(
                m_up.mult(py)
            )
        ).normalized();
    }
    
    /**
     * Renders the current scene to the given BufferedImage.
     * @param bi        image to render to
     * @param override  optional global material override (null for no override)
     */
    public void render(BufferedImage bi, Material override) {
	float pw = m_zNear * (float)Math.tan(m_FOV * 0.5);
	float ph = pw * (float)bi.getHeight() / (float)bi.getWidth();
	Vec3 view, target;
	float px, py;
        SceneIntersectionResult SIR;
        // ray limit will be set before each pixel anyway
        RenderContext ctx = new RenderContext(this, 0);
        for (int y = 0; y < bi.getHeight(); ++y) {
            py = 2.f * ((float)(bi.getHeight() - y - 1)
                    / (bi.getHeight() - 1) - 0.5f) * ph;
            for (int x = 0; x < bi.getWidth(); ++x) {
                px = 2.f * ((float)x / (bi.getWidth() - 1) - 0.5f) * pw;
                try {
                    view = calcViewVector(px, py);
                } catch (ZeroVectorNormalizationException ex) {
                    bi.setRGB(x, y, Scene.getInstance().getClearColour().getInt());
                    continue;
                }
                target = m_loc.add(view.mult(m_zFar));
                try {
                    SIR = Scene.getInstance().intersect(new Ray(m_loc, target));
                } catch (ZeroVectorNormalizationException ex) {
                    bi.setRGB(x, y, Scene.getInstance().getClearColour().getInt());
                    continue;
                }
                if (!SIR.intersects()) {
                    bi.setRGB(x, y, Scene.getInstance().getClearColour().getInt());
                    continue;
                }
                ctx.setRayRecursionLimit(Scene.getInstance().getRayRecursionLimit());
                Colour sample = override == null
                    ? SIR.getMaterial().sample(SIR.getPoint(), SIR.getNormal(), view, ctx)
                    : override.sample(SIR.getPoint(), SIR.getNormal(), view, ctx);
                sample.setA(1.f);
                bi.setRGB(x, y, sample.getInt());
            }
	}
    }
    
    public Vec3 getLocation() { return m_loc; }
    public Vec3 getForwardVector() { return m_fwd; }
    public Vec3 getRightVector() { return m_right; }
    public Vec3 getUpVector() { return m_up; }
    public float getFOV() { return m_FOV; }
    public float getZNear() { return m_zNear; }
    public float getZFar() { return m_zFar; }
    
    /** Camera point of view location. */
    private Vec3 m_loc;
    /** Camera local coordinate system - forward vector. */
    private Vec3 m_fwd;
    /** Camera local coordinate system - right vector. */
    private Vec3 m_right;
    /** Camera local coordinate system - up vector. */
    private Vec3 m_up;
    /** Camera horizontal field of view (vertical is derived from viewport aspect ratio). */
    private float m_FOV;
    /** Near plane distance from location. */
    private float m_zNear;
    /** Far plane distance from location. */
    private float m_zFar;
}
