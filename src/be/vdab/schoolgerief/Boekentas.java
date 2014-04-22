/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;

/**
 *
 * @author katja.radelicki
 */
public class Boekentas implements Laadbaar, Serializable{//moet interface Laadbaar ook Serializable zijn? blijkbaar niet

    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur,Volume laadvolume ) {
        if(kleur!=null && laadvolume!=null){
            this.laadvolume = laadvolume;
            this.kleur = kleur;
        }else throw new IllegalArgumentException("kleur of laadvolume is null");
    }
    
    
    
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        if(laadvolume!=null){
            this.laadvolume=laadvolume;
        }else throw new IllegalArgumentException("laadvolume is null");
    }

    /**
     * @return the kleur
     */
    public String getKleur() {
        return kleur;
    }

    /**
     * @param kleur the kleur to set
     */
    public void setKleur(String kleur) {
        if(kleur!=null){
            this.kleur = kleur;
        }else  throw new IllegalArgumentException("kleur is null");
    }
    
    @Override
    public String toString(){
        return "boekentas "+kleur+ " "+laadvolume;
    }
    
    @Override
    public boolean equals(Object o){
        if(o!=null){
            if((kleur.equals(((Boekentas)o).kleur) && (laadvolume.equals(((Boekentas)o).laadvolume)))){
                return true;
            }else return false;
        }else return false;
    }
    
    @Override
    public int hashCode(){
        return (kleur.hashCode()+laadvolume.toString().hashCode());
    }
    
}
