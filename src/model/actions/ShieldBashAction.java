package model.actions;

import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Player;
import model.combatants.Warrior;
import model.statuses.StunEffect;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ShieldBashAction implements Action {

    private final Warrior warrior;

    public ShieldBashAction(Warrior warrior) {
        this.warrior = warrior;
    }

    @Override public String getName()        { 
        return "Shield Bash (Special)"; 
    }

    @Override public String getDescription() { 
        return "Deal damage + stun target for 2 turns."; 
    }

    @Override
    public void execute(Combatant actor, List<Combatant> allCombatants) {
        Combatant target = pickTarget(allCombatants);
        if (target == null) return;

        int damage = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(damage);
        target.addStatusEffect(new StunEffect());

        System.out.printf("%s uses Shield Bash on %s! Damage: %d. %s is STUNNED for 2 turns!%n",
                actor.getName(), target.getName(), damage, target.getName());

        if (!target.isAlive()) {
            System.out.printf("  -> %s has been ELIMINATED!%n", target.getName());
        }

        warrior.triggerSpecialSkillCooldown();
    }

    private Combatant pickTarget(List<Combatant> allCombatants) {
        List<Combatant> liveEnemies = allCombatants.stream()
                .filter(c -> c instanceof Enemy && c.isAlive())
                .collect(Collectors.toList());

        if (liveEnemies.isEmpty()) return null;

        System.out.println("Choose a target for Shield Bash:");
        for (int i = 0; i < liveEnemies.size(); i++) {
            System.out.printf("  [%d] %s%n", i + 1, liveEnemies.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > liveEnemies.size());

        return liveEnemies.get(choice - 1);
    }
}
