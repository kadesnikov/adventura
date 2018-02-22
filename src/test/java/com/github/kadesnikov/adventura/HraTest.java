package com.github.kadesnikov.adventura;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kadesnikov.adventura.logika.Hra;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pokoj", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(false, hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi menza");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber zápalka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi hříště");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi nemocnice");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber vodka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi hříště");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("poloz vodka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("poloz zápalka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("pozar strom vodka zápalka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi ochrana");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("ukradi klic boss");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi ředitel");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vychod");
        
        assertEquals(true, hra1.konecHry());
    }
}
