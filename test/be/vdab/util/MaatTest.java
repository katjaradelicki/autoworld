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
public class MaatTest {

    @Test
    public void test_enum_Maat() {
        Maat m1 = Maat.centimeter;
        Maat m2 = Maat.decimeter;
        Maat m3 = Maat.meter;
    }

    @Test
    public void test_enum_toString(){
        Maat m1 = Maat.centimeter;
        assertEquals(m1.toString(), "centimeter");
        Maat m2 = Maat.decimeter;
        assertEquals(m2.toString(), "decimeter");
        Maat m3 = Maat.meter;
        assertEquals(m3.toString(), "meter");
    }

    @Test
    public void test_enum_Maat_values() {
        assertEquals(Maat.values().length, 3);
    }
    @Test
    public void test_enum_Maat_compareTo() {
        assertTrue(Maat.centimeter.compareTo(Maat.decimeter)<0);
        assertTrue(Maat.decimeter.compareTo(Maat.meter)<0);
        assertTrue(Maat.centimeter.compareTo(Maat.meter)<0);
    }

    @Test
    public void test_Datum_is_centimeter_Serializable() throws IOException, DatumException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(Maat.centimeter);
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }
    @Test
    public void test_Datum_is_decimeter_Serializable() throws IOException, DatumException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(Maat.centimeter);
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }
    @Test
    public void test_Maat_is_it_Serializable() throws IOException, DatumException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(Maat.centimeter);
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }

    @Test
    public void test_Maat_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException, DatumException {
        {
            Maat origineel = Maat.centimeter;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(Maat.centimeter);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Maat copy = (Maat) o;
            assertEquals(origineel, copy);
        }
        {
            Maat origineel = Maat.decimeter;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(Maat.decimeter);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Maat copy = (Maat) o;
            assertEquals(origineel, copy);
        }
        {
            Maat origineel = Maat.meter;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(Maat.meter);
            oos.close();
            byte[] pickled = out.toByteArray();
            InputStream in = new ByteArrayInputStream(pickled);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            Maat copy = (Maat) o;
            assertEquals(origineel, copy);
        }
    }
}
