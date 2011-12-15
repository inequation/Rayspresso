/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inequation.rayspresso.renderer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite that runs all of the renderer's tests.
 * @author inequation
 * @version 09.11.2011
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    org.inequation.rayspresso.renderer.Vec3Test.class,
    org.inequation.rayspresso.renderer.ColourTest.class,
    org.inequation.rayspresso.renderer.RayTest.class
})
public class RendererTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
