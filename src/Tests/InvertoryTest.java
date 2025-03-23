package Tests;

import AtributesOfPlayer.Invertory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Character.*;
import KeysAtributes.Items;
import Rase.Clovek;
import Rase.IRasa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvertoryTest {
    private Invertory invertory;
    private Postava postava;

    @BeforeEach
    void setUp() {
        // Inicializace postavy a inventáře
        postava = new Bojovnik("Hrac", 20, 5, 5, 5, (IRasa) new Clovek());
        invertory = new Invertory(postava, null); // move je null, protože ho nepotřebujeme pro tento test

        // Přidání předmětů do inventáře
        Items item1 = new Items("Shield", 0, 20, 0, 15, 0, true);
        Items item2 = new Items("Sword", 10, 0, 5, 0, 0, true);
        invertory.veciBatoh.add(item1);
        invertory.veciBatoh.add(item2);
    }

    @Test
    void testSetBonusesForItems() {
        // Uložení počátečního zdraví a atributů postavy
        double initialHealth = postava.getCurrentHealth();
        double initialIntelligence = postava.getInteligence();
        double initialOdolnost = postava.getOdolnost();
        double initialObratnost = postava.getObratnost();
        double initialSila = postava.getSila();

        // Aplikace bonusů
        invertory.setBonusesForItems();

        // Ověření, že se bonusy správně aplikovaly
        assertEquals(initialHealth + 20, postava.getCurrentHealth(), "Zdraví by mělo být zvýšeno o 20.");
        // assertEquals(initialIntelligence + 5, postava.getInteligence(), "Inteligence by měla být zvýšena o 5.");
        // assertEquals(initialOdolnost + 15, postava.getOdolnost(), "Odolnost by měla být zvýšena o 15.");
        // assertEquals(initialObratnost, postava.getObratnost(), "Obratnost by se neměla měnit.");
        // assertEquals(initialSila + 10, postava.getSila(), "Síla by měla být zvýšena o 10.");
    }

    @Test
    void testDisableBonusesForItems() {  // Uložení počátečního zdraví a atributů postavy
        double initialHealth = postava.getCurrentHealth();
        double initialIntelligence = postava.getInteligence();
        double initialOdolnost = postava.getOdolnost();
        double initialObratnost = postava.getObratnost();
        double initialSila = postava.getSila();
        // Aplikace bonusů
        invertory.setBonusesForItems();


        // Odstranění bonusů
        invertory.disableBonusesForItems(0);

        // Ověření, že se bonusy správně odstranily
        assertEquals(initialHealth, postava.getCurrentHealth(), "Zdraví by mělo být stejné jako před aplikací bonusů.");
        // assertEquals(initialIntelligence, postava.getInteligence(), "Inteligence by měla být stejná jako před aplikací bonusů.");
        // assertEquals(initialOdolnost, postava.getOdolnost(), "Odolnost by měla být stejná jako před aplikací bonusů.");
        // assertEquals(initialObratnost, postava.getObratnost(), "Obratnost by se neměla měnit.");
        //  assertEquals(initialSila, postava.getSila(), "Síla by měla být stejná jako před aplikací bonusů.");
    }


}