package com.terminalawakens.items;

import com.terminalawakens.equipment.Armor;
import com.terminalawakens.equipment.Equipment;
import com.terminalawakens.equipment.Weapon;

import java.util.List;

public class ItemFactory {

    public static List<Item> getShopItems() {
        return List.of(
                new HealthPotion(50),
                new ManaPotion(40)
        );
    }

    public static List<Equipment> getShopEquipments() {
        return List.of(
                new Weapon("Iron Sword", 5),
                new Armor("Leather Armor", 20, 5)
        );
    }

    public static List<Integer> getShopPrices() {
        return List.of(
                50,   // Health Potion
                40,   // Mana Potion
                150,  // Iron Sword
                100   // Leather Armor
        );
    }
}
