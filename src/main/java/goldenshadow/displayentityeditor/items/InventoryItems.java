package goldenshadow.displayentityeditor.items;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A utility class where all inventory items are created
 */
public class InventoryItems {

    /**
     * Creates the open gui item
     * @return The item
     */
    public ItemStack gui() {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("open_gui_name"),
                DisplayEntityEditor.messageManager.getList("open_gui_lore"),
                "InventoryGUI"
                );
        return itemStack;
    }

    /**
     * Creates the rotate yaw item
     * @return The item
     */
    public ItemStack rotateYaw(Player p) {
        ItemStack itemStack = new ItemStack(Material.MAGMA_CREAM);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("rotate_yaw_name"),
                DisplayEntityEditor.messageManager.getList("rotate_lore"),
                "InventoryRotateYaw",
                Utilities.reduceFloatLength(Double.toString(Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the rotate pitch item
     * @return The item
     */
    public ItemStack rotatePitch(Player p) {
        ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("rotate_pitch_name"),
                DisplayEntityEditor.messageManager.getList("rotate_lore"),
                "InventoryRotatePitch",
                Utilities.reduceFloatLength(Double.toString(Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the move x item
     * @return The item
     */
    public ItemStack moveX(Player p) {
        ItemStack itemStack = new ItemStack(Material.SHEARS);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("move_x_name"),
                DisplayEntityEditor.messageManager.getList("move_lore"),
                "InventoryMoveX",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the move y item
     * @return The item
     */
    public ItemStack moveY(Player p) {
        ItemStack itemStack = new ItemStack(Material.SHEARS);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("move_y_name"),
                DisplayEntityEditor.messageManager.getList("move_lore"),
                "InventoryMoveY",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the move z item
     * @return The item
     */
    public ItemStack moveZ(Player p) {
        ItemStack itemStack = new ItemStack(Material.SHEARS);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("move_z_name"),
                DisplayEntityEditor.messageManager.getList("move_lore"),
                "InventoryMoveZ",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the spawn item display item
     * @return The item
     */
    public ItemStack spawnItemDisplay() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("spawn_item_display_name"),
                DisplayEntityEditor.messageManager.getList("spawn_item_display_lore"),
                "InventorySpawnItem"
        );
        return itemStack;
    }

    /**
     * Creates the spawn block display item
     * @return The item
     */
    public ItemStack spawnBlockDisplay() {
        ItemStack itemStack = new ItemStack(Material.GRASS_BLOCK);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("spawn_block_display_name"),
                DisplayEntityEditor.messageManager.getList("spawn_block_display_lore"),
                "InventorySpawnBlock"
        );
        return itemStack;
    }

    /**
     * Creates the spawn text display item
     * @return The item
     */
    public ItemStack spawnTextDisplay() {
        ItemStack itemStack = new ItemStack(Material.OAK_SIGN);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("spawn_text_display_name"),
                DisplayEntityEditor.messageManager.getList("spawn_text_display_lore"),
                "InventorySpawnText"
        );
        return itemStack;
    }

    /**
     * Creates the unlock item
     * @return The item
     */
    public ItemStack unlock() {
        ItemStack itemStack = new ItemStack(Material.MUSIC_DISC_11);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("unlock_name"),
                DisplayEntityEditor.messageManager.getList("unlock_lore"),
                "InventoryUnlock"
        );
        return itemStack;
    }

    /**
     * Creates the highlight target item
     * @return The item
     */
    public ItemStack highlightTarget() {
        ItemStack itemStack = new ItemStack(Material.GLOWSTONE_DUST);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("highlight_target_name"),
                DisplayEntityEditor.messageManager.getList("highlight_target_lore"),
                "InventoryHighlight"
        );
        return itemStack;
    }

    /**
     * Creates the left rotation x item
     * @return The item
     */
    public ItemStack leftRotationX(Player p) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("left_rotation_x_name"),
                DisplayEntityEditor.messageManager.getList("left_rotation_lore"),
                "InventoryLRX",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the left rotation y item
     * @return The item
     */
    public ItemStack leftRotationY(Player p) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("left_rotation_y_name"),
                DisplayEntityEditor.messageManager.getList("left_rotation_lore"),
                "InventoryLRY",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the left rotation z item
     * @return The item
     */
    public ItemStack leftRotationZ(Player p) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("left_rotation_z_name"),
                DisplayEntityEditor.messageManager.getList("left_rotation_lore"),
                "InventoryLRZ",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the right rotation x item
     * @return The item
     */
    public ItemStack rightRotationX(Player p) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("right_rotation_x_name"),
                DisplayEntityEditor.messageManager.getList("right_rotation_lore"),
                "InventoryRRX",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the right rotation y item
     * @return The item
     */
    public ItemStack rightRotationY(Player p) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("right_rotation_y_name"),
                DisplayEntityEditor.messageManager.getList("right_rotation_lore"),
                "InventoryRRY",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the right rotation z item
     * @return The item
     */
    public ItemStack rightRotationZ(Player p) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("right_rotation_z_name"),
                DisplayEntityEditor.messageManager.getList("right_rotation_lore"),
                "InventoryRRZ",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the center pivot item
     * @return The item
     */
    public ItemStack centerPivot() {
        ItemStack itemStack = new ItemStack(Material.CHAIN);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("center_pivot_name"),
                DisplayEntityEditor.messageManager.getList("center_pivot_lore"),
                "InventoryCenterPivot"
        );
        return itemStack;
    }

    /**
     * Creates the translation x item
     * @return The item
     */
    public ItemStack translationX(Player p) {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SCRAP);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("translation_x_name"),
                DisplayEntityEditor.messageManager.getList("translation_lore"),
                "InventoryTX",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the translation y item
     * @return The item
     */
    public ItemStack translationY(Player p) {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SCRAP);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("translation_y_name"),
                DisplayEntityEditor.messageManager.getList("translation_lore"),
                "InventoryTY",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the translation z item
     * @return The item
     */
    public ItemStack translationZ(Player p) {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SCRAP);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("translation_z_name"),
                DisplayEntityEditor.messageManager.getList("translation_lore"),
                "InventoryTZ",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the scale x item
     * @return The item
     */
    public ItemStack scaleX(Player p) {
        ItemStack itemStack = new ItemStack(Material.SHULKER_SHELL);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("scale_x_name"),
                DisplayEntityEditor.messageManager.getList("scale_lore"),
                "InventorySX",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the scale y item
     * @return The item
     */
    public ItemStack scaleY(Player p) {
        ItemStack itemStack = new ItemStack(Material.SHULKER_SHELL);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("scale_y_name"),
                DisplayEntityEditor.messageManager.getList("scale_lore"),
                "InventorySY",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the scale z item
     * @return The item
     */
    public ItemStack scaleZ(Player p) {
        ItemStack itemStack = new ItemStack(Material.SHULKER_SHELL);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("scale_z_name"),
                DisplayEntityEditor.messageManager.getList("scale_lore"),
                "InventorySZ",
                Utilities.reduceFloatLength(Double.toString(0.1 * Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }

    /**
     * Creates the center on block item
     * @return The item
     */
    public ItemStack centerOnBlock() {
        ItemStack itemStack = new ItemStack(Material.LIGHTNING_ROD);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("center_on_block_name"),
                DisplayEntityEditor.messageManager.getList("center_on_block_lore"),
                "InventoryCenterBlock"
        );
        return itemStack;
    }

    /**
     * Creates the tool precision item
     * @return The item
     */
    public ItemStack toolPrecision() {
        ItemStack itemStack = new ItemStack(Material.COMPARATOR);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("tool_precision_name"),
                DisplayEntityEditor.messageManager.getList("tool_precision_lore"),
                "InventoryToolPrecision"
        );
        return itemStack;
    }

    /**
     * Creates the clone item
     * @return The item
     */
    public ItemStack cloneTool() {
        ItemStack itemStack = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("clone_tool_name"),
                DisplayEntityEditor.messageManager.getList("clone_tool_lore"),
                "InventoryClone"
        );
        return itemStack;
    }

    /**
     * Creates the group select item
     * @return The item
     */
    public ItemStack groupSelectTool(Player p) {
        ItemStack itemStack = new ItemStack(Material.MINECART);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("group_select_name"),
                DisplayEntityEditor.messageManager.getList("group_select_lore"),
                "InventoryGroupSelect",
                Utilities.reduceFloatLength(Double.toString(Utilities.getToolPrecision(p)))
        );
        return itemStack;
    }
}
