package goldenshadow.displayentityeditor.commands;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.conversation.InputData;
import goldenshadow.displayentityeditor.conversation.InputManager;
import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
            if (args.length == 0) {
                if (savedInventories.containsKey(p.getUniqueId())) {
                    returnInventory(p);
                    p.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("inventory_returned")));
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
                p.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("tools_received_1")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', DisplayEntityEditor.messageManager.getString("tools_received_2")));
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    DisplayEntityEditor.getPlugin().reloadConfig();
                    DisplayEntityEditor.alternateTextInput = DisplayEntityEditor.getPlugin().getConfig().getBoolean("alternate-text-input");
                    DisplayEntityEditor.useMiniMessageFormat = DisplayEntityEditor.getPlugin().getConfig().getBoolean("use-minimessage-format");
                    try {
                        DisplayEntityEditor.checkForMessageFile();
                    } catch (IOException e) {
                        p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("messages_reload_fail")));
                    }
                    p.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("config_reload")));
                    return true;
                }
            }

            if (args.length > 2) {
                if (DisplayEntityEditor.alternateTextInput) {
                    if (args[0].equalsIgnoreCase("edit")) {
                        String input = collectArgsToString(args);
                        Display display = Utilities.getNearestDisplayEntity(p.getLocation(), true);
                        if (display == null) {
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("name")) {
                            InputManager.successfulTextInput(new InputData(display, InputType.NAME, null), input, p);
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("background_color")) {
                            if (display instanceof TextDisplay) {
                                InputManager.successfulTextInput(new InputData(display, InputType.BACKGROUND_COLOR, null), input, p);
                                return true;
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("text")) {
                            if (display instanceof TextDisplay) {
                                InputManager.successfulTextInput(new InputData(display, InputType.TEXT, null), input, p);
                                return true;
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("text_append")) {
                            if (display instanceof TextDisplay) {
                                InputManager.successfulTextInput(new InputData(display, InputType.TEXT_APPEND, null), input, p);
                                return true;
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("glow_color")) {
                            if (!(display instanceof TextDisplay)) {
                                InputManager.successfulTextInput(new InputData(display, InputType.GLOW_COLOR, null), input, p);
                                return true;
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("block_state")) {
                            if (display instanceof BlockDisplay) {
                                InputManager.successfulTextInput(new InputData(display, InputType.BLOCK_STATE, ((BlockDisplay) display).getBlock().getMaterial()), input, p);
                                return true;
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("view_range")) {
                            if (InputManager.isFloat(input)) {
                                InputManager.successfulFloatInput(new InputData(display, InputType.VIEW_RANGE, null), Float.parseFloat(input), p);
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("display_height")) {
                            if (InputManager.isFloat(input)) {
                                InputManager.successfulFloatInput(new InputData(display, InputType.DISPLAY_HEIGHT, null), Float.parseFloat(input), p);
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("display_width")) {
                            if (InputManager.isFloat(input)) {
                                InputManager.successfulFloatInput(new InputData(display, InputType.DISPLAY_WIDTH, null), Float.parseFloat(input), p);
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("shadow_radius")) {
                            if (InputManager.isFloat(input)) {
                                InputManager.successfulFloatInput(new InputData(display, InputType.SHADOW_RADIUS, null), Float.parseFloat(input), p);
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("shadow_strength")) {
                            if (InputManager.isFloat(input)) {
                                float f = Float.parseFloat(input);
                                if (0 <= f && f <= 1) {
                                    InputManager.successfulFloatInput(new InputData(display, InputType.SHADOW_STRENGTH, null), Float.parseFloat(input), p);
                                    return true;
                                }
                                p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("shadow_strength_fail")));
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("text_opacity")) {
                            if (display instanceof TextDisplay) {
                                if (InputManager.isByte(input)) {
                                    InputManager.successfulByteInput(new InputData(display, InputType.TEXT_OPACITY, null), Integer.parseInt(input), p);
                                    return true;
                                }
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("background_opacity")) {
                            if (display instanceof TextDisplay) {
                                if (InputManager.isByte(input)) {
                                    InputManager.successfulByteInput(new InputData(display, InputType.BACKGROUND_OPACITY, null), Integer.parseInt(input), p);
                                    return true;
                                }
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("line_width")) {
                            if (display instanceof TextDisplay) {
                                if (InputManager.isInteger(input)) {
                                    InputManager.successfulIntegerInput(new InputData(display, InputType.LINE_WIDTH, null), Integer.parseInt(input), p);
                                    return true;
                                }
                            }
                            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
                            return true;
                        }
                    }
                }
                return true;
            }
            p.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_command_fail")));
            return true;
        }
        sender.sendMessage(DisplayEntityEditor.messageManager.getString("none_player_fail"));
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

    private static String collectArgsToString(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            if (i != 2) s.append(" ");
            s.append(args[i]);
        }
        return s.toString();
    }

}
