package strategy;

import model.actions.Action;
import model.actions.BasicAttack;
import model.combatants.Combatant;

import java.util.List;

public class BasicAttackStrategy implements EnemyActionStrategy {

    @Override
    public Action decideAction(Combatant enemy, List<Combatant> allCombatants) {
        return new BasicAttackAction();
    }
}
