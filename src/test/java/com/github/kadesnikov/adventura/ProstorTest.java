package com.github.kadesnikov.adventura;
import com.github.kadesnikov.adventura.logika.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kadesnikov.adventura.logika.Prostor;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2015/2016
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }


    @Test
    public void testVeci()
    {
    	com.github.kadesnikov.adventura.logika.Prostor prostor1 = new com.github.kadesnikov.adventura.logika.Prostor(null, null);
    	com.github.kadesnikov.adventura.logika.Vec vec1 = new com.github.kadesnikov.adventura.logika.Vec("a", true);
    	com.github.kadesnikov.adventura.logika.Vec vec2 = new com.github.kadesnikov.adventura.logika.Vec("b", false);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertSame(vec1, prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("c"));
    }
}

