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
public enum Maat implements Serializable{
    centimeter(1),decimeter(10),meter(100);
    
    private final int verhouding;
    private Maat(int verhouding){
        this.verhouding=verhouding;
    }
    public long get3DVerhouding(){
        return (long)verhouding*verhouding*verhouding;
    }
    
//    @Override
//    public String toString(){
//        String woord=null;
//        switch(this){
//            case centimeter: woord="centimeter"; break;
//            case decimeter: woord="decimeter"; break;
//            case meter: woord="meter"; break; 
//        }
//        return woord;
//    }
}
