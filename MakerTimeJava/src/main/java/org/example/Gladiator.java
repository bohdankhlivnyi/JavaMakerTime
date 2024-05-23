package org.example;

public abstract class Gladiator {
    public int constitution;
    public int strength;
    public int xp;
    public int dexterity;
    public int level;
    public int basicAttack;
    public int basicHP;
    public double critChance;
    public int charisma;
    public boolean haveShield;

    public Gladiator(int constitution, int strength, int dexterity, int basicAttack, int basicHP, double critChance, int charisma, boolean haveShield) {
        this.constitution = constitution;
        this.strength = strength;
        this.xp = 0; // XP starts at 0
        this.dexterity = dexterity;
        this.level = 1; // Starting level
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.critChance = critChance;
        this.charisma = charisma;
        this.haveShield = haveShield;
    }

    // Getters
    public int getConstitution() { return constitution; }
    public int getStrength() { return strength; }
    public int getXp() { return xp; }
    public int getDexterity() { return dexterity; }
    public int getLevel() { return level; }
    public int getBasicAttack() { return basicAttack; }
    public int getBasicHP() { return basicHP; }
    public double getCritChance() { return critChance; }
    public int getCharisma() { return charisma; }
    public boolean getHaveShield() { return haveShield; }

    // Abstract method for special ability
    public abstract void specialAbility();
}