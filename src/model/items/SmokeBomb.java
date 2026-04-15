package model.items;

import model.combatants.Combatant;
import model.statuses.SmokeBomb;

import java.util.List;

public class SmokeBomb implements Item {

    private boolean consumed = false;

    @Override public String getName() { 
        return "Smoke Bomb"; 
    }
    
    @Override public String getDescription() { 
        return "Negate enemy damage for this and next turn."; 
    }

    @Override public boolean isConsumed() { 
        return consumed; 
    }

    @Override
    public void use(Combatant user, List<Combatant> allCombatants) {
        user.addStatusEffect(new SmokeBombEffect());
        consumed = true;
        System.out.printf("%s uses Smoke Bomb! Incoming damage is 0 for 2 turns.%n", user.getName());
    }
}
