package goldenshadow.displayentityeditor.inventories;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.items.GUIItems;
import goldenshadow.displayentityeditor.items.InventoryItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class InventoryFactory {

    public static Inventory createItemDisplayGUI(ItemDisplay entity) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Item Display GUI");
        for (int i = 0; i < inventory.getSize(); i++) {
            switch (i) {
                case 4 -> inventory.setItem(i, GUIItems.name(entity.getCustomName()));
                case 5 -> inventory.setItem(i, GUIItems.rightRotNormalize(Utilities.getData(entity, "GUIRRNormalize")));
                case 6 -> inventory.setItem(i, GUIItems.height(entity.getDisplayHeight()));
                case 7 -> inventory.setItem(i, GUIItems.shadowRadius(entity.getShadowRadius()));
                case 8 -> inventory.setItem(i, GUIItems.skyLight(entity.getBrightness() != null ? entity.getBrightness().getSkyLight() : -1));

                case 10 -> inventory.setItem(i, entity.getItemStack());
                case 12 -> inventory.setItem(i, GUIItems.itemDisplayTransform(entity.getItemDisplayTransform()));
                case 13 -> inventory.setItem(i, GUIItems.glowing(entity.isGlowing()));
                case 14 -> inventory.setItem(i, GUIItems.leftRotNormalize(Utilities.getData(entity, "GUILRNormalize")));
                case 15 -> inventory.setItem(i, GUIItems.width(entity.getDisplayWidth()));
                case 16 -> inventory.setItem(i, GUIItems.shadowStrength(entity.getShadowStrength()));
                case 17 -> inventory.setItem(i, GUIItems.blockLight(entity.getBrightness() != null ? entity.getBrightness().getBlockLight() : -1));

                case 22 -> inventory.setItem(i, GUIItems.glowColor(entity.getGlowColorOverride()));
                case 23 -> inventory.setItem(i, GUIItems.viewRange(entity.getViewRange()));
                case 24 -> inventory.setItem(i, GUIItems.billboard(entity.getBillboard()));
                case 25 -> inventory.setItem(i, GUIItems.lock());
                case 26 -> inventory.setItem(i, GUIItems.delete());
                default -> inventory.setItem(i, GUIItems.filler());
            }
        }
        return inventory;
    }

    public static Inventory createBlockDisplayGUI(BlockDisplay entity) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Block Display GUI");
        for (int i = 0; i < inventory.getSize(); i++) {
            switch (i) {
                case 4 -> inventory.setItem(i, GUIItems.name(entity.getCustomName()));
                case 5 -> inventory.setItem(i, GUIItems.rightRotNormalize(Utilities.getData(entity, "GUIRRNormalize")));
                case 6 -> inventory.setItem(i, GUIItems.height(entity.getDisplayHeight()));
                case 7 -> inventory.setItem(i, GUIItems.shadowRadius(entity.getShadowRadius()));
                case 8 -> inventory.setItem(i, GUIItems.skyLight(entity.getBrightness() != null ? entity.getBrightness().getSkyLight() : -1));

                case 10 -> inventory.setItem(i, new ItemStack(entity.getBlock().getMaterial()));

                case 13 -> inventory.setItem(i, GUIItems.glowing(entity.isGlowing()));
                case 14 -> inventory.setItem(i, GUIItems.leftRotNormalize(Utilities.getData(entity, "GUILRNormalize")));
                case 15 -> inventory.setItem(i, GUIItems.width(entity.getDisplayWidth()));
                case 16 -> inventory.setItem(i, GUIItems.shadowStrength(entity.getShadowStrength()));
                case 17 -> inventory.setItem(i, GUIItems.blockLight(entity.getBrightness() != null ? entity.getBrightness().getBlockLight() : -1));

                case 22 -> inventory.setItem(i, GUIItems.glowColor(entity.getGlowColorOverride()));
                case 23 -> inventory.setItem(i, GUIItems.viewRange(entity.getViewRange()));
                case 24 -> inventory.setItem(i, GUIItems.billboard(entity.getBillboard()));
                case 25 -> inventory.setItem(i, GUIItems.lock());
                case 26 -> inventory.setItem(i, GUIItems.delete());
                default -> inventory.setItem(i, GUIItems.filler());
            }
        }
        return inventory;
    }

    @SuppressWarnings("deprecation")
    public static Inventory createTextDisplayGUI(TextDisplay entity) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Text Display GUI");
        for (int i = 0; i < inventory.getSize(); i++) {
            switch (i) {
                case 2 -> inventory.setItem(i, GUIItems.textBackgroundColor(entity.getBackgroundColor()));
                case 3 -> inventory.setItem(i, GUIItems.textDefaultBackground(entity.isDefaultBackground()));
                case 4 -> inventory.setItem(i, GUIItems.name(entity.getCustomName()));
                case 5 -> inventory.setItem(i, GUIItems.rightRotNormalize(Utilities.getData(entity, "GUIRRNormalize")));
                case 6 -> inventory.setItem(i, GUIItems.height(entity.getDisplayHeight()));
                case 7 -> inventory.setItem(i, GUIItems.shadowRadius(entity.getShadowRadius()));
                case 8 -> inventory.setItem(i, GUIItems.skyLight(entity.getBrightness() != null ? entity.getBrightness().getSkyLight() : -1));

                case 10 -> inventory.setItem(i, GUIItems.text());
                case 11 -> inventory.setItem(i, GUIItems.textBackgroundOpacity(entity.getBackgroundColor()));
                case 12 -> inventory.setItem(i, GUIItems.textSeeThrough(entity.isSeeThrough()));
                case 13 -> inventory.setItem(i, GUIItems.textOpacity(entity.getTextOpacity()));
                case 14 -> inventory.setItem(i, GUIItems.leftRotNormalize(Utilities.getData(entity, "GUILRNormalize")));
                case 15 -> inventory.setItem(i, GUIItems.width(entity.getDisplayWidth()));
                case 16 -> inventory.setItem(i, GUIItems.shadowStrength(entity.getShadowStrength()));
                case 17 -> inventory.setItem(i, GUIItems.blockLight(entity.getBrightness() != null ? entity.getBrightness().getBlockLight() : -1));

                case 20 -> inventory.setItem(i, GUIItems.textAlignment(entity.getAlignment()));
                case 21 -> inventory.setItem(i, GUIItems.textShadow(entity.isShadowed()));
                case 22 -> inventory.setItem(i, GUIItems.textLineWidth(entity.getLineWidth()));
                case 23 -> inventory.setItem(i, GUIItems.viewRange(entity.getViewRange()));
                case 24 -> inventory.setItem(i, GUIItems.billboard(entity.getBillboard()));
                case 25 -> inventory.setItem(i, GUIItems.lock());
                case 26 -> inventory.setItem(i, GUIItems.delete());
                default -> inventory.setItem(i, GUIItems.filler());
            }
        }
        return inventory;
    }

    public static ItemStack[] getInventoryItems() {
        ItemStack[] array = new ItemStack[36];

        array[0] = InventoryItems.gui();
        array[1] = InventoryItems.rotateYaw();
        array[2] = InventoryItems.rotatePitch();
        array[3] = InventoryItems.moveX();
        array[4] = InventoryItems.moveY();
        array[5] = InventoryItems.moveZ();

        array[27] = InventoryItems.spawnItemDisplay();
        array[28] = InventoryItems.spawnBlockDisplay();
        array[29] = InventoryItems.spawnTextDisplay();
        array[30] = InventoryItems.centerPivot();
        array[31] = InventoryItems.highlightTarget();
        array[32] = InventoryItems.unlock();

        array[18] = InventoryItems.translationX();
        array[19] = InventoryItems.translationY();
        array[20] = InventoryItems.translationZ();
        array[21] = InventoryItems.scaleX();
        array[22] = InventoryItems.scaleY();
        array[23] = InventoryItems.scaleZ();

        array[9] = InventoryItems.leftRotationX();
        array[10] = InventoryItems.leftRotationY();
        array[11] = InventoryItems.leftRotationZ();
        array[12] = InventoryItems.rightRotationX();
        array[13] = InventoryItems.rightRotationY();
        array[14] = InventoryItems.rightRotationZ();

        return array;
    }


}
