package com.terminalawakens.character;

public class Paladin extends Character{
    public Paladin(String name) {
        super(name,90, 90, 60, 60, 15, 17, 10);
    }

    @Override
    public void attack() {
        System.out.println(this.name + " performs a holy wave!");
    }
}
