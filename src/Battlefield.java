import Unit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battlefield {
    public Battlefield(int n) {
        // Створення екземпляру класу Battlefield
        // Задання користувачем команди для битви
        setBattleTeams(n);
        Tank.PrintAcii();
    }

    // Функція для задання користувачами команди для битви n vs n
    public void setBattleTeams(int n) {
        // Заповнення масиву союзників
        System.out.println("Задайте команду союзників:");
        Tank[] alliesTanks = createTanksFromFile("D:\\Java Programing\\Lab3_final\\src\\allies_tanks.txt", n);
        Aviation[] alliesAviation = createAviationFromFile("D:\\Java Programing\\Lab3_final\\src\\allies_aviation.txt", n);
        Infantry[] alliesInfantry = createInfantryFromFile("D:\\Java Programing\\Lab3_final\\src\\allies_infantry.txt", n);
        Artillery[] alliesArtillery = createArtilleryFromFile("D:\\Java Programing\\Lab3_final\\src\\allies_artillery.txt", n);

        // Заповнення масиву противників
        System.out.println("Задайте команду противників:");
        Tank[] enemyTanks = createTanksFromFile("D:\\Java Programing\\Lab3_final\\src\\enemy_tanks.txt", n);
        Aviation[] enemyAviation = createAviationFromFile("D:\\Java Programing\\Lab3_final\\src\\enemy_aviation.txt", n);
        Infantry[] enemyInfantry = createInfantryFromFile("D:\\Java Programing\\Lab3_final\\src\\enemy_infantry.txt", n);
        Artillery[] enemyArtillery = createArtilleryFromFile("D:\\Java Programing\\Lab3_final\\src\\enemy_artillery.txt", n);


        printTeam(alliesTanks, alliesAviation, alliesInfantry, alliesArtillery);
        printTeam(enemyTanks, enemyAviation, enemyInfantry, enemyArtillery);

        battle(alliesTanks, alliesAviation, alliesInfantry, alliesArtillery, enemyTanks, enemyAviation, enemyInfantry, enemyArtillery);
        System.out.println("\n\nAllies alive");
        printAllAlive(alliesTanks, alliesAviation, alliesInfantry, alliesArtillery);
        System.out.println("\n\nEnemies alive");
        printAllAlive(enemyTanks, enemyAviation, enemyInfantry, enemyArtillery);

        // Тут можна додати подальші дії або вивід інформації про створені команди
    }

    // Метод для створення масиву танків з файлу
    private Tank[] createTanksFromFile(String filename, int count) {
        Tank[] tanks = new Tank[count];
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            for (int i = 0; i < count && fileScanner.hasNext(); i++) {
                String model = fileScanner.nextLine();
                int hp = fileScanner.nextInt();
                int armor = fileScanner.nextInt();
                int firepower = fileScanner.nextInt();
                fileScanner.nextLine(); // Пропустимо рядок

                tanks[i] = new Tank(model, hp, armor, firepower);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tanks;
    }

    // Метод для створення масиву літаків з файлу
    private Aviation[] createAviationFromFile(String filename, int count) {
        Aviation[] aviation = new Aviation[count];
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            for (int i = 0; i < count && fileScanner.hasNext(); i++) {
                String model = fileScanner.nextLine();
                int hp = fileScanner.nextInt();
                fileScanner.nextLine(); // Пропустимо рядок
                String aircraftType = fileScanner.nextLine();
                int speed = fileScanner.nextInt();
                int damage = fileScanner.nextInt();
                fileScanner.nextLine(); // Пропустимо рядок

                aviation[i] = new Aviation(model, hp, aircraftType, speed, damage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return aviation;
    }

    // Метод для створення масиву піхоти з файлу
    private Infantry[] createInfantryFromFile(String filename, int count) {
        Infantry[] infantry = new Infantry[count];
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            for (int i = 0; i < count && fileScanner.hasNext(); i++) {
                String name = fileScanner.nextLine();
                int hp = fileScanner.nextInt();
                fileScanner.nextLine(); // Пропустимо рядок
                String weaponType = fileScanner.nextLine();
                int damage = fileScanner.nextInt();
                fileScanner.nextLine(); // Пропустимо рядок

                infantry[i] = new Infantry(name, hp, weaponType, damage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return infantry;
    }

    // Метод для створення масиву артилерії з файлу
    private Artillery[] createArtilleryFromFile(String filename, int count) {
        Artillery[] artillery = new Artillery[count];
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            for (int i = 0; i < count && fileScanner.hasNext(); i++) {
                String model = fileScanner.nextLine();
                int hp = fileScanner.nextInt();
                int calibre = fileScanner.nextInt();
                int range = fileScanner.nextInt();
                fileScanner.nextLine(); // Пропустимо рядок

                artillery[i] = new Artillery(model, hp, calibre, range);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return artillery;
    }


        // Метод для проведення повноцінної битви між командами
    public void battle(Tank[] alliesTanks, Aviation[] alliesAviation, Infantry[] alliesInfantry, Artillery[] alliesArtillery,
                       Tank[] enemyTanks, Aviation[] enemyAviation, Infantry[] enemyInfantry, Artillery[] enemyArtillery) {
        int i = 0;
        while (true) {
            // Битва між танками
            battleUnits(alliesTanks, enemyTanks);

            // Битва між літаками
            battleUnits(alliesAviation, enemyAviation);

            // Битва між піхотою
            battleUnits(alliesInfantry, enemyInfantry);

            // Битва між артилерією
            battleUnits(alliesArtillery, enemyArtillery);

            // Додаткові дії після битви
            if (checkVictory(alliesTanks, alliesAviation, alliesInfantry, alliesArtillery)) {
                System.out.println("Enemy has won the battle!");
                break;
            } else if (checkVictory(enemyTanks, enemyAviation, enemyInfantry, enemyArtillery)) {
                System.out.println("Allies have won the battle!");
                break;
            }else if (i > 100) {
                System.out.println("Forces are retreating from both sides, it is a draw...");
                break;
            } else {
                i++;
            }

        }
    }

        // Метод для проведення битви між однотипними військовими одиницями
    private <T extends MilitaryUnit> void battleUnits(T[] allies, T[] enemies) {
        Random random = new Random();

        for (T ally : allies) {
            for (T enemy : enemies) {
                // Симуляція атаки з ймовірністю в 50%
                if (random.nextBoolean()) {
                    simulateAttack(ally, enemy);
                } else {
                    simulateAttack(enemy, ally);
                }
            }
        }
    }
    // Метод для симуляції атаки між двома військовими одиницями
    private void simulateAttack(MilitaryUnit attacker, MilitaryUnit target) {
        if (attacker.getHealth() <= 0) {
            //System.out.println("Cannot carry out attack");
            return;
        }
        int damage = attacker.getFirepower();
        target.receiveDamage(damage);
        System.out.println(attacker.getName() + " атакує " + target.getName() + " і завдає " + damage + " урону.");
    }

    // Метод для розрахунку завданого удару


    // Метод для обробки результатів битви

    private boolean checkVictory(Tank[] tanks, Aviation[] aviation, Infantry[] infantry, Artillery[] artillery) {
        // Перевіряємо, чи всі військові одиниці знищені
        for (Tank tank : tanks) {
            if (!tank.isDestroyed()) {
                return false;
            }
        }

        for (Aviation plane : aviation) {
            if (!plane.isDestroyed()) {
                return false;
            }
        }

        for (Infantry soldier : infantry) {
            if (!soldier.isDestroyed()) {
                return false;
            }
        }

        for (Artillery artilleryUnit : artillery) {
            if (!artilleryUnit.isDestroyed()) {
                return false;
            }
        }

        return true;
    }
    private void printAllAlive(Tank[] tanks, Aviation[] aviation, Infantry[] infantry, Artillery[] artillery) {
        for (Tank tank : tanks) {
            if (!tank.isDestroyed()) {
                System.out.println(tank.getName());
            }
        }

        for (Aviation plane : aviation) {
            if (!plane.isDestroyed()) {
                System.out.println(plane.getName());
            }
        }

        for (Infantry soldier : infantry) {
            if (!soldier.isDestroyed()) {
                System.out.println(soldier.getName());
            }
        }

        for (Artillery artilleryUnit : artillery) {
            if (!artilleryUnit.isDestroyed()) {
                System.out.println(artilleryUnit.getName());
            }
        }
    }
    public void printTeam(Tank[] alliesTanks, Aviation[] alliesAviation, Infantry[] alliesInfantry, Artillery[] alliesArtillery) {
        System.out.println("\n\n !      /-----\\============@\n" +
                " |_____/_______\\_____\n" +
                "/____________________\\\n" +
                " \\+__+__+__+__+__+__*/");
        for (Tank tank : alliesTanks) {
            System.out.println("  " + tank);
        }

        System.out.println("\n\n ------\n" +
                "| | # \\                                      |\n" +
                "| ____ \\_________|----^\"|\"\"\"\"\"|\"\\___________ |\n" +
                "   \\___\\   FO + 94 >>    `\"\"\"\"\"\"\"\"     =====  \"|D\n" +
                "       ^^-------____--\"\"\"\"\"\"\"\"\"\"+\"\"--_  __--\"|\n" +
                "                   `\"\"|\"-->####)+---|`\"\"     |\n" +
                "                                 \\  \\\n" +
                "                                <- O -)");
        for (Aviation aviation : alliesAviation) {
            System.out.println("  " + aviation);
        }

        System.out.println("\n\n                           ______\n" +
                "        |\\_______________ (_____\\\\______________\n" +
                "HH======#H###############H#######################\n" +
                "        ' ~\"\"\"\"\"\"\"\"\"\"\"\"\"\"`##(_))#H\\\"\"\"\"\"Y########\n" +
                "                          ))    \\#H\\       `\"Y###\n" +
                "                          \"      }#H)");
        for (Infantry infantry : alliesInfantry) {
            System.out.println("  " + infantry);
        }

            System.out.println("\n\n   ⣶⣶⣶⣦⣤⣤⣤⣤⣤⣤     ⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⠟⠋⠉⢉⡉⠙⢿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⠇⠀⠺⣦⣾⣇⣀⠀⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⣠⣾⣿⠟⠀⢸⡄⠰⠖⢻⡿⣿⡉⠀⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⢀⣠⣴⣿⡿⠃⠀⠀⠈⢷⣄⠀⠛⠁⠈⢁⣴⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠸⠿⠿⠿⠟⠀⠀⠀⠀⠀⠀⠙⠛⠷⠶⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        for (Artillery artillery : alliesArtillery) {
            System.out.println("  " + artillery);
        }
    }


}
