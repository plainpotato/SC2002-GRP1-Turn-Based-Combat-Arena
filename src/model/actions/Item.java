package model.actions;

import combatants.Combatant;

import java.util.List;

public class ItemAction implements Action {

    private final List<Item> inventory;
    private final BattleDisplay display;

    public ItemAction(List<Item> inventory, BattleDisplay display) {
        this.inventory = inventory;
        this.display   = display;
    }

    @Override public String getName()        { 
        return "Use Item"; 
    }

    @Override public String getDescription() { 
        return "Use an item from your inventory."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {
        if (inventory.isEmpty()) {
            System.out.println("No items available!");
            return;
        }
    }
}
