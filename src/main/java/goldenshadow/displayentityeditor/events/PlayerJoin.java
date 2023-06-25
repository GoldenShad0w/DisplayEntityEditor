package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoin implements Listener {

    /**
     * Used to listen for when a player joins
     * @param event The event
     */
    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        if (event.getPlayer().isOp()) {
            if (DisplayEntityEditor.getPlugin().getConfig().getBoolean("send-update-message-on-join")) {
                DisplayEntityEditor.
                getVersion(v -> {
                    if (!DisplayEntityEditor.getPlugin().getDescription().getVersion().equals(v)) {
                        event.getPlayer().sendMessage(ChatColor.RED + "[DEE] You are not running the latest version! Update your plugin here: https://www.spigotmc.org/resources/display-entity-editor.110267/");
                        event.getPlayer().sendMessage(ChatColor.GRAY + "If you would like to disable these messages, you can do so in the config file.");
                    }
                });
            }
        }
    }
}
