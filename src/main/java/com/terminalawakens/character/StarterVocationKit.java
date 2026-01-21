package com.terminalawakens.character;

import com.terminalawakens.equipment.Bow;
import com.terminalawakens.equipment.Rod;
import com.terminalawakens.equipment.Sword;
import com.terminalawakens.equipment.Wand;
import com.terminalawakens.items.HealthPotion;
import com.terminalawakens.items.ManaPotion;

public class StarterVocationKit {

    public static void applyKit(Character character) {

        if (character instanceof Knight) {
            character.equipWeapon(new Sword());
            character.addItem(new HealthPotion());
            character.addItem(new HealthPotion());
            character.addItem(new ManaPotion());

        } else if (character instanceof Paladin) {
            character.equipWeapon(new Bow());
            character.addItem(new HealthPotion());
            character.addItem(new ManaPotion());
            character.addItem(new ManaPotion());

        } else if (character instanceof Druid) {
            character.equipWeapon(new Rod());
            character.addItem(new HealthPotion());
            character.addItem(new ManaPotion());
            character.addItem(new ManaPotion());
            character.addItem(new ManaPotion());

        } else if (character instanceof Sorcerer) {
            character.equipWeapon(new Wand());
            character.addItem(new HealthPotion());
            character.addItem(new ManaPotion());
            character.addItem(new ManaPotion());
            character.addItem(new ManaPotion());
            character.addItem(new ManaPotion());
        }
    }
}
