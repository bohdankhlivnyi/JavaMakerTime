package org.example;

public abstract class Gladiator {
    private int constitution;
    private int strength;
    private int xp;
    private int dexterity;
    private int level;
    private int charisma;
    private int basicAttack;
    private int basicHP;
    private int currentHP;
    private boolean haveShield;

    public Gladiator(int constitution, int strength, int xp, int dexterity, int level, int charisma, int basicAttack, int basicHP, boolean haveShield) {
        this.constitution = constitution;
        this.strength = strength;
        this.xp = xp;
        this.dexterity = dexterity;
        this.level = level;
        this.charisma = charisma;
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.currentHP = basicHP + constitution * 5; // Initialize current HP
        this.haveShield = haveShield;
    }

    // Getters and Setters
    public int getConstitution() { return constitution; }
    public int getStrength() { return strength; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public int getDexterity() { return dexterity; }
    public int getLevel() { return level; }
    public int getCharisma() { return charisma; }
    public int getBasicAttack() { return basicAttack; }
    public int getBasicHP() { return basicHP; }
    public int getCurrentHP() { return currentHP; }
    public void setCurrentHP(int currentHP) { this.currentHP = currentHP; }
    public boolean isHaveShield() { return haveShield; }

    // Abstract method for special ability
    public abstract void specialAbility();
}