/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;
//static import van Rijbewijs om test te laten werken. zodat hij A en B,..herkent. Bij mij geen problemen? want in de test is : import static be.vdab.util.mens.Rijbewijs.*; opgenomen
//wat is die static import features?
/**
 *
 * @author Katja
 */
public class Mens implements Comparable<Mens> , Serializable{
    
    private final String naam;
    private final Set<Rijbewijs> rijbewijzen=new TreeSet<>();//geen dubbels in

    public Mens(String naam,Rijbewijs ... rijbewijs ){
        this.naam=naam;
        rijbewijzen.addAll(Arrays.asList(rijbewijs));
    }
    
    /**
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }
    public Rijbewijs[] getRijbewijs(){
        return  (Rijbewijs[]) rijbewijzen.toArray(new Rijbewijs[0]);
    }
    public int hashCode(){
       return this.toString().hashCode();
    }
    
    public String toString(){
        StringBuffer buffer=new StringBuffer(naam);
        boolean minstensEen=false;
        //List<Rijbewijs> gesorteerdeRijbewijzen=new ArrayList(rijbewijzen);
        //Collections.sort(gesorteerdeRijbewijzen);//alfabetisch //hoe volgens enumvolgorde sorteren?
//        Iterator<Rijbewijs> it=gesorteerdeRijbewijzen.iterator();
//        if(it.hasNext()){
//            buffer.append("(").append(it.next());
//            minstensEen=true;
//        }
//        while (it.hasNext()){
//            buffer.append(", "+it.next());
//        }
//        if(minstensEen){
//            buffer.append(")");
//        }
        if(! rijbewijzen.isEmpty()){
            buffer.append("(")
                  .append(StringUtils.join(rijbewijzen,", "))
                  .append(")");
        }
        return buffer.toString();
    }
    
    public boolean equals(Object o){
        if(o!=null && ((Mens)o).naam.equals(naam) && heeftZelfdeRijbewijzen((Mens)o)){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean heeftZelfdeRijbewijzen(Mens mens){
        return rijbewijzen.equals(mens.rijbewijzen);//vergelijking van sets kijkt naar de inhoud
    }

    @Override
    public int compareTo(Mens o) {//wat doen bij gelijke namen en verschillende rijbewijzen? volgens compareTo zijn die mensen gelijk, maar via equals niet -->probleem (wel ok volgens testfiles)
        return naam.compareTo(((Mens)o).naam);
        
    }

   
    
    
}
