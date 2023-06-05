package goldenshadow.displayentityeditor.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    /**
     * Used to listen to when a player closes an inventory
     * @param event The event
     */
    @EventHandler
    public void close(InventoryCloseEvent event) {
        InventoryClick.currentEditMap.remove(event.getPlayer().getUniqueId());
    }
}
