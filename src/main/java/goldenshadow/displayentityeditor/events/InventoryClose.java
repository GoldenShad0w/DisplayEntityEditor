package goldenshadow.displayentityeditor.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void close(InventoryCloseEvent event) {
        InventoryClick.currentEditMap.remove(event.getPlayer().getUniqueId());
    }
}
