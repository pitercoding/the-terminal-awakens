package com.terminalawakens.character;

public abstract class Character {
    protected String name;
    protected int currentHealth;
    protected int maxHealth;
    protected int currentMana;
    protected int maxMana;
    protected int basicAttack;
    protected int specialAttack;
    protected int defense;

    public Character(String name, int maxHealth, int currentHealth, int maxMana, int currentMana, int basicAttack, int specialAttack, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.maxMana = maxMana;
        this.currentMana = currentMana;
        this.basicAttack = basicAttack;
        this.specialAttack = specialAttack;
        this.defense = defense;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getBasicAttack() {
        return basicAttack;
    }

    public void setBasicAttack(int basicAttack) {
        this.basicAttack = basicAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public abstract void attack();

    public void basicAttack(Character target) {
        target.takeDamage(this.basicAttack);
    }

    public void performSpecialAttack(Character target) {
        if (this.currentMana >= 10) {
            target.takeDamage(this.specialAttack);
            this.currentMana -= 10;
        } else {
            System.out.println(this.name + " doesn't have enough mana!");
        }
    }

    public void takeDamage(int damage){
        int actualDamage = damage - this.defense;
        if (actualDamage < 0) actualDamage = 0;
        this.currentHealth -= actualDamage;
        if (this.currentHealth < 0) this.currentHealth = 0;
        System.out.println(this.name + " took " + actualDamage + " damage. Current HP: " + this.currentHealth);
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }
}
