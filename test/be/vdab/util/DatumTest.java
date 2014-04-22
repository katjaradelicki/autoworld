/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.lang.reflect.Method;
import be.vdab.voertuigen.div.Nummerplaat;
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
public class DatumTest {

    @Test
    public void test_Datum_not_null() throws DatumException {
        assertNotNull(new Datum(1, 1, 1583));
    }

    @Test
    public void test_Datum_minimum_waarde() throws DatumException {
        Datum d = new Datum(1, 1, 1583);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_minimum_waarde_min_1() throws DatumException {
        Datum d = new Datum(31, 12, 1582);
    }

    @Test
    public void test_Datum_maximum_waarde() throws DatumException {
        Datum d = new Datum(31, 12, 4099);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_maximum_waarde_plus_1() throws DatumException {
        Datum d = new Datum(1, 1, 4100);
    }

    @Test
    public void test_Datum_getDag() throws DatumException {
        for (int teller = 1; teller <= 31; teller++) {
            Datum d = new Datum(teller, 1, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 3, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 5, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 7, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 8, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 10, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 12, 1910);
            assertTrue(d.getDag() == teller);
        }
        for (int teller = 1; teller <= 30; teller++) {
            Datum d = new Datum(teller, 4, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 6, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 9, 1910);
            assertTrue(d.getDag() == teller);
            d = new Datum(teller, 11, 1910);
            assertTrue(d.getDag() == teller);
        }
        for (int teller = 1; teller <= 28; teller++) {
            Datum d = new Datum(teller, 2, 1910);
            assertTrue(d.getDag() == teller);
        }
    }

    @Test
    public void test_Datum_getMaand() throws DatumException {
        for (int teller = 1; teller <= 12; teller++) {
            Datum d = new Datum(10, teller, 1910);
            assertTrue(d.getMaand() == teller);
        }
    }

    @Test
    public void test_Datum_getJaar() throws DatumException {
        for (int teller = 1984; teller < 4100; teller++) {
            Datum d = new Datum(10, 10, teller);
            assertTrue(d.getJaar() == teller);
        }
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_negatieve_Dag() throws DatumException {
        Datum d = new Datum(Integer.MIN_VALUE, 10, 1910);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_negatieve_Maand() throws DatumException {
        Datum d = new Datum(10, Integer.MIN_VALUE, 1910);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_negatief_Jaar() throws DatumException {
        Datum d = new Datum(10, 10, Integer.MIN_VALUE);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_te_grote_Dag() throws DatumException {
        Datum d = new Datum(Integer.MAX_VALUE, 10, 1910);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_te_grote_Maand() throws DatumException {
        Datum d = new Datum(10, Integer.MAX_VALUE, 1910);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_Datum_te_grote_Jaar() throws DatumException {
        Datum d = new Datum(10, 10, Integer.MAX_VALUE);
    }

    @Test
    public void test_equals_zichzelf() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        assertTrue(d1.equals(d1));
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void test_not_equals_null() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        assertFalse(d1.equals(null));
    }

    @Test
    public void test_equals_Datum() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        Datum d2 = new Datum(1, 2, 1903);
        assertTrue(d1.equals(d2));
    }

    @Test
    public void test_not_equals_Datum() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        Datum d2 = new Datum(12, 12, 1912);
        assertFalse(d1.equals(d2));
        assertFalse(d2.equals(d1));
    }

    @Test
    public void test_equals_Symetie() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        Datum d2 = new Datum(1, 2, 1903);
        assertTrue(d1.equals(d2));
        assertTrue(d2.equals(d1));
    }

    @Test
    public void test_equals_trasitief() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        Datum d2 = new Datum(1, 2, 1903);
        Datum d3 = new Datum(1, 2, 1903);
        assertTrue(d1.equals(d2));
        assertTrue(d2.equals(d3));
        assertTrue(d1.equals(d3));
    }

    @Test
    public void test_hashCode_equals() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        Datum d2 = new Datum(1, 2, 1903);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertEquals(d1, d2);
    }

    @Test
    public void test_hashCode_not_equals() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        Datum d2 = new Datum(12, 12, 1912);
        assertFalse(d1.hashCode() == d2.hashCode());
        assertFalse(d1.equals(d2));
    }

    @Test
    public void test_toString() throws DatumException {
        Datum d1 = new Datum(1, 2, 1903);
        assertEquals(d1.toString(), "01/02/1903");
        d1 = new Datum(1, 10, 1903);
        assertEquals(d1.toString(), "01/10/1903");
        d1 = new Datum(10, 1, 1903);
        assertEquals(d1.toString(), "10/01/1903");
        d1 = new Datum(10, 10, 1903);
        assertEquals(d1.toString(), "10/10/1903");
    }

    @Test
    public void test_28_februari_geen_schrikkeljaar() throws DatumException {
        Datum d = new Datum(28, 2, 1903);
        assertNotNull(d);
        assertEquals(d.getDag(), 28);
        assertEquals(d.getMaand(), 2);
        assertEquals(d.getJaar(), 1903);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_29_februari_geen_schrikkeljaar() throws DatumException {
        Datum d = new Datum(29, 2, 1903);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_30_februari_geen_schrikkeljaar() throws DatumException {
        Datum d = new Datum(30, 2, 1903);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_31_februari_geen_schrikkeljaar() throws DatumException {
        Datum d = new Datum(30, 2, 1903);
    }

    @Test
    public void test_28_februari_schrikkeljaar() throws DatumException {
        Datum d = new Datum(28, 2, 1904);
        assertNotNull(d);
        assertEquals(d.getDag(), 28);
        assertEquals(d.getMaand(), 2);
        assertEquals(d.getJaar(), 1904);
    }

    @Test
    public void test_29_februari_schrikkeljaar() throws DatumException {
        Datum d = new Datum(29, 2, 1904);
        assertEquals(d.getDag(), 29);
        assertEquals(d.getMaand(), 2);
        assertEquals(d.getJaar(), 1904);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_30_februari_schrikkeljaar() throws DatumException {
        Datum d = new Datum(30, 2, 1904);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_31_februari_schrikkeljaar() throws DatumException {
        Datum d = new Datum(30, 2, 1904);
    }

    @Test
    public void test_28_februari_geen_schrikkeljaar_100() throws DatumException {
        Datum d = new Datum(28, 2, 1900);
        assertNotNull(d);
        assertEquals(d.getDag(), 28);
        assertEquals(d.getMaand(), 2);
        assertEquals(d.getJaar(), 1900);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_29_februari_geen_schrikkeljaar_100() throws DatumException {
        Datum d = new Datum(29, 2, 1900);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_30_februari_geen_schrikkeljaar_100() throws DatumException {
        Datum d = new Datum(30, 2, 1900);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_31_februari_geen_schrikkeljaar_100() throws DatumException {
        Datum d = new Datum(30, 2, 1900);
    }

    @Test
    public void test_28_februari_schrikkeljaar_400() throws DatumException {
        Datum d = new Datum(28, 2, 1600);
        assertNotNull(d);
        assertEquals(d.getDag(), 28);
        assertEquals(d.getMaand(), 2);
        assertEquals(d.getJaar(), 1600);
    }

    @Test
    public void test_29_februari_schrikkeljaar_400() throws DatumException {
        Datum d = new Datum(29, 2, 1600);
        assertEquals(d.getDag(), 29);
        assertEquals(d.getMaand(), 2);
        assertEquals(d.getJaar(), 1600);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_30_februari_schrikkeljaar_400() throws DatumException {
        Datum d = new Datum(30, 2, 1600);
    }

    @Test(expected = be.vdab.util.DatumException.class)
    public void test_31_februari_schrikkeljaar_400() throws DatumException {
        Datum d = new Datum(30, 2, 1600);
    }

    @Test
    public void test_compareTo() throws DatumException {
        for (int dag = 2; dag <= 31; dag++) {
            Datum d1 = new Datum(1, 1, 2001);
            Datum d2 = new Datum(dag, 1, 2001);
            assertTrue(d1.compareTo(d2) < 0);
            assertTrue(d1.compareTo(d1) == 0);
            assertTrue(d2.compareTo(d1) > 0);
        }
        int[] maanden = {1, 3, 5, 7, 8, 10};
        for (int maand : maanden) {
            Datum d1 = new Datum(31, maand, 2001);
            Datum d2 = new Datum(1, maand + 1, 2001);
            assertTrue(d1.compareTo(d2) < 0);
            assertTrue(d1.compareTo(d1) == 0);
            assertTrue(d2.compareTo(d1) > 0);
        }
        int[] maanden2 = {4, 6, 9, 11};
        for (int maand : maanden) {
            Datum d1 = new Datum(30, maand, 2001);
            Datum d2 = new Datum(1, maand + 1, 2001);
            assertTrue(d1.compareTo(d2) < 0);
            assertTrue(d1.compareTo(d1) == 0);
            assertTrue(d2.compareTo(d1) > 0);
        }
        for (int jaar = 1583; jaar <= 4098; jaar++) {
            Datum d1 = new Datum(31, 12, jaar);
            Datum d2 = new Datum(1, 1, jaar + 1);
            assertTrue(d1.compareTo(d2) < 0);
            assertTrue(d1.compareTo(d1) == 0);
            assertTrue(d2.compareTo(d1) > 0);
        }
        {
            Datum d1 = new Datum(28, 2, 2001);
            Datum d2 = new Datum(1, 3, 2001);
            assertTrue(d1.compareTo(d2) < 0);
            assertTrue(d1.compareTo(d1) == 0);
            assertTrue(d2.compareTo(d1) > 0);
        }
        {
            Datum d1 = new Datum(29, 2, 2000);
            Datum d2 = new Datum(1, 3, 2000);
            assertTrue(d1.compareTo(d2) < 0);
            assertTrue(d1.compareTo(d1) == 0);
            assertTrue(d2.compareTo(d1) > 0);
        }
    }

    @Test
    public void test_Datum_is_it_Serializable() throws IOException, DatumException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(new Datum(25, 12, 2400));
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }

    @Test
    public void test_Datum_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException, DatumException {
        {
            final int DAG = 25;
            final int MAAND = 12;
            final int JAAR = 2400;
            final Datum origineel = new Datum(DAG, MAAND, JAAR);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(origineel);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Datum copy = (Datum) o;
            assertEquals(origineel, copy);
            assertTrue(copy.getDag() == DAG);
            assertTrue(copy.getMaand() == MAAND);
            assertTrue(copy.getJaar() == JAAR);
        }
        {
            final int DAG = 1;
            final int MAAND = 1;
            final int JAAR = 1584;
            final Datum origineel = new Datum(DAG, MAAND, JAAR);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(origineel);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Datum copy = (Datum) o;
            assertEquals(origineel, copy);
            assertTrue(copy.getDag() == DAG);
            assertTrue(copy.getMaand() == MAAND);
            assertTrue(copy.getJaar() == JAAR);
        }
        {
            final int DAG = 31;
            final int MAAND = 12;
            final int JAAR = 4099;
            final Datum origineel = new Datum(DAG, MAAND, JAAR);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(origineel);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Datum copy = (Datum) o;
            assertEquals(origineel, copy);
            assertTrue(copy.getDag() == DAG);
            assertTrue(copy.getMaand() == MAAND);
            assertTrue(copy.getJaar() == JAAR);
        }
        {
            final int DAG = 31;
            final int MAAND = 12;
            final int JAAR = 4099;
            final Datum origineel = new Datum(DAG, MAAND, JAAR);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(origineel);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Datum copy = (Datum) o;
            assertEquals(origineel, copy);
            assertTrue(copy.getDag() == DAG);
            assertTrue(copy.getMaand() == MAAND);
            assertTrue(copy.getJaar() == JAAR);
        }
        for (int JAAR = 1600; JAAR < 1700; JAAR += 4) {
            final int DAG = 29;
            final int MAAND = 2;
            final Datum origineel = new Datum(DAG, MAAND, JAAR);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(origineel);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Datum copy = (Datum) o;
            assertEquals(origineel, copy);
            assertTrue(copy.getDag() == DAG);
            assertTrue(copy.getMaand() == MAAND);
            assertTrue(copy.getJaar() == JAAR);
        }
    }

    @Test
    public void test_niet_bestaan_setters() {
        Method[] lijst = Datum.class.getDeclaredMethods();
        boolean probleem = false;
        for (Method m : lijst) {
            probleem &= m.isAccessible() && m.getName().startsWith("set");
        }
        if (probleem) {
            fail("there is at least one setter method in the IMMUTABLE class Nummerplaat");
        }
    }
}
