package com.terminalawakens.engine;

import com.terminalawakens.character.*;
import com.terminalawakens.character.Character;
import com.terminalawakens.creatures.MonsterFactory;
import com.terminalawakens.equipment.Armor;
import com.terminalawakens.equipment.Equipment;
import com.terminalawakens.equipment.Weapon;
import com.terminalawakens.items.Item;
import com.terminalawakens.items.ItemFactory;
import com.terminalawakens.shop.Shop;
import com.terminalawakens.util.messages.GameEnd;
import com.terminalawakens.util.messages.GameStart;
import com.terminalawakens.util.messages.Portraits;
import com.terminalawakens.util.messages.SlowConsole;
import com.terminalawakens.world.GameMap;
import com.terminalawakens.world.Position;
import com.terminalawakens.world.TileType;

import java.util.List;
import java.util.Scanner;

public class GameEngine {

    private Character player;
    private final Scanner scanner;
    private GameMap gameMap = new GameMap();
    private Position playerPosition = new Position(0, 0);

    private static final String[] EMPTY_MESSAGES = {
            "🌫️ The wind whispers ancient secrets...",
            "🍃 The path is silent, yet uneasy...",
            "🕯️ You feel like something is watching you...",
            "🌑 Only darkness answers your steps..."
    };

    public GameEngine() {
        this.scanner = new Scanner(System.in);
        this.gameMap = new GameMap();
        this.playerPosition = new Position(0, 0);
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
        System.out.println("Level:       " + player.getLevel());
        System.out.println("XP:          " + player.getCurrentXp() + " / " + player.getXpToNextLevel());
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
        // 1. Mostra o título e lore do jogo antes de criar o personagem
        GameStart.showTitle();
        GameStart.startLore();

        // 2. Cria o personagem
        createPlayer();
        StarterVocationKit.applyKit(player);
        showPlayerStatus();

        boolean running = true;

        while (running && player.isAlive()) {
            System.out.println("\nChoose an action:");
            System.out.println("1 - Explore");
            System.out.println("2 - Show Player Status");
            System.out.println("3 - Visit Items Shop");
            System.out.println("4 - Visit Equipment Shop");
            System.out.println("5 - Exit");

            System.out.print("Your option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> explore();
                case "2" -> showPlayerStatus();
                case "3" -> visitItemShop();
                case "4" -> visitEquipmentShop();
                case "5" -> running = false;
                default -> System.out.println("❌ Invalid option.");
            }
        }

        System.out.println("\n💀 Game Over.");
    }

    // ===================== Explore =====================
    private void explore() {
        boolean exploring = true;

        System.out.println("\n🧭 Use W A S D to move | Q to exit exploration");

        while (exploring) {
            System.out.println();
            gameMap.printMap(playerPosition.x, playerPosition.y);

            System.out.print("Move: ");
            String input = scanner.nextLine().toUpperCase();

            int newX = playerPosition.x;
            int newY = playerPosition.y;

            switch (input) {
                case "W" -> newY--;
                case "S" -> newY++;
                case "A" -> newX--;
                case "D" -> newX++;
                case "Q" -> exploring = false;
                default -> {
                    System.out.println("❌ Invalid command");
                    continue;
                }
            }

            if (newX < 0 || newY < 0 ||
                    newX >= gameMap.getWidth() ||
                    newY >= gameMap.getHeight()) {
                System.out.println("⛔ You can't go there.");
                continue;
            }

            playerPosition.x = newX;
            playerPosition.y = newY;

            TileType tile = gameMap.getTile(newX, newY);

            switch (tile) {
                case MONSTER -> handleMonster();
                case LOOT -> handleLoot();
                case NPC -> handleNpc();
                case SHOP -> visitItemShop();
                case EMPTY -> handleEmpty();
                case BOSS -> {
                    handleBoss();
                    exploring = false; // encerra exploração
                }
            }
        }
    }

    private void handleMonster() {
        System.out.println("\n⚔️ You feel a hostile presence...\n");
        CombatEngine.battle(player, MonsterFactory.spawnRandomMonster());

        gameMap.clearTileIfConsumable(playerPosition.x, playerPosition.y);
    }

