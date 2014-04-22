/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

import org.junit.*;
import static org.junit.Assert.*;
import static be.vdab.util.mens.Rijbewijs.*;

/**
 *
 * @author joris.geboers
 */
public class RijbewijsTest {

    @Test
    public void testEnumValues(){
        Rijbewijs x;
        x= A;
        x= B;
        x= BE;
        x= C;
        x= CE;
        x= D;
        x= DE;
    }

    @Test
    public void testEnumToString(){
        assertEquals("A", A.toString());
        assertEquals("B", B.toString());
        assertEquals("B+E", BE.toString());
        assertEquals("C", C.toString());
        assertEquals("C+E", CE.toString());
        assertEquals("D", D.toString());
        assertEquals("D+E", DE.toString());
    }

    @Test
    public void testVolgordeRijbewijzen(){
        assertTrue(A.ordinal()<B.ordinal());
        assertTrue(B.ordinal()<BE.ordinal());
        assertTrue(BE.ordinal()<C.ordinal());
        assertTrue(C.ordinal()<CE.ordinal());
        assertTrue(CE.ordinal()<D.ordinal());
        assertTrue(D.ordinal()<DE.ordinal());
    }
}
