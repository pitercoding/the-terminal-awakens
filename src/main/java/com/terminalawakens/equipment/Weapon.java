package com.terminalawakens.equipment;

public class Weapon extends Equipment {

    protected Weapon(String name, int bonusAttack) {
        super(name, 0, 0, bonusAttack, 0);
    }
}
