package com.terminalawakens.creatures;

import java.util.Random;

public class MonsterFactory {

    private static final Random random = new Random();

    /**
     * Gera um monstro aleatório para encontros normais
     */
    public static Monster spawnRandomMonster() {
        int roll = random.nextInt(4);
        switch (roll) {
            case 0: return new Goblin();
            case 1: return new Vampire();
            case 2: return new Dragon();
            case 3: return new GiantSpider();
            default: return new Goblin();
        }
    }

    /**
     * Gera o Boss Final
     */
    public static Monster spawnBoss() {
        return new TerminalOfVortex();
    }

    public static Monster spawnByLevel(int playerLevel) {
        int roll = random.nextInt(100); // 0 a 99
        if (playerLevel < 3) {
            // primeiros níveis
            if (roll < 50) return new Goblin();
            if (roll < 75) return new Vampire();
            return new GiantSpider();
        } else if (playerLevel < 5) {
            if (roll < 40) return new Vampire();
            if (roll < 70) return new Dragon();
            return new GiantSpider();
        } else {
            // nível alto, chance maior de monstros fortes
            if (roll < 50) return new Dragon();
            return new GiantSpider();
        }
    }
}
