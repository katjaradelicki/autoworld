/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen.div;

/**
 *
 * @author katja.radelicki
 */
public class DIV {
    private static final DIV instance=new DIV();
    //private Nummerplaat plaat;
    private static int teller=0;

    public DIV(){
        
    }
    /**
     * @return the instance
     */
    public static DIV getInstance() {
        return instance;
    }
    
    public Nummerplaat getNummerplaat(){//1 halen mag je wel, het is het maken dat niet buiten de package mag gaan
        if (teller<999){
        teller++;
        }else {teller=1;
        }
        return new Nummerplaat(String.format("AAA%03d", teller));
    }
    
    
}
