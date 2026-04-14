package model.combatants;

import model.actions.Action;
import model.actions.ArcaneBlastAction;
import ui.BattleDisplay;

/**
 * Wizard combatant.
 * HP: 200 | ATK: 50 | DEF: 10 | SPD: 20
 * Special: Arcane Blast – hits all enemies; each kill grants +10 ATK until end of level.
 */
public class Wizard extends Player {

    public static final int BASE_HP      = 200;
    public static final int BASE_ATTACK  = 50;
    public static final int BASE_DEFENSE = 10;
    public static final int BASE_SPEED   = 20;

    public Wizard(String name, BattleDisplay display) {
        super(name, BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED, display);
    }

    public void onArcaneBlastKill() {
        attack += 10;
    }

    @Override
    public Action createSpecialSkillAction() {
        return new ArcaneBlastAction(this);
    }

    @Override
    public String toString() {
        return String.format("Wizard %s [HP: %d/%d | ATK: %d | DEF: %d | SPD: %d | Cooldown: %d]",
                name, hp, maxHp, attack, defense, speed, specialSkillCooldown);
    }
}
