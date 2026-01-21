package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Knight extends Character {

    public Knight(String name) {
        super(name, 120, 30, 10, 15, 15);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        System.out.println("\n⚔️ " + name + " uses Power Strike!");
        castSpell(target, 10, "Power Strike");
    }

    @Override
    protected void increaseStats() {
        maxHealth += 15;
        maxMana += 5;
        basicAttack += 3;
        specialAttack += 1;
        defense += 2;
    }
}