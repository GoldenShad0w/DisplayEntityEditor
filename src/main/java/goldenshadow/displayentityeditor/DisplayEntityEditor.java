package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.commands.Command;
import goldenshadow.displayentityeditor.commands.TabComplete;
import goldenshadow.displayentityeditor.events.Interact;
import goldenshadow.displayentityeditor.events.InventoryClick;
import goldenshadow.displayentityeditor.events.InventoryClose;
import goldenshadow.displayentityeditor.events.PlayerJoin;
import goldenshadow.displayentityeditor.inventories.InventoryFactory;
import goldenshadow.displayentityeditor.items.GUIItems;
import goldenshadow.displayentityeditor.items.InventoryItems;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Display;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Consumer;

public final class DisplayEntityEditor extends JavaPlugin {

    private static DisplayEntityEditor plugin;
    public static ConversationFactory conversationFactory;
    public static InventoryFactory inventoryFactory;
    public static HashMap<UUID, Display> currentEditMap = new HashMap<>();
    public static NamespacedKey toolPrecisionKey;
    public static boolean alternateTextInput = false;
    public static boolean useMiniMessageFormat = false;
    public static MiniMessage miniMessage = MiniMessage.builder()
            .tags(TagResolver.builder()
                    .resolver(StandardTags.color())
                    .resolver(StandardTags.decorations())
                    .resolver(StandardTags.gradient())
                    .resolver(StandardTags.rainbow())
                    .resolver(StandardTags.font())
                    .resolver(StandardTags.newline())
                    .resolver(StandardTags.keybind())
                    .resolver(StandardTags.nbt())
                    .resolver(StandardTags.score())
                    .resolver(StandardTags.transition())
                    .build()
            )
            .build();
    public static MessageManager messageManager;

    /**
     * Used for when the plugin starts up
     */
    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveConfig();
        alternateTextInput = getConfig().getBoolean("alternate-text-input");
        useMiniMessageFormat = getConfig().getBoolean("use-minimessage-format");

        try {
            checkForMessageFile();
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to load messages.yml!");
        }

        conversationFactory = new ConversationFactory(plugin);
        inventoryFactory = new InventoryFactory(new GUIItems(), new InventoryItems());
        Objects.requireNonNull(getCommand("displayentityeditor")).setExecutor(new Command());
        Objects.requireNonNull(getCommand("displayentityeditor")).setTabCompleter(new TabComplete());
        Bukkit.getPluginManager().registerEvents(new Interact(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClose(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), plugin);
        toolPrecisionKey = new NamespacedKey(plugin, "toolPrecision");

        new Metrics(plugin, 18672);

        getVersion(v -> {
            if (this.getDescription().getVersion().equals(v)) {
                getLogger().info(messageManager.getString("version_check_success"));
            } else {
                getLogger().warning(messageManager.getString("version_check_fail"));
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
                plugin.getLogger().warning(messageManager.getString("version_check_error").formatted(exception.getMessage()));
            }
        });
    }

    public static void checkForMessageFile() throws IOException {
        File file = new File(getPlugin().getDataFolder().getAbsolutePath() + "/messages.yml");
        if (!file.exists()) {
            plugin.getLogger().info("Unable to find messages.yml - generating new file!");
            InputStream ip = DisplayEntityEditor.class.getClassLoader().getResourceAsStream("messages.yml");
            assert ip != null;
            Files.copy(ip, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        messageManager = new MessageManager();
    }
}
