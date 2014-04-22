/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author frank.roelants
 */
public class DatumExceptionTest {
    @Test
    public void test_Constructors(){
        DatumException de = new DatumException();
        de = new DatumException("Test");
        de = new DatumException(new Exception());
        de = new DatumException("Test", new Exception());
    }
    @Test
    public void test_Overloading(){
        IllegalAccessError iac = new IllegalAccessError();
        DatumException de = new DatumException("test",iac);
        assertTrue(de instanceof Exception);
        //assertFalse(de instanceof RuntimeException);
        assertEquals(de.getMessage(), "test");
        assertEquals(de.getCause(), iac);
    }}
