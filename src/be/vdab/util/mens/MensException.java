/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

/**
 *
 * @author Katja
 */
public class MensException extends RuntimeException{//unchecked exception
    public MensException (String boodschap){
        super(boodschap);
    }
}
