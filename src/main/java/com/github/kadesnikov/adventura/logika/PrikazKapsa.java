package com.github.kadesnikov.adventura.logika;


/**
 * Write a description of class PrikazKapsa here.
 * 
 * @author Aleksandr Kadesnikov
 * @version prosinec 2016
 */
public class PrikazKapsa implements IPrikaz
{
    // instance variables - replace the example below with your own
    private static final String NAZEV = "kapsa";
    private Kapsa kapsa;
    

    /***************************************************************************
     *  Metoda zjistí, jaké věci má hráč v kapse
     */
    public PrikazKapsa(Kapsa kapsa)
    {
        // initialise instance variables
        this.kapsa=kapsa;
    }

    /**
     * Metoda zjistí seznam věcí v kapse, nezávisle na jiných faktorech
     * 
     * @param parametry v tomto příkazu žádné nejsou
     * 
     * @return obsah kapsy
     */
    public String proved(String... parametry)
    {
        
        return kapsa.seznamVeciVKapse();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev()
    {
        return NAZEV;
    }
}
