/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author katja.radelicki
 */
public class Personenwagen extends Voertuig implements Serializable {//implementeerd sowieso Serializable doordat Voertuig dat doet
    
    private Color kleur;
    private static final int MAX_ZITPLAATSEN=8;

    public Personenwagen(String merk, Datum datumEersteIngebruikname,int aankoopprijs,int aantalZitplaatsen,Color kleur,Mens bestuurder,Mens... inzittenden){
        super(merk, datumEersteIngebruikname, aankoopprijs, aantalZitplaatsen, bestuurder, inzittenden);
        //Voertuig.MAX_ZITPLAATSEN=8;
        this.kleur=kleur;
    }
    protected int getMAX_ZITPLAATSEN(){
         return MAX_ZITPLAATSEN;
     }
    
    /**
     * @return the kleur
     */
    public Color getKleur() {
        return kleur;
    }

    /**
     * @param kleur the kleur to set
     */
    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
    
    @Override
    public String toString(){
        //String zin=super.toString()+" "+ getZitplaatsen();//aantal zitplaatsen? je zou denken iets met kleur want dat is specifiek voor personenwagen
                                                                             //getZitplaatsen() van die int moet je een string maken anders krijg je later problemen. Werd een getal niet automatisch naar een String omgezet bij concatinatie?
        return String.format("%s %d",super.toString(),getZitplaatsen());
    }
    
    
}
