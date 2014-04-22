/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen.div;

import java.io.Serializable;

/**
 *
 * @author katja.radelicki
 */
public class Nummerplaat implements Comparable<Nummerplaat>, Serializable{
    private final String plaat;

    /**
     * @return the plaat
     */
    public String getPlaat() {
        return plaat;
    }

    Nummerplaat(String plaat) {//alleen in de package kan je nummerplaat maken
        if(plaat==null || plaat.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.plaat = plaat;
    }

    @Override
    public String toString() {
        return plaat;
    }
    @Override
    public boolean equals(Object o){
        boolean returnvalue=false;
        if (o!=null){//plaat is !=null want in constructor getest
            returnvalue= plaat.equals(((Nummerplaat)o).plaat);
        }
        return returnvalue;
    }
    
    @Override
    public int hashCode(){
        return plaat.hashCode();
                
    }

    @Override
    public int compareTo(Nummerplaat o) {
        return plaat.compareTo((o.plaat));
    }
}
