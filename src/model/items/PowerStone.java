package model.items;

//import model.actions.Action;
import model.combatants.Combatant;
//import model.combatants.Player;

import java.util.List;

public class PowerStone implements Item {
    private boolean consumed = false;

    @Override public String getName() { return "Power Stone"; }
    @Override public String getDescription() { return "Trigger special skill for free (no cooldown change)."; }
    @Override public boolean isConsumed() { return consumed; }

    @Override
    public void use(Combatant user, List<Combatant> allCombatants) {
        if (!(user instanceof Player)) {
            System.out.println("Power Stone can only be used by Player.");
            return;
        }
        
    }
    
}
