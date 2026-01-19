package com.terminalawakens.creatures;

import com.terminalawakens.character.Character;

public abstract class Monster {

    protected String name;
    protected int currentHealth;
    protected int maxHealth;
    protected int damage;
    protected int xpReward;
    protected int goldReward;

    public Monster(String name, int maxHealth, int damage, int xpReward, int goldReward) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
        this.xpReward = xpReward;
        this.goldReward = goldReward;
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

    public int getDamage() {
        return damage;
    }

    public int getXpReward() {
        return xpReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void takeDamage(int damage) {
        currentHealth = Math.max(0, currentHealth - damage);
        System.out.println(name + " took " + damage + " damage. Current HP: " + currentHealth);
    }

    public void attack(Character target) {
        System.out.println(name + " attacks " + target.getName() + " for " + damage + " damage!");
        target.takeDamage(damage);
    }
}