package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;
import com.terminalawakens.equipment.*;

public class Paladin extends Character {

    public Paladin(String name) {
        super(name, 90, 60, 16, 17, 10);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        System.out.println("\n✨ " + name + " uses Divine Caldera!");
        castSpell(target, 15, "Divine Caldera");
    }

    @Override
    protected void increaseStats() {
        maxHealth += 10;
        maxMana += 15;
        basicAttack += 3;
        specialAttack += 1;
        defense += 2;
    }

    @Override
    public boolean canEquip(Weapon weapon) {
        return weapon instanceof Sword
                || weapon instanceof Bow
                || weapon instanceof Crossbow;
    }
}