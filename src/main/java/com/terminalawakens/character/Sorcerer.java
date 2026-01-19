package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Sorcerer extends Character {

    public Sorcerer(String name) {
        super (name, 60, 110, 20, 25, 8);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target,25, "Fireball");
    }
}
