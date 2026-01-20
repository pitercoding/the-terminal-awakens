package com.terminalawakens.engine;

import com.terminalawakens.character.Character;
import com.terminalawakens.character.Knight;
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
    }

    private void createPlayer() {
        // por enquanto fixo, depois vira menu
        this.player = new Knight("Knight Dark'Axe");
        System.out.println("Welcome, " + player.getName() + "!");
    }

    public void start() {
        System.out.println("=== The Terminal Awakens ===");

        boolean running = true;

        while (running && player.isAlive()) {
            System.out.println("\nChoose an action:");
            System.out.println("1 - Explore");
            System.out.println("2 - Exit");

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
        System.out.println("You venture forward into the darkness...");

        Monster monster = MonsterFactory.spawnRandomMonster();
        System.out.println("A wild " + monster.getName() + " appears!");

        CombatEngine.battle(player, monster);
    }
}
