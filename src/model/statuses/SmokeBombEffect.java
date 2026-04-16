package model.statuses;

import model.combatants.Combatant;

public class SmokeBombEffect extends AbstractStatusEffect {

    public SmokeBombEffect() {
        super(2);
    }

    @Override public String getName() { return "Smoke Bomb"; }

    @Override
    public void onApply(Combatant target) {
        System.out.printf("  [EFFECT] %s is surrounded by smoke! Enemy attacks deal 0 damage for %d turns.%n",
                target.getName(), duration);
    }

    @Override
    public void onExpire(Combatant target) {
        System.out.printf("  [EFFECT] Smoke Bomb on %s has dissipated.%n", target.getName());
    }
}
