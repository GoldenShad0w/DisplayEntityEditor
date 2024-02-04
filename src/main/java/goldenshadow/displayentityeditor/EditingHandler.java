package goldenshadow.displayentityeditor;

import org.bukkit.entity.Display;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.*;

public class EditingHandler {

    /**
     * The map of player UUIDs to the displays they are currently editing.
     */
    private final Map<UUID, Collection<Display>> editingDisplaysMap = new HashMap<>();

    /**
     * @param player The player that should be editing the displays.
     * @param displays The collection of displays the player should be editing.
     */
    public void setEditingDisplays(Player player, Collection<Display> displays) {
        editingDisplaysMap.put(player.getUniqueId(), displays);
    }

    /**
     * @param player The player that should no longer be editing any displays.
     */
    public void removeEditingDisplays(Player player) {
        editingDisplaysMap.remove(player.getUniqueId());
    }

    /**
     * @param player The player that is editing display(s).
     * @param lockSearchToggle Whether the searched entities should be locked/unlocked.
     * @return The collection of displays the player is currently editing.
     * If the player is not editing any displays, the nearest display entity to the player's location is returned
     * as a singleton collection.
     * @see Utilities#getNearestDisplayEntity(org.bukkit.Location, boolean)
     */
    @Nullable
    public Collection<Display> getEditingDisplays(Player player, boolean lockSearchToggle) {
        Collection<Display> displays = editingDisplaysMap.get(player.getUniqueId());

        if (displays == null) {
            Display d = Utilities.getNearestDisplayEntity(player.getLocation(), lockSearchToggle);
            if (d != null) {
                return Collections.singleton(d);
            }
            return null;
        }

        return displays;
    }

}
