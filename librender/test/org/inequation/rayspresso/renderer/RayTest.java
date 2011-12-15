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
public class RayTest {
    
    public RayTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of method getStart, of class Ray.
     */
    @Test
    public void testGetStart() throws Exception {
        Ray instance = new Ray(new Vec3(1, 2, 3), new Vec3(4, 5, 6));
        Vec3 expResult = new Vec3(1, 2, 3);
        Vec3 result = instance.getStart();
        assertEquals(expResult, result);
    }
        
    /**
     * Test of method getEnd, of class Ray.
     */
    @Test
    public void testGetEnd() throws Exception {
        Ray instance = new Ray(new Vec3(1, 2, 3), new Vec3(4, 5, 6));
        Vec3 expResult = new Vec3(4, 5, 6);
        Vec3 result = instance.getEnd();
        assertEquals(expResult, result);
    }
        
    /**
     * Test of method getDir, of class Ray.
     */
    @Test
    public void testGetDir() throws Exception {
        Ray instance = new Ray(new Vec3(1, 2, 3), new Vec3(4, 5, 6));
        Vec3 expResult = new Vec3(1.f / (float)Math.sqrt(3));
        Vec3 result = instance.getDir();
        assertEquals(expResult, result);
    }
        
    /**
     * Test of method getProjectedStart, of class Ray.
     */
    @Test
    public void testGetProjectedStart() throws Exception {
        Ray instance = new Ray(new Vec3(1, 2, 3), new Vec3(4, 5, 6));
        float fExpResult = 6.f / (float)Math.sqrt(3);
        float fResult = instance.getProjectedStart();
        assertEquals(fExpResult, fResult, 0.00001);
    }
        
    /**
     * Test of method getProjectedEnd, of class Ray.
     */
    @Test
    public void testGetProjectedEnd() throws Exception {
        Ray instance = new Ray(new Vec3(1, 2, 3), new Vec3(4, 5, 6));
        float fExpResult = 15.f / (float)Math.sqrt(3);
        float fResult = instance.getProjectedEnd();
        assertEquals(fExpResult, fResult, 0.00001);
    }
        
    /**
     * Test of method getLength, of class Ray.
     */
    @Test
    public void testGetLength() throws Exception {
        Ray instance = new Ray(new Vec3(1, 2, 3), new Vec3(4, 5, 6));
        float fExpResult = (float)Math.sqrt(3 * 9);
        float fResult = instance.getLength();
        assertEquals(fExpResult, fResult, 0.00001);
    }
}
