package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.commands.Command;
import goldenshadow.displayentityeditor.events.Interact;
import goldenshadow.displayentityeditor.events.InventoryClick;
import goldenshadow.displayentityeditor.events.InventoryClose;
import goldenshadow.displayentityeditor.events.PlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

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
}
