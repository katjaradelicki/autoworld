/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen.div;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import static be.vdab.util.mens.Rijbewijs.B;
import static be.vdab.util.mens.Rijbewijs.BE;
import static be.vdab.util.mens.Rijbewijs.C;
import static be.vdab.util.mens.Rijbewijs.CE;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;


/**
 *
 * @author katja.radelicki
 */
public class Main {
    public static void main(String[] args){
        Nummerplaat eerstePlaat= DIV.getInstance().getNummerplaat();
        System.out.println(eerstePlaat);
        Nummerplaat tweedePlaat=DIV.getInstance().getNummerplaat();
        System.out.println(tweedePlaat);
        try{
            System.out.println("creatie van Datum(1,3,2000)  ");
        Datum date=new Datum(1,3,2000 );
        System.out.println(date);
        } catch (DatumException de){
           System.out.println( de.getMessage());
        }
        try{
       // Datum d1=new Datum(30,2,2014); //niet toelaten. GregorianCalendar doet daar dan ook geen check op!? (januari is gelijk aan 0)
        //Datum d2=new Datum(1,3,2014);
        //d1.compareTo(d2);//geeft 1: hoe kan dat? dan werkt compareToe vanGregorianCalender niet goed?-->omdat 30/2 niet bestaat en 2 dagen later is dan 28, dus eigenlijk 2/3 en ja dat is dus groter dan 1/3-->zorgen dat dat dus niet mogelijk is dan werkt compareTo wel goed (berekening wordt gedaan met omzetting naar milliseconden)
            System.out.println("creatie van Datum(29,2,1900)"); 
        Datum d1=new Datum(29,2,1900);
            System.out.println("creatie van Datum(1,3,2014)");
        Datum d2=new Datum(1,2,2014);
        d1.compareTo(d2);
        }catch(DatumException de){
            System.out.println(de.getMessage());
        }
        
        //Rijbewijs rijbewijs=new Rijbewijs(Rijbewijs.RijbewijsTypes.BE);
        //System.out.println("rijbewijstype: "+rijbewijs);
        System.out.println("Rijbewijs "+Rijbewijs.CE);
        
        Mens mensZonderRijbewijs=new Mens("Albert");
        Rijbewijs [] rijbewijzen= mensZonderRijbewijs.getRijbewijs();
        System.out.println("lengte "+rijbewijzen.length);//0
       // System.err.println("inhoud:  "+rijbewijzen[0]);//indexoutOfBound
        
        /*
        Mens bestuurder_AB=new Mens("Evi", Rijbewijs.A,Rijbewijs.B);
        Mens inzittende_A=new Mens("Koen",Rijbewijs.A);
        Mens inzittende_BE=new Mens("Evert",Rijbewijs.BE);
        try{
        Datum datum1=new Datum(20,11,2013);
        Datum datum = new Datum(1, 2, 3456);
         int AANTAL_INZITTENDEN = 3;
         Mens BESTUURDER_BBECCE = new Mens("Ammelie",B,BE,C,CE);
        //Voertuig voertuig=new Voertuig("nissan", datum1, 25000, 5, bestuurder_AB, inzittende_A,inzittende_BE);// kan niet want voertuig is abstract. Kan ik voertuig dan niet testen?(buiten met Junit dan)
        Voertuig tv = new Voertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);     
        System.out.println("Voertuig");
           // System.out.println(voertuig);
            System.out.println(tv);
        }catch (DatumException de){
            System.out.println("Fout"+de.getMessage());
        }
        */
        
        Datum datum1=null;
        Datum datum=null;
        Volume volume=null;
        Mens BESTUURDER_BBECCE = new Mens("Ammelie",B,BE,C,CE);
        Mens INZITTENDE_A=new Mens("Hans",Rijbewijs.A);
                try{
        datum1=new Datum(20,11,2013);
        datum = new Datum(1, 2, 3456);
         volume=new Volume(10, 10, 10, Maat.meter);
        }catch (DatumException de){
            System.out.println("Fout"+de.getMessage());
        }catch(VolumeException ve){
                    System.out.println("Fout: "+ve.getMessage());
        }
        
        
        Personenwagen wagen=new Personenwagen("ford", datum, 30000, 5, Color.BLUE, BESTUURDER_BBECCE, INZITTENDE_A);
        System.out.println("Personenwagen: "+wagen);
        Pickup pickup=new Pickup("opel", datum, 40000, 5, Color.blue,volume ,BESTUURDER_BBECCE, INZITTENDE_A);
        System.out.println("Pickup: "+pickup);
        
        Vrachtwagen vrachtwagen=new Vrachtwagen("Toyota", datum, 100000, 3, volume, 2000, 6, BESTUURDER_BBECCE, INZITTENDE_A);//aantal zitplaatsen is groter dan MaX_zitplaatsen --> moet illegalException geven.
        System.out.println("Vrachtwagen: "+vrachtwagen);
        
        
    }
}
