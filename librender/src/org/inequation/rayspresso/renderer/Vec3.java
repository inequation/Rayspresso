package org.inequation.rayspresso.renderer;

import java.util.Arrays;

/**
 * Three-dimensional floating point vector.
 * @author inequation
 * @version 09.11.2011
 */
public class Vec3 implements Cloneable {
    /** Zero-argument constructor. */
    public Vec3() {
        m_v = new float[3];
    }
    
    /**
     * Initializes all components with the same number.
     * @param all value to initialize with
     */
    public Vec3(float all) {
        m_v = new float[3];
        m_v[0] = m_v[1] = m_v[2] = all;
    }
    
    /** Copy constructor. */
    public Vec3(Vec3 other) {
        m_v = new float[3];
        m_v[0] = other.m_v[0];
        m_v[1] = other.m_v[1];
        m_v[2] = other.m_v[2];
    }
    
    /**
     * Initializes components with the given parameters.
     * @param X the X component
     * @param Y the Y component
     * @param Z the Z component
     */
    public Vec3(float X, float Y, float Z) {
        m_v = new float[3];
        m_v[0] = X;
        m_v[1] = Y;
        m_v[2] = Z;
    }
    
    /** Retrieves the X component. */
    public float getX() {return m_v[0];}
    /** Retrieves the Y component. */
    public float getY() {return m_v[1];}
    /** Retrieves the Z component. */
    public float getZ() {return m_v[2];}
    /** Sets the X component. */
    public void setX(float X) {m_v[0] = X;}
    /** Sets the Y component. */
    public void setY(float Y) {m_v[1] = Y;}
    /** Sets the Z component. */
    public void setZ(float Z) {m_v[2] = Z;}
    /** Sets all the vector components. */
    public void set(float X, float Y, float Z) {
        m_v[0] = X;
        m_v[1] = X;
        m_v[2] = X;
    } 
    
    /** Computes the length squared of the vector. */
    public float lengthSquared() {return this.dot(this);}
    /** Computes the length of the vector. */
    public float length() {return (float)Math.sqrt(this.dot(this));}
    
    /** @return a new vector created by adding other to this. */
    public Vec3 add(Vec3 other) {
        return new Vec3(this.m_v[0] + other.m_v[0],
                            this.m_v[1] + other.m_v[1],
                            this.m_v[2] + other.m_v[2]);
    }
    
    /** Adds other to this. */
    public Vec3 addAssign(Vec3 other) {
        this.m_v[0] += other.m_v[0];
        this.m_v[1] += other.m_v[1];
        this.m_v[2] += other.m_v[2];
        return this;
    }
    
    /** @return a new vector created by subtracting other from this. */
    public Vec3 sub(Vec3 other) {
        return new Vec3(this.m_v[0] - other.m_v[0],
                            this.m_v[1] - other.m_v[1],
                            this.m_v[2] - other.m_v[2]);
    }
    
    /** Subtracts other from this. */
    public Vec3 subAssign(Vec3 other) {
        this.m_v[0] -= other.m_v[0];
        this.m_v[1] -= other.m_v[1];
        this.m_v[2] -= other.m_v[2];
        return this;
    }
    
    /** @return a new vector created by multiplying other by this, component-wise. */
    public Vec3 mult(Vec3 other) {
        return new Vec3(this.m_v[0] * other.m_v[0],
                            this.m_v[1] * other.m_v[1],
                            this.m_v[2] * other.m_v[2]);
    }
    
    /** Multiplies other by this, component-wise. */
    public Vec3 multAssign(Vec3 other) {
        this.m_v[0] *= other.m_v[0];
        this.m_v[1] *= other.m_v[1];
        this.m_v[2] *= other.m_v[2];
        return this;
    }
    
    /** @return a new vector created by dividing this by other, component-wise. */
    public Vec3 div(Vec3 other) {
        return new Vec3(this.m_v[0] / other.m_v[0],
                            this.m_v[1] / other.m_v[1],
                            this.m_v[2] / other.m_v[2]);
    }
    
    /** Divides this by other, component-wise. */
    public Vec3 divAssign(Vec3 other) {
        this.m_v[0] /= other.m_v[0];
        this.m_v[1] /= other.m_v[1];
        this.m_v[2] /= other.m_v[2];
        return this;
    }
    
    /** @return a new vector created by multiplying all components of this by other. */
    public Vec3 mult(float other) {
        return new Vec3(this.m_v[0] * other,
                            this.m_v[1] * other,
                            this.m_v[2] * other);
    }
    
    /** Multiplies other by this. */
    public Vec3 multAssign(float other) {
        this.m_v[0] *= other;
        this.m_v[1] *= other;
        this.m_v[2] *= other;
        return this;
    }
    
    /** @return a new vector created by dividing all components of this by other. */
    public Vec3 div(float other) {
        other = 1.f / other;
        return new Vec3(this.m_v[0] * other,
                            this.m_v[1] * other,
                            this.m_v[2] * other);
    }
    
    /** Divides other by this. */
    public Vec3 divAssign(float other) {
        other = 1.f / other;
        this.m_v[0] *= other;
        this.m_v[1] *= other;
        this.m_v[2] *= other;
        return this;
    }
    
    /**
     * Computes the dot product of this and other. */
    public float dot(Vec3 other) {
        return this.m_v[0] * other.m_v[0]
            + this.m_v[1] * other.m_v[1]
            + this.m_v[2] * other.m_v[2];
    }
    
    /** @return a new vector containing the cross product of this and other. */
    public Vec3 cross(Vec3 other) {
        return new Vec3(this.m_v[1] * other.m_v[2] - this.m_v[2] * other.m_v[1],
                this.m_v[2] * other.m_v[0] - this.m_v[0] * other.m_v[2],
                this.m_v[0] * other.m_v[1] - this.m_v[1] * other.m_v[0]);
    }
    
    /** Normalizes this. */
    public Vec3 normalize() throws ZeroVectorNormalizationException {
        float l = this.length();
        if (l <= 0.f)
            throw new ZeroVectorNormalizationException();
        return this.divAssign(l);
    }
    
    /** @return a normalized version of this */
    public Vec3 normalized() throws ZeroVectorNormalizationException {
        float l = this.length();
        if (l <= 0.f)
            throw new ZeroVectorNormalizationException();
        return this.div(l);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            Vec3 diff = this.sub((Vec3)o);
            return diff.lengthSquared() < 0.0001;
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
        return m_v[0] + " " + m_v[1] + " " + m_v[2];
    }
    
    /** Actual component array. */
    private float[] m_v;
}
