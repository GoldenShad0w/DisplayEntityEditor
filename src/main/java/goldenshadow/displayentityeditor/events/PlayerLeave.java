package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.commands.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    /**
     * Used to listen for when a player leaves
     * @param event The event
     */
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Command.returnInventory(event.getPlayer());
    }
}
