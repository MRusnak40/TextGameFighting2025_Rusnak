package Tests;

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

import static NPCs.EnemyEnum.DARKKNIGHT;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Character.*;

class FightTest {
    Postava postava = new Bojovnik("TEST", 20, 7, 6, 25, (IRasa) new Clovek());
    Move move = new Move();
    Fight fight = new Fight(postava, move);
    Enemy enemy = new DarkKnight(EnemyEnum.DARKKNIGHT, "Posluhovač temna", 10, 4, 1, 10, 3, 150);

    @Test
    void ifPlayerHpisZeroThenTrue() {
        GameLoadingData gameLoadingData = new GameLoadingData();
        Gameos gameos = new Gameos();
        gameos.loadGame();
        move.setRooms(gameos.roomes);
        // Nastavení zdraví postavy na 0
        postava.setCurrentHealth(0);

        // Ověření, že metoda isPlayerDead vrátí true
        assertTrue(fight.isPlayerDead(0, enemy), "Hráč by měl být mrtvý, když má 0 HP.");
    }

@Test
    void playerAttackisReal(){

        double hpenemy= enemy.getHp();

        fight.playerAttack(enemy);
        assertTrue(enemy.getHp()<hpenemy);
    }


@Test
    void enemyAttackIsReal(){
double hp=postava.getCurrentHealth();

fight.enemyAttack(enemy);
assertTrue(postava.getCurrentHealth()<=hp);
    }





}