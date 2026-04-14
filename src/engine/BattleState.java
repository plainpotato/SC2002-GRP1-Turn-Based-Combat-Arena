package engine;

import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleState {

    private final Player player;
    private final List<Enemy> allEnemies;   // grows when backup spawns
    private final List<Enemy> activeEnemies; // currently fighting

    private int roundNumber = 0;
    private boolean backupSpawned = false;

    public BattleState(Player player, List<Enemy> initialEnemies) {
        this.player       = player;
        this.allEnemies   = new ArrayList<>(initialEnemies);
        this.activeEnemies = new ArrayList<>(initialEnemies);
    }

    public Player getPlayer()               { return player; }
    public List<Enemy> getActiveEnemies()   { return new ArrayList<>(activeEnemies); }
    public int getRoundNumber()             { return roundNumber; }
    public boolean isBackupSpawned()        { return backupSpawned; }

    public List<Combatant> getLivingCombatants() {
        List<Combatant> all = new ArrayList<>();
        if (player.isAlive()) all.add(player);
        activeEnemies.stream().filter(Combatant::isAlive).forEach(all::add);
        return all;
    }

    public List<Combatant> getAllCombatants() {
        List<Combatant> all = new ArrayList<>();
        all.add(player);
        all.addAll(activeEnemies);
        return all;
    }

    public void incrementRound() { roundNumber++; }

    public boolean allActiveEnemiesEliminated() {
        return activeEnemies.stream().noneMatch(Combatant::isAlive);
    }

    public void spawnBackup(List<Enemy> backup) {
        if (!backupSpawned) {
            activeEnemies.addAll(backup);
            allEnemies.addAll(backup);
            backupSpawned = true;
            System.out.println("\n*** BACKUP SPAWN! ***");
            backup.forEach(e -> System.out.printf("  -> %s has entered the battle!%n", e.getName()));
            System.out.println();
        }
    }

    public boolean isPlayerDefeated() {
        return !player.isAlive();
    }

    public boolean isPlayerVictorious() {
        return allActiveEnemiesEliminated();
    }

    public int countRemainingEnemies() {
        return (int) activeEnemies.stream().filter(Combatant::isAlive).count();
    }
}
