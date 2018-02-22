package com.github.kadesnikov.adventura.logika;
import java.util.Map;
import java.util.HashMap;


/**
 * Write a description of class Kapsa here.
 * 
 * @author Alex Kadesnikov 
 * @version 12.2016
 */
public class Kapsa
{
    // instance variables - replace the example below with your own
    private static final int KAPACITA = 5 ;
    private Map<String, Vec> seznamVeci ;

    /**
     * Vytvoření nové kapsy se seznamem věcí
     */
    
    public Kapsa()
    {
        // initialise instance variables
        seznamVeci = new HashMap<>();
    }

    /**
     *  Metoda vkládá věci do kapsy
     *  
     *  @param parametr je název vkládané věci.
     *  @vrací true, pokud se věc do kapsy vložila, false, pokud ne
     */
    
    public boolean vlozVecDoKapsy (Vec vec) {
        if (seznamVeci.size() < KAPACITA ) {
            seznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }
    /**
     * Napíše info o věcech v kapse
     */
    public boolean obsahujeVecVKapse (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }
    /**
     * Vrátí věc z kapsy
     */
    public Vec vyberVecZKapsy (String jmenoVeci) {
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }
    /**
     * Vypíše věci, které jsou v kapse
     */
    public String seznamVeciVKapse() {
        String nazvy = "Máš v kapse: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;

    }
    /**
     * Vypíše kolik věcí v kapse je
     */
    public int getKapacitaKapsy() {
        return seznamVeci.size();

    }
    
}
