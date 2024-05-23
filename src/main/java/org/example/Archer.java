package org.example;

public class Archer extends Gladiator {
    public Archer() {
        super(8, 12, 18, 15, 50, 0.25, 10, false);
    }

    @Override
    public void specialAbility() {
        System.out.println("Archer uses Rapid Shot!");
    }
}