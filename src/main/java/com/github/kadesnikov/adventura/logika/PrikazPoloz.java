package com.github.kadesnikov.adventura.logika;


/**
 * Write a description of class PrikazPoloz here.
 * 
 * @author Alex Kadesnikov 
 * @version 12.2016
 */
public class PrikazPoloz implements IPrikaz
{
    // instance variables - replace the example below with your own
    private HerniPlan plan;
    private Kapsa kapsa;
    private static final String NAZEV = "poloz";

    /**
     * Metoda vloží věci z kapsy do prostoru
     */
    public PrikazPoloz(HerniPlan plan, Kapsa kapsa)
    {
        this.plan = plan;
        this.kapsa = kapsa;
    }

    /**
     *  Metoda vezmě věc z kapsy a vloží ji do aktuálního prostoru, ale jen pokud kapsa tuhle věc obsahuje
     *  
     *  @param parametry jeden parametr, název pokládané věci
     *  
     *  @return vrací text oznamující hráči úspěšnost položení věci
     *  
     */
    public String proved(String... parametry)
    {
        if (parametry.length == 0) //pokud není zadána pokládaná věc
        {
            return "Co chceš položit?";
        }
        String nazevPokladaneVeci = parametry[0];
        Prostor aktualni = plan.getAktualniProstor();  
        Vec pokladana = kapsa.vyberVecZKapsy(nazevPokladaneVeci); //automaticky se z kapsy odebere pokládaná věc

        if (pokladana == null) //pokud v kapse pokládaná věc nebyla, tak logicky nelze položit
        {
            return "Nejde položit věc, kterou nemáš";
        }
        else
        {
            aktualni.vlozVec(pokladana); //pokud v kapse je, tak se vloží do aktuálního prostoru
            return "Položil jsi to!";
        }  
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
