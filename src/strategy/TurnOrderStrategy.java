package strategy;

import model.combatants.Combatant;

import java.util.List;

public interface TurnOrderStrategy {

    /**
     * Given all living combatants, return them in the order they should act.
     * @param combatants all living participants in the current round
     * @return ordered list (first = acts first)
     */
    List<Combatant> determineTurnOrder(List<Combatant> combatants);
}
