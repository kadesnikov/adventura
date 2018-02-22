/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kadesnikov.adventura.logika;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    Alex Kadesnikov
 * @version   12.2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Metoda vytvoří novou věc, přiřadí ji název a přenositelnost
     *  například  tužka, true (přenositelná)
     * 
     * @param nazev nazev věci, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param přenositelnost určení přenositelnosti.
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     *  Metoda vrací jméno věci
     *  @return název věci
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     *  Metoda vrací přenositelnost věci
     *  @return přenositelnost, vrátí true, pokud je věc přenositelná
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
