/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author katja.radelicki
 */
public class Programma {
    
    public static void main (String [] args){
        SortedSet<Voertuig> voertuigen=new TreeSet<Voertuig>();
        Datum datum=null;
        Volume volumePickup=null;
        Volume volumeVrachtwagen=null;
        try{
        datum=new Datum(21, 11, 2012);
        }
        catch(DatumException de){
            System.out.println(de.getMessage());
        }
        Mens bestuurder_BC=new Mens("Albert",Rijbewijs.B,Rijbewijs.C);
        Mens inzittende_A=new Mens("Karolien",Rijbewijs.A);
        Mens inzittende=new Mens("Zora");
        Mens inzittende_B=new Mens("David",Rijbewijs.B);
        Mens inzittende_DDE=new Mens("Evert",Rijbewijs.D,Rijbewijs.DE);
        Mens bestuurder_DE=new Mens("Boris",Rijbewijs.DE);
        Personenwagen wagen1Opel=new Personenwagen("Opel", datum, 250000, 4, Color.blue, bestuurder_BC, inzittende_A, inzittende);
        Personenwagen wagen2=new Personenwagen("Mazda", datum, 27000, 5, Color.blue, bestuurder_BC, inzittende_A);
        
        try{
            volumePickup=new Volume(3, 3, 2, Maat.meter);
            volumeVrachtwagen=new Volume(3, 6, 2, Maat.meter);
        }catch(VolumeException ve){
            System.out.println(ve.getMessage());
        }
        
        Pickup pickup1Opel=new Pickup("Opel", datum, 400000, 5, Color.RED, volumePickup, bestuurder_BC,  inzittende_B,inzittende_DDE,inzittende_A);
        Pickup pickup2=new Pickup("Toyota", datum, 39000, 4, Color.blue, volumePickup, bestuurder_BC);
        Vrachtwagen vrachtwagen1=new Vrachtwagen("BMW", datum, 90000, 2, volumeVrachtwagen, 3000, 6, bestuurder_BC, inzittende_A);
        Vrachtwagen vrachtwagen2=new Vrachtwagen("Mercedes", datum, 120000, 3, volumeVrachtwagen, 2000, 8, bestuurder_BC, inzittende);
        voertuigen.add(wagen1Opel); //gesorteerd op nummerplaat
        voertuigen.add(wagen2);
        voertuigen.add(pickup1Opel);
        voertuigen.add(pickup2);
        voertuigen.add(vrachtwagen1);
        voertuigen.add(vrachtwagen2);
        
        print(voertuigen);
        
        /*
        //copy manier1
      //copy (niet nodig dadelijk naar list omzetten, maar dan zit het niet in een SortedSet, oplossing: zelf copy uitvoeren, maar niet enkel referenties
        Set<Voertuig> voertuigen2=null;//=new TreeSet<Voertuig>(voertuigen); //new TreeSet<Voertuig>( org.apache.commons.collections.ComparatorUtils.reversedComparator( Voertuig.getAankoopprijsComparator()));
        //transformeren naar List, ook een copy
        List<Voertuig> lijst=new ArrayList<Voertuig>(voertuigen);//niet voertuigen 2
        //sort
          Collections.sort(lijst, org.apache.commons.collections.ComparatorUtils.reversedComparator( Voertuig.getAankoopprijsComparator()));
          print(lijst);
        */
        Set<Voertuig> voertuigen2=new TreeSet<Voertuig>( org.apache.commons.collections.ComparatorUtils.reversedComparator( Voertuig.getAankoopprijsComparator()));
        voertuigen2.addAll(voertuigen);
        print(voertuigen2);
        
          //Copy manier 2: beter
          Set<Voertuig> voertuigen3=new TreeSet<Voertuig>( Voertuig.getMerkComparator());
          voertuigen3.addAll(voertuigen);//deep copy of niet , staat niet in de api. Maakt het uit hier? wanneer wel?als je de vrachtwagens gaat wijzigen en je wil dat die alleen in de copy gewijzigd zijn dan deep copy nodig
          print(voertuigen3);
          
          try{
          schrijweg((TreeSet) voertuigen3,"wagenpark.ser");
          }catch(FileNotFoundException fnfe){
              System.out.println(fnfe.getMessage());
          }catch(IOException ioe){
               System.out.println(ioe.getMessage());
          }
          TreeSet<Voertuig> voertuigen4=lees("wagenpark.ser");
          System.out.println("Voertuigen4");
          print(voertuigen4);
          
        
    }
    
    private static void print(java.util.Collection<Voertuig> verzameling){
        System.out.println("Verzameling: ");
        for(Voertuig voertuig:verzameling){
            
            System.out.println( voertuig.toString());
        }
    }
    
    private static void schrijweg(TreeSet<Voertuig> verzameling, String file) throws FileNotFoundException,IOException{
        
        FileOutputStream outputStream=new FileOutputStream(file);
        ObjectOutputStream oos=   new ObjectOutputStream(outputStream);
        oos.writeObject(verzameling);//TreeSet implements Serializable, superklasse AbstractSet implements NIET Serializable, maar heeft een no-args constructor-->dus serialiseerbaar
        oos.close();
        //voertuig serialiseerbaar maken want die zit in de treeSet, maar de comparator als innerclasses moeten ook serialiseerbaar gemaaakt worden!
        
    }
    
    private static TreeSet<Voertuig> lees (String file){//gekozen voor treeSet , zo duidelijk dat er een TreeSet uitkomt (die serialiseerbaar is)
        TreeSet<Voertuig> voertuigen=null;
        ObjectInputStream ois=null;
        try{
            FileInputStream fis=new FileInputStream(file);
            ois=new ObjectInputStream(fis);
            voertuigen=  (TreeSet<Voertuig>) ois.readObject();//je moet exact weten wat er is ingegaan, anders kan je object niet casten naar juiste klasse!?
        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }catch( IOException ioe){
            System.out.println(ioe.getMessage());
        }catch(ClassNotFoundException cnfe){
            System.out.println(cnfe.getMessage());
        }finally{
            try{
                ois.close();
            }catch(IOException ioe){
                System.err.println(" ioe.getMessage()");
            }
        }
        
        return voertuigen;
    }
    
}
