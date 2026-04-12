package model.actions;

import java.util.List;
import java.util.stream.Collectors;


public class ArcaneBlastAction implements Action {

    private final Wizard wizard;

    public ArcaneBlastAction(Wizard wizard) {
        this.wizard = wizard;
    }

    @Override public String getName()        { 
        return "Arcane Blast (Special)"; 
    }

    @Override public String getDescription() { 
        return "Hit all enemies; kills grant +10 ATK permanently."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {
        List<Combatant> liveEnemies = allCombatants.stream()
                .filter(c -> c instanceof Enemy && c.isAlive())
                .collect(Collectors.toList());

        if (liveEnemies.isEmpty()) {
            System.out.println("No enemies to blast!");
            return;
        }
    }
}
