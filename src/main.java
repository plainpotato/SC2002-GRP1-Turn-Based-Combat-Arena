import engine.BattleEngine;
import engine.BattleResult;
import engine.Level;
import model.combatants.Enemy;
import model.combatants.Player;
import strategy.SpeedBasedTurnOrderStrategy;
import ui.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            BattleDisplay display = new ConsoleBattleDisplay();

            // Setup
            GameSetup setup = new GameSetup(display);
            GameConfig config = setup.run();

            Player player   = config.getPlayer();
            Level level     = config.getLevel();

            List<Enemy> initialEnemies = level.createInitialSpawn();
            List<Enemy> backupEnemies  = level.createBackupSpawn();

            // Battle
            BattleEngine engine = new BattleEngine(
                    new SpeedBasedTurnOrderStrategy(),
                    display,
                    level
            );

            BattleResult result = engine.run(player, initialEnemies, backupEnemies);

            // Replay
            System.out.println("\nWhat would you like to do?");
            System.out.println("  [1] Replay with the same settings");
            System.out.println("  [2] Start a new game");
            System.out.println("  [3] Exit");
            System.out.print("Enter choice (1-3): ");

            int choice = -1;
            while (choice < 1 || choice > 3) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next();
                }
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\nRestarting with same settings...");
                    // Fall through to while(true) with same config (just restart loop for now)
                }
                case 2 -> System.out.println("\nStarting a new game...");
                case 3 -> {
                    System.out.println("\nThanks for playing. Goodbye!");
                    running = false;
                }
            }
        }

        scanner.close();
    }
}
