/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;
import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.VolumeException;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author frank.roelants
 */
public class PersonenwagenTest implements MensFactorInTest{

    private final Datum datum;
    private final Datum datum2;
    private final int GETAL4=4;
    private final int ZITPLAATSEN_4=4;
    private final String datumString;
    private final String GETAL4_STRING;
    private final int ZITPLAATSEN5=5;
    private final Color KLEUR=Color.PINK;

    public PersonenwagenTest() throws DatumException {
        datum = new Datum(1, 2, 3456);
        datum2 = new Datum(1, 2, 2134);
        datumString = "01/02/3456";
        GETAL4_STRING=Integer.toString(GETAL4);
    }

    @Test
    public void test_not_null() {
        Personenwagen pw = new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertNotNull(pw);
    }

    @Test
    public void test_getNummerplaat() {
        assertNotNull(new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE).getNummerplaat());
    }

    @Test
    public void test_getMerk() {
        assertEquals(new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE).getMerk(), "a");
    }

    @Test
    public void test_getDatum() {
        assertEquals(new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE).getDatumEersteIngebruikname(), datum);
    }
    @Test
    public void test_getZitplaatsen() {
        assertEquals(new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE).getZitplaatsen(), GETAL4);
    }

    @Test
    public void test_getAankoopprijs() {
        assertEquals(new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE).getAankoopprijs(), 100,GETAL4);
    }

    @Test
    public void test_setMerk() {
        Personenwagen tv = new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        tv.setMerk("b");
        assertFalse(tv.getMerk().equals("a"));
        assertTrue(tv.getMerk().equals("b"));
    }

    @Test
    public void test_setDatum() throws DatumException {
        Personenwagen tv = new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        tv.setDatumEersteIngebruikname(datum2);
        assertFalse(tv.getDatumEersteIngebruikname().equals(datum));
        assertTrue(tv.getDatumEersteIngebruikname().equals(datum2));
    }

    @Test
    public void test_setAankoopprijs() {
        Personenwagen tv = new Personenwagen("a", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        tv.setAankoopprijs(200);
        assertFalse(tv.getAankoopprijs() == 100);
        assertTrue(tv.getAankoopprijs() == 200);
    }

    @Test
    public void test_equals_zichzelf() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertTrue(v1.equals(v1));
    }
    @Test
    public void test_polyforme_equals_zichzelf() throws DatumException {
        Personenwagen p1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Voertuig v1 = p1;
        assertTrue(p1.equals(v1));
        assertTrue(v1.equals(p1));
    }

    @Test
    public void test_not_equals_null() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertFalse(v1.equals(null));
    }

    @Test
    public void test_equals_Voertuig() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    }

    @Test
    public void test_not_equals_Voertuig() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("otto", datum2, 14300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
        assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
    }

    @Test
    public void test_equals_Symetie() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("auto", datum, 1830,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
        assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
    }

    @Test
    public void test_equals_trasitief() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v3 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
        assertEquals(v2.equals(v3), v2.getNummerplaat().equals(v3.getNummerplaat()));
        assertEquals(v1.equals(v3), v1.getNummerplaat().equals(v3.getNummerplaat()));
    }

    @Test
    public void test_hashCode_equals() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertEquals((v1.hashCode() == v2.hashCode()), v1.equals(v2));
    }

    @Test
    public void test_toString(){
        Personenwagen pw = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        String nummerplaatString = pw.getNummerplaat().getPlaat();
        String merkString = pw.getMerk();
        String aankoopprijsString = Integer.toString(pw.getAankoopprijs());

        String toString = String.format("%s %s %s %s %s %s",nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)",GETAL4_STRING);
        assertEquals( toString, pw.toString());
    }
    
    @Test
    public void test_getKleur(){
        assertEquals(Color.ORANGE, new Personenwagen("a", datum, 18300, 1, Color.ORANGE, BESTUURDER_BBECCE).getKleur());
    }
    @Test
    public void test_setKleur(){
        Personenwagen pw = new Personenwagen("a", datum, 18300, 1, Color.ORANGE, BESTUURDER_BBECCE);
        assertEquals(Color.ORANGE, pw.getKleur());
        pw.setKleur(Color.WHITE);
        assertEquals(Color.WHITE, pw.getKleur());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_0_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, 0, KLEUR, BESTUURDER_BBECCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Min_1_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, -1, KLEUR, BESTUURDER_BBECCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_MININT_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, Integer.MIN_VALUE, KLEUR, BESTUURDER_BBECCE));
    }
    
    @Test
    public void test_8_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, 8, KLEUR, BESTUURDER_BBECCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_9_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, 9, KLEUR, BESTUURDER_BBECCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_10_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, 10, KLEUR, BESTUURDER_BBECCE));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_MAXINT_Zitplaatsen() {
        assertNotNull(new Personenwagen("a", datum, 18300, Integer.MAX_VALUE, KLEUR, BESTUURDER_BBECCE));
    }
    
    @Test
    public void test_compareTo() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("otto", datum2, 14300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertEquals(v1.compareTo(v2) < 0, v1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
        assertEquals(v1.compareTo(v1) == 0, v1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
        assertEquals(v2.compareTo(v1) > 0, v2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
    }

    @Test
    public void test_polyforme_compareTo() throws DatumException {
        Personenwagen p1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen p2 = new Personenwagen("otto", datum2, 14300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Voertuig v1=p1;
        Voertuig v2=p2;
        assertEquals(p1.compareTo(v2) < 0, p1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
        assertEquals(p1.compareTo(v1) == 0, p1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
        assertEquals(p2.compareTo(v1) > 0, p2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
    }

    @Test
    public void test_MerkComparator() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("bus", datum2, 14300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertNotNull(Personenwagen.getMerkComparator());
        assertEquals(Personenwagen.getMerkComparator().compare(v1,v2) < 0, v1.getMerk().compareTo(v2.getMerk()) < 0);
        assertEquals(Personenwagen.getMerkComparator().compare(v1,v1) == 0, v1.getMerk().compareTo(v1.getMerk()) == 0);
        assertEquals(Personenwagen.getMerkComparator().compare(v2,v1) > 0, v2.getMerk().compareTo(v1.getMerk()) > 0);
    }

    @Test
    public void test_AankoopComparator() throws DatumException {
        Personenwagen v1 = new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        Personenwagen v2 = new Personenwagen("bus", datum2, 14300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
        assertNotNull(Personenwagen.getAankoopprijsComparator());
        assertEquals(Personenwagen.getAankoopprijsComparator().compare(v1,v2) < 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v2.getAankoopprijs())) < 0);
        assertEquals(Personenwagen.getAankoopprijsComparator().compare(v1,v1) == 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) == 0);
        assertEquals(Personenwagen.getAankoopprijsComparator().compare(v2,v1) > 0, new Integer(v2.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) > 0);
    }

    @Test
    public void test_Personenwagen_is_it_Serializable() throws IOException, VolumeException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(new Personenwagen("auto", datum, 18300,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE));
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }

    @Test
    public void test_Personenwagen_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException, VolumeException {
        final String MERK = "auto";
        final int PRIJS = 2400;
        final Personenwagen origineel = new Personenwagen(MERK, datum, PRIJS,ZITPLAATSEN_4, KLEUR,BESTUURDER_BBECCE);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(origineel);
        oos.close();
        byte[] pickled = out.toByteArray();
        InputStream in = new ByteArrayInputStream(pickled);
        ObjectInputStream ois = new ObjectInputStream(in);
        Object o = ois.readObject();
        Personenwagen copy = (Personenwagen) o;
        assertEquals(origineel, copy);
        assertEquals(copy.getMerk(),MERK);
        assertTrue(copy.getAankoopprijs() == PRIJS);
        assertEquals(copy.getDatumEersteIngebruikname(),datum);
        assertTrue(copy.getZitplaatsen()== ZITPLAATSEN_4);
    }
}