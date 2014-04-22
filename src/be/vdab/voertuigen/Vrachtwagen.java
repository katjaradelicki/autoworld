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
import be.vdab.util.mens.MensException;

/**
 *
 * @author katja.radelicki
 */
public class Vrachtwagen extends Voertuig implements Laadbaar  {
    
    private Volume laadvolume;//alle klasses die Laadbaar implementeren moet je zelf attribuut laadvolume toevoegen? geen andere manier voor om dat niet telkens te hoeven herhalen? abstracteklasse met attribuut laadvolume bv? enige oplossing?
    private int maximaalToegelatenMassa;
    private int aantalAssen;
    private static final int MAX_ZITPLAATSEN=3;
    

    public Vrachtwagen( String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen,Volume laadvolume,int maximaalToegelatenMassa,  int aantalAssen, Mens bestuurder, Mens... inzittenden) throws MensException {
        
        super(merk, datumEersteIngebruikname, aankoopprijs, aantalZitplaatsen, bestuurder, inzittenden);
        this.laadvolume = laadvolume;
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
        
        
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

    /**
     * @return the maximaalToegelatenMassa
     */
    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }

    /**
     * @param maximaalToegelatenMassa the maximaalToegelatenMassa to set
     */
    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }

    /**
     * @return the aantalAssen
     */
    public int getAantalAssen() {
        return aantalAssen;
    }

    /**
     * @param aantalAssen the aantalAssen to set
     */
    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }
    
    public String toString(){
        //String.format("%s %s %s %s %s assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)", AA1_STRING, MTM1_STRING, VOLUME10_STRING);
    return super.toString()+" assen:"+Integer.toString(aantalAssen)+", maximaal toegelaten massa:"+Integer.toString( maximaalToegelatenMassa)+", laadvolume:"+laadvolume;
    }
    
}
