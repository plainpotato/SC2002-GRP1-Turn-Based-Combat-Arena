package model.combatants;

import model.actions.Action;
import strategy.EnemyActionStrategy;
import strategy.BasicAttackStrategy;

import java.util.List;

public abstract class Enemy extends AbstractCombatant {

    private EnemyActionStrategy actionStrategy;

    protected Enemy(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
        this.actionStrategy = new BasicAttackStrategy();
    }

    /** Allows future AI injection (DIP). */
    public void setActionStrategy(EnemyActionStrategy strategy) {
        this.actionStrategy = strategy;
    }

    @Override
    public Action chooseAction(List<Combatant> allCombatants) {
        return actionStrategy.decideAction(this, allCombatants);
    }
}
