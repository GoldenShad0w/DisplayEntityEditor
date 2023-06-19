package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.commands.Command;
import goldenshadow.displayentityeditor.events.Interact;
import goldenshadow.displayentityeditor.events.InventoryClick;
import goldenshadow.displayentityeditor.events.InventoryClose;
import goldenshadow.displayentityeditor.events.PlayerJoin;
import goldenshadow.displayentityeditor.inventories.InventoryFactory;
import goldenshadow.displayentityeditor.items.GUIItems;
import goldenshadow.displayentityeditor.items.InventoryItems;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Display;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

public final class DisplayEntityEditor extends JavaPlugin {

    private static DisplayEntityEditor plugin;
    public static ConversationFactory conversationFactory;
    public static InventoryFactory inventoryFactory;
    public static HashMap<UUID, Display> currentEditMap = new HashMap<>();
    public static NamespacedKey toolPrecisionKey;

    /**
     * Used for when the plugin starts up
     */
    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        conversationFactory = new ConversationFactory(plugin);
        inventoryFactory = new InventoryFactory(new GUIItems(), new InventoryItems());
        Objects.requireNonNull(getCommand("displayentityeditor")).setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new Interact(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClose(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), plugin);
        toolPrecisionKey = new NamespacedKey(plugin, "toolPrecision");

        new Metrics(plugin, 18672);

        getVersion(v -> {
            if (this.getDescription().getVersion().equals(v)) {
                getLogger().info("You are on the latest version!");
            } else {
                getLogger().warning("You are not running the latest version! Update your plugin here: https://www.spigotmc.org/resources/display-entity-editor.110267/");
            }
        });
    }

    /**
     * Getter for the plugin instance
     * @return The plugin
     */
    public static DisplayEntityEditor getPlugin() {
        return plugin;
    }

    /**
     * Used to get the newest version of the plugin available on spigot
     * @param consumer The consumer
     */
    public static void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=110267").openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().warning("Unable to check for updates: " + exception.getMessage());
            }
        });
    }
}
