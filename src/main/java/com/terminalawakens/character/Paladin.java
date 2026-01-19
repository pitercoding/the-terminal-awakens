package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Paladin extends Character{

    public Paladin(String name) {
        super(name, 90, 60, 15, 17, 10);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target,15, "Divine Caldera");
    }
}
