package model.items;

import model.combatants.Combatant;

import java.util.List;

public class Potion implements Item {
    private static final int MAX_HEAL_AMOUNT = 100;
    private boolean consumed = false;

    @Override
    public String getName() {
        return "Potion";
    }

    @Override
    public String getDescription() {
        return "Restores 100 HP";
    }

    @Override public boolean isConsumed() { return consumed; }

    @Override
    public void use(Combatant user, List<Combatant> allCombatants) {
        int before = user.getHp();
        user.heal(MAX_HEAL_AMOUNT);
        int after = user.getHp();
        consumed = true;
        System.out.printf("%s used a Potion! HP %d -> %d (+%d).%n", user.getName(), before, after, after - before);
    }
    
}
