package model.statuses;

import model.combatants.Combatant;

public class StunEffect extends AbstractStatusEffect {

    public StunEffect() {
        super(2);
    }

    @Override public String getName() { 
        return "Stun"; 
    }

    @Override
    public void onApply(Combatant target) {
        System.out.printf("  [EFFECT] %s is now STUNNED for %d turns.%n", target.getName(), duration);
    }

    @Override
    public void onExpire(Combatant target) {
        System.out.printf("  [EFFECT] Stun on %s has expired.%n", target.getName());
    }
}
