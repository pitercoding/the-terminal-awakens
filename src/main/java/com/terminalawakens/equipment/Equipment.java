package com.terminalawakens.equipment;

public abstract class Equipment {
    protected String name;
    protected int bonusHealth;
    protected int bonusMana;
    protected int bonusAttack;
    protected int bonusDefense;

    public Equipment(String name, int bonusHealth, int bonusMana, int bonusAttack, int bonusDefense) {
        this.name = name;
        this.bonusHealth = bonusHealth;
        this.bonusMana = bonusMana;
        this.bonusAttack = bonusAttack;
        this.bonusDefense = bonusDefense;
    }

    public String getName() {
        return name;
    }

    public int getBonusMana() {
        return bonusMana;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }
}
