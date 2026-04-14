package engine;

import model.combatants.*;

import java.util.ArrayList;
import java.util.List;

public enum Level {

    EASY(1, "Easy") {
        @Override
        public List<Enemy> createInitialSpawn() {
            List<Enemy> enemies = new ArrayList<>();
            enemies.add(new Goblin("Goblin A"));
            enemies.add(new Goblin("Goblin B"));
            enemies.add(new Goblin("Goblin C"));
            return enemies;
        }

        @Override
        public List<Enemy> createBackupSpawn() {
            return new ArrayList<>(); // no backup for Easy
        }
    },

    MEDIUM(2, "Medium") {
        @Override
        public List<Enemy> createInitialSpawn() {
            List<Enemy> enemies = new ArrayList<>();
            enemies.add(new Goblin("Goblin"));
            enemies.add(new Wolf("Wolf"));
            return enemies;
        }

        @Override
        public List<Enemy> createBackupSpawn() {
            List<Enemy> backup = new ArrayList<>();
            backup.add(new Wolf("Wolf A"));
            backup.add(new Wolf("Wolf B"));
            return backup;
        }
    },

    HARD(3, "Hard") {
        @Override
        public List<Enemy> createInitialSpawn() {
            List<Enemy> enemies = new ArrayList<>();
            enemies.add(new Goblin("Goblin A"));
            enemies.add(new Goblin("Goblin B"));
            return enemies;
        }

        @Override
        public List<Enemy> createBackupSpawn() {
            List<Enemy> backup = new ArrayList<>();
            backup.add(new Goblin("Goblin C"));
            backup.add(new Wolf("Wolf A"));
            backup.add(new Wolf("Wolf B"));
            return backup;
        }
    };

    private final int levelNumber;
    private final String displayName;

    Level(int levelNumber, String displayName) {
        this.levelNumber = levelNumber;
        this.displayName = displayName;
    }

    public int getLevelNumber()   { return levelNumber; }
    public String getDisplayName() { return displayName; }

    public abstract List<Enemy> createInitialSpawn();
    public abstract List<Enemy> createBackupSpawn();
}
