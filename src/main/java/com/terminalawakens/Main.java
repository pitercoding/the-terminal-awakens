package com.terminalawakens;

import com.terminalawakens.character.Knight;
import com.terminalawakens.combat.CombatEngine;
import com.terminalawakens.creatures.Goblin;

public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight("Knight Dark'Axe");
        Goblin goblin = new Goblin();

//        System.out.println("== Simple Combat ==");
//        CombatEngine.battle(knight, goblin);

//        System.out.println("== Testing Special Attack ==");
//        knight.performSpecialAttack(goblin);
//
//        System.out.println();
//        System.out.println("Knight Mana: " + knight.getCurrentMana());
//        System.out.println("Goblin HP: " + goblin.getCurrentHealth());
    }
}
