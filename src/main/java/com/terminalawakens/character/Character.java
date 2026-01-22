package com.terminalawakens.character;

import com.terminalawakens.creatures.Monster;
import com.terminalawakens.equipment.Armor;
import com.terminalawakens.equipment.Weapon;
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
    protected String basicWeapon;
    protected Weapon equippedWeapon;
    protected Armor equippedArmor;
    protected int gold;


    // =========== Constructor =========== //
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
        this.gold = 0;
    }

    // =========== Getters =========== //
    public String getName() { return name; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getCurrentMana() { return currentMana; }
    public int getMaxMana() { return maxMana; }
    public int getBasicAttack() { return basicAttack; }
    public int getSpecialAttack() { return specialAttack; }
    public int getDefense() { return defense; }
    public int getGold() { return gold; }
    public Weapon getEquippedWeapon() {return equippedWeapon; }
    public Armor getEquippedArmor() { return equippedArmor; }
    public int getLevel() { return level; }
    public int getCurrentXp() { return currentXp; }
    public int getXpToNextLevel() { return xpToNextLevel; }

    // ************** SPECIAL METHODS ************** //

    // =========== Attack =========== //
    public void performBasicAttack(Monster target) {
        String weaponName = (equippedWeapon != null)
                ? equippedWeapon.getName()
                : "Fists";

        System.out.println("\n⚔️ " + name + " attacks with " + weaponName + "!");
        target.takeDamage(basicAttack);
    }

    public abstract void performSpecialAttack(Monster target);

    protected void castSpell(Monster target, int manaCost, String spellName) {
        if (currentMana >= manaCost) {
            System.out.println("\n" + name + " casts " + spellName + "!");
            target.takeDamage(specialAttack);
            currentMana -= manaCost;
        } else {
            System.out.println("\n" + name + " doesn't have enough mana to cast " + spellName + "!");
        }
    }

    // =========== Damage Handling =========== //
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        actualDamage = applyManaShield(actualDamage);

        currentHealth = Math.max(0, currentHealth - actualDamage);
        System.out.println(name + " took " + actualDamage + " damage. Current HP: " + currentHealth);
    }

    protected int applyManaShield(int damage) { return damage; }

    public boolean isAlive() { return currentHealth > 0; }

    // =========== XP & Level =========== //
    public void gainXp(int amount) {
        System.out.println("\n🎉 " + name + " gained " + amount + " XP!");
        currentXp += amount;

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

        System.out.println("\n✨ LEVEL UP! " + name + " reached Level " + level + "!");
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

    // =========== Healing =========== //
    public void heal(int amount) {
        if (currentHealth == 0) {
            System.out.println(name + " cannot be healed (defeated).");
            return;
        }

        int healed = Math.min(amount, maxHealth - currentHealth);
        currentHealth += healed;

        System.out.println("\n💖 " + name + " healed " + healed + " HP. Current HP: " + currentHealth);
    }

    public void restoreMana(int amount) {
        int restored = Math.min(amount, maxMana - currentMana);
        currentMana += restored;

        System.out.println("\n🔮 " + name + " restored " + restored + " Mana. Current Mana: " + currentMana + "\n");
    }

    // =========== Inventory =========== //
    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("\n📦 " + name + " obtained: " + item.getName());
    }

    public boolean hasItems() { return !inventory.isEmpty(); }

    public void showInventory() {
        System.out.println("\n--- Inventory ---");
        if (inventory.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + " - " + inventory.get(i).getName());
        }
        System.out.println("----------------\n");
    }

    public void useItem(int index) {
        if (index < 0 || index >= inventory.size()) {
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.remove(index);
        item.use(this);
    }

    // =========== Equipments =========== //
    public boolean canEquip(Weapon weapon) {
        return true; // padrão: pode usar qualquer arma
    }

    public void equipWeapon(Weapon weapon) {

        if (!canEquip(weapon)) {
            System.out.println("❌ " + name + " cannot use " + weapon.getName());
            return;
        }

        if (equippedWeapon != null) {
            System.out.println("🔁 Replacing weapon: " + equippedWeapon.getName());
            unequipWeapon();
        }

        equippedWeapon = weapon;
        basicAttack += weapon.getBonusAttack();

        System.out.println("\n⚔️ Equipped weapon: " + weapon.getName()
                + " (ATK +" + weapon.getBonusAttack() + ")");
    }

    public void unequipWeapon() {
        if (equippedWeapon == null) return;

        basicAttack -= equippedWeapon.getBonusAttack();
        System.out.println("\n⚔️ Unequipped weapon: " + equippedWeapon.getName());
        equippedWeapon = null;
    }

    public void equipArmor(Armor armor) {
        if (equippedArmor != null) {
            System.out.println("🔁 Replacing armor: " + equippedArmor.getName());
            unequipArmor();
        }

        equippedArmor = armor;
        maxHealth += armor.getBonusHealth();
        defense += armor.getBonusDefense();

        System.out.println("\n🛡️ Equipped armor: " + armor.getName()
                + " (HP +" + armor.getBonusHealth()
                + ", DEF +" + armor.getBonusDefense() + ")");
    }

    public void unequipArmor() {
        if (equippedArmor == null) return;

        maxHealth -= equippedArmor.getBonusHealth();
        defense -= equippedArmor.getBonusDefense();

        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }

        System.out.println("\n🛡️ Unequipped armor: " + equippedArmor.getName());
        equippedArmor = null;
    }

    // =========== Loots =========== //
    public void addGold(int amount) {
        gold += amount;
        System.out.println("💰 " + name + " received " + amount + " gold.");
    }
}