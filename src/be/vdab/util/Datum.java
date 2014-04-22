/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author katja.radelicki
 */
public class Datum implements Serializable, Comparable<Datum> {

    private final int dag;
    private final int maand;
    private final int jaar;
    private static final int[] langeMaanden = {1, 3, 5, 7, 8, 10, 12};//31 dagen
    private static final int[] korteMaanden = {4, 6, 9, 11};//30 dagen

    public Datum(int dag, int maand, int jaar) throws DatumException {

        if (!isCorrecteDatum(dag, maand, jaar)) {
            throw new DatumException("datum moet van formaat zijn  dag: 0-31, maand: 0-12, jaar: 1583-4099");
        } else {
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        }

    }

    /**
     * @return the dag
     */
    public int getDag() {
        return dag;
    }

    /**
     * @return the maand
     */
    public int getMaand() {
        return maand;
    }

    /**
     * @return the jaar
     */
    public int getJaar() {
        return jaar;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%4d", dag, maand, jaar);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && dag == ((Datum) o).dag && maand == ((Datum) o).maand && jaar == ((Datum) o).jaar) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int getal = dag + maand + jaar;
        int hashCode = getal % 31;
        return hashCode;
    }

    @Override
    public int compareTo(Datum d) {
        Calendar datum1 = new GregorianCalendar(jaar, maand - 1, dag);
        Calendar datum2 = new GregorianCalendar(d.jaar, d.maand - 1, d.dag);
        //System.out.println("compareTo: " + datum1.compareTo(datum2));
        return datum1.compareTo(datum2);

    }

    private boolean isSchrikkelJaar(int jaar) {
        //bij de eeuwjaren moet het jaartal deelbaar zijn door 400. zo is 1900 geen schrikkeljaar (ookal deelbaar door 4) en 1600 wel een schrikkeljaar!
        if (jaar % 100 == 0) {
            //eeuwjaar
            if (jaar % 400 == 0) {
                return true;
            } else {
                return false;
            }
        } else {//geen eeuwjaren

            if (jaar % 4 == 0) {
                return true;//29 dagen
            } else {
                return false;//28 dagen
            }
        }
        //return jaar%4==0 && jaar%100!=0 || jaar%400==0;
    }

    private boolean isKorteMaand(int maand) {
//        boolean zitErin = false;
//        
//        for (int i = 0; i < korteMaanden.length; i++) {
//            if (maand == korteMaanden[i]) {
//                zitErin = true;
//            }
//        }
//        return zitErin;
        return ArrayUtils.contains(korteMaanden, maand);
    }

    private boolean isLangeMaand(int maand) {
        boolean zitErin = false;
        for (int i = 0; i < langeMaanden.length; i++) {
            if (maand == langeMaanden[i]) {
                zitErin = true;
            }
        }
        return zitErin;
    }

    private boolean isHeleKorteMaand(int maand) {
//        if (maand == 2) {
//            return true;
//        } else {
//            return false;
//        }
        return maand==2;
    }

    private boolean isCorrecteDag(int dag, int maand, int jaar) {
        boolean isCorrect = false;
        if (isHeleKorteMaand(maand)) {
            if (isSchrikkelJaar(jaar)) {
                //dag moet tussen 1 en 29 liggen
                //System.out.println("is schrikkeljaar tussen 1 en 29");
                if (dag >= 1 && dag <= 29) {
                    isCorrect = true;
                }

            } else {
                //dag tussen 1 en 28
               //System.out.println("is geen schrikkeljaar tussen 1 en 28");
                if (dag >= 1 && dag <= 28) {
                    isCorrect = true;
                }
            }
        } else if (isKorteMaand(maand)) {
            if (dag >= 1 && dag <= 30) {
                isCorrect = true;
            }
        } else if (isLangeMaand(maand)) {
            if (dag >= 1 && dag <= 31) {
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    private boolean isCorrecteDatum(int dag, int maand, int jaar) {
        boolean isCorrect = false;
        if (isCorrecteDag(dag, maand, jaar) && jaar >= 1583 && jaar <= 4099 && maand >= 1 && maand <= 12) {
            isCorrect = true;
        }
        return isCorrect;
        //int dvdm[]={31,28,31,30,31,30,31,31,30,31,30,31};
        //return jaar > 1582 && jaar <4100 &&
        //       maand>0     && maand<13   &&
        //       dag>0       && dag<= (dvdm[maand-1]+((maand==2 &&(jaar%4==0 && jaar%100!=0 || jaar%400==0))?1:0));
    }

}
