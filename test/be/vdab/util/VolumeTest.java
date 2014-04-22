/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

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
public class VolumeTest {

    private static final int PRIEMGETAL = 11117;
    private final int ZERO = 0;

    @Test
    public void test_volume_not_null() throws VolumeException {
        assertNotNull(new Volume(1, 1, 1, Maat.centimeter));
    }

    @Test
    public void test_Volume_minimum_waarde() throws VolumeException {
        Volume v = new Volume(0, 0, 0, Maat.centimeter);
    }

    @Test(expected = be.vdab.util.VolumeException.class)
    public void test_Volume_minimum_waarde_min_1() throws VolumeException {
        Volume v = new Volume(-1, -1, -1, Maat.centimeter);
    }

    @Test
    public void test_Volume_maximum_waarde() throws VolumeException {
        Volume v = new Volume(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Maat.meter);
    }

    @Test
    public void test_Volume_getDiepte() throws VolumeException {
        for (int teller = 0; teller <= Integer.MAX_VALUE - PRIEMGETAL; teller += PRIEMGETAL) {
            Volume v = new Volume(ZERO, ZERO, teller, Maat.centimeter);
            assertEquals(teller, v.getDiepte());
            assertEquals(ZERO, v.getBreedte());
            assertEquals(ZERO, v.getHoogte());
        }
    }

    @Test
    public void test_Volume_getBreedte() throws VolumeException {
        for (int teller = 0; teller <= Integer.MAX_VALUE - PRIEMGETAL; teller += PRIEMGETAL) {
            Volume v = new Volume(ZERO, teller, ZERO, Maat.centimeter);
            assertEquals(ZERO, v.getDiepte());
            assertEquals(teller, v.getBreedte());
            assertEquals(ZERO, v.getHoogte());
        }
    }

    @Test
    public void test_Volume_getHoogte() throws VolumeException {
        for (int teller = 0; teller <= Integer.MAX_VALUE - PRIEMGETAL; teller += PRIEMGETAL) {
            Volume v = new Volume(teller, ZERO, ZERO, Maat.centimeter);
            assertEquals(ZERO, v.getDiepte());
            assertEquals(ZERO, v.getBreedte());
            assertEquals(teller, v.getHoogte());
        }
    }

    @Test
    public void test_Volume_getMaat() throws VolumeException {
        Volume v = new Volume(ZERO, ZERO, ZERO, Maat.centimeter);
        assertEquals(Maat.centimeter, v.getMaat());
        v = new Volume(ZERO, ZERO, ZERO, Maat.decimeter);
        assertEquals(Maat.decimeter, v.getMaat());
        v = new Volume(ZERO, ZERO, ZERO, Maat.meter);
        assertEquals(Maat.meter, v.getMaat());
    }

