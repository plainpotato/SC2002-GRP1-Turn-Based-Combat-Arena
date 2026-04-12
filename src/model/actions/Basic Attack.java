package model.actions;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BasicAttackAction implements Action {

    @Override
    public String getName() { 
        return "Basic Attack"; 
    }

    @Override
    public String getDescription() { 
        return "Attack a single target."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {

    }

    private Combatant pickTarget(Combatant actor, List<Combatant> allCombatants) {

    }
}
