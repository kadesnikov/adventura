package com.github.kadesnikov.adventura.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Kapsa kapsa;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan, Kapsa) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        Kapsa kapsa = new Kapsa();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan, kapsa));
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, kapsa));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, kapsa));
        platnePrikazy.vlozPrikaz(new PrikazKapsa(kapsa));
        platnePrikazy.vlozPrikaz(new PrikazUkradi(herniPlan, kapsa));
        platnePrikazy.vlozPrikaz(new PrikazPozar(herniPlan));
       
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Úkolem hry je útěk Ivana Petrova z vezení v Rusku,\n" +
               "do níž se dostal po doživotnímu odsouzení za lajk a repost jednoho článku v sociální síti,\n" +
               "který byl považován za extremismus a pokus revoluce.\n" +
               "Útěk bude probíhat tak, že Ivan bude muset okrást ředitele a vzít klíč, pomocí kterého otevře dveře a uteče.\n" +
               "Ale lehky to nebude, jelikož ředitel se vždy nachazí pod ochranou a sedí ve své uzamčené místnosti,\n" +
               "ale trošku chaosu mu určitě pomůže \n" +  
               "Uděláme požár!\n" + 
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if(herniPlan.jeVyhra()){
                konecHry = true;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. Pokud nevíš, co dál, můžeš zadat příkaz napoveda";
        }
        if(herniPlan.jeVyhra()){
            konecHry = true;
            textKVypsani = "Tobě se konečně podařilo útect z vezení v Rusku, což je skoro nemožný.\n" + 
            "Ale jinak všude jsou Sibiř a mědvědi, takže přeji hodně štěstí přežít ruskou realitu.\n" +
            "Gratuluji!";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
     
     /**
     *  Metoda vrátí odkaz na kapsu, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na kapsu
     */
     public Kapsa getKapsa(){
        return kapsa;
     }
    
}

