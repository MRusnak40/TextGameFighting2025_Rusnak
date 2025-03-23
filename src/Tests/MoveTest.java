package Tests;

import MainGame.Gameos;
import MainGame.Move;
import org.junit.jupiter.api.Test;
import MainGame.Fight;
import MainGame.GameLoadingData;
import MainGame.Gameos;
import MainGame.Move;
import NPCs.DarkKnight;
import NPCs.Enemy;
import NPCs.EnemyEnum;
import Rase.Clovek;
import Rase.IRasa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveTest {

    Move move=new Move();

    @Test
    void setDirection() {
        // Vytvoření instance Gameos a načtení hry
        Gameos gameos = new Gameos();
        gameos.loadGame();

        // Nastavení místností v objektu Move
        move.setRooms(gameos.roomes);
        move.setCurretX(0);
        move.setCurretY(0); // Nastavení Y souřadnice na 0

        // Pokus o pohyb doleva
        move.setDirection("left");

        // Ověření, že X souřadnice se snížila o 1
        assertEquals(-1, move.getCurretX() );
    }

    @Test
    void controllingPossibilites() {
        Gameos gameos = new Gameos();
        gameos.loadGame();
        move.setRooms(gameos.roomes);
        move.setCurretX(0);
        move.setCurretY(0);
        move.setDirection("left");
        assertTrue(move.controllingPossibilites());
    }

    @Test
    void isMoved() {
        Gameos gameos = new Gameos();
        gameos.loadGame();
        move.setRooms(gameos.roomes);
        move.setCurretX(0);
        move.setCurretY(0);
        move.setDirection("left");
        move.controllingPossibilites();
        assertTrue(move.isMoved());
    }
}