package goldenshadow.displayentityeditor;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.List;

public class Utilities {

    /**
     * Used to easily set an items meta
     * @param item The item
     * @param name The name it should get
     * @param lore The lore it should get
     * @param data The data it should get
     */
    public static void setMeta(ItemStack item, String name, List<String> lore, String data) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&' ,name));
        lore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING, data);

        meta.addItemFlags(ItemFlag.values());
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
    }

    /**
     * Used to easily set an items meta
     * @param item The item
     * @param name The name it should get
     * @param lore The lore it should get
     * @param data The data it should get
     * @param formatData Data that should be used to format a string
     */
    public static void setMeta(ItemStack item, String name, List<String> lore, String data, Object... formatData) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&' ,name));
        lore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate).formatted(formatData));
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING, data);

        meta.addItemFlags(ItemFlag.values());
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
    }

    /**
     * Used to check if an item has a specific NamespacedKey
     * @param item The item
     * @return True if it does, otherwise false
     */
    public static boolean hasDataKey(ItemStack item) {
        if (item.getItemMeta() != null) {
            return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING);
        }
        return false;
    }

    /**
     * Used to get the specific tools type
     * @param item The item
     * @return The tool type
     */
    public static String getToolValue(ItemStack item) {
        if (item.getItemMeta() != null) {
            return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(DisplayEntityEditor.getPlugin(), "tool"), PersistentDataType.STRING);
        }
        return null;
    }

    /**
     * Used to add a new namespacedKey to an entity
     * @param entity The entity
     * @param dataKey The key
     * @param dataValue The value
     * @implNote Yes, I am aware that PersistentDataType.BOOLEAN exists, but I was getting NoSuchField exceptions, so I chose the path of least resistance
     */
    public static void setData(Display entity, String dataKey, boolean dataValue) {
        entity.getPersistentDataContainer().set(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.STRING, Boolean.toString(dataValue));
    }

    /**
     * Used to get data stored in an entity
     * @param entity The entity
     * @param dataKey The key
     * @implNote Yes, I am aware that PersistentDataType.BOOLEAN exists, but I was getting NoSuchField exceptions, so I chose the path of least resistance
     */
    public static boolean getData(Display entity, String dataKey) {
        String b = entity.getPersistentDataContainer().get(new NamespacedKey(DisplayEntityEditor.getPlugin(), dataKey), PersistentDataType.STRING);
        return b == null || b.equals("true");
    }

    /**
     * Used to get a string representation of an RGB color
     * @param color The color
     * @return The string representation
     */
    public static String getColor(Color color) {
        if (color == null) return "None";
        return "RBG: " + color.getRed() + ", " + color.getBlue() + ", " + color.getGreen();
    }

    /**
     * Used to format an info message for chat
     * @param message The raw message
     * @return The formatted message
     */
    public static String getInfoMessageFormat(String message) {
        return ChatColor.translateAlternateColorCodes('&', DisplayEntityEditor.messageManager.getString("info_message_format").formatted(message));
    }

    /**
     * Used to format an error message for chat
     * @param message The raw message
     * @return The formatted message
     */
    public static String getErrorMessageFormat(String message) {
        return ChatColor.translateAlternateColorCodes('&', DisplayEntityEditor.messageManager.getString("error_message_format").formatted(message));
    }

    /**
     * Used to get the nearest display entity
     * @param location The location from where the nearest display entity should be gotten
     * @param lockSearchToggle If this method should look for locked or unlocked entities. If true, it will only look for unlocked entities, and if false it will only look for locked ones
     * @return The nearest display entity or null if none were found
     */
    @Nullable
    public static Display getNearestDisplayEntity(Location location, boolean lockSearchToggle) {
        Display entity = null;
        double distance = 5;
        assert location.getWorld() != null;
        for (Entity e : location.getWorld().getNearbyEntities(location, 5,5,5)) {
            if (e instanceof Display d) {
                if (lockSearchToggle) {
                    if (!d.getScoreboardTags().contains("dee:locked")) {
                        double dis = d.getLocation().distance(location);
                        if (dis < distance) {
                            entity = d;
                            distance = dis;
                        }
                    }
                } else {
                    if (d.getScoreboardTags().contains("dee:locked")) {
                        double dis = d.getLocation().distance(location);
                        if (dis < distance) {
                            entity = d;
                            distance = dis;
                        }
                    }
                }
            }
        }
        return entity;
    }

    public static BaseComponent[] getCommandMessage(String commandMessage, String hint) {
        TextComponent click = new TextComponent(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', DisplayEntityEditor.messageManager.getString("command_message").formatted(commandMessage, hint)));

        click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/deeditor edit " + commandMessage));

        return new ComponentBuilder(click).create();
    }

}
