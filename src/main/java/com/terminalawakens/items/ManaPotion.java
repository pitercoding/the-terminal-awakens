package com.terminalawakens.items;

import com.terminalawakens.character.Character;

public class ManaPotion extends Item {

    private int manaAmount = 40;

    public ManaPotion() {
        super("Mana Potion");
    }

    @Override
    public void use(Character target) {
        target.restoreMana(manaAmount);
    }
}
