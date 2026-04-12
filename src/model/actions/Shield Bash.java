package model.actions;

import model.combatants.Combatant;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ShieldBashAction implements Action {

    private final Warrior warrior;

    public ShieldBashAction(Warrior warrior) {
        this.warrior = warrior;
    }

    @Override public String getName()        { 
        return "Shield Bash (Special)"; 
    }

    @Override public String getDescription() { 
        return "Deal damage + stun target for 2 turns."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {

    }

    private Combatant pickTarget(List<Combatant> allCombatants) {

    }
}
