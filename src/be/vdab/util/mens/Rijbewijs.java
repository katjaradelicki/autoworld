/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

import java.io.Serializable;

/**
 *
 * @author Katja
 */
/* // Wijze 1
public enum Rijbewijs implements Serializable{
    A,B,BE,C,CE,D,DE;
        @Override    
        public String toString(){
            String word=super.toString();
            switch(this){
                case BE: word= "B+E";break;
                case CE: word= "C+E";break;
                case DE: word= "D+E";break;
            }
            return word;
        }
}
*/

/* // Wijze 2
public enum Rijbewijs implements Serializable{
    A,
    B,
    BE{@Override public String toString(){ return "B+E";}},
    C,
    CE{@Override public String toString(){ return "C+E";}},
    D,
    DE{@Override public String toString(){ return "D+E";}};
        
}
*/


// Wijze 3
public enum Rijbewijs implements Serializable{
    A,B,BE,C,CE,D,DE;
        @Override    
        public String toString(){
            String word=super.toString();
            if(word.length()>1){
                word=word.substring(0, 1)+"+"+word.substring(1, 2);
            }
            return word;
        }
}





/*
public class Rijbewijs {
    
    public enum RijbewijsTypes {
        A,B,BE,C,CE,D,DE;
        @Override    
        public String toString(){
            String word=null;
            switch(this){
                case A: word= "A";break;
                case B: word= "B";break;
                case BE: word= "B+E";break;
                case C: word= "C";break;
                case CE: word= "C+E";break;
                case D: word= "D";break;
                case DE: word= "D+E";break;
            }
            return word;
        }
    
    }
    private RijbewijsTypes rijbewijsType;
    public Rijbewijs(RijbewijsTypes rijbewijsType){
        this.rijbewijsType=rijbewijsType;
    }
    
    @Override
    public String toString(){
        return rijbewijsType+"";
    }
    
  
    
}*/
