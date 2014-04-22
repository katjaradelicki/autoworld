/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import java.awt.Color;

/**
 *
 * @author katja.radelicki
 */
public class Pickup extends Personenwagen implements Laadbaar {

    private Volume laadvolume;
    private static final int MAX_ZITPLAATSEN=5;

    public Pickup( String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen, Color kleur, Volume laadvolume,Mens bestuurder, Mens... inzittenden) {
        super(merk, datumEersteIngebruikname, aankoopprijs, aantalZitplaatsen, kleur, bestuurder, inzittenden);
        this.laadvolume = laadvolume;
        //MAX_ZITPLAATSEN=5;
    }
    
    protected int getMAX_ZITPLAATSEN(){
         return MAX_ZITPLAATSEN;
     }
    
    
    @Override
    public Volume getLaadvolume() {
       return laadvolume;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        this.laadvolume=laadvolume;
    }
    
    @Override 
    public String toString(){
        return (super.toString()+" "+laadvolume);
    } 
    
}
