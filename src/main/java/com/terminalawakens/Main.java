package com.terminalawakens;

import com.terminalawakens.character.Druid;
import com.terminalawakens.character.Knight;
import com.terminalawakens.character.Paladin;
import com.terminalawakens.character.Sorcerer;

public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight("Knight Dark'Axe");
        System.out.println("Knight alive? " + knight.isAlive());
        knight.takeDamage(30);

        Sorcerer sorcerer = new Sorcerer("Cacheiro");
        System.out.println("Sorcerer alive? " + sorcerer.isAlive());
        sorcerer.takeDamage(30);

        Druid druid = new Druid("Radagast O Castanho");
        System.out.println("Druid alive? " + druid.isAlive());
        druid.takeDamage(30);

        Paladin paladin = new Paladin("Galadriel");
        System.out.println("Paladin alive? " + paladin.isAlive());
        paladin.takeDamage(30);
    }
}
