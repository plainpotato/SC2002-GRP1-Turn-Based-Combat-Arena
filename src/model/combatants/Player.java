package model.combatants;

import model.actions.*;
import model.items.Item;
import ui.BattleDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for player-controlled combatants (Warrior, Wizard).
 * Manages inventory, special skill cooldown, and action selection via CLI.
 */
public abstract class Player extends AbstractCombatant {

    protected static final int SPECIAL_SKILL_COOLDOWN = 3;

    protected int specialSkillCooldown = 0;   // 0 = ready
    protected final List<Item> inventory = new ArrayList<>();
    protected final BattleDisplay display;

    protected Player(String name, int maxHp, int attack, int defense, int speed,
                     BattleDisplay display) {
        super(name, maxHp, attack, defense, speed);
        this.display = display;
    }

    // ── Inventory ─────────────────────────────────────────────────────────────

    public void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return new ArrayList<>(inventory);
    }

    public boolean hasItems() {
        return !inventory.isEmpty();
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    // ── Cooldown ──────────────────────────────────────────────────────────────

    public int getSpecialSkillCooldown() { return specialSkillCooldown; }

    public boolean isSpecialSkillReady() { return specialSkillCooldown == 0; }

    /** Called when the special skill (or Power Stone) is actually executed. */
    public void triggerSpecialSkillCooldown() {
        specialSkillCooldown = SPECIAL_SKILL_COOLDOWN;
    }

    /** Decrements cooldown at end of combatant's turn (only if turn was taken). */
    public void decrementCooldown() {
        if (specialSkillCooldown > 0) specialSkillCooldown--;
    }

    // ── Action Selection ──────────────────────────────────────────────────────

    /**
     * Presents available actions to the player and returns their chosen Action.
     * The concrete player class supplies the SpecialSkill action.
     */
    @Override
    public Action chooseAction(List<Combatant> allCombatants) {
        List<Action> available = buildAvailableActions(allCombatants);
        display.showActionMenu(this, available);
        int choice = display.readPlayerChoice(1, available.size());
        return available.get(choice - 1);
    }

    protected List<Action> buildAvailableActions(List<Combatant> allCombatants) {
        List<Action> actions = new ArrayList<>();
        actions.add(new BasicAttackAction());
        actions.add(new DefendAction());
        if (hasItems()) {
            actions.add(new ItemAction(inventory, display));
        }
        if (isSpecialSkillReady()) {
            actions.add(createSpecialSkillAction());
        }
        return actions;
    }

    /** Returns the class-specific special skill action. Public so PowerStone can invoke it. */
    public abstract Action createSpecialSkillAction();

    /** Restores cooldown to a previous value — used by PowerStone to avoid cooldown changes. */
    public void restoreCooldown(int previousCooldown) {
        this.specialSkillCooldown = previousCooldown;
    }

    // ── Round Lifecycle ───────────────────────────────────────────────────────

    @Override
    public void onRoundEnd() {
        super.onRoundEnd();
        // Reset temporary defense if DefendEffect has expired (handled by effect itself)
    }
}
