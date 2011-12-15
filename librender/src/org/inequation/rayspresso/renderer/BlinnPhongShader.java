package org.inequation.rayspresso.renderer;

/**
 * A material implementing the Blinn-Phong lighting model.
 * @author inequation
 * @version 22.11.2011
 */
public class BlinnPhongShader extends Material {
    /**
     * Constructor.
     * @param diffuse   diffuse component
     * @param specular  specular component (alpha is used as exponent)
     */
    public BlinnPhongShader(Colour diffuse, Colour specular) {
	m_diff = new Colour(diffuse);
        m_spec = new Colour(specular);
    }

    /** See Material.sample(). */
    @Override
    public Colour sample(Vec3 P, Vec3 N, Vec3 V, RenderContext ctx) {
        //return Colour(N * 0.5 + Vec3f(0.5));
	//return Colour(this->WorldNormalToScreenNormal(N) * 0.5 + Vec3f(0.5));
	//float dot = N.dot(V) * 0.5 + 0.5; return Colour(1 - dot, 0.f, dot);
	Colour accum = new Colour(0.f, 0.f, 0.f, 0.f);
	Vec3 L, H;
	float intensity;
        LightSamplingResult LSR;
	for (Light i : Scene.getInstance().getLights()) {
            LSR = i.sample(P);
            if (!LSR.lit())
                continue;
            L = LSR.getLightVector();
            // diffuse
            intensity = LSR.getAttenuation() * Math.max(0.f, L.dot(N));
            accum.addAssign(i.getColour().mult(m_diff).mult(intensity));
            try {
                // specular
                H = L.add(V).normalized();
            } catch (ZeroVectorNormalizationException ex) {
                H = V;
            }
            intensity = LSR.getAttenuation() * (float)Math.pow(Math.max(0.f, H.dot(N)), m_spec.getA());
            accum.addAssign(i.getColour().mult(m_spec).mult(intensity));
	}
	accum.clamp();
	return accum;
    }
    
    /** Diffuse colour component. */
    private Colour m_diff;
    /** Specular colour component. */
    private Colour m_spec;
}
