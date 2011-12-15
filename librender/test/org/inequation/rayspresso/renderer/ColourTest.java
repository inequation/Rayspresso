/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inequation.rayspresso.renderer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author inequation
 */
public class ColourTest {
    
    public ColourTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of clone method, of class Colour.
     */
    @Test
    public void testClone() {
        Colour instance = new Colour(1, 2, 3, 4);
        Colour clone = new Colour(instance);
        assertNotSame(instance, clone);
    }

    /**
     * Test of add method, of class Colour.
     */
    @Test
    public void testAdd() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(5, 5, 5, 5);
        Colour result = instance.add(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAssign method, of class Colour.
     */
    @Test
    public void testAddAssign() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(5, 5, 5, 5);
        instance.addAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of sub method, of class Colour.
     */
    @Test
    public void testSub() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(3, 1, -1, -3);
        Colour result = instance.sub(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of subAssign method, of class Colour.
     */
    @Test
    public void testSubAssign() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(3, 1, -1, -3);
        instance.subAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of mult method, of class Colour.
     */
    @Test
    public void testMult_Colour() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(4, 6, 6, 4);
        Colour result = instance.mult(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of multAssign method, of class Colour.
     */
    @Test
    public void testMultAssign_Colour() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(4, 6, 6, 4);
        instance.multAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of div method, of class Colour.
     */
    @Test
    public void testDiv_Colour() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(4, 3.f / 2.f, 2.f / 3.f, 1.f / 4.f);
        Colour result = instance.div(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of divAssign method, of class Colour.
     */
    @Test
    public void testDivAssign_Colour() {
        Colour other = new Colour(1, 2, 3, 4);
        Colour instance = new Colour(4, 3, 2, 1);
        Colour expResult = new Colour(4, 3.f / 2.f, 2.f / 3.f, 1.f / 4.f);
        instance.divAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of mult method, of class Colour.
     */
    @Test
    public void testMult_float() {
        float other = 4.f;
        Colour instance = new Colour(1, 2, 3, 4);
        Colour expResult = new Colour(4, 8, 12, 16);
        Colour result = instance.mult(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of multAssign method, of class Colour.
     */
    @Test
    public void testMultAssign_float() {
        float other = 4.f;
        Colour instance = new Colour(1, 2, 3, 4);
        Colour expResult = new Colour(4, 8, 12, 16);
        instance.multAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of div method, of class Colour.
     */
    @Test
    public void testDiv_float() {
        float other = 4.f;
        Colour instance = new Colour(1, 2, 3, 4);
        Colour expResult = new Colour(1.f / 4.f, 2.f / 4.f, 3.f / 4.f, 1);
        Colour result = instance.div(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of divAssign method, of class Colour.
     */
    @Test
    public void testDivAssign_float() {
        float other = 4.f;
        Colour instance = new Colour(1, 2, 3, 4);
        Colour expResult = new Colour(1.f / 4.f, 2.f / 4.f, 3.f / 4.f, 1);
        instance.divAssign(other);
        assertEquals(expResult, instance);
    }

    /**
     * Test of clamp method, of class Colour.
     */
    @Test
    public void testClamp() {
        Colour instance = new Colour(-1, 2, 0.5f, 0.99f);
        Colour expResult = new Colour(0, 1, 0.5f, 0.99f);
        instance.clamp();
        assertEquals(expResult, instance);
    }
    
    /**
     * Test of toString method, of class Colour.
     */
    @Test
    public void testToString() throws Exception {
        Colour instance = new Colour(1, 2, 3, 4);
        String expResult = 1.f + " " + 2.f + " " + 3.f + " " + 4.f;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
