package model.actions;

import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Player;
import ui.BattleDisplay;
import model.statuses.SmokeBombEffect;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BasicAttackAction implements Action {

    @Override
    public String getName() { 
        return "Basic Attack"; 
    }

    @Override
    public String getDescription() { 
        return "Attack a single target."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {
        Combatant target = pickTarget(actor, allCombatants);
        if (target == null) return;

        int rawDamage = Math.max(0, actor.getAttack() - target.getDefense());

        if (actor instanceof Enemy && target.hasStatusEffect(SmokeBombEffect.class)) {
            System.out.printf("%s attacks %s — 0 damage (Smoke Bomb active)!%n",
                    actor.getName(), target.getName());
            return;
        }

        target.takeDamage(rawDamage);
        System.out.printf("%s attacks %s for %d damage. (ATK %d - DEF %d)%n",
                actor.getName(), target.getName(), rawDamage,
                actor.getAttack(), target.getDefense());

        if (!target.isAlive()) {
            System.out.printf("  -> %s has been ELIMINATED!%n", target.getName());
        }
    }

    private Combatant pickTarget(Combatant actor, List<Combatant> allCombatants) {
        if (actor instanceof Enemy) {
            return allCombatants.stream()
                    .filter(c -> c instanceof Player && c.isAlive())
                    .findFirst()
                    .orElse(null);
        }

        List<Combatant> liveEnemies = allCombatants.stream()
                .filter(c -> c instanceof Enemy && c.isAlive())
                .collect(Collectors.toList());

        if (liveEnemies.isEmpty()) return null;

        System.out.println("Choose a target:");
        for (int i = 0; i < liveEnemies.size(); i++) {
            System.out.printf("  [%d] %s%n", i + 1, liveEnemies.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
        } 
        while (choice < 1 || choice > liveEnemies.size());

        return liveEnemies.get(choice - 1);
    }
}
