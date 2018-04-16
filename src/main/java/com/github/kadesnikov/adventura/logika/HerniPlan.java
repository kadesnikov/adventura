package com.github.kadesnikov.adventura.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan extends Observable{
    
    private Prostor aktualniProstor;
    private Prostor prostorOchrana;
    private Prostor prostorReditel;
    private Prostor prostorHriste;
    private Kapsa kapsa;
    private final static String VITEZNA = "vychod";

    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        kapsa = new Kapsa();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor vychod = new Prostor(VITEZNA, "tvoje šance na útěk! běž co nejrychleji!!!", 500.0, 300.0);
        Prostor menza = new Prostor("menza","jídelna pro odsouzení lidi", 150.0, 280.0);
        Prostor nemocnice = new Prostor("nemocnice","nemocnice... jediné možné místo s ženama", 280.0, 50.0);
        Prostor pokoj = new Prostor("pokoj","tvůj pokoj. místo kde budeš trávit celý život, pokud neutečeš", 12.0, 150.0);
        Prostor chodba = new Prostor("chodba","chodba. toto jsi vždycky viděl ve filmech, dvoupatrový prostor s chodbou a pokoji", 150.0, 150.0);
        Prostor hriste = new Prostor("hříště","klasické hříště s košíkovou", 280.0, 150.0);
        Prostor ochrana = new Prostor("ochrana","ochrana. tam by se jít nikomu nechtělo, pokud nechceš mít problémy, nebo útect :)", 330.0, 170.0);
        Prostor reditel = new Prostor("ředitel","ředitelův kancelář", 430.0, 200.0);


        // přiřazují se průchody mezi prostory (sousedící prostory)
        reditel.setVychod(vychod);
        reditel.setVychod(ochrana);
        pokoj.setVychod(chodba);
        chodba.setVychod(pokoj);
        chodba.setVychod(menza);
        chodba.setVychod(hriste);
        menza.setVychod(chodba);
        hriste.setVychod(chodba);
        hriste.setVychod(nemocnice);
        hriste.setVychod(ochrana);
        nemocnice.setVychod(hriste);
        ochrana.setVychod(reditel);
        ochrana.setVychod(hriste);
        
        //postavy ve hře
        Postava boss = new Postava("boss");
        Postava strom = new Postava("strom");
        
        //věci ve hře
        Vec tuzka = new Vec("tužka",true,"/com/github/kadesnikov/adventura/img/tuzka.png");
        Vec postel = new Vec ("postel", false,"/com/github/kadesnikov/adventura/img/postel.png");
        //Vec klic = new Vec ("klic", true);
        Vec zapalka = new Vec("zápalka",true,"/com/github/kadesnikov/adventura/img/zapalka.png");
        Vec vodka = new Vec ("vodka", true,"/com/github/kadesnikov/adventura/img/vodka.png");
        Vec jod = new Vec("jód",true,"/com/github/kadesnikov/adventura/img/jod.png");
        Vec stul = new Vec ("stůl", false,"/com/github/kadesnikov/adventura/img/stul.png");
        Vec mic= new Vec ("míč", true,"/com/github/kadesnikov/adventura/img/mic.png");
        Vec vidlice = new Vec("vidlice",true,"/com/github/kadesnikov/adventura/img/vidlice.png");
        Vec konvice = new Vec ("konvice", true,"/com/github/kadesnikov/adventura/img/konvice.png");
        
        
        //umístění věcí
        menza.vlozVec(zapalka);
        nemocnice.vlozVec(vodka);
        pokoj.vlozVec(tuzka);
        pokoj.vlozVec(postel);
        nemocnice.vlozVec(jod);
        ochrana.vlozVec(konvice);
        hriste.vlozVec(mic);
        menza.vlozVec(vidlice);
        menza.vlozVec(stul);
        //chodba.vlozVec(klic); //dá se prozkoumat bez prikazu ukradi jestli reditel je opravdu ve sve mistnosti
        
        //umístění postav
        reditel.vlozPostavu(boss);
        hriste.vlozPostavu(strom);
        
        //pro implementaci příkazu požár
        prostorHriste = hriste;
        prostorReditel = reditel;
        prostorOchrana = ochrana;
        
        aktualniProstor = pokoj;  // hra začíná v mistnosti pokoj  
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    
    /**
     *  Metoda vrací odkaz na kapsu.
     *
     *@return     kapsa
     */
    
    public Kapsa getKapsa() {
        return kapsa;
    }
    
    
    /**
     *  Metoda vrací odkaz na prostor ochrana.
     *
     *@return    prostor ochrana
     */
    public Prostor getProstorOchrana() {
        return prostorOchrana;
    }
    
    /**
     *  Metoda vrací odkaz na prostor ředitel.
     *
     *@return    prostor ředitel
     */
    public Prostor getProstorReditel() {
        return prostorReditel;
    }
    
    /**
     *  Metoda vrací odkaz na prostor hříště.
     *
     *@return    prostor hříště
     */
    public Prostor getProstorHriste() {
        return prostorHriste;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       this.setChanged();
       this.notifyObservers();
    }
    
    /**
     *  Metoda vrací status výhry
     *
     *@return  true/false v závislosti, zda je výhra setnuta
     */
    public boolean jeVyhra(){
        return aktualniProstor.getNazev().equals(VITEZNA);
    }

}
