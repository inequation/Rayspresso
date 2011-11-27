package org.inequation.rayspresso.renderer;

/**
 * Point light source.
 * @author inequation
 * @version 22.11.2011
 */
public class PointLight extends Light {
    /**
     * @param   location    point light origin
     * @param   c           light colour
     * @param   brightness  light brightness
     */
    public PointLight(Vec3 location, Colour c, float brightness) {
        super(c, brightness);
        m_loc = new Vec3(location);
    }

    /**
     * Samples the light at the given point.
     * @param   point   sampling point
     * @return light sampling results
     */
    @Override
    LightSamplingResult sample(Vec3 point) {
        Ray r;
        try {
            r = new Ray(point, m_loc, true);
        } catch (ZeroVectorNormalizationException ex) {
            return new LightSamplingResult();
        }
        SceneIntersectionResult SIR = Scene.getInstance().intersect(r);
	if (SIR.intersects())
            // light is occluded, we don't contribute to the pixel's shading
            return new LightSamplingResult();
	Vec3 L;
        try {
            L = m_loc.sub(point).normalized();
        } catch (ZeroVectorNormalizationException ex) {
            return new LightSamplingResult();
        }
        return new LightSamplingResult(true,
            Math.min(1.f, m_bright / L.lengthSquared()), L);
    }
    
    /** Retrieves point light origin. */
    public Vec3 getLocation() { return m_loc; }
    
    /** Point light origin. */
    private Vec3 m_loc;
}
