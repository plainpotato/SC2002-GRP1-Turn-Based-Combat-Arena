package model.combatants;

// import model.actions.Action;
// import model.effects.Status;


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
}
