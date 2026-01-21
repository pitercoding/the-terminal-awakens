package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public class Druid extends Character {

    private boolean manaShieldActive = false; // por padrão desativado

    public Druid(String name) {
        super (name, 70, 100, 15, 20, 10);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target,20, "Nature's Wrath");
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

    @Override
    protected void increaseStats() {
        maxHealth += 5;
        maxMana += 30;
        basicAttack += 1;
        specialAttack += 4;
        defense += 1;
    }
}
