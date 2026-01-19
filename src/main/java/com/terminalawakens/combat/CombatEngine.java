package com.terminalawakens.combat;

import com.terminalawakens.character.Character;
import com.terminalawakens.creatures.Monster;

public class CombatEngine {

    public static void battle(Character player, Monster monster) {

        System.out.println("Battle started: " + player.getName() + " vs. " + monster.getName());
        System.out.println();

        while (player.isAlive() && monster.isAlive()) {

            player.performBasicAttack(monster);

            if (!monster.isAlive()) {
                break;
            }

            monster.attack(player);
            System.out.println();
        }

        if (player.isAlive()) {
            System.out.println(player.getName() + " won!");
            System.out.println(
                    player.getName() + " earned " +
                            monster.getXpReward() + " XP and " +
                            monster.getGoldReward() + " gold."
            );
        } else {
            System.out.println(player.getName() + " was defeated...");
        }
    }
}