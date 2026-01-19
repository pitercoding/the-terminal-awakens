package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Druid extends Character{

    public Druid(String name) {
        super (name, 70, 100, 15, 20, 10);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target,20, "Nature's Wrath");
    }
}
