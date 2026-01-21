package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;
import com.terminalawakens.items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {

    protected String name;
    protected int currentHealth;
    protected int maxHealth;
    protected int currentMana;
    protected int maxMana;
    protected int basicAttack;
    protected int specialAttack;
    protected int defense;
    protected int level;
    protected int currentXp;
    protected int xpToNextLevel;
    protected List<Item> inventory;

    // =========== Construtor =========== //
    public Character(String name, int maxHealth, int maxMana, int basicAttack, int specialAttack, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.maxMana = maxMana;
        this.currentMana = maxMana;
        this.basicAttack = basicAttack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.level = 1;
        this.currentXp = 0;
        this.xpToNextLevel = 100;
        this.inventory = new ArrayList<>();
    }

    // =========== Getters =========== //
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

    // =========== Special Methods =========== //

    // ---- Attack --- //
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

    // ---- Receiving Damage --- //
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

    // ---- Alive or Dead --- //
    public boolean isAlive() {
        return currentHealth > 0;
    }

    // ---- XP & Level ---- //
    public void gainXp(int amount) {
        currentXp += amount;
        System.out.println(name + " gained " + amount + " XP.");

        while (currentXp >= xpToNextLevel) {
            levelUp();
        }
    }

    public void levelUp() {
        currentXp -= xpToNextLevel;
        level++;
        xpToNextLevel = (int) (xpToNextLevel * 1.5);

        increaseStats();
        restoreResources();

        System.out.println("✨ LEVEL UP! " + name + " reached level " + level + "!");
    }

    protected void restoreResources() {
        currentHealth = maxHealth;
        currentMana = maxMana;
    }

    protected void increaseStats() {
        maxHealth += 10;
        maxMana += 5;
        basicAttack += 2;
        specialAttack += 2;
        defense += 1;
    }

    // ---- Healing ---- //
    public void heal(int amount) {
        if (currentHealth == 0) {
            System.out.println(name + " cannot be healed (defeated).");
            return;
        }

        int healed = Math.min(amount, maxHealth - currentHealth);
        currentHealth += healed;

        System.out.println(name + " healed " + healed + " HP. Current HP: " + currentHealth);
    }

    public void restoreMana(int amount) {
        int restored = Math.min(amount, maxMana - currentMana);
        currentMana += restored;

        System.out.println("\n" + name + " restored " + restored + " Mana. Current Mana: " + currentMana + "\n");
    }

    // ---- Inventory ---- //
    public void addItem(Item item) {
        inventory.add(item);
        System.out.println(name + " obtained: " + item.getName());
    }

    public boolean hasItems() {
        return !inventory.isEmpty();
    }

    public void showInventory() {
        System.out.println("\n--- Inventory ---");

        if (inventory.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + " - " + inventory.get(i).getName());
        }
    }

    public void useItem(int index) {
        if (index < 0 || index >= inventory.size()) {
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.remove(index);
        item.use(this);
    }
}