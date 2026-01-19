package com.terminalawakens.character;

public class Sorcerer extends Character {

    public Sorcerer(String name) {
        super (name, 60, 60, 110, 110, 20, 25, 8);
    }

    @Override
    public void attack() {
        System.out.println(this.name + " performs a wand attack!");
    }
}
