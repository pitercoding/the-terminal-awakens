package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;
import com.terminalawakens.equipment.Axe;
import com.terminalawakens.equipment.Club;
import com.terminalawakens.equipment.Sword;
import com.terminalawakens.equipment.Weapon;

public class Knight extends Character {

    public Knight(String name) {
        super(name, 120, 30, 13, 16, 15);
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

    @Override
    public boolean canEquip(Weapon weapon) {
        return weapon instanceof Sword
                || weapon instanceof Axe
                || weapon instanceof Club;
    }
}