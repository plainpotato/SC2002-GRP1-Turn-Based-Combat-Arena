package model.actions;

import model.combatants.Combatant;

import java.util.List;


public class DefendAction implements Action {

    private static final int DEFENSE_BONUS = 10;

    @Override public String getName() { 
        return "Defend"; 
    }
    
    @Override public String getDescription() { 
        return "Increase DEF by 10 for this and next round."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {

    }
}
