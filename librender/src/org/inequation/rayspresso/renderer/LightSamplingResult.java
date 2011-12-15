package org.inequation.rayspresso.renderer;

/**
 * Light sampling result class (see Light.sample).
 * @author inequation
 * @version 22.11.2011
 */
public class LightSamplingResult {
    /**
     * Full constructor, definig all properties.
     * @param   isLit       whether the sample point is to be lit (i.e. unoccluded by other geometry)
     * @param   attenuation light attenuation multiplier
     * @param   lightVector vector from point to light source
     */
    public LightSamplingResult(boolean isLit, float attenuation, Vec3 lightVector) {
        m_lit = isLit;
        m_att = attenuation;
        m_L = lightVector;                
    }
    
    /**
     * Shortcut constructor, implying light source was occluded.
     */
    public LightSamplingResult() {
        m_lit = false;
    }
    
    /** Retrieves whether the sample point was lit by this light - if false, the light does not contribute to the shading. */
    public boolean lit() { return m_lit; }
    public float getAttenuation() { return m_att; }
    public Vec3 getLightVector() { return m_L; }
    
    /** Whether or not the sample point was lit (i.e. light source was not occluded by other geometry). */
    private boolean m_lit;
    /** Light attenuation multiplier. */
    private float m_att;
    /** Vector from sampled point to light source. */
    private Vec3 m_L;
}