    @Test
    public void test_equals_zichzelf() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        assertTrue(v1.equals(v1));
    }

    @Test
    public void test_not_equals_null() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        assertFalse(v1.equals(null));
    }

    @Test
    public void test_equals_Volume() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v2 = new Volume(1, 2, 1903, Maat.centimeter);
        assertTrue(v1.equals(v2));
    }

    @Test
    public void test_not_equals_Volume() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v2 = new Volume(12, 12, 1912, Maat.centimeter);
        assertFalse(v1.equals(v2));
        assertFalse(v2.equals(v1));
    }

    @Test
    public void test_equals_Symetie() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v2 = new Volume(1, 2, 1903, Maat.centimeter);
        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1));
    }

    @Test
    public void test_equals_trasitief() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v2 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v3 = new Volume(1, 2, 1903, Maat.centimeter);
        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v3));
        assertTrue(v1.equals(v3));
    }

    @Test
    public void test_hashCode_equals() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v2 = new Volume(1, 2, 1903, Maat.centimeter);
        assertEquals(v1.hashCode(), v2.hashCode());
        assertEquals(v1, v2);
    }

    @Test
    public void test_hashCode_not_equals() throws VolumeException {
        Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
        Volume v2 = new Volume(12, 12, 1912, Maat.centimeter);
        assertFalse(v1.hashCode() == v2.hashCode());
        assertFalse(v1.equals(v2));
    }

    @Test
    public void test_toString() throws VolumeException{
      Volume v1 = new Volume(1, 2, 1903, Maat.centimeter);
      assertEquals(v1.toString(), "1(h)x2(b)x1903(d) centimeter");
    }

    @Test
    public void test_compareTo_hoogte() throws VolumeException {
        for (int hoogte = 2; hoogte <= 100; hoogte++) {
            Volume v1 = new Volume(1, 1, 1584, Maat.centimeter);
            Volume v2 = new Volume(hoogte, 1, 1584, Maat.centimeter);
            assertTrue(v1.compareTo(v2) < 0);
            assertTrue(v1.compareTo(v1) == 0);
            assertTrue(v2.compareTo(v1) > 0);
        }
    }
    @Test
    public void test_compareTo_breedte() throws VolumeException {
        for (int breedte = 2; breedte <= 100; breedte++) {
            Volume v1 = new Volume(1, 1, 2001, Maat.centimeter);
            Volume v2 = new Volume(1, breedte, 2001, Maat.centimeter);
            assertTrue(v1.compareTo(v2) < 0);
            assertTrue(v1.compareTo(v1) == 0);
            assertTrue(v2.compareTo(v1) > 0);
        }
    }
    @Test
    public void test_compareTo_diepte() throws VolumeException {
        for (int diepte = 2; diepte <= 100; diepte++) {
            Volume v1 = new Volume(1, 1, 2001, Maat.centimeter);
            Volume v2 = new Volume(1, 1, 2001 + diepte, Maat.centimeter);
            assertTrue(v1.compareTo(v2) < 0);
            assertTrue(v1.compareTo(v1) == 0);
            assertTrue(v2.compareTo(v1) > 0);
        }
    }
    @Test
    public void test_compareTo_gelijk() throws VolumeException {
        {//gelijk volume
          Volume v0 = new Volume(100,100,100,Maat.centimeter);
          Volume v1 = new Volume(10,10,10, Maat.decimeter);
          Volume v2 = new Volume(1,1,1,Maat.meter);
          assertTrue(v0.compareTo(v1) == 0);
          assertTrue(v1.compareTo(v0) == 0);
          assertTrue(v1.compareTo(v2) == 0);
          assertTrue(v2.compareTo(v1) == 0);
          assertTrue(v0.compareTo(v2) == 0);
          assertTrue(v2.compareTo(v0) == 0);
        }
    }
    @Test
    public void test_compareTo_grotere_hoogte_in_cm() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(101, 100, 100, Maat.centimeter);
      Volume v1 = new Volume(10, 10, 10, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 1, Maat.meter);
      assertTrue(v0.compareTo(v1) > 0);
      assertTrue(v1.compareTo(v0) < 0);
      assertTrue(v0.compareTo(v2) > 0);
      assertTrue(v2.compareTo(v0) < 0);
    }
    @Test
    public void test_compareTo_grotere_breedte_in_cm() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(100, 101, 100, Maat.centimeter);
      Volume v1 = new Volume(10, 10, 10, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 1, Maat.meter);
      assertTrue(v0.compareTo(v1) > 0);
      assertTrue(v1.compareTo(v0) < 0);
      assertTrue(v0.compareTo(v2) > 0);
      assertTrue(v2.compareTo(v0) < 0);
    }
    @Test
    public void test_compareTo_grotere_diepte_in_cm() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(100, 100, 101, Maat.centimeter);
      Volume v1 = new Volume(10, 10, 10, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 1, Maat.meter);
      assertTrue(v0.compareTo(v1) > 0);
      assertTrue(v1.compareTo(v0) < 0);
      assertTrue(v0.compareTo(v2) > 0);
      assertTrue(v2.compareTo(v0) < 0);
    }
    @Test
    public void test_compareTo_grotere_hoogte_in_dm() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(109, 100, 100, Maat.centimeter);
      Volume v1 = new Volume(11, 10, 10, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 1, Maat.meter);
      assertTrue(v1.compareTo(v0) > 0);
      assertTrue(v0.compareTo(v1) < 0);
      assertTrue(v1.compareTo(v2) > 0);
      assertTrue(v2.compareTo(v1) < 0);
    }
    @Test
    public void test_compareTo_grotere_breedte_in_dm() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(100, 109, 100, Maat.centimeter);
      Volume v1 = new Volume(10, 11, 10, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 1, Maat.meter);
      assertTrue(v1.compareTo(v0) > 0);
      assertTrue(v0.compareTo(v1) < 0);
      assertTrue(v1.compareTo(v2) > 0);
      assertTrue(v2.compareTo(v1) < 0);
    }
    @Test
    public void test_compareTo_grotere_diepte_in_dm() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(100, 100, 109, Maat.centimeter);
      Volume v1 = new Volume(10, 10, 11, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 1, Maat.meter);
      assertTrue(v1.compareTo(v0) > 0);
      assertTrue(v0.compareTo(v1) < 0);
      assertTrue(v1.compareTo(v2) > 0);
      assertTrue(v2.compareTo(v1) < 0);
    }

    @Test
    public void test_compareTo_grotere_hoogte_in_m() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(199, 100, 100, Maat.centimeter);
      Volume v1 = new Volume(19, 10, 10, Maat.decimeter);
      Volume v2 = new Volume(2, 1, 1, Maat.meter);
      assertTrue(v2.compareTo(v0) > 0);
      assertTrue(v0.compareTo(v2) < 0);
      assertTrue(v2.compareTo(v1) > 0);
      assertTrue(v1.compareTo(v2) < 0);
    }
    @Test
    public void test_compareTo_grotere_breedte_in_m() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(100, 199, 100, Maat.centimeter);
      Volume v1 = new Volume(10, 19, 10, Maat.decimeter);
      Volume v2 = new Volume(1, 2, 1, Maat.meter);
      assertTrue(v2.compareTo(v0) > 0);
      assertTrue(v0.compareTo(v2) < 0);
      assertTrue(v2.compareTo(v1) > 0);
      assertTrue(v1.compareTo(v2) < 0);
    }
    @Test
    public void test_compareTo_grotere_diepte_in_m() throws VolumeException {
      //cm³ > dm³, cm³> m³, dm³==m³
      Volume v0 = new Volume(100, 100, 199, Maat.centimeter);
      Volume v1 = new Volume(10, 10, 19, Maat.decimeter);
      Volume v2 = new Volume(1, 1, 2, Maat.meter);
      assertTrue(v2.compareTo(v0) > 0);
      assertTrue(v0.compareTo(v2) < 0);
      assertTrue(v2.compareTo(v1) > 0);
      assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void test_Volume_is_it_Serializable() throws IOException, VolumeException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(new Volume(25, 12, 2400, Maat.centimeter));
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }

    @Test
    public void test_Volume_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException, VolumeException {
        final int HOOGTE = 25;
        final int BREEDTE = 12;
        final int DIEPTE = 2400;
        final Maat MAAT = Maat.centimeter;
        final Volume origineel = new Volume(HOOGTE, BREEDTE, DIEPTE, MAAT);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(origineel);
        oos.close();
        byte[] pickled = out.toByteArray();
        InputStream in = new ByteArrayInputStream(pickled);
        ObjectInputStream ois = new ObjectInputStream(in);
        Object o = ois.readObject();
        Volume copy = (Volume) o;
        assertEquals(origineel, copy);
        assertTrue(copy.getHoogte() == HOOGTE);
        assertTrue(copy.getBreedte() == BREEDTE);
        assertTrue(copy.getDiepte() == DIEPTE);
        assertEquals(copy.getMaat(), MAAT);
    }
}
