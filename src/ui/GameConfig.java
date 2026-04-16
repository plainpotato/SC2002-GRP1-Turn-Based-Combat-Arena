package ui;

import engine.Level;
import model.combatants.Player;

public class GameConfig {

    private final Player player;
    private final Level level;

    public GameConfig(Player player, Level level) {
        this.player = player;
        this.level  = level;
    }

    public Player getPlayer() { 
        return player; 
    }

    public Level getLevel()  { 
        return level; 
    }
}
