/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
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
public class VrachtwagenTest implements MensFactorInTest{

  private final Datum datum;
  private final Datum datum2;
  private final Volume VOLUME10;
  private final Volume VOLUME12;
  private final int MAXIAAM_TOEGELATEN_MASSA_1 = 7500;
  private final int MAXIAAM_TOEGELATEN_MASSA_2 = 13500;
  
  private final int AANTAL_ASSEN_2 = 2;
  private final int AANTAL_ASSEN_3 = 3;
  
  private final int AANKOOPPRIJS_1 = 36000;
  private final int AANKOOPPRIJS_2 = 46000;
  
  private final int ZITPLAATSEN = 3;
  
  private final String datumString;
  private final String VOLUME10_STRING;
  private final String MTM1_STRING;
  private final String AA1_STRING;

  public VrachtwagenTest() throws DatumException, VolumeException {
    datum = new Datum(1, 2, 3456);
    datum2 = new Datum(1, 2, 2134);
    VOLUME10 = new Volume(10, 10, 10, Maat.decimeter);
    VOLUME12 = new Volume(12, 12, 12, Maat.decimeter);
    datumString = "01/02/3456";
    VOLUME10_STRING = VOLUME10.toString();
    MTM1_STRING= Integer.toString(MAXIAAM_TOEGELATEN_MASSA_1);
    AA1_STRING= Integer.toString(AANTAL_ASSEN_2);
  }

