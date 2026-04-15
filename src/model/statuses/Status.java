package model.statuses;

import model.combatants.Combatant;

public class Status {
    String getName();

    int getDuration();

    void onApply(Combatant target);

    void tick(Combatant target);

    void onExpire(Combatant target);

    boolean isExpired();
}
