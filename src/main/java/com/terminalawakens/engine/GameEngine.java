package com.terminalawakens.engine;

import com.terminalawakens.character.*;
import com.terminalawakens.character.Character;
import com.terminalawakens.combat.CombatEngine;
import com.terminalawakens.creatures.Monster;
import com.terminalawakens.creatures.MonsterFactory;

import java.util.Scanner;

public class GameEngine {

    private Character player;
    private Scanner scanner;

    public GameEngine() {
        this.scanner = new Scanner(System.in);
        createPlayer();
        showPlayerStatus();
    }

    private void createPlayer() {
        System.out.println("=== Create your character ===");

        System.out.print("Enter your character name: ");
        String characterName = scanner.nextLine();

        System.out.print("Enter your battle phrase: ");
        String catchPhrase = scanner.nextLine();

        System.out.println("\nChoose your vocation: ");
        System.out.println("1 - Knight");
        System.out.println("2 - Paladin");
        System.out.println("3 - Druid");
        System.out.println("4 - Sorcerer");
        System.out.print("Your choice: ");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >=1 && choice <= 4) break;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid choice. Try again.");
        }

        switch (choice) {
            case 1 -> player = new Knight(characterName);
            case 2 -> player = new Paladin(characterName);
            case 3 -> player = new Druid(characterName);
            case 4 -> player = new Sorcerer(characterName);
        }

        System.out.println("\n\"" + catchPhrase + "\"");
        System.out.println("A new hero awakens... \n");
    }

    private void showPlayerStatus() {
        System.out.println("=== Character Status ===");
        System.out.println("Name: " + player.getName());
        System.out.println("Vocation: " + player.getClass().getSimpleName());
        System.out.println("HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("Mana: " + player.getCurrentMana() + "/" + player.getMaxMana());
        System.out.println("Basic Attack: " + player.getBasicAttack());
        System.out.println("Special Attack: " + player.getSpecialAttack());
        System.out.println("Defense: " + player.getDefense());
    }

    public void start() {
        System.out.println("\n=== The Terminal Awakens ===");

        boolean running = true;

        while (running && player.isAlive()) {
            System.out.println("\nChoose an action:");
            System.out.println("1 - Explore");
            System.out.println("2 - Exit");

            System.out.print("Your option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    explore();
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Game Over.");
    }

    private void explore() {
        System.out.println("\nYou venture forward into the darkness...");

        Monster monster = MonsterFactory.spawnRandomMonster();
        System.out.println("A wild " + monster.getName() + " appears!");

        CombatEngine.battle(player, monster);
    }
}
