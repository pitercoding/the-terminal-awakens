package com.terminalawakens.engine;

import com.terminalawakens.character.Character;
import com.terminalawakens.character.Druid;
import com.terminalawakens.character.Sorcerer;
import com.terminalawakens.creatures.Monster;

import java.util.Scanner;

public class CombatEngine {

    public static void battle(Character player, Monster monster) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Battle Start ===");
        System.out.println(player.getName() + " vs. " + monster.getName() + "\n");

        while (player.isAlive() && monster.isAlive()) {

            showStatus(player, monster);
            showCombatMenu();

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.println("⚔️ " + player.getName() + " performs a Basic Attack!");
                    player.performBasicAttack(monster);
                    System.out.println();
                }
                case 2 -> {
                    System.out.println("✨ " + player.getName() + " uses Special Attack!");
                    player.performSpecialAttack(monster);
                    System.out.println();
                }
                case 3 -> {
                    if (!player.hasItems()) {
                        System.out.println("🧪 Your inventory is empty.\n");
                        continue;
                    }

                    player.showInventory();
                    System.out.print("Choose an item: ");
                    int itemChoice = scanner.nextInt() - 1;
                    player.useItem(itemChoice);
                    System.out.println();
                    continue; // Using an item does NOT lose a turn
                }
                case 4 -> {
                    if (player instanceof Sorcerer sorcerer) sorcerer.toggleManaShield();
                    else if (player instanceof Druid druid) druid.toggleManaShield();
                    else System.out.println("🛡️ Mana Shield not available for your vocation.\n");
                    continue; // não perde o turno
                }
                case 5 -> {
                    if (attemptEscape()) {
                        System.out.println("🏃 " + player.getName() + " successfully escaped!");
                        return;
                    } else {
                        System.out.println("🏃 " + player.getName() + " failed to escape!\n");
                    }
                }
                default -> {
                    System.out.println("❌ Invalid option!\n");
                    continue; // não perde o turno
                }
            }

            if (monster.isAlive()) {
                System.out.println("⚔️ " + monster.getName() + " attacks!");
                monster.attack(player);
                System.out.println();
            }
        }

        // Resultado do combate
        if (player.isAlive()) {
            System.out.println("🎉 " + player.getName() + " won the battle!");
            player.gainXp(monster.getXpReward());
            System.out.println("💰 " + player.getName() + " earned " + monster.getGoldReward() + " gold.\n");
        } else {
            System.out.println("💀 " + player.getName() + " was defeated...");
        }
    }

    private static void showStatus(Character player, Monster monster) {
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
        System.out.println("3 - Use Item");
        System.out.println("4 - Toggle Mana Shield");
        System.out.println("5 - Run");
        System.out.print("Your choice: ");
    }

    private static boolean attemptEscape() {
        return Math.random() < 0.5;
    }
}