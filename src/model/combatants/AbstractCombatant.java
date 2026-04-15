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

}
