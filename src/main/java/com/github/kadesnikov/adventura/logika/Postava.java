package com.github.kadesnikov.adventura.logika;


/**
 * Write a description of class Postava here.
 * 
 * @author Aleksandr Kadesnikov 
 * @version 1.1.2017
 */
public class Postava
{
    // instance variables - replace the example below with your own
    private String jmeno;

    /***************************************************************************
     * Metoda vytváří novou postavu
     * 
     * @param postava je název postavy, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * 
     */
    public Postava(String jmeno)
    {
        // initialise instance variables
        this.jmeno=jmeno;
    }

    /**
     *  Metoda vrací jméno postavy
     *  
     *  @return jméno postavy
     */
    public String getJmeno()
    {
        return jmeno;
    }

}
