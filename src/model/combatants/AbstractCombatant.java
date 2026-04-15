package model.combatants;

import model.statuses.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class AbstractCombatant implements Combatant {

    protected final String name;
    protected int hp;
    protected final int maxHp;
    protected int attack;
    protected int defense;
    protected final int baseDefense;
    protected final int speed;

    protected final List<StatusEffect> statusEffects = new ArrayList<>();

    protected AbstractCombatant(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.baseDefense = defense;
        this.speed = speed;
    }

    @Override public String getName() { 
        return name; 
    }
    @Override public int getHp() { 
        return hp; 
    }

    @Override public int getMaxHp() { 
        return maxHp; 
    }

    @Override public int getAttack() { 
        return attack; 
    }

    @Override public int getDefense() { 
        return defense; 
    }

    @Override public int getSpeed() { 
        return speed; 
    }

    @Override
    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, maxHp));
    }

    @Override public void setAttack(int attack) { 
        this.attack = attack; 
    }
    @Override public void setDefense(int defense) { 
        this.defense = defense; 
    }

    @Override public boolean isAlive() { 
        return hp > 0; 
    }

    @Override public boolean isEliminated() { 
        return !isAlive(); 
    }

    @Override
    public void addStatusEffect(StatusEffect effect) {
        statusEffects.removeIf(e -> e.getClass().equals(effect.getClass()));
        statusEffects.add(effect);
        effect.onApply(this);
    }

    @Override
    public void removeStatusEffect(Class<? extends StatusEffect> effectType) {
        Iterator<StatusEffect> it = statusEffects.iterator();
        while (it.hasNext()) {
            StatusEffect e = it.next();
            if (e.getClass().equals(effectType)) {
                e.onExpire(this);
                it.remove();
            }
        }
    }

    @Override
    public List<StatusEffect> getStatusEffects() {
        return new ArrayList<>(statusEffects);
    }

    @Override
    public boolean hasStatusEffect(Class<? extends StatusEffect> effectType) {
        return statusEffects.stream().anyMatch(e -> e.getClass().equals(effectType));
    }

    @Override
    public void tickStatusEffects() {
        Iterator<StatusEffect> it = statusEffects.iterator();
        while (it.hasNext()) {
            StatusEffect e = it.next();
            e.tick(this);
            if (e.isExpired()) {
                e.onExpire(this);
                it.remove();
            }
        }
    }

    @Override
    public void takeDamage(int damage) {
        setHp(hp - Math.max(0, damage));
    }

    @Override
    public void heal(int amount) {
        setHp(hp + amount);
    }

    @Override
    public void onRoundEnd() {

    }

    @Override
    public String toString() {
        return String.format("%s [HP: %d/%d | ATK: %d | DEF: %d | SPD: %d]",
                name, hp, maxHp, attack, defense, speed);
    }
}
