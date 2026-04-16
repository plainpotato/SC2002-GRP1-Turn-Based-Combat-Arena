package model.combatants;

import model.actions.Action;
import model.actions.ShieldBashAction;
import ui.BattleDisplay;

/**
 * Warrior combatant.
 * HP: 260 | ATK: 40 | DEF: 20 | SPD: 30
 * Special: Shield Bash – BasicAttack damage + stun target for current + next turn.
 */

public class Warrior extends Player {

    public static final int BASE_HP      = 260;
    public static final int BASE_ATTACK  = 40;
    public static final int BASE_DEFENSE = 20;
    public static final int BASE_SPEED   = 30;

    public Warrior(String name, BattleDisplay display) {
        super(name, BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED, display);
    }

    @Override
    public Action createSpecialSkillAction() {
        return new ShieldBashAction(this);
    }

    @Override
    public String toString() {
        return String.format("Warrior %s [HP: %d/%d | ATK: %d | DEF: %d | SPD: %d | Cooldown: %d]",
                name, hp, maxHp, attack, defense, speed, specialSkillCooldown);
    }
}
