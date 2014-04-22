/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
public class NummerplaatTest {

  @Test
  public void test_Nummerplaat_not_null() {
    assertNotNull(new Nummerplaat("123465"));
  }

  @Test
  public void test_Nummerplaat_getPlaat() {
    final String plaat = "AAA007";
    Nummerplaat np = new Nummerplaat(plaat);
    assertEquals(np.getPlaat(), plaat);
  }

  @Test
  public void test_equals_zichzelf() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    assertTrue(np1.equals(np1));
  }

  @Test
  public void test_not_equals_null() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    assertFalse(np1.equals(null));
  }

  @Test
  public void test_equals_Nummerplaat() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    Nummerplaat np2 = new Nummerplaat("13245678");
    assertTrue(np1.equals(np2));
  }

  @Test
  public void test_not_equals_Nummerplaat() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    Nummerplaat np2 = new Nummerplaat("1324567");
    Nummerplaat np3 = new Nummerplaat("132456789");
    assertFalse(np1.equals(np2));
    assertFalse(np1.equals(np3));
  }

  @Test
  public void test_equals_Symetie() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    Nummerplaat np2 = new Nummerplaat("13245678");
    assertTrue(np1.equals(np2));
    assertTrue(np2.equals(np1));
  }

  @Test
  public void test_equals_trasitief() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    Nummerplaat np2 = new Nummerplaat("13245678");
    Nummerplaat np3 = new Nummerplaat("13245678");
    assertTrue(np1.equals(np2));
    assertTrue(np2.equals(np3));
    assertTrue(np1.equals(np3));
  }

  @Test
  public void test_hashCode_equals() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    Nummerplaat np2 = new Nummerplaat("13245678");
    assertEquals(np1.hashCode(), np2.hashCode());
    assertEquals(np1, np2);
  }

  @Test
  public void test_hashCode_not_equals() {
    Nummerplaat np1 = new Nummerplaat("13245678");
    Nummerplaat np2 = new Nummerplaat("132456");
    assertFalse(np1.hashCode() == np2.hashCode());
    assertFalse(np1.equals(np2));
  }

  @Test
  public void test_toString() {
    String nps = "123456";
    Nummerplaat np = new Nummerplaat(nps);
    assertEquals(nps, np.toString());
  }

  @Test
  public void test_compareTo() {
    final String S1 = "AAA123";
    final String SG1 = "AAA124";
    final String SG2 = "AAA141";
    final String SG3 = "AAA411";
    final String SE = "AAA123";
    final String SK1 = "AAA440";
    final String SK2 = "AAA404";
    final String SK3 = "AAA044";
    Nummerplaat n = new Nummerplaat(S1);
    Nummerplaat ng1 = new Nummerplaat(SG1);
    Nummerplaat ng2 = new Nummerplaat(SG2);
    Nummerplaat ng3 = new Nummerplaat(SG3);
    Nummerplaat ne = new Nummerplaat(SE);
    Nummerplaat nk1 = new Nummerplaat(SK1);
    Nummerplaat nk2 = new Nummerplaat(SK2);
    Nummerplaat nk3 = new Nummerplaat(SK3);
    assertEquals(n.compareTo(ng1) < 0, S1.compareTo(SG1) < 0);
    assertEquals(n.compareTo(ng2) < 0, S1.compareTo(SG2) < 0);
    assertEquals(n.compareTo(ng3) < 0, S1.compareTo(SG3) < 0);
    assertEquals(n.compareTo(ne) == 0, S1.compareTo(SE) == 0);
    assertEquals(n.compareTo(nk1) > 0, S1.compareTo(SK1) > 0);
    assertEquals(n.compareTo(nk2) > 0, S1.compareTo(SK2) > 0);
    assertEquals(n.compareTo(nk3) > 0, S1.compareTo(SK3) > 0);
  }

  @Test
  public void test_Nummerplaat_is_it_Serializable() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(new Nummerplaat("AAA001"));
    oos.close();
    assertTrue(out.toByteArray().length > 0);
  }

  @Test
  public void test_Nummerplaat_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException {
    final Nummerplaat plaat = new Nummerplaat("AAA001");
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(plaat);
    oos.close();
    byte[] pickled = out.toByteArray();
    InputStream in = new ByteArrayInputStream(pickled);
    ObjectInputStream ois = new ObjectInputStream(in);
    Object o = ois.readObject();
    Nummerplaat copy = (Nummerplaat) o;
    assertEquals(plaat, copy);
  }

  @Test
  public void test_niet_bestaan_setters() {
    Method[] lijst = Nummerplaat.class.getDeclaredMethods();
    boolean probleem = false;
    for (Method m : lijst) {
      probleem &= m.isAccessible() && m.getName().startsWith("set");
    }
    if (probleem) {
      fail("there is at least one setter method in the IMMUTABLE class Nummerplaat");
    }
  }

  @Test
  public void test_noarg_constructor(){
      Constructor[] clijst = Nummerplaat.class.getConstructors();
      boolean probleem = false;
      for (Constructor c : clijst){
          Class[] noargs = c.getParameterTypes();
          if (noargs.length==0){
              probleem = true;
          }
      }
      if (probleem)
          fail("there is a no-argument constructor");
  }

  @Test
  public void test_constructorvisibility(){
      Constructor[] clijst = Nummerplaat.class.getConstructors();
      boolean probleem = false;
      for (Constructor c : clijst){
          int modifiers = c.getModifiers();
          if ((modifiers&7)!=0){
              probleem = true;
          }
      }
      if (probleem)
          fail("Gebruik package visibility voor de constructor van Nummplaat");
  }
}
