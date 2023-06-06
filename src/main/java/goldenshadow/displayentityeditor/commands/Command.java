package goldenshadow.displayentityeditor.commands;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public class Command implements CommandExecutor {


    private static final HashMap<UUID, ItemStack[]> savedInventories = new HashMap<>();

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
            p.sendMessage(Utilities.getInfoMessageFormat("Given display entity tools. Left click to cycle through the tools."));
            p.sendMessage(ChatColor.DARK_AQUA + "[DEE] " + ChatColor.BLUE + "Run this command again to have your inventory returned!");
            return true;
        }
        sender.sendMessage("This command must be run by a player!");
        return true;
    }

    private static void saveInventory( Player player) {
        savedInventories.put(player.getUniqueId(), player.getInventory().getContents().clone());
        player.getInventory().clear();
    }

    private static void returnInventory(Player player) {
        if (!savedInventories.containsKey(player.getUniqueId())) throw new RuntimeException("Return inventory didn't exist!");
        player.getInventory().clear();
        ItemStack[] saved = savedInventories.get(player.getUniqueId());
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            player.getInventory().setItem(i, saved[i]);
        }
    }
}
