package ui;

import engine.BattleResult;
import engine.BattleState;
import model.actions.Action;
import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Player;
import model.items.Item;

import java.util.List;

/**
 * Boundary interface between the battle engine and the user interface.
 * SRP / DIP: Engine depends on this abstraction, not on System.out directly.
 * ISP: Only methods actually needed by the engine are declared here.
 */
public interface BattleDisplay {

    void showBattleStart(Player player, List<Enemy> enemies);

    void showRoundHeader(int roundNumber);

    void showTurnOrder(List<Combatant> turnOrder);

    void showTurnStart(Combatant combatant);

    void showStunnedSkip(Combatant combatant);

    void showActionMenu(Player player, List<Action> actions);

    void showItemMenu(List<Item> items);

    /** Read a validated integer choice in [min, max] from stdin. */
    int readPlayerChoice(int min, int max);

    void showRoundSummary(BattleState state);

    void showBattleEnd(BattleResult result, BattleState state);

    void showMessage(String message);
}
