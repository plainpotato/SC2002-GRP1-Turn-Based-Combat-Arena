package ui;

import engine.BattleResult;
import engine.BattleState;
import model.actions.Action;
import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Player;
import model.items.Item;

import java.util.List;
import java.util.Scanner;

public class ConsoleBattleDisplay implements BattleDisplay {

    private static final String SEPARATOR = "=".repeat(60);
    private static final String THIN_SEP  = "-".repeat(60);

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showBattleStart(Player player, List<Enemy> enemies) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  BATTLE START!");
        System.out.println(SEPARATOR);
        System.out.printf("  Player : %s%n", player);
        System.out.println("  Enemies:");
        enemies.forEach(e -> System.out.printf("    - %s%n", e));
        System.out.println(SEPARATOR + "\n");
    }

    @Override
    public void showRoundHeader(int roundNumber) {
        System.out.println("\n" + THIN_SEP);
        System.out.printf("  ROUND %d%n", roundNumber);
        System.out.println(THIN_SEP);
    }

    @Override
    public void showTurnOrder(List<Combatant> turnOrder) {
        System.out.print("  Turn order: ");
        for (int i = 0; i < turnOrder.size(); i++) {
            System.out.print(turnOrder.get(i).getName());
            if (i < turnOrder.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\n");
    }

    @Override
    public void showTurnStart(Combatant combatant) {
        System.out.printf("-- %s's turn --%n", combatant.getName());
    }

    @Override
    public void showStunnedSkip(Combatant combatant) {
        System.out.printf("  %s is STUNNED and cannot act!%n", combatant.getName());
    }

    @Override
    public void showActionMenu(Player player, List<Action> actions) {
        System.out.println("\nChoose an action:");
        for (int i = 0; i < actions.size(); i++) {
            Action a = actions.get(i);
            System.out.printf("  [%d] %-25s - %s%n", i + 1, a.getName(), a.getDescription());
        }
    }

    @Override
    public void showItemMenu(List<Item> items) {
        System.out.println("\nChoose an item:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.printf("  [%d] %-20s - %s%n", i + 1, item.getName(), item.getDescription());
        }
    }

    @Override
    public int readPlayerChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.printf("Enter choice (%d-%d): ", min, max);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    System.out.printf("Invalid choice. Please enter a number between %d and %d.%n", min, max);
                }
            } else {
                scanner.next(); // discard bad token
                System.out.println("Please enter a valid number.");
            }
        }
        return choice;
    }

    @Override
    public void showRoundSummary(BattleState state) {
        System.out.println("\n" + THIN_SEP);
        System.out.printf("  END OF ROUND %d%n", state.getRoundNumber());
        System.out.printf("  Player: %s%n", state.getPlayer());
        System.out.println("  Enemies:");
        state.getActiveEnemies().forEach(e -> {
            String status = e.isAlive() ? String.format("HP: %d/%d", e.getHp(), e.getMaxHp()) : "X ELIMINATED";
            System.out.printf("    - %-15s %s%n", e.getName(), status);
        });
        System.out.println(THIN_SEP);
    }

    @Override
    public void showBattleEnd(BattleResult result, BattleState state) {
        System.out.println("\n" + SEPARATOR);
        if (result == BattleResult.PLAYER_VICTORY) {
            System.out.println("  VICTORY!");
            System.out.println("  Congratulations, you have defeated all your enemies.");
            System.out.println(THIN_SEP);
            System.out.printf("  Remaining HP   : %d / %d%n",
                    state.getPlayer().getHp(), state.getPlayer().getMaxHp());
            System.out.printf("  Total Rounds   : %d%n", state.getRoundNumber());
        } else {
            System.out.println("  DEFEATED.");
            System.out.println("  Don't give up, try again!");
            System.out.println(THIN_SEP);
            System.out.printf("  Enemies remaining : %d%n", state.countRemainingEnemies());
            System.out.printf("  Rounds survived   : %d%n", state.getRoundNumber());
        }
        System.out.println(SEPARATOR);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
