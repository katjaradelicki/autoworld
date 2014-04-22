/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

/**
 *
 * @author katja.radelicki
 */
public class DatumException extends Exception{
    
    public DatumException (String message){
        super(message);
    }
    
    public DatumException(){
        super();
    }
    
    public DatumException(Throwable t){
        super(t);
    }
    
    public DatumException(String message,Throwable cause){
        super(message, cause);
    }
    
}
