package com.github.kadesnikov.adventura;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kadesnikov.adventura.logika.Postava;

/**
 * The test class PostavaTest.
 *
 * @author  Alex Kadesnikov
 * @version 01.01.2017
 */
public class PostavaTest
{
    /**
     * Default constructor for test class PostavaTest
     */
    public PostavaTest()
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
    
    /**
     * Testuje metodu getJmeno
     */
    @Test
    public void testPostava()
    {
        Postava postava1 = new Postava("Max");
        assertEquals("Max", postava1.getJmeno());
       
    }
}
