package goldenshadow.displayentityeditor.commands;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public class Command implements CommandExecutor {


    private static final HashMap<UUID, ItemStack[]> savedInventories = new HashMap<>();

    /**
     * Used for when the deeditor command is issued
     * @param sender The sender
     * @param command The command
     * @param label The commands label
     * @param args The commands arguments
     * @return True if the command was correctly handled, false otherwise
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull org.bukkit.command.Command command,@NotNull String label, String[] args) {
        if (sender instanceof Player p) {
            if (savedInventories.containsKey(p.getUniqueId())) {
                returnInventory(p);
                p.sendMessage(Utilities.getInfoMessageFormat("Your inventory has been returned to you!"));
                savedInventories.remove(p.getUniqueId());
                return true;
            }
            saveInventory(p);
            ItemStack[] array = DisplayEntityEditor.inventoryFactory.getInventoryArray();
            for (int i = 0; i < array.length; i++) {
                p.getInventory().setItem(i, array[i]);
            }
            if (!p.getPersistentDataContainer().has(DisplayEntityEditor.toolPrecisionKey, PersistentDataType.DOUBLE)) {
                p.getPersistentDataContainer().set(DisplayEntityEditor.toolPrecisionKey, PersistentDataType.DOUBLE, 1d);
            }
            p.sendMessage(Utilities.getInfoMessageFormat("Given display entity tools. Left click to cycle through the tools."));
            p.sendMessage(ChatColor.DARK_AQUA + "[DEE] " + ChatColor.BLUE + "Run this command again to have your inventory returned!");
            return true;
        }
        sender.sendMessage("This command must be run by a player!");
        return true;
    }

    /**
     * A utility method used to save a players inventory in order to be able to return it later
     * @param player The player whose inventory should be saved
     */
    private static void saveInventory( Player player) {
        savedInventories.put(player.getUniqueId(), player.getInventory().getContents().clone());
        player.getInventory().clear();
    }

    /**
     * A utility method used to return a players inventory
     * @param player The player whose inventory should be returned
     */
    private static void returnInventory(Player player) {
        if (!savedInventories.containsKey(player.getUniqueId())) throw new RuntimeException("Return inventory didn't exist!");
        player.getInventory().clear();
        ItemStack[] saved = savedInventories.get(player.getUniqueId());
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            player.getInventory().setItem(i, saved[i]);
        }
    }
}
