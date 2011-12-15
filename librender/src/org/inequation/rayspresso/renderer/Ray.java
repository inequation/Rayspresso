package org.inequation.rayspresso.renderer;

/**
 * Class representing a single ray being cast into the scene.
 * @author inequation
 * @version 22.11.2011
 */
public class Ray {
    /** Constructs a ray spanning from start to end. */
    public Ray(Vec3 start, Vec3 end) throws ZeroVectorNormalizationException {
        this(start, end, false);
    }
    
    /**
     * Constructs a ray spanning from start to end.
     * @param   initialOffset   if true, the starting point will be offset by a small epsilon in the direction of the endpoint (used to avoid immediate collisions on exit ray calculations)
     */
    public Ray(Vec3 start, Vec3 end, boolean initialOffset)
        throws ZeroVectorNormalizationException {
        m_start = new Vec3(start);
        m_end = new Vec3(end);
        m_dir = end.sub(start).normalized();
	if (initialOffset)
            m_start.addAssign(m_dir.mult(m_offsetEpsilon));
	m_projStart = m_dir.dot(m_start);
	m_projEnd = m_dir.dot(m_end);
	m_length = m_projEnd - m_projStart;
    }
    
    /** Retrieves the starting point of the ray. */
    public Vec3 getStart() {return m_start;}
    /** Retrieves the end point of the ray. */
    public Vec3 getEnd() {return m_end;}
    /** Retrieves a unit vector, representing the direction of the ray. */
    public Vec3 getDir() {return m_dir;}
    /** Retrieves the starting point of the ray, projected onto the ray axis. */
    public float getProjectedStart() {return m_projStart;}
    /** Retrieves the end point of the ray, projected onto the ray axis. */
    public float getProjectedEnd() {return m_projEnd;}
    /** Retrieves the ray length. */
    public float getLength() {return m_length;}
    
    /** Properties of the ray. */
    private Vec3 m_start, m_end, m_dir;
    /** Cached values of the properties of the ray projected upon the ray axis. */
    float m_projStart, m_projEnd, m_length;
    
    /**
     * Epsilon by which the ray starting point is advanced towards the end, if
     * initial offset is requested.
     */
    private static final float m_offsetEpsilon = 5e-4f;
}
