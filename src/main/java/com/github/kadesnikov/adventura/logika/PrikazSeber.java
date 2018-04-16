/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kadesnikov.adventura.logika;

import java.util.Observable;

/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    Jarmila Pavlíčková, Aleksandr Kadesnikov
 * @version   0.00.000
 */
public class PrikazSeber extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    //private Kapsa kapsa;
    // private Batoh batoh; 
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor .... Metoda sebere věci z aktuálního prostoru a vloží je do kapsy
     */
    public PrikazSeber(HerniPlan plan, Kapsa kapsa)
    {
        this.plan = plan;
        //this.kapsa= kapsa;
        //batoh = plan.getBatoh();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Příkaz se snaží sebrat věc z aktuálního prostoru a vložit ji do kapsy, ale pouze pokud je 
     * v kapse volné místo a věc je přenositelná
     * 
     *  @param parametry je zde jeden parametr, a to je název sbírané věci
     *  
     *  @return vrací text, který je zobrazen hráčí po provedení příkazu, a informuje ho o úspěšnosi sebrání věci
     */
    public String proved(String... parametry){
        if(parametry.length == 0) //pokud se nezadá parametr
        {
            return "co mám sebrat?";
        }        
        String nazevSbiraneVeci = parametry[0];        
        Prostor aktualni = plan.getAktualniProstor();
        Kapsa kapsa = plan.getKapsa();
        Vec sbirana = aktualni.odeberVec(nazevSbiraneVeci);  //z aktuálního prostoru se odebere volaná věc 
        
        if(sbirana == null)//pokud sbíraná věc není v prostoru
        {
            return "to tu neni";
        }
        else{
            if(sbirana.jePrenositelna()) //pokud je sbíraná věc přenositelná
            {  if(kapsa.getKapacitaKapsy() <5)//kapacita není plná  
                {
               kapsa.vlozVecDoKapsy(sbirana);
               setChanged();
               notifyObservers();
               return "Vložil jsi do kapsy " + nazevSbiraneVeci;
            }
            else{
                aktualni.vlozVec(sbirana); //pokud věc není přenositelná, tak se vloží zpět do aktuálního prostoru
                return "Máš plnou kapsu věcí";
            }
                
            }
            else
            {
                aktualni.vlozVec(sbirana); //pokud věc není přenositelná, tak se vloží zpět do aktuálního prostoru
                return "To neuneseš";
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
