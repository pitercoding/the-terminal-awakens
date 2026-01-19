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

    public void performSpecialAttack(Monster target) {
        int manaCost = 10;

        if (currentMana >= manaCost) {
            System.out.println(name + " uses a special attack!");
            target.takeDamage(specialAttack);
            currentMana -= manaCost;
        } else {
            System.out.println(name + " doesn't have enough mana!");
        }
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        currentHealth = Math.max(0, currentHealth - actualDamage);
        System.out.println(name + " took " + actualDamage + " damage. Current HP: " + currentHealth);
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }
}