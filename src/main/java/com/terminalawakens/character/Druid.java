package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;
import com.terminalawakens.equipment.Bow;
import com.terminalawakens.equipment.Rod;
import com.terminalawakens.equipment.Weapon;

public class Druid extends Character {

    private boolean manaShieldActive = false;

    public Druid(String name) {
        super(name, 70, 100, 16, 22, 10);
    }

    @Override
    public void performSpecialAttack(Monster target) {
        castSpell(target, 20, "Nature's Wrath");
    }

    public void toggleManaShield() {
        manaShieldActive = !manaShieldActive;
        System.out.println("\uD83D\uDEE1\uFE0F " + name + " has " + (manaShieldActive ? "activated" : "deactivated") + " Mana Shield!\n");
    }

    @Override
    protected int applyManaShield(int damage) {
        if (!manaShieldActive || currentMana <= 0) return damage;

        int absorbableDamage = (int) (damage * 0.70); // 70% absorção
        int manaCost = Math.min(currentMana, absorbableDamage);

        currentMana -= manaCost;

        int finalDamage = damage - manaCost;

        if (currentMana == 0) {
            manaShieldActive = false;
            System.out.println("🔮 Mana Shield broke!");
        }

        return finalDamage;
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
        return weapon instanceof Rod || weapon instanceof Bow;
    }

}