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
public class VolumeExceptionTest {
    @Test
    public void test_Constructors(){
        VolumeException de = new VolumeException();
        de = new VolumeException("Test");
        de = new VolumeException(new Exception());
        de = new VolumeException("Test", new Exception());
    }
    @Test
    public void test_Overloading(){
        IllegalAccessError iac = new IllegalAccessError();
        VolumeException de = new VolumeException("test",iac);
        assertTrue(de instanceof Exception);
        //assertFalse(de instanceof RuntimeException);
        assertEquals(de.getMessage(), "test");
        assertEquals(de.getCause(), iac);
    }
}
