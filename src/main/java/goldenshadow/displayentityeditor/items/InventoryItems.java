package goldenshadow.displayentityeditor.items;

import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

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
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Open GUI",
                List.of(
                        ChatColor.GRAY + "Click to open the GUI of the",
                        ChatColor.GRAY + "nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to open"
                ),
                "InventoryGUI"
                );
        return itemStack;
    }

    /**
     * Creates the rotate yaw item
     * @return The item
     */
    public ItemStack rotateYaw() {
        ItemStack itemStack = new ItemStack(Material.MAGMA_CREAM);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Rotate Horizontally (yaw)",
                List.of(
                        ChatColor.GRAY + "Click to rotate the nearest",
                        ChatColor.GRAY + "unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to rotate by +1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to rotate by -1"
                ),
                "InventoryRotateYaw"
        );
        return itemStack;
    }

    /**
     * Creates the rotate pitch item
     * @return The item
     */
    public ItemStack rotatePitch() {
        ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Rotate Vertically (pitch)",
                List.of(
                        ChatColor.GRAY + "Click to rotate the nearest",
                        ChatColor.GRAY + "unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to rotate by +1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to rotate by -1"
                ),
                "InventoryRotatePitch"
        );
        return itemStack;
    }

    /**
     * Creates the move x item
     * @return The item
     */
    public ItemStack moveX() {
        ItemStack itemStack = new ItemStack(Material.SHEARS);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Move X (Teleport)",
                List.of(
                        ChatColor.GRAY + "Click to move the nearest",
                        ChatColor.GRAY + "unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to move +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to move -0.1"
                ),
                "InventoryMoveX"
        );
        return itemStack;
    }

    /**
     * Creates the move y item
     * @return The item
     */
    public ItemStack moveY() {
        ItemStack itemStack = new ItemStack(Material.SHEARS);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Move Y (Teleport)",
                List.of(
                        ChatColor.GRAY + "Click to move the nearest",
                        ChatColor.GRAY + "unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to move +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to move -0.1"
                ),
                "InventoryMoveY"
        );
        return itemStack;
    }

    /**
     * Creates the move z item
     * @return The item
     */
    public ItemStack moveZ() {
        ItemStack itemStack = new ItemStack(Material.SHEARS);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Move Z (Teleport)",
                List.of(
                        ChatColor.GRAY + "Click to move the nearest",
                        ChatColor.GRAY + "unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to move +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to move -0.1"
                ),
                "InventoryMoveZ"
        );
        return itemStack;
    }

    /**
     * Creates the spawn item display item
     * @return The item
     */
    public ItemStack spawnItemDisplay() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Spawn item_display",
                List.of(
                        ChatColor.GRAY + "Click to spawn a new item display",
                        ChatColor.GRAY + "entity at your position",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to spawn"
                ),
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
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Spawn block_display",
                List.of(
                        ChatColor.GRAY + "Click to spawn a new block display",
                        ChatColor.GRAY + "entity at your position",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to spawn"
                ),
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
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Spawn text_display",
                List.of(
                        ChatColor.GRAY + "Click to spawn a new text display",
                        ChatColor.GRAY + "entity at your position",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to spawn"
                ),
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
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Unlock Nearest Display Entity",
                List.of(
                        ChatColor.GRAY + "Click to unlock the nearest locked",
                        ChatColor.GRAY + "display entity, making it editable again",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to unlock"
                ),
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
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Highlight Target",
                List.of(
                        ChatColor.GRAY + "Click to highlight the display entity",
                        ChatColor.GRAY + "that will be targeted by your tools",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to highlight"
                ),
                "InventoryHighlight"
        );
        return itemStack;
    }

    /**
     * Creates the left rotation x item
     * @return The item
     */
    public ItemStack leftRotationX() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Left Rotation X",
                List.of(
                        ChatColor.GRAY + "Click to change the left rotation",
                        ChatColor.GRAY + "of the nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryLRX"
        );
        return itemStack;
    }

    /**
     * Creates the left rotation y item
     * @return The item
     */
    public ItemStack leftRotationY() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Left Rotation Y",
                List.of(
                        ChatColor.GRAY + "Click to change the left rotation",
                        ChatColor.GRAY + "of the nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryLRY"
        );
        return itemStack;
    }

    /**
     * Creates the left rotation z item
     * @return The item
     */
    public ItemStack leftRotationZ() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Left Rotation Z",
                List.of(
                        ChatColor.GRAY + "Click to change the left rotation",
                        ChatColor.GRAY + "of the nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryLRZ"
        );
        return itemStack;
    }

    /**
     * Creates the right rotation x item
     * @return The item
     */
    public ItemStack rightRotationX() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Right Rotation X",
                List.of(
                        ChatColor.GRAY + "Click to change the left rotation",
                        ChatColor.GRAY + "of the nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryRRX"
        );
        return itemStack;
    }

    /**
     * Creates the right rotation y item
     * @return The item
     */
    public ItemStack rightRotationY() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Right Rotation Y",
                List.of(
                        ChatColor.GRAY + "Click to change the left rotation",
                        ChatColor.GRAY + "of the nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryRRY"
        );
        return itemStack;
    }

    /**
     * Creates the right rotation z item
     * @return The item
     */
    public ItemStack rightRotationZ() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Right Rotation Z",
                List.of(
                        ChatColor.GRAY + "Click to change the left rotation",
                        ChatColor.GRAY + "of the nearest unlocked display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryRRZ"
        );
        return itemStack;
    }

    /**
     * Creates the center pivot item
     * @return The item
     */
    public ItemStack centerPivot() {
        ItemStack itemStack = new ItemStack(Material.CHAIN);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Center Pivot Point",
                List.of(
                        ChatColor.GRAY + "Click to auto adjust the translation so that",
                        ChatColor.GRAY + "the pivot is centered relative to the scale.",
                        ChatColor.GRAY + "This will make it easier to rotate the entity",
                        ChatColor.GRAY + "around itself",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to center"
                ),
                "InventoryCenterPivot"
        );
        return itemStack;
    }

    /**
     * Creates the translation x item
     * @return The item
     */
    public ItemStack translationX() {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SCRAP);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Translation X",
                List.of(
                        ChatColor.GRAY + "Click to change the translation of",
                        ChatColor.GRAY + "the nearest unlocked display entity.",
                        ChatColor.GRAY + "Changing the translation will move the",
                        ChatColor.GRAY + "visual part of the entity but not its",
                        ChatColor.GRAY + "hitbox or pivot point",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryTX"
        );
        return itemStack;
    }

    /**
     * Creates the translation y item
     * @return The item
     */
    public ItemStack translationY() {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SCRAP);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Translation Y",
                List.of(
                        ChatColor.GRAY + "Click to change the translation of",
                        ChatColor.GRAY + "the nearest unlocked display entity.",
                        ChatColor.GRAY + "Changing the translation will move the",
                        ChatColor.GRAY + "visual part of the entity but not its",
                        ChatColor.GRAY + "hitbox or pivot point",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryTY"
        );
        return itemStack;
    }

    /**
     * Creates the translation z item
     * @return The item
     */
    public ItemStack translationZ() {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SCRAP);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Translation Z",
                List.of(
                        ChatColor.GRAY + "Click to change the translation of",
                        ChatColor.GRAY + "the nearest unlocked display entity.",
                        ChatColor.GRAY + "Changing the translation will move the",
                        ChatColor.GRAY + "visual part of the entity but not its",
                        ChatColor.GRAY + "hitbox or pivot point",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventoryTZ"
        );
        return itemStack;
    }

    /**
     * Creates the scale x item
     * @return The item
     */
    public ItemStack scaleX() {
        ItemStack itemStack = new ItemStack(Material.SHULKER_SHELL);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Scale X",
                List.of(
                        ChatColor.GRAY + "Click to change the scale of the",
                        ChatColor.GRAY + "nearest unlocked display entity.",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventorySX"
        );
        return itemStack;
    }

    /**
     * Creates the scale y item
     * @return The item
     */
    public ItemStack scaleY() {
        ItemStack itemStack = new ItemStack(Material.SHULKER_SHELL);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Scale Y",
                List.of(
                        ChatColor.GRAY + "Click to change the scale of the",
                        ChatColor.GRAY + "nearest unlocked display entity.",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventorySY"
        );
        return itemStack;
    }

    /**
     * Creates the scale z item
     * @return The item
     */
    public ItemStack scaleZ() {
        ItemStack itemStack = new ItemStack(Material.SHULKER_SHELL);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Scale Z",
                List.of(
                        ChatColor.GRAY + "Click to change the scale of the",
                        ChatColor.GRAY + "nearest unlocked display entity.",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by +0.1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change by -0.1"
                ),
                "InventorySZ"
        );
        return itemStack;
    }

    /**
     * Creates the center on block item
     * @return The item
     */
    public ItemStack centerOnBlock() {
        ItemStack itemStack = new ItemStack(Material.LIGHTNING_ROD);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Center On Block",
                List.of(
                        ChatColor.GRAY + "Click to position the nearest display entity",
                        ChatColor.GRAY + "so that it is centered on the block.",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to center xyz",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to center xz"
                ),
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
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Change Tool Precision",
                List.of(
                        ChatColor.GRAY + "Click to change the multiplier for",
                        ChatColor.GRAY + "your tools precision.",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to increase by 1",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "SHIFT RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to decrease by 1"
                ),
                "InventoryToolPrecision"
        );
        return itemStack;
    }

    public ItemStack cloneTool() {
        ItemStack itemStack = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Clone Display Entity",
                List.of(
                        ChatColor.GRAY + "Click to duplicate the nearest",
                        ChatColor.GRAY + "display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to clone"
                ),
                "InventoryClone"
        );
        return itemStack;
    }
}
