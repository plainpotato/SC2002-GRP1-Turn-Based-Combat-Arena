package strategy;

import model.actions.Action;
import model.combatants.Combatant;

import java.util.List;

public interface EnemyActionStrategy {

    /**
     * Decide which action the enemy should take this turn.
     * @param enemy        the acting enemy
     * @param allCombatants all participants (to find targets)
     * @return the chosen Action
     */
    Action decideAction(Combatant enemy, List<Combatant> allCombatants);
}
