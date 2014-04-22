/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Katja
 */
//
public abstract class Voertuig implements Comparable<Voertuig>, Serializable {

    private final Nummerplaat nummerplaat = DIV.getInstance().getNummerplaat();
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private final int zitplaatsen;
    private Mens bestuurder;
    private final Set<Mens> ingezetenen = new TreeSet<>();//bestuurder exclusief
    private static final Rijbewijs[] geldigeRijbewijzen = {Rijbewijs.B, Rijbewijs.BE};//had beter een set geweest//enum Rijbewijs moet niet serialiseerbaar zijn? of is dat automatisch?
    protected final static int MAX_ZITPLAATSEN = 7;//(niet final anders kan je in de subclass de waarde niet meer veranderen)
    //een abstract voertuig zou eigenlijk geen MAX_ZITPLAATSEN mogen hebben, maar dan geven testen errors

    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen, Mens bestuurder, Mens... inzittenden) throws MensException {

//        int aantalBestuurders = 0;
//        if (bestuurder != null && aantalZitplaatsen > 0) {
//            aantalBestuurders = 1;
//        } else {
//            throw new IllegalArgumentException("er is geen bestuurder");
//        }
//
//        if (aantalZitplaatsen > getMAX_ZITPLAATSEN()) {
//
//            throw new IllegalArgumentException("te veel zitplaatsen. Maximum is " + getMAX_ZITPLAATSEN());
//        }
//        if (ArrayUtils.contains(inzittenden, bestuurder)) {//bestuurder zit er dus in als bestuurder en als inzittende, dus 1 ervan niet meetellen
//            aantalBestuurders = 0;
//        }
//        if (inzittenden.length + aantalBestuurders > aantalZitplaatsen) {
//            throw new MensException("te veel inzittenden. Maximum " + aantalZitplaatsen + " zitplaatsen");
//        }
//        if (!heeftCorrectRijbewijsVoorDitVoertuig(bestuurder)) {
//            throw new MensException("bestuurder heeft het correct rijbewijs niet. Bestuurder: " + bestuurder);
//        }
//        this.merk = merk;
//        this.datumEersteIngebruikname = datumEersteIngebruikname;
//        this.aankoopprijs = aankoopprijs;
//        zitplaatsen = aantalZitplaatsen;
//        this.bestuurder = bestuurder;
//        //this.inzittenden.add(bestuurder);
//        for (Mens gast : inzittenden) {
//            this.ingezetenen.add(gast);
//        }
        //bestuurder bij inzittenden zetten is voor de meeste methodes eenvoudiger
         if(bestuurder==null || aantalZitplaatsen<=0){
             throw new IllegalArgumentException("er is geen bestuurder");
         }
         if(aantalZitplaatsen>getMAX_ZITPLAATSEN()){
             throw new IllegalArgumentException("te veel zitplaatsen. Maximum is "+getMAX_ZITPLAATSEN());
         }
         if(!heeftCorrectRijbewijsVoorDitVoertuig(bestuurder)){
             throw new MensException("bestuurder heeft het correct rijbewijs niet. Bestuurder: "+bestuurder);
         }
         ingezetenen.addAll(Arrays.asList(inzittenden));
         ingezetenen.add(bestuurder);
         if(ingezetenen.size()>aantalZitplaatsen) {
             throw new MensException("te veel inzittenden. Maximum "+aantalZitplaatsen+" zitplaatsen");
         }
         this.merk=merk;
         this.datumEersteIngebruikname=datumEersteIngebruikname;
         this.aankoopprijs=aankoopprijs;
         this.zitplaatsen=aantalZitplaatsen;
         this.bestuurder=bestuurder;
    }

    protected int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }

    /**
     * @return the nummerplaat
     */
    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }

    /**
     * @return the merk
     */
    public String getMerk() {
        return merk;
    }

    /**
     * @return the datumEersteInGebruikName
     */
    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }

    /**
     * @return the aankoopprijs
     */
    public int getAankoopprijs() {
        return aankoopprijs;
    }

    /**
     * @return the zitplaatsen
     */
    public int getZitplaatsen() {
        return zitplaatsen;
    }

    /**
     * @return the bestuurder
     */
    public Mens getBestuurder() {
        return bestuurder;
    }

    /**
     * @return the inzittenden
     */
    public Set<Mens> getIngezetenen() {//inclusief bestuurder  
        //ingezetenen.add(bestuurder);// maar niet definitief toevoegen
        //volgens de test wordt verwacht dat deze er alfabetisch gesorteerd uitkomen
//        Set<Mens> copyIngezetenen= new TreeSet<Mens>( ingezetenen);
//        copyIngezetenen.add(bestuurder);
//        return copyIngezetenen;
        return Collections.unmodifiableSet(ingezetenen);
    }

    public void setBestuurder(Mens toekomstigeBestuurder) {//oude bestuurder wordt dan inzittende
        //je moet een inzittende bestuurder maken en dan wordt de bestuurder inzittende. Een wissel-->als er net genoeg plaats is,moet je kunnen wisselen dus mag je niet "volzet" krijgen
        //moet geldig rijbewijs hebben
        Mens oudeBestuurder = this.bestuurder;
        if (toekomstigeBestuurder != null) {
            if (heeftCorrectRijbewijsVoorDitVoertuig(toekomstigeBestuurder)) {
                if (!isIngezetene(toekomstigeBestuurder)) {
                    addIngezetene(toekomstigeBestuurder);
                }
                bestuurder = toekomstigeBestuurder;
            } else {
                throw new MensException("bestuurder heeft geen correct rijbewijs. Bestuurder: " + toekomstigeBestuurder);
            }
        } else {
            throw new NullPointerException("de bestuurder is NULL");
        }
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    /**
     * @param datumEersteIngebruikname the datumEersteIngebruikname to set
     */
    public void setDatumEersteIngebruikname(Datum datumEersteIngebruikname) {
        this.datumEersteIngebruikname = datumEersteIngebruikname;
    }

    /**
     * @param aankoopprijs the aankoopprijs to set
     */
    public void setAankoopprijs(int aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    private boolean heeftCorrectRijbewijsVoorDitVoertuig(Mens mens) {
        //B en BE zijn correct
//         Rijbewijs[] bestuurdersRijbewijzen=mens.getRijbewijs();
//         boolean correct=false;
//         for(Rijbewijs bestuurdersRijbewijs:bestuurdersRijbewijzen){
//             if(Arrays.asList(geldigeRijbewijzen).contains(bestuurdersRijbewijs)) correct=true;
//         }
//         return correct;
        return CollectionUtils.containsAny(Arrays.asList(geldigeRijbewijzen), Arrays.asList(mens.getRijbewijs()));
    }

    public void addIngezetene(Mens extraInzittende) {
        //controle voldoende plaatsen, mag er nog niet inzitten en mag niet null zijn
        if (extraInzittende != null) {//geen exceptie throwen als extraInzittende er al in zit als bestuurder
            if (!ingezetenen.contains(extraInzittende)) {
                if (heeftNogMinstensEenPlaats()) {

                    ingezetenen.add(extraInzittende);//in een set kunnen geen dubbels zitten, bestuurder wel nog apart testen

                } else {
                    throw new MensException("toevoegen gaat niet, er is te weinig plaats");
                }
            } else {
                //zit hij er al in bij de inzittenden, dan niets doen
            }
        } else {
            throw new NullPointerException("je probeert NULL toe te voegen");
        }
    }

    //wordt niet getest in testfiles
//    private boolean removeIngezetene(Mens weggaandeInzittende) {
//        if (weggaandeInzittende.equals(bestuurder)) {
//            throw new MensException("Bestuurder kan het voertuig niet verlaten");
//        } else {
//            return ingezetenen.remove(weggaandeInzittende);
//        }
//    }

    public Set<Mens> getIngezeteneExclusiefBestuurder() {//als je wil dat dit in de testfile ter beschikking is public zetten. Kan je dan geen private methods testen?
        //FRANK: normaal zit de bestuurder bij de inzittenden.
        Set<Mens> copy = new TreeSet<>(ingezetenen);
        copy.remove(bestuurder);
        return Collections.unmodifiableSet(copy); //eigenlijk al veilig zonder unmodifiableSet want je werkt met een kopie
    }

    public boolean isIngezetene(Mens mens) {
        return ingezetenen.contains(mens);
    }

    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return Arrays.copyOf(geldigeRijbewijzen, geldigeRijbewijzen.length);
    }

    private boolean heeftNogMinstensEenPlaats() {
        return ingezetenen.size() < zitplaatsen;
    }

    @Override
    public String toString() {
        //String.format("%s %s %s %s %s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)" + " " + "[Anita, Bert]");
        StringBuffer inzittendeStringBuffer = new StringBuffer();
        if(! getIngezeteneExclusiefBestuurder().isEmpty()){
            inzittendeStringBuffer.append(" [")
                                  .append(StringUtils.join(getIngezeteneExclusiefBestuurder(), ", "))
                                  .append("]");
        }
//            bestuurderEnInzittendeStringBuffer.append(bestuurder);
//        //namen inzittende moeten gesorteerd in String terecht komen
//        List<Mens> gesorteerdeIngezetenen = new ArrayList(ingezetenen);
//        Collections.sort(gesorteerdeIngezetenen);//compareTo van mens is op naam, dus ok, natural ordening is dan op alfabetische naam
//
//        boolean minstensEen = false;
//        Iterator<Mens> it = gesorteerdeIngezetenen.iterator();
//        if (it.hasNext()) {
//            bestuurderEnInzittendeStringBuffer.append(" [").append(it.next().getNaam());
//            minstensEen = true;
//        }
//        while (it.hasNext()) {
//            bestuurderEnInzittendeStringBuffer.append(", " + it.next().getNaam());
//        }
//        if (minstensEen) {
//            bestuurderEnInzittendeStringBuffer.append("]");
//        }
//
//        
        String tekst = String.format("%s %s %s %s %s%s", nummerplaat, merk, datumEersteIngebruikname, aankoopprijs, bestuurder, inzittendeStringBuffer.toString());
        return tekst;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            return nummerplaat.equals(((Voertuig) o).nummerplaat);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        //als equals true geeft, moet hashcode ook gelijk zijn. Dus hashcode niet op heel het voertuig toepassen
        return nummerplaat.hashCode();
    }

    @Override
    public int compareTo(Voertuig v) {
        return nummerplaat.compareTo(v.nummerplaat);
    }

    public interface MerkComparator extends Comparator<Voertuig>, Serializable {
    }

    public static MerkComparator getMerkComparator() {
        return new MerkComparator() {
            @Override
            public int compare(Voertuig v1, Voertuig v2) {
                if (v1 != null && v2 != null) {
                    return (v1.merk).compareTo(v2.merk); //niet consistent met equals:ok?
                } else {
                    throw new NullPointerException("één van de voertuigen is NULL");
                }
            }
        };
    }

    public interface PrijsComparator extends Comparator<Voertuig>, Serializable {
    }

    public static PrijsComparator getAankoopprijsComparator() {
        return new PrijsComparator() {
            @Override
            public int compare(Voertuig v1, Voertuig v2) {
                if (v1 != null && v2 != null) {
                    return v1.aankoopprijs-v2.aankoopprijs;
                } else {
                    throw new NullPointerException("één van de voertuigen is NULL");
                }
            }
        };
    }

}
