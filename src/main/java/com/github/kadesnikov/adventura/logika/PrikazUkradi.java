package com.github.kadesnikov.adventura.logika;


/**
 * Write a description of class PrikazDej here.
 * 
 * @author Aleksanr Kadesnikov 
 * @version prosinec 2016
 */
public class PrikazUkradi implements IPrikaz
{
    // instance variables - replace the example below with your own
    private HerniPlan plan;
    private Kapsa kapsa; 
    private static final String NAZEV = "ukradi";

    /**
     * Metoda vytváří příkaz ukradi, který ukradne nejakou vec...
     */
   public PrikazUkradi(HerniPlan plan, Kapsa kapsa)
    {
        this.plan = plan;
        this.kapsa = kapsa;
        
    }

    /**
     *  Metoda ukrast předměty u postav
     *  
     *  @param parametry první parametr je vec, kterou chceš dostat, druhý je postava
     *  
     *  @return vrací text informující hráče o úspěšnosti příkazu, případně text, který řekne postava
     */
    
    public String proved(String... parametry)
    {   if (parametry.length == 0)  //pokud není zadána věc
        {
            return "Koho chceš okrast? A co?\n"+
            "Píš takhle: ukradi věc postava";  
        }
        if (parametry.length == 1) //pokud není zadána postava
        {
            return "Koho chceš okrast? A co?\n"+
            "Píš takhle: ukradi věc postava";  
        }
        Vec klic = new Vec("klic",true);
        Prostor aktualni = plan.getAktualniProstor();
        String darovanaVec = parametry[0];
        String jmeno = parametry[1];
        Postava postava = aktualni.najdiPostavu(jmeno);
        if (postava == null) //pokud v místnosti není postava, od které chceš věc ukrást
        {
            return "Buď tu ta postava není, nebo jsi to napsal špatně" ;
            }
         if (jmeno.equals("boss") && darovanaVec.equals("klic") && postava.getJmeno().equals("boss") )//pokud boss je v mistnosti
        {   kapsa.vlozVecDoKapsy(klic);
            return "Okradl jsi bossa! Utíkej!";
        }
        return "Postava tohle nemá, ukrást není možné";
    }
   
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
   public String getNazev(){
        return NAZEV;
    }
}
