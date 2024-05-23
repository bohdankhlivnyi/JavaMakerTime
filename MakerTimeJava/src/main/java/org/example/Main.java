package org.example;

import org.example.Archer;
import org.example.Tank;
import org.example.Warrior;
import org.example.Gladiator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Gladiator Game!");
        System.out.println("Choose your gladiator class:");
        System.out.println("1. Archer - High dexterity and attack.");
        System.out.println("2. Tank - High health and defense.");
        System.out.println("3. Warrior - High strength and balanced attributes.");

        int choice = scanner.nextInt();
        Gladiator player = null;

        switch (choice) {
            case 1:
                player = new Archer();
                break;
            case 2:
                player = new Tank();
                break;
            case 3:
                player = new Warrior();
                break;
            default://Якщо вибрали персонажа який не існує
                System.out.println("Invalid choice! Defaulting to Warrior.");
                player = new Warrior();
                break;
        }

        System.out.println("You have chosen the class: " + player.getClass().getSimpleName());
        System.out.println("You have " + (player.getBasicHP() + player.getConstitution() * 5) + "hp and max attack " + (player.getBasicAttack() + player.getStrength() * 3));
    }
}