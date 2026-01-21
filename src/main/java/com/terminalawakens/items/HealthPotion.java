package com.terminalawakens.items;

import com.terminalawakens.character.Character;

public class HealthPotion extends Item {

    private final int healAmount = 50;

    public HealthPotion() {
        super("Health Potion");
    }

    @Override
    public void use(Character target) {
        target.heal(healAmount);
    }
}

