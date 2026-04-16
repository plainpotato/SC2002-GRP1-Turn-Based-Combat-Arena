package model.statuses;

import model.combatants.Combatant;

public interface StatusEffect {
    String getName();

    int getDuration();

    void onApply(Combatant target);

    void tick(Combatant target);

    void onExpire(Combatant target);

    boolean isExpired();
}
