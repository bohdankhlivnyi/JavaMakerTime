package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] monsterNames = {"Bandit", "Orc", "Troll", "Vampire", "Werewolf", "Dragon", "Zombie", "Skeleton", "Giant", "Demon", "Wendigo", "Predator"};
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
            default:
                System.out.println("Invalid choice! Defaulting to Warrior.");
                player = new Warrior();
                break;
        }

        System.out.println("You have chosen the class: " + player.getClass().getSimpleName());
        System.out.println("You have " + (player.getBasicHP() + player.getConstitution() * 5) + "hp and max attack " + (player.getBasicAttack() + player.getStrength() * 3));

        // Start the game loop
        while (true) {
            System.out.println("Do you want to start a battle? (yes/no)");
            String startBattle = scanner.next();

            if (startBattle.equalsIgnoreCase("no")) {
                break;
            }

            // Generate a random monster
            String monsterName = monsterNames[randNumber(0, monsterNames.length)];
            int monsterAttack = randNumber(5, 10) + player.getLevel();
            int monsterHP = randNumber(20, 40) + player.getLevel() * 2;

            Monster monster = new Monster(monsterName, monsterAttack, monsterHP);

            System.out.println("You encounter a " + monster.getName() + " with " + monster.getHp() + " HP and " + monster.getAttack() + " attack!");

            System.out.println("What do you want to do?");
            System.out.println("1. Fight");
            System.out.println("2. Negotiate");
            System.out.println("3. Flee");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    fight(player, monster);
                    break;
                case 2:
                    negotiate(player, monster);
                    break;
                case 3:
                    flee(player, monster);
                    break;
                default:
                    System.out.println("Invalid action! The monster attacks you!");
                    fight(player, monster);
                    break;
            }
        }
    }

    public static int randNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static void fight(Gladiator player, Monster monster) {
        // Implement fight logic here
        System.out.println("Fight initiated!");
        // Example logic for fight, can be expanded
        while (player.getCurrentHP() > 0 && monster.getHp() > 0) {
            int playerAttack = player.getBasicAttack() + player.getStrength() * 3;
            int monsterAttack = monster.getAttack();

            System.out.println("You attack the " + monster.getName() + " for " + playerAttack + " damage!");
            monster.setHp(monster.getHp() - playerAttack);

            if (monster.getHp() <= 0) {
                System.out.println("You defeated the " + monster.getName() + "!");
                player.setXp(player.getXp() + 10);
                break;
            }

            System.out.println("The " + monster.getName() + " attacks you for " + monsterAttack + " damage!");
            player.setCurrentHP(player.getCurrentHP() - monsterAttack);

            if (player.getCurrentHP() <= 0) {
                //System.out.println("You were defeated by the " + monster.getName() + "!");
                System.err.println("You were killed by the " + monster.getName() + "!");
                break;
            }
        }
    }

    public static void negotiate(Gladiator player, Monster monster) {
        int number = randNumber(0, 100);
        int number1 = randNumber(0, 50) + player.getCharisma();
        if (number1 >= number) {
            System.out.println("You successfully negotiated with the " + monster.getName() + "! You gain 10 XP.");
            player.setXp(player.getXp() + 10);
        } else {
            System.out.println("Negotiation failed! You must fight!");
            fight(player, monster);
        }
    }

    public static void flee(Gladiator player, Monster monster) {
        int number = randNumber(0, 100);
        int number1 = randNumber(0, 50) + player.getDexterity();
        if (number1 >= number) {
            System.out.println("You successfully fled from the " + monster.getName() + "!");
        } else {
            System.out.println("Flee attempt failed! You must fight!");
            fight(player, monster);
        }
    }
}