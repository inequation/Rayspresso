package org.inequation.rayspresso.renderer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Vector unit test.
 * @author inequation
 */
public class Vec3Test {
    
    public Vec3Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of clone method, of class Vec3.
     */
    @Test
    public void testClone() {
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 clone = instance.clone();
        assertNotSame(instance, clone);
    }

    /**
     * Test of lengthSquared method, of class Vec3.
     */
    @Test
    public void testLengthSquared() {
        Vec3 instance = new Vec3(1, 1, 1);
        float expResult = 3.f;
        float result = instance.lengthSquared();
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of length method, of class Vec3.
     */
    @Test
    public void testLength() {
        Vec3 instance = new Vec3(1, 1, 1);
        float expResult = (float)Math.sqrt(3);
        float result = instance.length();
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of add method, of class Vec3.
     */
    @Test
    public void testAdd() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(4, 4, 4);
        Vec3 result = instance.add(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAssign method, of class Vec3.
     */
    @Test
    public void testAddAssign() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(4, 4, 4);
        instance.addAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of sub method, of class Vec3.
     */
    @Test
    public void testSub() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(2, 0, -2);
        Vec3 result = instance.sub(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of subAssign method, of class Vec3.
     */
    @Test
    public void testSubAssign() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(2, 0, -2);
        instance.subAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of mult method, of class Vec3.
     */
    @Test
    public void testMult_Vec3() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(3, 4, 3);
        Vec3 result = instance.mult(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of multAssign method, of class Vec3.
     */
    @Test
    public void testMultAssign_Vec3() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(3, 4, 3);
        instance.multAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of div method, of class Vec3.
     */
    @Test
    public void testDiv_Vec3() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(3, 1, 1.f / 3.f);
        Vec3 result = instance.div(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of divAssign method, of class Vec3.
     */
    @Test
    public void testDivAssign_Vec3() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(3, 1, 1.f / 3.f);
        instance.divAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of mult method, of class Vec3.
     */
    @Test
    public void testMult_float() {
        float other = 3.f;
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 expResult = new Vec3(3, 6, 9);
        Vec3 result = instance.mult(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of multAssign method, of class Vec3.
     */
    @Test
    public void testMultAssign_float() {
        float other = 3.f;
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 expResult = new Vec3(3, 6, 9);
        instance.multAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of div method, of class Vec3.
     */
    @Test
    public void testDiv_float() {
        float other = 3.f;
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 expResult = new Vec3(1.f / 3.f, 2.f / 3.f, 1);
        Vec3 result = instance.div(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of divAssign method, of class Vec3.
     */
    @Test
    public void testDivAssign_float() {
        float other = 3.f;
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 expResult = new Vec3(1.f / 3.f, 2.f / 3.f, 1);
        instance.divAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of dot method, of class Vec3.
     */
    @Test
    public void testDot() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        float expResult = 3 + 4 + 3;
        float result = instance.dot(other);
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of cross method, of class Vec3.
     */
    @Test
    public void testCross() {
        Vec3 other = new Vec3(1, 2, 3);
        Vec3 instance = new Vec3(3, 2, 1);
        Vec3 expResult = new Vec3(4, -8, 4);
        Vec3 result = instance.cross(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of normalize method, of class Vec3.
     */
    @Test (expected=ZeroVectorNormalizationException.class)
    public void testNormalize() throws Exception {
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 expResult = new Vec3(
            1.f / (float)Math.sqrt(14),
            (float)Math.sqrt(2.f / 7.f),
            3.f / (float)Math.sqrt(14));
        instance.normalize();
        assertEquals(expResult, instance);
        instance = new Vec3(0, 0, 0);
        instance.normalize();
    }

    /**
     * Test of normalized method, of class Vec3.
     */
    @Test (expected=ZeroVectorNormalizationException.class)
    public void testNormalized() throws Exception {
        Vec3 instance = new Vec3(1, 2, 3);
        Vec3 expResult = new Vec3(
            1.f / (float)Math.sqrt(14),
            (float)Math.sqrt(2.f / 7.f),
            3.f / (float)Math.sqrt(14));
        Vec3 result = instance.normalized();
        assertEquals(expResult, result);
        instance = new Vec3(0, 0, 0);
        result = instance.normalized();
    }
    
    /**
     * Test of toString method, of class Vec3.
     */
    @Test
    public void testToString() throws Exception {
        Vec3 instance = new Vec3(1, 2, 3);
        String expResult = 1.f + " " + 2.f + " " + 3.f;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
