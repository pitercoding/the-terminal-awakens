package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Sorcerer extends Character {

    private boolean manaShieldActive = false; // por padrão desativado

    public Sorcerer(String name) {
        super (name, 60, 110, 20, 25, 8);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target,25, "Fireball");
    }

    public void toggleManaShield() {
        manaShieldActive = !manaShieldActive;
        System.out.println(name + " has " + (manaShieldActive ? "activated" : "deactivated") + " Mana Shield!\n");
    }

    @Override
    protected int applyManaShield(int damage) {
        if (!manaShieldActive) return damage; // não absorve dano
        if (currentMana > 0) {
            int manaAbsorb = Math.min(currentMana, damage);
            currentMana -= manaAbsorb;
            damage -= manaAbsorb;
        }
        return damage;
    }
}