  @Test
  public void test_not_null() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE));
  }
  
  @Test
  public void test_getNummerplaat() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getNummerplaat());
  }

  @Test
  public void test_getMerk() {
    assertEquals(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getMerk(), "a");
  }

  @Test
  public void test_getDatum() {
    assertEquals(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getDatumEersteIngebruikname(), datum);
  }

  @Test
  public void test_getAankoopprijs() {
    assertEquals(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getAankoopprijs(), AANKOOPPRIJS_1);
  }

  @Test
  public void test_Eén_Tot_Drie_Zitplaats() {
      for(int zitplaatsen = 1; zitplaatsen<=3; zitplaatsen++){
        assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, zitplaatsen, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE));
      }
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void test_4_Zitplaatsen() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, 4, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A, INGEZETENE_B, INGEZETENE_C));
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void test_5_Zitplaatsen() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, 5, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A, INGEZETENE_B, INGEZETENE_C));
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void test_MAXINT_Zitplaatsen() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, Integer.MAX_VALUE, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A, INGEZETENE_B, INGEZETENE_C));
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void test_0_Zitplaatsen() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, 0, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A, INGEZETENE_B, INGEZETENE_C));
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void test_Min_1_Zitplaatsen() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, -1, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A, INGEZETENE_B, INGEZETENE_C));
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void test_MININT_Zitplaatsen() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, Integer.MIN_VALUE, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A, INGEZETENE_B, INGEZETENE_C));
  }
  
  @Test
  public void test_twee_inzittende() {
    assertNotNull(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE,INGEZETENE_A));
  }
  
  @Test
  public void test_getLaadvolume() {
    assertEquals(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getLaadvolume(), VOLUME10);
  }

  @Test
  public void test_getMaximaalToegelatenMassa() {
    assertEquals(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getMaximaalToegelatenMassa(), MAXIAAM_TOEGELATEN_MASSA_1);
  }

  @Test
  public void test_getMaximaalAantalAssen() {
    assertEquals(new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE).getAantalAssen(), AANTAL_ASSEN_2);
  }

  @Test
  public void test_setMerk() {
    Vrachtwagen tv = new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    tv.setMerk("b");
    assertFalse(tv.getMerk().equals("a"));
    assertTrue(tv.getMerk().equals("b"));
  }

  @Test
  public void test_setDatum() throws DatumException {
    Vrachtwagen tv = new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    tv.setDatumEersteIngebruikname(datum2);
    assertFalse(tv.getDatumEersteIngebruikname().equals(datum));
    assertTrue(tv.getDatumEersteIngebruikname().equals(datum2));
  }

  @Test
  public void test_setAankoopprijs() {
    Vrachtwagen tv = new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    tv.setAankoopprijs(200);
    assertFalse(tv.getAankoopprijs() == 100);
    assertTrue(tv.getAankoopprijs() == 200);
  }

  @Test
  public void test_setLaadvolume() {
    Vrachtwagen p = new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    p.setLaadvolume(VOLUME12);
    assertFalse(p.getLaadvolume() == VOLUME10);
    assertTrue(p.getLaadvolume() == VOLUME12);
  }

  @Test
  public void test_setMaximaalToegelatenMassa() {
    Vrachtwagen p = new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    p.setMaximaalToegelatenMassa(MAXIAAM_TOEGELATEN_MASSA_2);
    assertFalse(p.getMaximaalToegelatenMassa() == MAXIAAM_TOEGELATEN_MASSA_1);
    assertTrue(p.getMaximaalToegelatenMassa() == MAXIAAM_TOEGELATEN_MASSA_2);
  }

  @Test
  public void test_setAantalAssen() {
    Vrachtwagen p = new Vrachtwagen("a", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    p.setAantalAssen(AANTAL_ASSEN_3);
    assertFalse(p.getAantalAssen() == AANTAL_ASSEN_2);
    assertTrue(p.getAantalAssen() == AANTAL_ASSEN_3);
  }

  @Test
  public void test_equals_zichzelf() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertTrue(v1.equals(v1));
  }

  @Test
  public void test_polyforme_equals_zichzelf() throws DatumException {
    Vrachtwagen p1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Voertuig v1 = p1;
    assertTrue(p1.equals(v1));
    assertTrue(v1.equals(p1));
  }

  @Test
    @SuppressWarnings("ObjectEqualsNull")
  public void test_not_equals_null() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertFalse(v1.equals(null));
  }

  @Test
  public void test_equals_Voertuig() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
  }

  @Test
  public void test_not_equals_Voertuig() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("otto", datum2, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
  }

  @Test
  public void test_equals_Symetie() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
  }

  @Test
  public void test_equals_trasitief() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v3 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    assertEquals(v2.equals(v3), v2.getNummerplaat().equals(v3.getNummerplaat()));
    assertEquals(v1.equals(v3), v1.getNummerplaat().equals(v3.getNummerplaat()));
  }

  @Test
  public void test_hashCode_equals() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertEquals((v1.hashCode() == v2.hashCode()), v1.equals(v2));
  }

  @Test
  public void test_toString() {
    Vrachtwagen pw = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    String nummerplaatString = pw.getNummerplaat().getPlaat();
    String merkString = pw.getMerk();
    String aankoopprijsString = Integer.toString(pw.getAankoopprijs());
    String toString = pw.toString();
    String toString2 = String.format("%s %s %s %s %s assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)", AA1_STRING, MTM1_STRING, VOLUME10_STRING);
    assertEquals(toString, toString2);

  }

  @Test
  public void test_compareTo() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("otto", datum2, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertEquals(v1.compareTo(v2) < 0, v1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
    assertEquals(v1.compareTo(v1) == 0, v1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
    assertEquals(v2.compareTo(v1) > 0, v2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
  }

  @Test
  public void test_polyforme_compareTo() throws DatumException {
    Vrachtwagen p1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen p2 = new Vrachtwagen("otto", datum2, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Voertuig v1 = p1;
    Voertuig v2 = p2;
    assertEquals(p1.compareTo(v2) < 0, p1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
    assertEquals(p1.compareTo(v1) == 0, p1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
    assertEquals(p2.compareTo(v1) > 0, p2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
  }

  @Test
  public void test_MerkComparator() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("bus", datum2, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertNotNull(Vrachtwagen.getMerkComparator());
    assertEquals(Vrachtwagen.getMerkComparator().compare(v1, v2) < 0, v1.getMerk().compareTo(v2.getMerk()) < 0);
    assertEquals(Vrachtwagen.getMerkComparator().compare(v1, v1) == 0, v1.getMerk().compareTo(v1.getMerk()) == 0);
    assertEquals(Vrachtwagen.getMerkComparator().compare(v2, v1) > 0, v2.getMerk().compareTo(v1.getMerk()) > 0);
  }

  @Test
  public void test_AankoopComparator() throws DatumException {
    Vrachtwagen v1 = new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    Vrachtwagen v2 = new Vrachtwagen("bus", datum2, AANKOOPPRIJS_1, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    assertNotNull(Vrachtwagen.getAankoopprijsComparator());
    assertEquals(Vrachtwagen.getAankoopprijsComparator().compare(v1, v2) < 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v2.getAankoopprijs())) < 0);
    assertEquals(Vrachtwagen.getAankoopprijsComparator().compare(v1, v1) == 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) == 0);
    assertEquals(Vrachtwagen.getAankoopprijsComparator().compare(v2, v1) > 0, new Integer(v2.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) > 0);
  }

  @Test
  public void test_Vrachtwagen_is_it_Serializable() throws IOException, VolumeException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(new Vrachtwagen("auto", datum, AANKOOPPRIJS_2, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE));
    oos.close();
    assertTrue(out.toByteArray().length > 0);
  }

  @Test
  public void test_Vrachtwagen_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException, VolumeException {
    final String MERK = "Vrachtwagen";
    final int PRIJS = 24000;
    final Vrachtwagen origineel = new Vrachtwagen(MERK, datum, PRIJS, ZITPLAATSEN, VOLUME10, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBECCE);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(origineel);
    oos.close();
    byte[] pickled = out.toByteArray();
    InputStream in = new ByteArrayInputStream(pickled);
    ObjectInputStream ois = new ObjectInputStream(in);
    Object o = ois.readObject();
    Vrachtwagen copy = (Vrachtwagen) o;
    assertEquals(origineel, copy);
    assertEquals(copy.getMerk(), MERK);
    assertTrue(copy.getAankoopprijs() == PRIJS);
    assertEquals(copy.getDatumEersteIngebruikname(), datum);
    assertEquals(copy.getLaadvolume(), VOLUME10);
    assertTrue(copy.getMaximaalToegelatenMassa() == MAXIAAM_TOEGELATEN_MASSA_1);
    assertTrue(copy.getAantalAssen() == AANTAL_ASSEN_2);
  }
}
