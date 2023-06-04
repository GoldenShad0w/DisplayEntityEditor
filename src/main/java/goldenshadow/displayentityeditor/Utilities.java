package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.enums.PresetColor;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class Utilities {

    public static void setMeta(ItemStack item, String name, List<String> lore, String data) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING, data);

        meta.addItemFlags(ItemFlag.values());
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
    }

    public static boolean hasDataKey(ItemStack item) {
        if (item.getItemMeta() != null) {
            return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING);
        }
        return false;
    }

    public static String getToolValue(ItemStack item) {
        if (item.getItemMeta() != null) {
            return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING);
        }
        return null;
    }

    public static void setData(Display entity, String dataKey, boolean dataValue) {
        entity.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.STRING, Boolean.toString(dataValue));
    }

    public static boolean getData(Display entity, String dataKey) {
        String b = entity.getPersistentDataContainer().get(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.STRING);
        return b == null || b.equals("true");
    }

    public static String getColor(Color color) {
        if (color == null) return "None";
        return "RBG: " + color.getRed() + ", " + color.getBlue() + ", " + color.getGreen();
    }

    public static String getInfoMessageFormat(String message) {
        return ChatColor.DARK_AQUA + "[DEE] " + ChatColor.AQUA + message;
    }

    public static String getErrorMessageFormat(String message) {
        return ChatColor.DARK_RED + "[DEE] " + ChatColor.RED + message;
    }


}
