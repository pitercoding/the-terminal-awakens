package com.terminalawakens.util;

import com.terminalawakens.character.Character;
import com.terminalawakens.equipment.*;
import com.terminalawakens.items.HealthPotion;
import com.terminalawakens.items.ManaPotion;

import java.util.Random;

public class LootGenerator {

    private static final Random random = new Random();

    // Aplica loot no personagem após derrotar um monstro
    public static void generateLoot(Character player) {

        // Health Potion – 40% chance
        if (random.nextInt(100) < 40) {
            player.addItem(new HealthPotion());
        }

        // Mana Potion – 40% chance
        if (random.nextInt(100) < 40) {
            player.addItem(new ManaPotion());
        }

        // Equipment – 20% chance
        if (random.nextInt(100) < 20) {
            Equipment equipment = randomEquipment();
            System.out.println("\n🧥 Equipment dropped: " + equipment.getName());

            // decisão simples: equipa automaticamente?
            if (equipment instanceof Weapon weapon) {
                player.equipWeapon(weapon);
            } else if (equipment instanceof Armor armor) {
                player.equipArmor(armor);
            }
        }
    }

    private static Equipment randomEquipment() {
        return switch (random.nextInt(7)) {
            case 0 -> new Sword();
            case 1 -> new Axe();
            case 2 -> new Club();
            case 3 -> new Bow();
            case 4 -> new Crossbow();
            case 5 -> new Wand();
            case 6 -> new Rod();
            default -> new Armor("Leather Armor", 10, 2);
        };
    }
}
