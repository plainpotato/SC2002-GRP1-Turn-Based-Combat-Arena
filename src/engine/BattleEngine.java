package engine;

import model.combatants.Combatant;
import model.combatants.Enemy;
import model.combatants.Player;
import model.actions.Action;
import model.statuses.StunEffect;
import strategy.TurnOrderStrategy;
import ui.BattleDisplay;

import java.util.List;

/**
 * Core battle loop. Manages rounds, turn order, action dispatch, and win/loss detection.
 *
 * DIP: Depends on Combatant, TurnOrderStrategy, BattleDisplay abstractions.
 * SRP: Delegates state to BattleState, display to BattleDisplay, ordering to TurnOrderStrategy.
 * OCP: New actions/effects need no changes here.
 */
public class BattleEngine {

    private final TurnOrderStrategy turnOrderStrategy;
    private final BattleDisplay display;
    private final Level level;

    private BattleState state;

    public BattleEngine(TurnOrderStrategy turnOrderStrategy,
                        BattleDisplay display,
                        Level level) {
        this.turnOrderStrategy = turnOrderStrategy;
        this.display           = display;
        this.level             = level;
    }

    /**
     * Runs the full battle and returns the outcome.
     */
    public BattleResult run(Player player, List<Enemy> initialEnemies, List<Enemy> backupEnemies) {
        state = new BattleState(player, initialEnemies);

        display.showBattleStart(player, initialEnemies);

        while (true) {
            state.incrementRound();
            display.showRoundHeader(state.getRoundNumber());

            // ── Apply pre-existing status effects ────────────────────────────
            // Effects are ticked at the START of each combatant's turn (inside processTurn).

            // ── Check backup spawn (before the round's actions) ──────────────
            if (!state.isBackupSpawned()
                    && state.allActiveEnemiesEliminated()
                    && !backupEnemies.isEmpty()) {
                state.spawnBackup(backupEnemies);
            }

            // ── Check win condition before acting ────────────────────────────
            if (checkEndConditions()) break;

            // ── Determine turn order for this round ──────────────────────────
            List<Combatant> turnOrder = turnOrderStrategy.determineTurnOrder(
                    state.getLivingCombatants());

            display.showTurnOrder(turnOrder);

            // ── Process each combatant's turn ─────────────────────────────────
            for (Combatant combatant : turnOrder) {
                if (!combatant.isAlive()) continue; // may have been eliminated mid-round

                processTurn(combatant);

                // Check mid-round end conditions
                if (state.isPlayerDefeated()) break;

                // Mid-round backup spawn: if all active enemies just died
                if (!state.isBackupSpawned()
                        && state.allActiveEnemiesEliminated()
                        && !backupEnemies.isEmpty()) {
                    state.spawnBackup(backupEnemies);
                }
            }

            // ── End-of-round cleanup ──────────────────────────────────────────
            display.showRoundSummary(state);

            // Call onRoundEnd for all living combatants
            state.getLivingCombatants().forEach(Combatant::onRoundEnd);

            // Decrement player cooldown (only if their turn was taken — always true unless stunned)
            // Cooldown decrement happens here; stun skips the turn but still decrements cooldown
            // per assignment spec ("decreasing cooldown only if a turn by the combatant took place")
            // → handled inside processTurn

            if (checkEndConditions()) break;
        }

        BattleResult result = state.isPlayerVictorious()
                ? BattleResult.PLAYER_VICTORY
                : BattleResult.PLAYER_DEFEAT;

        display.showBattleEnd(result, state);
        return result;
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private void processTurn(Combatant combatant) {
        display.showTurnStart(combatant);

        // Tick status effects at start of this combatant's turn
        combatant.tickStatusEffects();

        // Stunned: skip action but still tick (stun was already ticked above)
        if (combatant.hasStatusEffect(StunEffect.class)) {
            display.showStunnedSkip(combatant);
            // Spec: cooldown decrements only if a turn took place → stun = NO decrement
            return;
        }

        // Choose and execute action
        List<Combatant> allCombatants = state.getAllCombatants();
        Action action = combatant.chooseAction(allCombatants);
        action.execute(combatant, allCombatants);

        // Decrement player cooldown after turn (only if turn was actually taken)
        if (combatant instanceof Player) {
            ((Player) combatant).decrementCooldown();
        }
    }

    private boolean checkEndConditions() {
        if (state.isPlayerDefeated()) {
            return true;
        }
        if (state.isPlayerVictorious()) {
            return true;
        }
        return false;
    }
}
