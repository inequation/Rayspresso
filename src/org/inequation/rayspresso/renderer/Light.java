package org.inequation.rayspresso.renderer;

/**
 * Base class for light sources.
 * @author inequation
 * @version 22.11.2011
 */
public abstract class Light {
    /**
     * Full light constructor.
     * @param   c           light colour
     * @param   brightness  light brightness
     */
    public Light(Colour c, float brightness) {
        m_colour = new Colour(c);
        m_colour.setA(1.f);
        m_bright = brightness;
    }

    /** Retrieves light colour. */
    Colour getColour() { return m_colour; }

    /**
     * Samples the light for point.
     * @param   point   point which is to be lit
     * @return a LightSamplingResult containing occlusion information, attenuation and light vector
     */
    abstract LightSamplingResult sample(Vec3 point);
    
    /** Light colour. */
    protected Colour m_colour;
    /** Light brightness. */
    protected float m_bright;
}
