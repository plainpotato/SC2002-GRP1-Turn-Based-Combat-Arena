package model.statuses;

import model.combatants.Combatant;

public class DefendEffect extends AbstractStatusEffect {

    private final int bonus;

    public DefendEffect(int bonus) {
        super(2);
        this.bonus = bonus;
    }

    @Override public String getName() { 
        return "Defend"; 
    }

    @Override
    public void onApply(Combatant target) {
        target.setDefense(target.getDefense() + bonus);
        System.out.printf("  [EFFECT] %s DEF +%d (now %d).%n",
                target.getName(), bonus, target.getDefense());
    }

    @Override
    public void onExpire(Combatant target) {
        target.setDefense(target.getDefense() - bonus);
        System.out.printf("  [EFFECT] Defend on %s expired. DEF restored to %d.%n",
                target.getName(), target.getDefense());
    }
}
