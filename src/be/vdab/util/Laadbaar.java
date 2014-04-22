/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

/**
 *
 * @author katja.radelicki
 */
public interface Laadbaar {
    
    //public Volume laadvolume; alleen methods toegelaten in een interface <-> abstracte klasse
    
    public Volume getLaadvolume();
    public void setLaadvolume(Volume laadvolume);
    
}
