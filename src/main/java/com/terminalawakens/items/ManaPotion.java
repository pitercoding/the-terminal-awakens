package com.terminalawakens.items;

import com.terminalawakens.character.Character;

public class ManaPotion extends Item {

    private int manaAmount;

    public ManaPotion() {
        super("Mana Potion");
        this.manaAmount = 40; // default
    }

    public ManaPotion(int manaAmount) {
        super("Mana Potion");
        this.manaAmount = manaAmount;
    }

    @Override
    public void use(Character target) {
        target.restoreMana(manaAmount);
    }
}
