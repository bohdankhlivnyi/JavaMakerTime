package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int needXPToNextLevel = 100;
    public static String[] monsterNames = {"Bandit", "Orc", "Troll", "Vampire", "Werewolf", "Dragon", "Zombie", "Skeleton", "Giant", "Demon", "Wendigo", "Predator"};
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
            default:
                System.out.println("Defaulting to Warrior.");
                player = new Warrior();
                break;
        }

        System.out.println("You have chosen the class: " + player.getClass().getSimpleName());
        System.out.println("You have " + player.getCurrentHP() + "hp and max attack " + (player.getBasicAttack() + player.getStrength() * 3));
        
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Start travel");
            System.out.println("2. Show attributes");
            System.out.println("3. Exit");

            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    startTravel(player, scanner, monsterNames);
                    break;
                case 2:
                    showAttributes(player);
                    break;
                case 3:
                    System.out.println("Exiting game...");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again.");
                    break;
            }
        }
    }

    public static void startTravel(Gladiator player, Scanner scanner, String[] monsterNames) {
        while (true) {
            System.out.println("Do you want to start a battle? (yes/no)");
            String startBattle = scanner.next();

            if (startBattle.equalsIgnoreCase("no")) {
                break;
            }

            String monsterName = monsterNames[randNumber(0, monsterNames.length)];
            int monsterAttack = randNumber(5, 15) + player.getLevel() * 2;
            int monsterHP = randNumber(20, 40) + player.getLevel() * 5;

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

            // Після бою HP відновлюється
            player.setCurrentHP(player.getBasicHP() + player.getConstitution() * 5);
        }
    }

    public static void fight(Gladiator player, Monster monster) {
        System.out.println("Fight initiated!");

        int giveXP = (int) Math.sqrt(monster.getHp() * monster.getAttack()) / 2;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (player.getCurrentHP() > 0 && monster.getHp() > 0) {
            // Хід гладіатора
            System.out.println("Your turn. What do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Block");

            int choice = scanner.nextInt();

            int playerAttack;
            int monsterAttack = monster.getAttack();
            boolean playerBlocked = false;

            switch (choice) {
                case 1: // Атака
                    playerAttack = randNumber(player.getBasicAttack(), player.getBasicAttack() + player.getStrength() * 3);
                    monster.setHp(monster.getHp() - playerAttack);
                    System.out.println("You attack the " + monster.getName() + " for " + playerAttack + " damage!");
                    break;
                case 2: // Блок
                    playerBlocked = true;
                    System.out.println("You block the attack!");
                    if (player.isHaveShield()) {
                        monsterAttack /= 5;
                    } else {
                        monsterAttack /= 2;
                    }
                    break;
                default:
                    System.out.println("Invalid choice! You hesitate and lose your turn!");
                    break;
            }

            if (monster.getHp() <= 0) {
                System.out.println("You defeated the " + monster.getName() + "!");
                player.setXp(player.getXp() + giveXP);
                System.out.println("You gained " + giveXP + " XP!");
                if (player.getXp() >= needXPToNextLevel) {
                    levelUp(player);
                }
                break;
            }

            // Пауза перед ходом
            try {
                Thread.sleep(250); // Пауза на 250 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Хід монстра
            System.out.println("The " + monster.getName() + " attacks you for " + monsterAttack + " damage!");
            if (!playerBlocked) {
                player.setCurrentHP(player.getCurrentHP() - monsterAttack);
            }

            if (player.getCurrentHP() <= 0) {
                System.err.println("You were killed by the " + monster.getName() + "!");
                loseXP(player);
                showMenuAfterDeath(player);
                return;
            }
        }
    }

    public static void negotiate(Gladiator player, Monster monster) {
        int number = randNumber(0, 100);
        int number1 = randNumber(0, 50) + player.getCharisma();
        if (number1 >= number) {
            System.out.println("You successfully negotiated with the " + monster.getName() + "! You gain 10 XP.");
            player.setXp(player.getXp() + 10);
            if (player.getXp() >= needXPToNextLevel) {
                levelUp(player);
            }
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

    public static void levelUp(Gladiator player) {
        Scanner scanner = new Scanner(System.in);
        player.setLevel(player.getLevel() + 1);
        player.setAttributePoints(player.getAttributePoints() + 5);
        needXPToNextLevel += 100;
        System.out.println("Congratulations! You've reached level " + player.getLevel() + " and gained 5 attribute points.");
        System.out.println("Which attributes would you like to improve?");
        System.out.println("1. Constitution");
        System.out.println("2. Strength");
        System.out.println("3. Dexterity");
        System.out.println("4. Charisma");

        for (int i = 0; i < 5; i++) {
            System.out.println("You have " + player.getAttributePoints() + " attribute points left.");
            int improvementChoice = scanner.nextInt();

            switch (improvementChoice) {
                case 1:
                    player.setConstitution(player.getConstitution() + 1);
                    break;
                case 2:
                    player.setStrength(player.getStrength() + 1);
                    break;
                case 3:
                    player.setDexterity(player.getDexterity() + 1);
                    break;
                case 4:
                    player.setCharisma(player.getCharisma() + 1);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            player.setAttributePoints(player.getAttributePoints() - 1);
        }
        player.setCurrentHP(player.getBasicHP() + player.getConstitution() * 5);
    }

    public static void loseXP(Gladiator player) {
        player.setXp(player.getXp() - player.getXp() / 2);
        System.err.println("You lose " + player.getXp() / 2 + " XP!");
        if (player.getXp() < 0) {
            player.setXp(0);
        }
    }

    public static void showAttributes(Gladiator player) {
        System.out.println("Attributes:");
        System.out.println("1. Constitution: " + player.getConstitution());
        System.out.println("2. Strength: " + player.getStrength());
        System.out.println("3. Dexterity: " + player.getDexterity());
        System.out.println("4. Charisma: " + player.getCharisma());

        System.out.println("Current XP: " + player.getXp());
        System.out.println("XP needed for next level: " + (needXPToNextLevel - player.getXp()));
        System.out.println("Level: " + player.getLevel());
        System.out.println("Current HP: " + player.getCurrentHP());
        System.out.println("Basic Attack: " + player.getBasicAttack());
        System.out.println("Shield: " + (player.isHaveShield() ? "Yes" : "No"));
    }

    public static void showMenuAfterDeath(Gladiator player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Start travel again");
        System.out.println("2. Show attributes");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                //startTravel(player, scanner, new String[]{"Bandit", "Orc", "Troll", "Vampire", "Werewolf", "Dragon", "Zombie", "Skeleton", "Giant", "Demon", "Wendigo", "Predator"});
                startTravel(player, scanner, monsterNames);
                break;
            case 2:
                showAttributes(player);
                break;
            case 3:
                System.out.println("Exiting game...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please choose again.");
                break;
        }
    }

    public static int randNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
}