package com.terminalawakens.engine;

import com.terminalawakens.character.*;
import com.terminalawakens.character.Character;
import com.terminalawakens.creatures.Monster;
import com.terminalawakens.creatures.MonsterFactory;
import com.terminalawakens.equipment.Armor;
import com.terminalawakens.equipment.Equipment;
import com.terminalawakens.equipment.Weapon;
import com.terminalawakens.items.Item;
import com.terminalawakens.items.ItemFactory;
import com.terminalawakens.shop.Shop;

import java.util.List;
import java.util.Scanner;

public class GameEngine {

    private Character player;
    private final Scanner scanner;

    public GameEngine() {
        this.scanner = new Scanner(System.in);
        createPlayer();
        StarterVocationKit.applyKit(player);
        showPlayerStatus();
    }

    // ===================== Player Creation =====================
    private void createPlayer() {
        System.out.println("=== Create Your Character ===");

        System.out.print("Enter your character name: ");
        String characterName = scanner.nextLine();

        System.out.print("Enter your battle phrase: ");
        String catchPhrase = scanner.nextLine();

        System.out.println("\nChoose your vocation:");
        System.out.println("1 - Knight");
        System.out.println("2 - Paladin");
        System.out.println("3 - Druid");
        System.out.println("4 - Sorcerer");
        System.out.print("Your choice: ");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) break;
            } catch (NumberFormatException ignored) {}
            System.out.println("❌ Invalid choice. Try again.");
        }

        switch (choice) {
            case 1 -> player = new Knight(characterName);
            case 2 -> player = new Paladin(characterName);
            case 3 -> player = new Druid(characterName);
            case 4 -> player = new Sorcerer(characterName);
        }

        System.out.println("\n🌟 " + player.getName() + " says: \"" + catchPhrase + "\"");
        System.out.println("🌟 A new hero awakens...\n");
    }

    // ===================== Show Player Status =====================
    private void showPlayerStatus() {
        System.out.println("\n=== Character Status ===");
        System.out.println("Name:        " + player.getName());
        System.out.println("Vocation:    " + player.getClass().getSimpleName());
        System.out.println("HP:          " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("Mana:        " + player.getCurrentMana() + "/" + player.getMaxMana());
        System.out.println("Basic ATK:   " + player.getBasicAttack());
        System.out.println("Special ATK: " + player.getSpecialAttack());
        System.out.println("Defense:     " + player.getDefense());

        String weaponName = (player.getEquippedWeapon() != null)
                ? player.getEquippedWeapon().getName()
                : "None";
        System.out.println("Weapon:      " + weaponName);
        System.out.println("Gold:        " + player.getGold());
    }

    // ===================== Game Loop =====================
    public void start() {
        System.out.println("\n=== The Terminal Awakens ===");

        boolean running = true;

        while (running && player.isAlive()) {
            System.out.println("\nChoose an action:");
            System.out.println("1 - Explore");
            System.out.println("2 - Exit");
            System.out.println("3 - Visit Items Shop");
            System.out.println("4 - Visit Equipment Shop");

            System.out.print("Your option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> explore();
                case "2" -> running = false;
                case "3" -> visitItemShop();
                case "4" -> visitEquipmentShop();
                default -> System.out.println("❌ Invalid option.");
            }
        }

        System.out.println("\n💀 Game Over.");
    }

    // ===================== Explore =====================
    private void explore() {
        System.out.println("\n⚔️ You venture forward into the darkness...\n");

        Monster monster = MonsterFactory.spawnRandomMonster();
        System.out.println("⚔️ A wild " + monster.getName() + " appears!\n");

        CombatEngine.battle(player, monster);
    }

    // ===================== Item Shop =====================
    private void visitItemShop() {
        List<Item> items = ItemFactory.getShopItems();
        List<Integer> prices = ItemFactory.getShopPrices().subList(0, items.size());

        Shop shop = new Shop(items, prices);
        boolean shopping = true;

        System.out.println("\n🛒 === Welcome to the Item Shop ===");

        while (shopping) {
            shop.showItems();
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (choice == 0) {
                shopping = false;
                continue;
            }

            shop.buyItem(player, choice);
            System.out.println("💰 Current gold: " + player.getGold() + "\n");
        }
    }

    // ===================== Equipment Shop =====================
    private void visitEquipmentShop() {
        List<Equipment> equipments = ItemFactory.getShopEquipments();
        List<Integer> prices = ItemFactory.getShopPrices().subList(ItemFactory.getShopItems().size(), ItemFactory.getShopPrices().size());

        boolean shopping = true;

        System.out.println("\n🛡️ === Welcome to the Equipment Shop ===");

        while (shopping) {
            for (int i = 0; i < equipments.size(); i++) {
                System.out.printf("%d - %-15s | Price: %d gold%n", i + 1, equipments.get(i).getName(), prices.get(i));
            }
            System.out.println("0 - Exit shop");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (choice == 0) {
                shopping = false;
                continue;
            }

            Equipment equipment = equipments.get(choice - 1);
            int price = prices.get(choice - 1);

            if (player.getGold() >= price) {
                if (equipment instanceof Weapon w) player.equipWeapon(w);
                else if (equipment instanceof Armor a) player.equipArmor(a);
                player.addGold(-price);
                System.out.println("✅ " + player.getName() + " bought " + equipment.getName() + " for " + price + " gold.");
                System.out.println("💰 Current gold: " + player.getGold() + "\n");
            } else {
                System.out.println("❌ Not enough gold.");
                System.out.println("💰 Current gold: " + player.getGold() + "\n");
            }
        }
    }
}