package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;
import com.terminalawakens.equipment.Rod;
import com.terminalawakens.equipment.Wand;
import com.terminalawakens.equipment.Weapon;

public class Sorcerer extends Character {

    private boolean manaShieldActive = false;

    public Sorcerer(String name) {
        super(name, 60, 110, 20, 25, 8);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target, 25, "Fireball");
    }

    public void toggleManaShield() {
        manaShieldActive = !manaShieldActive;
        System.out.println("\uD83D\uDEE1\uFE0F " + name + " has " + (manaShieldActive ? "activated" : "deactivated") + " Mana Shield!\n");
    }

    @Override
    protected int applyManaShield(int damage) {
        if (!manaShieldActive) return damage;
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

    @Override
    public boolean canEquip(Weapon weapon) {
        return weapon instanceof Wand || weapon instanceof Rod;
    }

}