package model.combatants;

/**
 * Wolf enemy.
 * HP: 40 | ATK: 45 | DEF: 5 | SPD: 35
 */
public class Wolf extends Enemy {

    public static final int BASE_HP      = 40;
    public static final int BASE_ATTACK  = 45;
    public static final int BASE_DEFENSE = 5;
    public static final int BASE_SPEED   = 35;

    public Wolf(String name) {
        super(name, BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED);
    }
}
