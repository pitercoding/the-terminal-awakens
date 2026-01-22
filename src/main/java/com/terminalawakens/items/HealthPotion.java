package com.terminalawakens.items;

import com.terminalawakens.character.Character;

public class HealthPotion extends Item {

    private int healAmount;

    public HealthPotion() {
        super("Health Potion");
        this.healAmount = 50; // default
    }

    public HealthPotion(int healAmount) {
        super("Health Potion");
        this.healAmount = healAmount;
    }

    @Override
    public void use(Character target) {
        target.heal(healAmount);
    }
}

