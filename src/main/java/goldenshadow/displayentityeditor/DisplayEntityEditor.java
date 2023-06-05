package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.commands.Command;
import goldenshadow.displayentityeditor.events.Interact;
import goldenshadow.displayentityeditor.events.InventoryClick;
import goldenshadow.displayentityeditor.events.InventoryClose;
import goldenshadow.displayentityeditor.events.PlayerChat;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Consumer;

public final class DisplayEntityEditor extends JavaPlugin {

    private static DisplayEntityEditor plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("displayentityeditor")).setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new Interact(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerChat(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClose(), plugin);

        Metrics metrics = new Metrics(plugin, 18672);

        getVersion(v -> {
            if (this.getDescription().getVersion().equals(v)) {
                getLogger().info("You are on the latest version!");
            } else {
                getLogger().warning("You are not running the latest version! Update your plugin here: https://www.spigotmc.org/resources/display-entity-editor.110267/");
            }
        });


        new BukkitRunnable() {
            @Override
            public void run() {
                Iterator<UUID> it = PlayerChat.inputDataMap.keySet().iterator();
                while (it.hasNext()) {
                    UUID uuid = it.next();
                    if (PlayerChat.inputDataMap.get(uuid).decayTime() < System.currentTimeMillis()) {
                        it.remove();
                        Player p = Bukkit.getPlayer(uuid);
                        if (p != null) {
                            p.sendMessage(Utilities.getErrorMessageFormat("Too much time has passed, please try again!"));
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DisplayEntityEditor getPlugin() {
        return plugin;
    }

    private void getVersion(final Consumer<String> consumer) {
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
