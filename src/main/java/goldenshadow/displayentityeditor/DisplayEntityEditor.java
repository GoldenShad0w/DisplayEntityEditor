package goldenshadow.displayentityeditor;

import goldenshadow.displayentityeditor.commands.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DisplayEntityEditor extends JavaPlugin {

    private static DisplayEntityEditor plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("displayentityeditor")).setExecutor(new Command());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DisplayEntityEditor getPlugin() {
        return plugin;
    }
}
