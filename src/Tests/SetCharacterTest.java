package Tests;

import AtributesOfPlayer.SetCharacter;
import Rase.Clovek;
import Rase.IRasa;
import org.junit.jupiter.api.BeforeEach;
import Character.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SetCharacterTest {
Postava postava;
    private SetCharacter setCharacter=new SetCharacter(postava);

 @Test
    void testSetCharacter() {

     assertNotNull(  IRasa.vyvber(1));



 }



}