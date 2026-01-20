package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;

public abstract class Character {

    protected String name;
    protected int currentHealth;
    protected int maxHealth;
    protected int currentMana;
    protected int maxMana;
    protected int basicAttack;
    protected int specialAttack;
    protected int defense;

    public Character(String name, int maxHealth, int maxMana, int basicAttack, int specialAttack, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.maxMana = maxMana;
        this.currentMana = maxMana;
        this.basicAttack = basicAttack;
        this.specialAttack = specialAttack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getBasicAttack() {
        return basicAttack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void performBasicAttack(Monster target) {
        System.out.println(name + " attacks with a basic attack!");
        target.takeDamage(basicAttack);
    }

    public abstract void performSpecialAttack(Monster target);

    protected void castSpell(Monster target, int manaCost, String spellName) {
        if (currentMana >= manaCost) {
            System.out.println(name + " casts " + spellName + "!");
            target.takeDamage(specialAttack);
            currentMana -= manaCost;
        } else {
            System.out.println(name + " doesn't have enough mana!");
        }
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);

        // Aplicar mana shield se a subclasse quiser
        actualDamage = applyManaShield(actualDamage);

        currentHealth = Math.max(0, currentHealth - actualDamage);
        System.out.println(name + " took " + actualDamage + " damage. Current HP: " + currentHealth);
    }

    // Subclasses podem sobrescrever se quiserem
    protected int applyManaShield(int damage) {
        return damage;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }
}