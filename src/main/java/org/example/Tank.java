package org.example;

public class Tank extends Gladiator {
    public Tank() {
        super(5, 2, 0, 1, 0, 3, 10, 30, true);
    }

    @Override
    public void specialAbility() {
        System.out.println("Tank uses Shield Block!");
    }
}