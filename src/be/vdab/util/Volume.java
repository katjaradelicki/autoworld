/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author katja.radelicki
 */
public class Volume implements Serializable, Comparable<Volume>{
    private final int hoogte;
    private final int breedte;
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if(maat!=null){
            if(hoogte >=0 && breedte>=0 && diepte>= 0){
                this.hoogte = hoogte;
                this.breedte = breedte;
                this.diepte = diepte;
                this.maat = maat;
            }else
                throw new VolumeException("negatieve maten gaat niet");
        }else
            throw new NullPointerException("maat is null");
    }
    public long getVolume(){
        long volume=getHoogte()*getBreedte()*getDiepte()*maat.get3DVerhouding();
        return volume;
    }
    
    
    
    @Override
    public String toString(){
        //1(h)x2(b)x1903(d) centimeter
        return getHoogte()+"(h)x"+getBreedte()+"(b)x"+getDiepte()+"(d) "+getMaat();
    }
    
    @Override
    public boolean equals(Object o){
        if(o!=null){
            Volume volume=(Volume)o;
            return getHoogte()==volume.getHoogte()   &&
                   getBreedte()==volume.getBreedte() &&
                   getDiepte()== volume.getDiepte()  &&
                   getMaat().equals(volume.getMaat());//toString() is niet nodig//werkt equals van maat zoals het hoort zonder in de enum te overschrijven? maat.equals(volume.maat)?
        }else return false;
    }
    
    @Override
    public int compareTo(Volume v){
        if (v!=null){
            return new Long(getVolume()).compareTo(v.getVolume());
        }else throw new NullPointerException("parameter volume is NULL");
    }
    
    @Override
    public int hashCode(){
        Long volume=new Long(getVolume());
        return volume.hashCode();
    }

    /**
     * @return the hoogte
     */
    public int getHoogte() {
        return hoogte;
    }

    /**
     * @return the breedte
     */
    public int getBreedte() {
        return breedte;
    }

    /**
     * @return the diepte
     */
    public int getDiepte() {
        return diepte;
    }

    /**
     * @return the maat
     */
    public Maat getMaat() {
        return maat;
    }
    
}
