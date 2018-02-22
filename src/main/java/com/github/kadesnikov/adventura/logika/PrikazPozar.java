package com.github.kadesnikov.adventura.logika;


/**
 * Write a description of class PrikazPozar here.
 * 
 * @author Aleksandr Kadesnikov
 * @version prosinec 2016
 */
public class PrikazPozar implements IPrikaz
{
    // instance variables - replace the example below with your own
    private HerniPlan plan;
    private static final String NAZEV = "pozar";

     /**
     * Metoda vytváří příkaz požár, který při určitých podmínkách vyvolá požár...
     */
    public PrikazPozar(HerniPlan plan)
    {
        // initialise instance variables
        this.plan=plan;
    }

    /**
     *  Metoda požár
     *  
     *  @param parametry první parametr je postava,co chceš aby hořelo, druhý a třetí jsou věci které tomu pomůžou.
     *  
     *  @return vrací text informující hráče o úspěšnosti příkazu, případně text, který řekne postava
     */
    public String proved(String... parametry)
    {   
        
        if (parametry.length == 0 || parametry.length == 1 || parametry.length == 2)  //pokud není zadána postava nebo veci 
        {
            return "Napíš, z čeho chceš požár udělat?\n"+
            "Píš takhle: pozar postava vec1 vec2";  
        }
       
        Prostor aktualni = plan.getAktualniProstor();
        String jmeno = parametry[0];
        String nazev1 = parametry[1];
        String nazev2 = parametry[2];
        Postava postava = aktualni.najdiPostavu(jmeno);
        Vec vec1 = aktualni.najdiVec(nazev1);
        Vec vec2 = aktualni.najdiVec(nazev2);
        Postava boss = new Postava("boss");
        Prostor ochrana = plan.getProstorOchrana();
        Prostor reditel = plan.getProstorReditel();
        Prostor hriste = plan.getProstorHriste();
        Postava horici_strom = new Postava("hořící strom");
        
        if (postava == null) //pokud v místnosti není postava
        {
            return "Buď tu ta postava není, nebo jsi to napsal špatně" ;
            }
            
             if (vec1 == null || vec2 == null) //pokud v místnosti není věc1
        {
            return "Buď tu ta věc není, nebo jsi to napsal špatně" ;
            }
        
            
         if (vec1.getNazev().equals("zápalka") && vec2.getNazev().equals("vodka") && postava.getJmeno().equals("strom")
         ||
         vec2.getNazev().equals("zápalka") && vec1.getNazev().equals("vodka") && postava.getJmeno().equals("strom") )
         //pokud v mistnosti leží vodka a zápalka a je postava strom, bude požár, strom bude hořet
        {   
            ochrana.vlozPostavu(boss);
            hriste.vlozPostavu(horici_strom);
            hriste.odeberPostavu("strom");
            hriste.odeberVec("zápalka");
            hriste.odeberVec("vodka");
            reditel.odeberPostavu("boss");
            return "Požár!! Hoří strom! Boss vybehl do mistnosti Ochrana!";
        }
        return "něco tobě chybí abys udělal požár, hledej věci!";
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
