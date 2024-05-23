package org.example;

public class Tank extends Gladiator {
    public Tank() {
        super(20, 10, 10, 10, 100, 0.10, 15, true);
    }

    @Override
    public void specialAbility() {
        System.out.println("Tank uses Shield Wall!");
    }
}