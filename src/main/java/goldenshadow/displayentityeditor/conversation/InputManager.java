package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.enums.InputType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

/**
 * A manager class for handling text inputs
 */
public class InputManager {


    /**
     * Used to create a text input that awaits a string
     * @param player The player whose input is awaited
     * @param message The message sent to the player
     * @param data The data about the input
     */
    public static void createTextInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("generic_prompt").formatted(message, DisplayEntityEditor.messageManager.getString("prompt_escape_word")));
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new TextPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence(DisplayEntityEditor.messageManager.getString("prompt_escape_word")).buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }

    /**
     * Used to create a text input that awaits an integer
     * @param player The player whose input is awaited
     * @param message The message sent to the player
     * @param data The data about the input
     */
    public static void createIntegerInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("generic_prompt").formatted(message, DisplayEntityEditor.messageManager.getString("prompt_escape_word")));
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new IntegerPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence(DisplayEntityEditor.messageManager.getString("prompt_escape_word")).buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }

    /**
     * Used to create a text input that awaits a float
     * @param player The player whose input is awaited
     * @param message The message sent to the player
     * @param data The data about the input
     */
    public static void createFloatInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("generic_prompt").formatted(message, DisplayEntityEditor.messageManager.getString("prompt_escape_word")));
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new FloatPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence(DisplayEntityEditor.messageManager.getString("prompt_escape_word")).buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }

    /**
     * Used to create a text input that awaits a byte
     * @param player The player whose input is awaited
     * @param message The message sent to the player
     * @param data The data about the input
     */
    public static void createByteInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("generic_prompt").formatted(message, DisplayEntityEditor.messageManager.getString("prompt_escape_word")));
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new BytePrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence(DisplayEntityEditor.messageManager.getString("prompt_escape_word")).buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isByte(String s) {
        if (isInteger(s)) {
            int i = Integer.parseInt(s);
            return 0 <= i && i <= 255;
        }
        return false;
    }

    public static void successfulIntegerInput(InputData inputData, int i, Player player) {
        if (inputData.inputType() == InputType.LINE_WIDTH) {
            ((TextDisplay) inputData.entity()).setLineWidth(i);
            player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("line_width_success")));
        }
    }

    @SuppressWarnings("deprecation")
    public static void successfulByteInput(InputData inputData, int integer, Player player) {
        switch (inputData.inputType()) {
            case TEXT_OPACITY -> {
                byte b = (byte) integer;
                ((TextDisplay) inputData.entity()).setTextOpacity(b);
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("opacity_success")));
            }
            case BACKGROUND_OPACITY -> {

                TextDisplay t = (TextDisplay) inputData.entity();
                if (t.getBackgroundColor() != null) {
                    t.setBackgroundColor(Color.fromARGB(integer, t.getBackgroundColor().getRed(), t.getBackgroundColor().getGreen(), t.getBackgroundColor().getBlue()));
                } else {
                    t.setBackgroundColor(Color.fromARGB(integer,0,0,0));
                }
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("opacity_success")));
            }
        }
    }

    public static void successfulFloatInput(InputData inputData, float f, Player player) {
        switch (inputData.inputType()) {
            case VIEW_RANGE -> {
                inputData.entity().setViewRange(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("view_range_success")));
            }
            case DISPLAY_WIDTH -> {
                inputData.entity().setDisplayWidth(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("display_width_success")));
            }
            case DISPLAY_HEIGHT -> {
                inputData.entity().setDisplayHeight(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("display_height_success")));
            }
            case SHADOW_RADIUS -> {
                inputData.entity().setShadowRadius(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("shadow_radius_success")));
            }
            case SHADOW_STRENGTH -> {
                if (0 <= f && f <= 1) {
                    inputData.entity().setShadowStrength(f);
                    player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("shadow_strength_success")));
                } else {
                    player.sendRawMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("shadow_strength_fail")));
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public static void successfulTextInput(InputData inputData, String s ,Player player) {
        switch (inputData.inputType()) {
            case NAME -> {

                inputData.entity().setCustomNameVisible(true);

                if (DisplayEntityEditor.useMiniMessageFormat) {
                    Component c = DisplayEntityEditor.miniMessage.deserialize(s);
                    BaseComponent[] b = BungeeComponentSerializer.get().serialize(c);
                    inputData.entity().setCustomName(TextComponent.toLegacyText(b));
                } else {
                    inputData.entity().setCustomName(ChatColor.translateAlternateColorCodes('&', s));
                }
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("name_success")));
            }
            case TEXT -> {
                String message = s;

                if (DisplayEntityEditor.useMiniMessageFormat) {
                    Component c = DisplayEntityEditor.miniMessage.deserialize(s);
                    BaseComponent[] b = BungeeComponentSerializer.get().serialize(c);
                    ((TextDisplay) inputData.entity()).setText(TextComponent.toLegacyText(b));
                } else {
                    message = message.replace("\\n", "\n");
                    ((TextDisplay) inputData.entity()).setText(ChatColor.translateAlternateColorCodes('&', message));
                }

                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("text_success")));
            }
            case TEXT_APPEND -> {
                String message = s;
                if (DisplayEntityEditor.useMiniMessageFormat) {
                    Component c = DisplayEntityEditor.miniMessage.deserialize(s);
                    BaseComponent[] b = BungeeComponentSerializer.get().serialize(c);
                    ((TextDisplay) inputData.entity()).setText(((TextDisplay) inputData.entity()).getText() + TextComponent.toLegacyText(b));
                } else {
                    message = message.replace("\\n", "\n");
                    ((TextDisplay) inputData.entity()).setText(ChatColor.translateAlternateColorCodes('&', ((TextDisplay) inputData.entity()).getText() + message));
                }
                player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("text_append_success")));
            }
            case BACKGROUND_COLOR -> {
                int[] array = parseStringToRGB(s);
                if (array != null) {
                    TextDisplay t = (TextDisplay) inputData.entity();
                    if (t.getBackgroundColor() != null) {
                        t.setBackgroundColor(Color.fromARGB(t.getBackgroundColor().getAlpha(), array[0], array[1], array[2]));
                    } else {
                        t.setBackgroundColor(Color.fromARGB(255,array[0],array[1],array[2]));
                    }
                    player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("background_color_success")));
                } else {
                    player.sendRawMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_color_fail")));
                }
            }
            case GLOW_COLOR -> {
                int[] array = parseStringToRGB(s);
                if (array != null) {

                    BlockData blockData;
                    if (inputData.entity() instanceof BlockDisplay) blockData = ((BlockDisplay) inputData.entity()).getBlock();
                    else {
                        blockData = null;
                    }

                    inputData.entity().setGlowColorOverride(Color.fromRGB(array[0], array[1], array[2]));

                    if (inputData.entity() instanceof BlockDisplay) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(DisplayEntityEditor.getPlugin(), () -> ((BlockDisplay) inputData.entity()).setBlock(blockData), 1L);
                    }

                    player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("glow_color_success")));
                } else {
                    player.sendRawMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_color_fail")));
                }
            }
            case BLOCK_STATE -> {
                if (inputData.blockMaterial() != null) {
                    try {
                        BlockData blockData = Bukkit.createBlockData(inputData.blockMaterial(), s);
                        ((BlockDisplay) inputData.entity()).setBlock(blockData);
                        player.sendRawMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("block_state_success")));
                    } catch (IllegalArgumentException e) {
                        player.sendRawMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("block_state_fail")));
                    }
                }
            }
        }
    }

    /**
     * Used to parse a text input for an RBG value into an array of those values
     * @param input The text input
     * @return An array where index 0 is red, 1 is green and 2 is blue
     */
    private static int[] parseStringToRGB(String input) {
        String[] parts = input.split(",");
        if (parts.length != 3) {
            return null;
        }

        try {
            int[] values = new int[3];
            for (int i = 0; i < 3; i++) {
                values[i] = Integer.parseInt(parts[i].trim());
            }
            return values;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
