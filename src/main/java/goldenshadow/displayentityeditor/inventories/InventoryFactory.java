package goldenshadow.displayentityeditor.inventories;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.items.GUIItems;
import goldenshadow.displayentityeditor.items.InventoryItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryFactory {

    private final GUIItems guiItems;
    private final InventoryItems inventoryItems;

    /**
     * Used to create a new inventory factory
     * @param guiItems The gui items object it should use
     * @param inventoryItems The inventory items object it should use
     */
    public InventoryFactory(GUIItems guiItems, InventoryItems inventoryItems) {
        this.guiItems = guiItems;
        this.inventoryItems = inventoryItems;
    }

    /**
     * Getter for the gui items
     * @return The class containing all gui items
     */
    public GUIItems getGuiItems() {
        return guiItems;
    }

    public InventoryItems getInventoryItems() {
        return inventoryItems;
    }


    /**
     * Used to create the gui for item displays
     * @param entity The item display entity being edited
     * @return The gui
     */
    public Inventory createItemDisplayGUI(ItemDisplay entity) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&' ,DisplayEntityEditor.messageManager.getString("item_display_gui_name")));
        for (int i = 0; i < inventory.getSize(); i++) {
            switch (i) {
                case 4 -> inventory.setItem(i, guiItems.name(entity.getCustomName()));
                case 5 -> inventory.setItem(i, guiItems.rightRotNormalize(Utilities.getData(entity, "GUIRRNormalize")));
                case 6 -> inventory.setItem(i, guiItems.height(entity.getDisplayHeight()));
                case 7 -> inventory.setItem(i, guiItems.shadowRadius(entity.getShadowRadius()));
                case 8 -> inventory.setItem(i, guiItems.skyLight(entity.getBrightness() != null ? entity.getBrightness().getSkyLight() : -1));

                case 10 -> inventory.setItem(i, entity.getItemStack());
                case 12 -> inventory.setItem(i, guiItems.itemDisplayTransform(entity.getItemDisplayTransform()));
                case 13 -> inventory.setItem(i, guiItems.glowing(entity.isGlowing()));
                case 14 -> inventory.setItem(i, guiItems.leftRotNormalize(Utilities.getData(entity, "GUILRNormalize")));
                case 15 -> inventory.setItem(i, guiItems.width(entity.getDisplayWidth()));
                case 16 -> inventory.setItem(i, guiItems.shadowStrength(entity.getShadowStrength()));
                case 17 -> inventory.setItem(i, guiItems.blockLight(entity.getBrightness() != null ? entity.getBrightness().getBlockLight() : -1));

                case 22 -> inventory.setItem(i, guiItems.glowColor(entity.getGlowColorOverride()));
                case 23 -> inventory.setItem(i, guiItems.viewRange(entity.getViewRange()));
                case 24 -> inventory.setItem(i, guiItems.billboard(entity.getBillboard()));
                case 25 -> inventory.setItem(i, guiItems.lock());
                case 26 -> inventory.setItem(i, guiItems.delete());
                default -> inventory.setItem(i, guiItems.filler());
            }
        }
        return inventory;
    }

    /**
     * Used to create the gui for block displays
     * @param entity The block display entity being edited
     * @return The gui
     */
    public Inventory createBlockDisplayGUI(BlockDisplay entity) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&' ,DisplayEntityEditor.messageManager.getString("block_display_gui_name")));
        for (int i = 0; i < inventory.getSize(); i++) {
            switch (i) {
                case 4 -> inventory.setItem(i, guiItems.name(entity.getCustomName()));
                case 5 -> inventory.setItem(i, guiItems.rightRotNormalize(Utilities.getData(entity, "GUIRRNormalize")));
                case 6 -> inventory.setItem(i, guiItems.height(entity.getDisplayHeight()));
                case 7 -> inventory.setItem(i, guiItems.shadowRadius(entity.getShadowRadius()));
                case 8 -> inventory.setItem(i, guiItems.skyLight(entity.getBrightness() != null ? entity.getBrightness().getSkyLight() : -1));

                case 10 -> inventory.setItem(i, new ItemStack(entity.getBlock().getMaterial()));
                case 11 -> inventory.setItem(i, guiItems.blockState(entity.getBlock().getAsString(true)));

                case 13 -> inventory.setItem(i, guiItems.glowing(entity.isGlowing()));
                case 14 -> inventory.setItem(i, guiItems.leftRotNormalize(Utilities.getData(entity, "GUILRNormalize")));
                case 15 -> inventory.setItem(i, guiItems.width(entity.getDisplayWidth()));
                case 16 -> inventory.setItem(i, guiItems.shadowStrength(entity.getShadowStrength()));
                case 17 -> inventory.setItem(i, guiItems.blockLight(entity.getBrightness() != null ? entity.getBrightness().getBlockLight() : -1));

                case 22 -> inventory.setItem(i, guiItems.glowColor(entity.getGlowColorOverride()));
                case 23 -> inventory.setItem(i, guiItems.viewRange(entity.getViewRange()));
                case 24 -> inventory.setItem(i, guiItems.billboard(entity.getBillboard()));
                case 25 -> inventory.setItem(i, guiItems.lock());
                case 26 -> inventory.setItem(i, guiItems.delete());
                default -> inventory.setItem(i, guiItems.filler());
            }
        }
        return inventory;
    }

    /**
     * Used to create the gui for text displays
     * @param entity The text display entity being edited
     * @return The gui
     */
    @SuppressWarnings("deprecation")
    public Inventory createTextDisplayGUI(TextDisplay entity) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&' ,DisplayEntityEditor.messageManager.getString("text_display_gui_name")));
        for (int i = 0; i < inventory.getSize(); i++) {
            switch (i) {
                case 2 -> inventory.setItem(i, guiItems.textBackgroundColor(entity.getBackgroundColor()));
                case 3 -> inventory.setItem(i, guiItems.textDefaultBackground(entity.isDefaultBackground()));
                case 4 -> inventory.setItem(i, guiItems.name(entity.getCustomName()));
                case 5 -> inventory.setItem(i, guiItems.rightRotNormalize(Utilities.getData(entity, "GUIRRNormalize")));
                case 6 -> inventory.setItem(i, guiItems.height(entity.getDisplayHeight()));
                case 7 -> inventory.setItem(i, guiItems.shadowRadius(entity.getShadowRadius()));
                case 8 -> inventory.setItem(i, guiItems.skyLight(entity.getBrightness() != null ? entity.getBrightness().getSkyLight() : -1));

                case 10 -> inventory.setItem(i, guiItems.text());
                case 11 -> inventory.setItem(i, guiItems.textBackgroundOpacity(entity.getBackgroundColor()));
                case 12 -> inventory.setItem(i, guiItems.textSeeThrough(entity.isSeeThrough()));
                case 13 -> inventory.setItem(i, guiItems.textOpacity(entity.getTextOpacity()));
                case 14 -> inventory.setItem(i, guiItems.leftRotNormalize(Utilities.getData(entity, "GUILRNormalize")));
                case 15 -> inventory.setItem(i, guiItems.width(entity.getDisplayWidth()));
                case 16 -> inventory.setItem(i, guiItems.shadowStrength(entity.getShadowStrength()));
                case 17 -> inventory.setItem(i, guiItems.blockLight(entity.getBrightness() != null ? entity.getBrightness().getBlockLight() : -1));

                case 20 -> inventory.setItem(i, guiItems.textAlignment(entity.getAlignment()));
                case 21 -> inventory.setItem(i, guiItems.textShadow(entity.isShadowed()));
                case 22 -> inventory.setItem(i, guiItems.textLineWidth(entity.getLineWidth()));
                case 23 -> inventory.setItem(i, guiItems.viewRange(entity.getViewRange()));
                case 24 -> inventory.setItem(i, guiItems.billboard(entity.getBillboard()));
                case 25 -> inventory.setItem(i, guiItems.lock());
                case 26 -> inventory.setItem(i, guiItems.delete());
                default -> inventory.setItem(i, guiItems.filler());
            }
        }
        return inventory;
    }

    /**
     * Used to generate an array of tools to be easily added to a players inventory
     * @return An array of tools
     */
    public ItemStack[] getInventoryArray(Player p) {
        ItemStack[] array = new ItemStack[36];

        array[0] = inventoryItems.gui();
        array[1] = inventoryItems.rotateYaw(p);
        array[2] = inventoryItems.rotatePitch(p);
        array[3] = inventoryItems.moveX(p);
        array[4] = inventoryItems.moveY(p);
        array[5] = inventoryItems.moveZ(p);
        array[6] = inventoryItems.toolPrecision();

        array[27] = inventoryItems.spawnItemDisplay();
        array[28] = inventoryItems.spawnBlockDisplay();
        array[29] = inventoryItems.spawnTextDisplay();
        array[30] = inventoryItems.centerPivot();
        array[31] = inventoryItems.highlightTarget();
        array[32] = inventoryItems.unlock();
        array[33] = inventoryItems.centerOnBlock();

        array[18] = inventoryItems.translationX(p);
        array[19] = inventoryItems.translationY(p);
        array[20] = inventoryItems.translationZ(p);
        array[21] = inventoryItems.scaleX(p);
        array[22] = inventoryItems.scaleY(p);
        array[23] = inventoryItems.scaleZ(p);
        array[24] = inventoryItems.cloneTool();

        array[9] = inventoryItems.leftRotationX(p);
        array[10] = inventoryItems.leftRotationY(p);
        array[11] = inventoryItems.leftRotationZ(p);
        array[12] = inventoryItems.rightRotationX(p);
        array[13] = inventoryItems.rightRotationY(p);
        array[14] = inventoryItems.rightRotationZ(p);

        return array;
    }


}
