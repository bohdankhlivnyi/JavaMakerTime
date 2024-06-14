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
    private int attributePoints;

    public Gladiator(int constitution, int strength, int xp, int dexterity, int level, int charisma, int basicAttack, int basicHP, boolean haveShield) {
        this.constitution = constitution;
        this.strength = strength;
        this.xp = xp;
        this.dexterity = dexterity;
        this.level = level;
        this.charisma = charisma;
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.currentHP = basicHP + constitution * 5; //Initialize current HP
        this.haveShield = haveShield;
        this.attributePoints = 0; //Initialize attribute points
    }

    //Getters and Setters
    public int getConstitution() { return constitution; }
    public void setConstitution(int constitution) { this.constitution = constitution; }
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public int getDexterity() { return dexterity; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getCharisma() { return charisma; }
    public void setCharisma(int charisma) { this.charisma = charisma; }
    public int getBasicAttack() { return basicAttack; }
    public void setBasicAttack(int basicAttack) { this.basicAttack = basicAttack; }
    public int getBasicHP() { return basicHP; }
    public void setBasicHP(int basicHP) { this.basicHP = basicHP; }
    public int getCurrentHP() { return currentHP; }
    public void setCurrentHP(int currentHP) { this.currentHP = currentHP; }
    public boolean isHaveShield() { return haveShield; }
    public int getAttributePoints() { return attributePoints; }
    public void setAttributePoints(int attributePoints) { this.attributePoints = attributePoints; }
}