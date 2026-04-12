package model.items;

import model.combatants.Combatant;

import java.util.List;

public interface Item {

    // Getters
    String getName();
    String getDescription();

    void use(Combatant user, List<Combatant> allCombatant);

    boolean isConsumed();
}
