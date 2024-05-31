package org.example;

public class Monster {
    private String name;
    private int attack;
    private int hp;

    public Monster(String name, int attack, int hp) {
        this.name = name;
        this.attack = attack;
        this.hp = hp;
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getAttack() { return attack; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
}