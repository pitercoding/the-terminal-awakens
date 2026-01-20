package com.terminalawakens.combat;

import com.terminalawakens.character.Character;
import com.terminalawakens.creatures.Monster;

import java.util.Scanner;

public class CombatEngine {

    public static void battle(Character player, Monster monster) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nBattle started: " + player.getName() + " vs. " + monster.getName());
        System.out.println();

        while (player.isAlive() && monster.isAlive()) {

            showStatus(player, monster);
            showCombatMenu();

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> player.performBasicAttack(monster);

                case 2 -> player.performSpecialAttack(monster);

                case 3 -> {
                    if (attemptEscape()) {
                        System.out.println(player.getName() + " successfully escaped!");
                        return; // sai do combate
                    } else {
                        System.out.println(player.getName() + " failed to escape!");
                    }
                }

                default -> {
                    System.out.println("Invalid option!");
                    continue; // não perde o turno
                }
            }

            if (monster.isAlive()) {
                monster.attack(player);
                System.out.println();
            }
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

    public static void showStatus(Character player, Monster monster) {
        System.out.println("--- Combat Status ---");
        System.out.println(player.getName() +
                " | HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth() +
                " | Mana: " + player.getCurrentMana() + "/" + player.getMaxMana());
        System.out.println(monster.getName() +
                " | HP: " + monster.getCurrentHealth() + "/" + monster.getMaxHealth());
        System.out.println("---------------------\n");
    }

    private static void showCombatMenu() {
        System.out.println("Choose your action:");
        System.out.println("1 - Basic Attack");
        System.out.println("2 - Special Attack");
        System.out.println("3 - Run");
        System.out.print("Your choice: ");
    }

    private static boolean attemptEscape() {
        return Math.random() < 0.5;
    }
}