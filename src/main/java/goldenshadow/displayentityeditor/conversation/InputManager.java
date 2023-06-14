package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;

public class InputManager {


    public static void createTextInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new TextPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }

    public static void createIntegerInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new IntegerPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }

    public static void createFloatInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new FloatPrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }

    public static void createByteInput(Player player, String message, InputData data) {
        message = Utilities.getInfoMessageFormat(message + " (enter \"cancel\" to abort)");
        Conversation c = DisplayEntityEditor.conversationFactory.withFirstPrompt(new BytePrompt(message)).thatExcludesNonPlayersWithMessage("This must be done by a player!").withLocalEcho(false).withEscapeSequence("cancel").buildConversation(player);
        c.getContext().setSessionData("data", data);
        c.begin();
    }
}
