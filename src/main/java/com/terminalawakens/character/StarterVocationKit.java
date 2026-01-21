package com.terminalawakens.character;

import com.terminalawakens.items.HealthPotion;
import com.terminalawakens.items.ManaPotion;

public class StarterVocationKit {

    public static void applyKit(Character character) {
        String vocation = character.getClass().getSimpleName();

        switch (vocation) {
            case "Knight" -> {
                character.setBasicWeapon("Sword");
                character.addItem(new HealthPotion());
                character.addItem(new HealthPotion());
                character.addItem(new ManaPotion());
            }
            case "Paladin" -> {
                character.setBasicWeapon("Arrow");
                character.addItem(new HealthPotion());
                character.addItem(new ManaPotion());
                character.addItem(new ManaPotion());
            }
            case "Druid" -> {
                character.setBasicWeapon("Rod");
                character.addItem(new HealthPotion());
                character.addItem(new ManaPotion());
                character.addItem(new ManaPotion());
                character.addItem(new ManaPotion());
            }
            case "Sorcerer" -> {
                character.setBasicWeapon("Wand");
                character.addItem(new HealthPotion());
                character.addItem(new ManaPotion());
                character.addItem(new ManaPotion());
                character.addItem(new ManaPotion());
                character.addItem(new ManaPotion());
            }
            default -> System.out.println("No starter kit defined for " + vocation);
        }
    }
}
