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
public class PickupTest implements MensFactorInTest {

  private final Datum datum;
  private final Datum datum2;
  private final int GETAL4 = 4;
  private final int ZITPLAATSEN5 = 5;
  private final Volume VOLUME10;
  private final Volume VOLUME12;
  private final String datumString;
  private final String GETAL4_STRING;
  private final String VOLUME10_STRING;
  private final Color KLEUR = Color.PINK;

  public PickupTest() throws DatumException, VolumeException {
    datum = new Datum(1, 2, 3456);
    datum2 = new Datum(1, 2, 2134);
    VOLUME10 = new Volume(10, 10, 10, Maat.decimeter);
    VOLUME12 = new Volume(12, 12, 12, Maat.decimeter);
    datumString = "01/02/3456";
    GETAL4_STRING = Integer.toString(GETAL4);
    VOLUME10_STRING = VOLUME10.toString();
  }

  @Test
  public void test_not_null() {
    assertNotNull(new Pickup("Cadilac", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE));
  }

  @Test
  public void test_getNummerplaat() {
    assertNotNull(new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE).getNummerplaat());
  }

  @Test
  public void test_getMerk() {
    assertEquals(new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE).getMerk(), "a");
  }

  @Test
  public void test_getDatum() {
    assertEquals(new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE).getDatumEersteIngebruikname(), datum);
  }

  @Test
  public void test_getZitplaatsen() {
    assertEquals(new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE).getZitplaatsen(), GETAL4);
  }

  @Test
  public void test_getAankoopprijs() {
    assertEquals(new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE).getAankoopprijs(), 100);
  }

  @Test
  public void test_getLaadvolume() {
    assertEquals(new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE).getLaadvolume(), VOLUME10);
  }

  @Test
  public void test_setMerk() {
    Pickup tv = new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    tv.setMerk("b");
    assertFalse(tv.getMerk().equals("a"));
    assertTrue(tv.getMerk().equals("b"));
  }

  @Test
  public void test_setDatum() throws DatumException {
    Pickup tv = new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    tv.setDatumEersteIngebruikname(datum2);
    assertFalse(tv.getDatumEersteIngebruikname().equals(datum));
    assertTrue(tv.getDatumEersteIngebruikname().equals(datum2));
  }

  @Test
  public void test_setAankoopprijs() {
    Pickup tv = new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    tv.setAankoopprijs(200);
    assertFalse(tv.getAankoopprijs() == 100);
    assertTrue(tv.getAankoopprijs() == 200);
  }

  @Test
  public void test_setLaadvolume() {
    Pickup p = new Pickup("a", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    p.setLaadvolume(VOLUME12);
    assertFalse(p.getLaadvolume() == VOLUME10);
    assertTrue(p.getLaadvolume() == VOLUME12);
  }

  @Test
  public void test_equals_zichzelf() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertTrue(v1.equals(v1));
  }

  @Test
  public void test_polyforme_equals_zichzelf() throws DatumException {
    Pickup p1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Voertuig v1 = p1;
    Personenwagen w1 = p1;
    assertTrue(p1.equals(v1));
    assertTrue(v1.equals(p1));
    assertTrue(p1.equals(w1));
    assertTrue(w1.equals(p1));
  }

  @Test
  public void test_not_equals_null() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertFalse(v1.equals(null));
  }

  @Test
  public void test_equals_Voertuig() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
  }

  @Test
  public void test_not_equals_Voertuig() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("otto", datum2, 14300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
  }

  @Test
  public void test_equals_Symetie() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("auto", datum, 1830, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
  }

  @Test
  public void test_equals_trasitief() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v3 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    assertEquals(v2.equals(v3), v2.getNummerplaat().equals(v3.getNummerplaat()));
    assertEquals(v1.equals(v3), v1.getNummerplaat().equals(v3.getNummerplaat()));
  }

  @Test
  public void test_hashCode_equals() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertEquals((v1.hashCode() == v2.hashCode()), v1.equals(v2));
  }

  @Test
  public void test_toString() throws DatumException {
    Pickup pw = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    String nummerplaatString = pw.getNummerplaat().getPlaat();
    String merkString = pw.getMerk();
    String aankoopprijsString = Integer.toString(pw.getAankoopprijs());

    String toString = String.format("%s %s %s %s %s %s %s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)", GETAL4_STRING, VOLUME10_STRING);
    String toString2 = pw.toString();
    assertEquals(toString, toString2);
  }

  @Test
  public void test_compareTo() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("otto", datum2, 14300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertEquals(v1.compareTo(v2) < 0, v1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
    assertEquals(v1.compareTo(v1) == 0, v1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
    assertEquals(v2.compareTo(v1) > 0, v2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
  }

  @Test
  public void test_polyforme_compareTo() throws DatumException {
    Pickup p1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup p2 = new Pickup("otto", datum2, 14300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Voertuig v1 = p1;
    Voertuig v2 = p2;
    Personenwagen w1 = p1;
    Personenwagen w2 = p2;
    assertEquals(p1.compareTo(v2) < 0, p1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
    assertEquals(p1.compareTo(v1) == 0, p1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
    assertEquals(p2.compareTo(v1) > 0, p2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
    assertEquals(p1.compareTo(w2) < 0, p1.getNummerplaat().compareTo(w2.getNummerplaat()) < 0);
    assertEquals(p1.compareTo(w1) == 0, p1.getNummerplaat().compareTo(w1.getNummerplaat()) == 0);
    assertEquals(p2.compareTo(w1) > 0, p2.getNummerplaat().compareTo(w1.getNummerplaat()) > 0);
  }

  @Test
  public void test_MerkComparator() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("bus", datum2, 14300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertNotNull(Pickup.getMerkComparator());
    assertEquals(Pickup.getMerkComparator().compare(v1, v2) < 0, v1.getMerk().compareTo(v2.getMerk()) < 0);
    assertEquals(Pickup.getMerkComparator().compare(v1, v1) == 0, v1.getMerk().compareTo(v1.getMerk()) == 0);
    assertEquals(Pickup.getMerkComparator().compare(v2, v1) > 0, v2.getMerk().compareTo(v1.getMerk()) > 0);
  }

  @Test
  public void test_AankoopComparator() throws DatumException {
    Pickup v1 = new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    Pickup v2 = new Pickup("bus", datum2, 14300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    assertNotNull(Pickup.getAankoopprijsComparator());
    assertEquals(Pickup.getAankoopprijsComparator().compare(v1, v2) < 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v2.getAankoopprijs())) < 0);
    assertEquals(Pickup.getAankoopprijsComparator().compare(v1, v1) == 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) == 0);
    assertEquals(Pickup.getAankoopprijsComparator().compare(v2, v1) > 0, new Integer(v2.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) > 0);
  }

  @Test
  public void test_Pickup_is_it_Serializable() throws IOException, VolumeException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(new Pickup("auto", datum, 18300, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE));
    oos.close();
    assertTrue(out.toByteArray().length > 0);
  }

  @Test
  public void test_Pickup_Serializable_and_compare_to_the_original() throws IOException, ClassNotFoundException, VolumeException {
    final String MERK = "auto";
    final int PRIJS = 2400;
    final Pickup origineel = new Pickup(MERK, datum, PRIJS, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(origineel);
    oos.close();
    byte[] pickled = out.toByteArray();
    InputStream in = new ByteArrayInputStream(pickled);
    ObjectInputStream ois = new ObjectInputStream(in);
    Object o = ois.readObject();
    Pickup copy = (Pickup) o;
    assertEquals(origineel, copy);
    assertEquals(copy.getMerk(), MERK);
    assertTrue(copy.getAankoopprijs() == PRIJS);
    assertEquals(copy.getDatumEersteIngebruikname(), datum);
    assertTrue(copy.getZitplaatsen() == GETAL4);
    assertEquals(copy.getLaadvolume(), VOLUME10);
  }
}
