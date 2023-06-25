package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;

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
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new TextPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
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
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new IntegerPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
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
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new FloatPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
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
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new BytePrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }
}
