package com.terminalawakens.character;

public class Druid extends Character{

    public Druid(String name) {
        super (name, 70, 70, 100, 100, 15, 20, 10);
    }

    @Override
    public void attack() {
        System.out.println(this.name + " performs a rod attack!");
    }
}
