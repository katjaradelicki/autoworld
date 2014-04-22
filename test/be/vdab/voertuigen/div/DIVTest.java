/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import org.junit.internal.runners.statements.ExpectException;
import static org.junit.Assert.*;

public class DIVTest {

    @Test
    public void test_singleton() {
        assertSame(DIV.getInstance(), DIV.getInstance());
    }

    @Test
    public void test_niet_private_constructor() {
        Constructor<?>[] lijst = DIV.class.getDeclaredConstructors();
        boolean probleem = false;
        for (Constructor<?> c : lijst) {
            probleem &= c.isAccessible();
        }
        if (probleem) {
            fail("there is an accessible constructor in DIV");
        }
    }

    @Test
    public void test_niet_bestaan_setters() {
        Method[] lijst = DIV.class.getDeclaredMethods();
        boolean probleem = false;
        for (Method m : lijst) {
            probleem &= m.isAccessible() && m.getName().startsWith("set");
        }
        if (probleem) {
            fail("there is a setter method in DIV");
        }
    }

    @Test
    public void test_singleton_not_null() {
        assertNotNull(DIV.getInstance());
    }

    @Test
    public void test_Nummerplaat() {
        assertNotNull(DIV.getInstance().getNummerplaat());
    }

    @Test
    public void test_Verschillende_Nummerplaten() {
        assertNotSame(DIV.getInstance().getNummerplaat(), DIV.getInstance().getNummerplaat());
    }

    @Test
    public void test_Rotatie_Verschillende_Nummerplaat_Objecten() {
        Nummerplaat eerste = DIV.getInstance().getNummerplaat();
        for (int teller = 1; teller <= 998; teller++) {
            Nummerplaat plaat = DIV.getInstance().getNummerplaat();
        }
        Nummerplaat terugEerste = DIV.getInstance().getNummerplaat();
        assertNotSame(eerste, terugEerste);
        assertEquals(eerste, terugEerste);
    }

    @Test
    public void test_Rotatie_Verschillende_Nummerplaten_Waarden() {
        Set<Nummerplaat> platen=null;
        synchronized (DIV.getInstance()) {
            platen = new HashSet<Nummerplaat>(999);
            for (int teller = 1; teller <=999; teller++) {
                platen.add(DIV.getInstance().getNummerplaat());
            }
        }
        assertEquals(platen.size(), 999);
    }
}
