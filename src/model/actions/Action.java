package com.arena.model.actions;
import com.arena.model.combatants.Combatant;
import java.util.List;


public interface Action {

    String getName();

    String getDescription();

    /**
     * Execute the action.
     *
     * @param actor    the combatant performing the action
     * @param targets  all combatants in the battle (actor can pick from enemies/allies)
     */
    void execute(Combatant actor, List<Combatant> targets);
}
