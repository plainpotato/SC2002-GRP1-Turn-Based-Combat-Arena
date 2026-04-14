package model.combatants;

/**
 * Goblin enemy.
 * HP: 55 | ATK: 35 | DEF: 15 | SPD: 25
 */
public class Goblin extends Enemy {

    public static final int BASE_HP      = 55;
    public static final int BASE_ATTACK  = 35;
    public static final int BASE_DEFENSE = 15;
    public static final int BASE_SPEED   = 25;

    public Goblin(String name) {
        super(name, BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
    }
}
