package com.terminalawakens.character;

public class Knight extends Character {

    public Knight(String name) {
        super(name,120, 120, 30, 30, 10, 15, 15);
    }

    @Override
    public void attack() {
        System.out.println(this.name + " performs a axe slash!");
    }
}
