package com.github.kadesnikov.adventura.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Aleksandr Kadesnikov
 *@version    pro školní rok 2015/2016
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Kapsa kapsa;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Kapsa kapsa) {
        this.plan = plan;
        this.kapsa = kapsa;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
       

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        Prostor prostorOchrana = plan.getAktualniProstor();
        if (sousedniProstor == null) { //pokud tam takový východ není
            return "Tam se odsud jít nedá!";
        }
        if (sousedniProstor.getNazev().equals("ředitel") 
        && !kapsa.obsahujeVecVKapse("klic") //pokud chceš do mistnosti ředitel a nemáš klíč 
        )
            {plan.setAktualniProstor(prostorOchrana);
             return "Nemáš klíč a ti to zakazuje ochrana.";
             
            }
        
        //pokud tam takový východ je
        plan.setAktualniProstor(sousedniProstor);
        return sousedniProstor.dlouhyPopis();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
