package org.inequation.rayspresso.renderer;

import java.util.Arrays;

/**
 * Linear, floating-point colour (all components in the [0..1] range).
 * @author inequation
 * @version 08.11.2011
 */
public class Colour {
    /**
     * Initializes components with the given parameters.
     * @param R the R component
     * @param G the G component
     * @param B the B component
     * @param A the A component
     */
    public Colour(float R, float G, float B, float A) {
        m_v = new float[4];
        m_v[0] = R;
        m_v[1] = G;
        m_v[2] = B;
        m_v[3] = A;
    }
    
    /** Zero-argument constructor. */
    public Colour() {
        m_v = new float[4];
    }
    
    /**
     * Initializes all components with the same number.
     * @param all value to initialize with
     */
    public Colour(float all) {
        m_v = new float[4];
        m_v[0] = m_v[1] = m_v[2] = m_v[3] = all;
    }
    
    /** Standard 3-component constructor (alpha is assumed to be 1). */
    public Colour(float R, float G, float B) {
        this(R, G, B, 1.f);
    }
    
    /** Copy constructor. */
    public Colour(Colour other) {
        m_v = new float[4];
        m_v[0] = other.m_v[0];
        m_v[1] = other.m_v[1];
        m_v[2] = other.m_v[2];
        m_v[3] = other.m_v[3];
    }
    
    /** Retrieves the R component. */
    public float getR() {return m_v[0];}
    /** Retrieves the G component. */
    public float getG() {return m_v[1];}
    /** Retrieves the B component. */
    public float getB() {return m_v[2];}
    /** Retrieves the A component. */
    public float getA() {return m_v[3];}
    /** Sets the R component. */
    public void setR(float R) {m_v[0] = R;}
    /** Sets the G component. */
    public void setG(float G) {m_v[1] = G;}
    /** Sets the B component. */
    public void setB(float B) {m_v[2] = B;}
    /** Sets the A component. */
    public void setA(float A) {m_v[3] = A;}
    /** Sets all the vector components. */
    public void set(float R, float G, float B, float A) {
        m_v[0] = R;
        m_v[1] = G;
        m_v[2] = B;
        m_v[3] = A;
    } 
    
    /** @return a new vector created by adding other to this. */
    public Colour add(Colour other) {
        return new Colour(this.m_v[0] + other.m_v[0],
                            this.m_v[1] + other.m_v[1],
                            this.m_v[2] + other.m_v[2],
                            this.m_v[3] + other.m_v[3]);
    }
    
    /** Adds other to this. */
    public void addAssign(Colour other) {
        this.m_v[0] += other.m_v[0];
        this.m_v[1] += other.m_v[1];
        this.m_v[2] += other.m_v[2];
        this.m_v[3] += other.m_v[3];
    }
    
    /** @return a new vector created by subtracting other from this. */
    public Colour sub(Colour other) {
        return new Colour(this.m_v[0] - other.m_v[0],
                            this.m_v[1] - other.m_v[1],
                            this.m_v[2] - other.m_v[2],
                            this.m_v[3] - other.m_v[3]);
    }
    
    /** Subtracts other from this. */
    public void subAssign(Colour other) {
        this.m_v[0] -= other.m_v[0];
        this.m_v[1] -= other.m_v[1];
        this.m_v[2] -= other.m_v[2];
        this.m_v[3] -= other.m_v[3];
    }
    
    /** @return a new vector created by multiplying other by this, component-wise. */
    public Colour mult(Colour other) {
        return new Colour(this.m_v[0] * other.m_v[0],
                            this.m_v[1] * other.m_v[1],
                            this.m_v[2] * other.m_v[2],
                            this.m_v[3] * other.m_v[3]);
    }
    
    /** Multiplies other by this, component-wise. */
    public void multAssign(Colour other) {
        this.m_v[0] *= other.m_v[0];
        this.m_v[1] *= other.m_v[1];
        this.m_v[2] *= other.m_v[2];
        this.m_v[3] *= other.m_v[3];
    }
    
    /** @return a new vector created by dividing this by other, component-wise. */
    public Colour div(Colour other) {
        return new Colour(this.m_v[0] / other.m_v[0],
                            this.m_v[1] / other.m_v[1],
                            this.m_v[2] / other.m_v[2],
                            this.m_v[3] / other.m_v[3]);
    }
    
    /** Divides this by other, component-wise. */
    public void divAssign(Colour other) {
        this.m_v[0] /= other.m_v[0];
        this.m_v[1] /= other.m_v[1];
        this.m_v[2] /= other.m_v[2];
        this.m_v[3] /= other.m_v[3];
    }
    
    /** @return a new vector created by multiplying all components of this by other. */
    public Colour mult(float other) {
        return new Colour(this.m_v[0] * other,
                            this.m_v[1] * other,
                            this.m_v[2] * other,
                            this.m_v[3] * other);
    }
    
    /** Multiplies other by this. */
    public void multAssign(float other) {
        this.m_v[0] *= other;
        this.m_v[1] *= other;
        this.m_v[2] *= other;
        this.m_v[3] *= other;
    }
    
    /** @return a new vector created by dividing all components of this by other. */
    public Colour div(float other) {
        other = 1.f / other;
        return new Colour(this.m_v[0] * other,
                            this.m_v[1] * other,
                            this.m_v[2] * other,
                            this.m_v[3] * other);
    }
    
    /** Divides other by this. */
    public void divAssign(float other) {
        other = 1.f / other;
        this.m_v[0] *= other;
        this.m_v[1] *= other;
        this.m_v[2] *= other;
        this.m_v[3] *= other;
    }
    
    /** Clamps all of the colour's components to the [0..1] range. */
    public void clamp() {
        m_v[0] = Math.min(Math.max(m_v[0], 0.f), 1.f);
        m_v[1] = Math.min(Math.max(m_v[1], 0.f), 1.f);
        m_v[2] = Math.min(Math.max(m_v[2], 0.f), 1.f);
        m_v[3] = Math.min(Math.max(m_v[3], 0.f), 1.f);
    }
    
    /** Retrieves a TYPE_INT_ABGR pixel for use with awt/swing. */
    public int getInt() {
        return (int)(m_v[2] * 255.f)
            | ((int)(m_v[1] * 255.f) << 8)
            | ((int)(m_v[0] * 255.f) << 16)
            | ((int)(m_v[3] * 255.f) << 24);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            Colour diff = this.sub((Colour)o);
            return (diff.getR() * diff.getR()
                    + diff.getG() * diff.getG()
                    + diff.getB() * diff.getB()
                    + diff.getA() * diff.getA()) < 0.0001;
        } else
            return super.equals(o);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Arrays.hashCode(this.m_v);
        return hash;
    }
    
    @Override
    public String toString() {
        return m_v[0] + " " + m_v[1] + " " + m_v[2] + " " + m_v[3];
    }
    
    /** Actual component array. */
    private float[] m_v;
}
