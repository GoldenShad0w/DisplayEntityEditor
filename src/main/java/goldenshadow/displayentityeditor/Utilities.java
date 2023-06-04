package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.enums.PresetColor;
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

    public static void setMeta(ItemStack item, String name, List<String> lore, String dataKey) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.BYTE, Byte.valueOf("1"));
        meta.addItemFlags(ItemFlag.values());
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
    }

    public static boolean hasDataKey(ItemStack item, String dataKey) {
        if (item.getItemMeta() != null) {
            return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.BOOLEAN);
        }
        return false;
    }

    public static void setData(Display entity, String dataKey, boolean dataValue) {
        entity.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.BOOLEAN, dataValue);
    }

    public static boolean getData(Display entity, String dataKey) {
        Boolean b = entity.getPersistentDataContainer().get(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.BOOLEAN);
        return b != null ? b : true;
    }

    public static String getColor(Color color) {
        if (color == null) return "WHITE";
        for (PresetColor c : PresetColor.values()) {
            if (c.getColor().equals(color)) return c.toString();
        }
        return "RBG: " + color.getRed() + ", " + color.getBlue() + ", " + color.getGreen();
    }

}
