package ui;

import engine.Level;
import model.combatants.Player;
import model.combatants.Warrior;
import model.combatants.Wizard;
import model.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSetup {

    private static final String SEPARATOR = "=".repeat(60);
    private static final String THIN_SEP  = "-".repeat(60);

    private final Scanner scanner = new Scanner(System.in);
    private final BattleDisplay display;

    public GameSetup(BattleDisplay display) {
        this.display = display;
    }

    public GameConfig run() {
        showWelcome();
        Player player = selectPlayer();
        selectItems(player);
        Level level = selectLevel();
        return new GameConfig(player, level);
    }

    private void showWelcome() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("       TURN-BASED COMBAT ARENA");
        System.out.println(SEPARATOR);

        System.out.println("\n  -- PLAYER CLASSES --");
        System.out.println(THIN_SEP);
        System.out.printf("  [1] Warrior%n");
        System.out.printf("      HP: 260 | ATK: 40 | DEF: 20 | SPD: 30%n");
        System.out.printf("      Special: Shield Bash - deal damage + stun target for 2 turns%n%n");
        System.out.printf("  [2] Wizard%n");
        System.out.printf("      HP: 200 | ATK: 50 | DEF: 10 | SPD: 20%n");
        System.out.printf("      Special: Arcane Blast - hit all enemies; kills grant +10 ATK%n");

        System.out.println("\n  -- ENEMIES --");
        System.out.println(THIN_SEP);
        System.out.printf("  Goblin  - HP: 55 | ATK: 35 | DEF: 15 | SPD: 25%n");
        System.out.printf("  Wolf    - HP: 40 | ATK: 45 | DEF:  5 | SPD: 35%n");

        System.out.println("\n  -- ITEMS --");
        System.out.println(THIN_SEP);
        System.out.printf("  [1] Potion      - Restore 100 HP (capped at max)%n");
        System.out.printf("  [2] Power Stone - Free use of your special skill (no cooldown change)%n");
        System.out.printf("  [3] Smoke Bomb  - Negate all enemy damage for 2 turns%n");

        System.out.println("\n  -- DIFFICULTY LEVELS --");
        System.out.println(THIN_SEP);
        System.out.printf("  [1] Easy   - 3 Goblins%n");
        System.out.printf("  [2] Medium - 1 Goblin + 1 Wolf; Backup: 2 Wolves%n");
        System.out.printf("  [3] Hard   - 2 Goblins;          Backup: 1 Goblin + 2 Wolves%n");
        System.out.println(SEPARATOR + "\n");
    }

    private Player selectPlayer() {
        System.out.println("Select your class:");
        System.out.println("  [1] Warrior");
        System.out.println("  [2] Wizard");

        BattleDisplay bd = display;
        int choice = readChoice(1, 2);
        ConsoleBattleDisplay cbd = (ConsoleBattleDisplay) display;

        return switch (choice) {
            case 1 -> new Warrior("Warrior", display);
            case 2 -> new Wizard("Wizard", display);
            default -> throw new IllegalStateException("Unexpected choice: " + choice);
        };
    }

    private void selectItems(Player player) {
        System.out.println("\nSelect your first item:");
        showItemOptions();
        Item item1 = buildItem(readChoice(1, 3));

        System.out.println("\nSelect your second item (duplicates allowed):");
        showItemOptions();
        Item item2 = buildItem(readChoice(1, 3));

        player.addItem(item1);
        player.addItem(item2);

        System.out.printf("%nItems chosen: %s + %s%n", item1.getName(), item2.getName());
    }

    private void showItemOptions() {
        System.out.println("  [1] Potion");
        System.out.println("  [2] Power Stone");
        System.out.println("  [3] Smoke Bomb");
    }

    private Item buildItem(int choice) {
        return switch (choice) {
            case 1 -> new Potion();
            case 2 -> new PowerStone();
            case 3 -> new SmokeBomb();
            default -> throw new IllegalStateException("Unexpected item choice: " + choice);
        };
    }

    private Level selectLevel() {
        System.out.println("\nSelect difficulty:");
        System.out.println("  [1] Easy");
        System.out.println("  [2] Medium");
        System.out.println("  [3] Hard");

        int choice = readChoice(1, 3);
        return switch (choice) {
            case 1 -> Level.EASY;
            case 2 -> Level.MEDIUM;
            case 3 -> Level.HARD;
            default -> throw new IllegalStateException("Unexpected level choice: " + choice);
        };
    }

    private int readChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.printf("Enter choice (%d-%d): ", min, max);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                }
            } else {
                scanner.next();
                System.out.println("Please enter a valid number.");
            }
        }
        return choice;
    }
}
