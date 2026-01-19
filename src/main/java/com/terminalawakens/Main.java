package com.terminalawakens;

import com.terminalawakens.character.Knight;
import com.terminalawakens.combat.CombatEngine;
import com.terminalawakens.creatures.Goblin;

public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight("Knight Dark'Axe");
        Goblin goblin = new Goblin();

        CombatEngine.battle(knight, goblin);
    }
}