    private void handleLoot() {
        Item loot = ItemFactory.getRandomLoot();
        player.addItem(loot);

        System.out.println("\n🎁 You found: " + loot.getName() + "\n");

        gameMap.clearTileIfConsumable(playerPosition.x, playerPosition.y);
    }

    private void handleNpc() {
        String[] dialogues = {
                "The deeper you go, the quieter the world becomes.",
                "Gold fades. Wisdom doesn't.",
                "The boss waits where the map ends.",
                "You must grow stronger before facing him..."
        };

        System.out.println("\n🧙 Old Eremo:");
        String dialogue = dialogues[(int)(Math.random() * dialogues.length)];
        System.out.println("\"" + dialogue + "\"\n");
    }

    private void handleEmpty() {
        String msg = EMPTY_MESSAGES[
                (int) (Math.random() * EMPTY_MESSAGES.length)
                ];

        System.out.println("\n" + msg + "\n");

        double roll = Math.random();

        if (roll < 0.10) {
            handleMonster();
        } else if (roll < 0.15) {
            handleLoot();
        }
    }

    private void handleBoss() {
        if (player.getLevel() < 2) {
            System.out.println("\n⚠️ You feel you are not strong enough to face this foe. " +
                    "Reach at least Level 2 before challenging the boss.\n");
            return; // Sai do método, boss não aparece
        }

        System.out.println("""
        
        👑 A dark presence overwhelms the air...
        This is it.
        """);

        SlowConsole.printSlowly(Portraits.PortraitTerminalOfVortex(), 5);

        GameStart.encounterTerminal();

        CombatEngine.battle(player, MonsterFactory.spawnBoss());

        if (player.isAlive()) {
            System.out.println("🏆 You defeated the Terminal of Vortex!");

            // ================== LORE FINAL ==================
            GameEnd.finishTerminalOfVortex(player.getName());
            System.exit(0); // encerra o jogo após a lore final
        }
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
            System.out.print("Choose item (number) or 0 to exit: ");
            String itemInput = scanner.nextLine();

            if (!itemInput.matches("\\d+")) {
                System.out.println("❌ Invalid input.\n");
                continue;
            }

            int itemChoice = Integer.parseInt(itemInput);

            if (itemChoice == 0) {
                shopping = false;
                continue;
            }

            System.out.print("Quantity: ");
            String qtyInput = scanner.nextLine();

            if (!qtyInput.matches("\\d+")) {
                System.out.println("❌ Invalid quantity.\n");
                continue;
            }

            int quantity = Integer.parseInt(qtyInput);

            shop.buyItem(player, itemChoice, quantity);
            System.out.println("💰 Current gold: " + player.getGold() + "\n");
        }
    }

    // ===================== Equipment Shop =====================
    private void visitEquipmentShop() {
        List<Equipment> equipments = ItemFactory.getShopEquipments();
        List<Integer> prices = ItemFactory.getShopPrices()
                .subList(ItemFactory.getShopItems().size(), ItemFactory.getShopPrices().size());

        boolean shopping = true;

        System.out.println("\n🛡️ === Welcome to the Equipment Shop ===");

        while (shopping) {

            for (int i = 0; i < equipments.size(); i++) {
                System.out.printf(
                        "%d - %-15s | Price: %d gold%n",
                        i + 1,
                        equipments.get(i).getName(),
                        prices.get(i)
                );
            }

            System.out.println("0 - Exit shop");
            System.out.print("Your choice: ");

            String input = scanner.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("❌ Invalid input.\n");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (choice == 0) {
                shopping = false;
                continue;
            }

            if (choice < 1 || choice > equipments.size()) {
                System.out.println("❌ Invalid choice.\n");
                continue;
            }

            Equipment equipment = equipments.get(choice - 1);
            int price = prices.get(choice - 1);

            if (player.getGold() < price) {
                System.out.println("❌ Not enough gold.");
                System.out.println("💰 Current gold: " + player.getGold() + "\n");
                continue;
            }

            if (equipment instanceof Weapon w) {
                player.equipWeapon(w);
            } else if (equipment instanceof Armor a) {
                player.equipArmor(a);
            }

            player.addGold(-price);

            System.out.println("✅ " + player.getName() + " bought "
                    + equipment.getName() + " for " + price + " gold.");
            System.out.println("💰 Current gold: " + player.getGold() + "\n");
        }
    }
}