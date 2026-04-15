package model.actions;

import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Wizard;

import java.util.List;
import java.util.stream.Collectors;


public class ArcaneBlastAction implements Action {

    private final Wizard wizard;

    public ArcaneBlastAction(Wizard wizard) {
        this.wizard = wizard;
    }

    @Override public String getName()        { 
        return "Arcane Blast (Special)"; 
    }

    @Override public String getDescription() { 
        return "Hit all enemies; kills grant +10 ATK permanently."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {
        List<Combatant> liveEnemies = allCombatants.stream()
                .filter(c -> c instanceof Enemy && c.isAlive())
                .collect(Collectors.toList());

        if (liveEnemies.isEmpty()) {
            System.out.println("No enemies to blast!");
            return;
        }
                
        System.out.printf("%s unleashes Arcane Blast on all enemies!%n", actor.getName());

        int currentAtk = actor.getAttack(); // snapshot; attack grows mid-blast per spec

        for (Combatant target : liveEnemies) {
            int damage = Math.max(0, actor.getAttack() - target.getDefense());
            target.takeDamage(damage);
            System.out.printf("  %s takes %d damage (HP: %d/%d).%n",
                    target.getName(), damage, target.getHp(), target.getMaxHp());

            if (!target.isAlive()) {
                System.out.printf("  -> %s ELIMINATED! Wizard ATK: %d -> %d%n",
                        target.getName(), actor.getAttack(), actor.getAttack() + 10);
                wizard.onArcaneBlastKill(); // +10 ATK applied immediately (affects next targets)
            }
        }

        wizard.triggerSpecialSkillCooldown();
    }
}
