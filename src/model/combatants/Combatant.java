package model.combatants;

import model.actions.Action;
import model.effects.Status;


public interface Combatant {
    String getName();

    int getHp();
    int getMaxHp();
    int getAttack();
    int getDefense();
    int getSpeed();

    void setHp(int hp);
    void setAttack(int attack);
    void setDefense(int defense);

    boolean isAlive();
    boolean isEliminated();

    void addStatusEffect(StatusEffect effect);

    void removeStatusEffect(Class<? extends StatusEffect> effectType);

    List<StatusEffect> getStatusEffects();

    boolean hasStatusEffect(Class<? extends StatusEffect> effectType);

    void tickStatusEffects();

    Action chooseAction(List<Combatant> allCombatants);

    void takeDamage(int damage);

    void heal(int amount);

    void onRoundEnd();

    default void onTurnSkipped() {}
}
