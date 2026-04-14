package strategy;

import model.actions.Action;
import model.actions.Basic Attack;
import model.combatants.Combatant;

import java.util.List;

/**
 * Default enemy strategy: always perform BasicAttack.
 */
public class BasicAttackStrategy implements EnemyActionStrategy {

    @Override
    public Action decideAction(Combatant enemy, List<Combatant> allCombatants) {
        return new BasicAttackAction();
    }
}
