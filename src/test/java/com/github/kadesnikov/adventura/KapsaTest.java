package com.github.kadesnikov.adventura;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kadesnikov.adventura.logika.Kapsa;
import com.github.kadesnikov.adventura.logika.Vec;

/**
 * The test class KapsaTest.
 *
 * @author  Alex Kadesnikov
 * @version 01.01.2017
 */
public class KapsaTest
{
    /**
     * Default constructor for test class KapsaTest
     */
    public KapsaTest()
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
     * Testuje kapsu a jeji obsah, zda se do ní dají vložit nepřenositelné věci 
     * a taktéž, zda se do ní dají vložit věci, pokud v ní není volné místo
     */
    @Test
    public void testKapsy()
    {
        Kapsa kapsa = new Kapsa();
        Vec vec1 = new Vec("1",true);
        Vec vec2 = new Vec("2",true);
        Vec vec3 = new Vec("3",true);
        Vec vec4 = new Vec("4",true);
        Vec vec5 = new Vec("5",true);
        Vec vec6 = new Vec("6",true);
        Vec vec7 = new Vec("7",true);
        kapsa.vlozVecDoKapsy(vec1);
        kapsa.vlozVecDoKapsy(vec2);
        kapsa.vlozVecDoKapsy(vec3);
        kapsa.vlozVecDoKapsy(vec4);
        kapsa.vlozVecDoKapsy(vec5);
        kapsa.vlozVecDoKapsy(vec6);
        kapsa.vlozVecDoKapsy(vec7);
        kapsa.vyberVecZKapsy("1");
        assertEquals(false, kapsa.obsahujeVecVKapse("1"));
        assertEquals(true, kapsa.obsahujeVecVKapse("2"));
        assertEquals(true, kapsa.obsahujeVecVKapse("3"));
        assertEquals(true, kapsa.obsahujeVecVKapse("4"));
        assertEquals(true, kapsa.obsahujeVecVKapse("5"));
        assertEquals(false, kapsa.obsahujeVecVKapse("6"));
        assertEquals(false, kapsa.obsahujeVecVKapse("7"));
    }

   

    
}


