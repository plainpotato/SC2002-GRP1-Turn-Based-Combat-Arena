package model.statuses;

import model.combatants.Combatant;

public abstract class AbstractStatusEffect implements StatusEffect {

    protected int duration;

    protected AbstractStatusEffect(int duration) {
        this.duration = duration;
    }

    @Override public int getDuration() { 
        return duration; 
    }

    @Override public boolean isExpired() { 
        return duration <= 0; 
    }

    @Override
    public void tick(Combatant target) {
        if (duration > 0) duration--;
    }

    @Override public void onApply(Combatant target)  {}
    @Override public void onExpire(Combatant target) {}
}
