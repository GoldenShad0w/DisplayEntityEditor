package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class TextPrompt extends StringPrompt {

    private final String message;

    public TextPrompt(String message) {
        this.message = message;
    }


    @NotNull
    @Override
    public String getPromptText(@NotNull ConversationContext conversationContext) {
        return message;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public Prompt acceptInput(@NotNull ConversationContext conversationContext, @Nullable String s) {
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        if (s != null) {
            Player player = (Player) conversationContext.getForWhom();
            switch (inputData.inputType()) {
                case NAME -> {
                    inputData.entity().setCustomNameVisible(true);
                    inputData.entity().setCustomName(ChatColor.translateAlternateColorCodes('&', s));
                    player.sendRawMessage(Utilities.getInfoMessageFormat("Name set!"));
                }
                case TEXT -> {
                    String message = s;
                    message = message.replace("\\n", "\n");
                    ((TextDisplay) inputData.entity()).setText(ChatColor.translateAlternateColorCodes('&', message));
                    player.sendRawMessage(Utilities.getInfoMessageFormat("Set text!"));
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
                        player.sendRawMessage(Utilities.getInfoMessageFormat("Background color set!"));
                    } else {
                        player.sendRawMessage(Utilities.getErrorMessageFormat("The value needs follow the format: R, G, B"));
                    }
                }
                case GLOW_COLOR -> {
                    int[] array = parseStringToRGB(s);
                    if (array != null) {
                        inputData.entity().setGlowColorOverride(Color.fromRGB(array[0], array[1], array[2]));
                        player.sendRawMessage(Utilities.getInfoMessageFormat("Glow color set!"));
                    } else {
                        player.sendRawMessage(Utilities.getErrorMessageFormat("The value needs follow the format: R, G, B"));
                    }
                }
                case BLOCK_STATE -> {
                    if (inputData.blockMaterial() != null) {
                        try {
                            BlockData blockData = Bukkit.createBlockData(inputData.blockMaterial(), s);
                            ((BlockDisplay) inputData.entity()).setBlock(blockData);
                            player.sendRawMessage(Utilities.getInfoMessageFormat("Block state set!"));
                        } catch (IllegalArgumentException e) {
                            player.sendRawMessage(Utilities.getErrorMessageFormat("The value given was not a valid block state! Try looking at a block with the block state you want with f3 on to see its block states or use an online tool!"));
                        }
                    }
                }
            }
        }
        return END_OF_CONVERSATION;
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
