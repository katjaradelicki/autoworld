/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;

import be.vdab.util.mens.Mens;
import static be.vdab.util.mens.Rijbewijs.*;
import org.junit.Ignore;

/**
 *
 * @author joris.geboers
 */
@Ignore
public interface MensFactorInTest {
    Mens BESTUURDER_A = new Mens("Andree",A);
    Mens BESTUURDER_AB = new Mens("Amadeus",A,B);
    Mens BESTUURDER_B = new Mens("Bernard",B);
    Mens BESTUURDER_BC = new Mens("Beatrice-Clothilde",B,C);
    Mens BESTUURDER_C = new Mens("Catherina",C);
    Mens BESTUURDER_D = new Mens("Didier",D);
    Mens BESTUURDER_BE = new Mens("Beatrice-Emanuella",BE);
    Mens BESTUURDER_BBE = new Mens("Babette-Emanuella",B,BE);
    Mens BESTUURDER_CE = new Mens("Cederic-Eduard",CE);
    Mens BESTUURDER_DE = new Mens("Dominique-Emille",CE);
    Mens BESTUURDER_BBECCE = new Mens("Ammelie",B,BE,C,CE);
    Mens INGEZETENE_A = new Mens("Anita");
    Mens INGEZETENE_B = new Mens("Bert");
    Mens INGEZETENE_C = new Mens("Christina");
    Mens INGEZETENE_D = new Mens("Duts");
    Mens INGEZETENE_E = new Mens("Elsa");
    Mens INGEZETENE_F = new Mens("Fred");
    Mens INGEZETENE_G = new Mens("Gerda");
    Mens INGEZETENE_H = new Mens("Hedwig");
    Mens INGEZETENE_I = new Mens("Ingrid");
}
