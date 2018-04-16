package com.github.kadesnikov.adventura;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kadesnikov.adventura.logika.Vec;

/**
 * The test class VecTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VecTest
{
    /**
     * Default constructor for test class VecTest
     */
    public VecTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /***************************************************************************
     * Test metody přenositelnosti
     */
    @Test
    public void testPrenositelnost()
    {
        Vec vec1 = new Vec("whiskey", true,"tuzka.png");
        Vec vec2 = new Vec("stůl", false,"tuzka.png");
        assertEquals(true, vec1.jePrenositelna());
        assertEquals(false, vec2.jePrenositelna());
    }
    
    /**
     * Testuje metodu getNazev
     */
    @Test
    public void testNazev()
    {
        Vec vec1 = new Vec("Max", true,"tuzka.png");
        assertEquals("Max", vec1.getNazev());
       
    }
}
