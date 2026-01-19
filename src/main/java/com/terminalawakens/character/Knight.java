package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Knight extends Character {

    public Knight(String name) {
        super(name,120, 30, 10, 15, 15);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target,10, "Power Strike");
    }
}
