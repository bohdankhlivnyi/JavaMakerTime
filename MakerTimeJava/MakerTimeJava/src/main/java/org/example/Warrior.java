package org.example;

public class Warrior extends Gladiator {
    public Warrior() {
        super(12, 18, 12, 20, 75, 0.15, 12, false);
    }

    @Override
    public void specialAbility() {
        System.out.println("Warrior uses Power Strike!");
    }
}