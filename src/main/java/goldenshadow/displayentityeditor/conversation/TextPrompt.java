package goldenshadow.displayentityeditor.conversation;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class TextPrompt extends StringPrompt {

    private final String message;

    /**
     * Used to create a new string prompt
     * @param message The message that should be displayed
     */
    public TextPrompt(String message) {
        this.message = message;
    }

    /**
     * Used to get the prompt text
     * @param conversationContext The conversation context
     * @return The message specified when this object was created
     */
    @NotNull
    @Override
    public String getPromptText(@NotNull ConversationContext conversationContext) {
        return message;
    }

    /**
     * Used for when a valid input was given
     * @param conversationContext The conversation context
     * @param s The value that was given
     * @return End of the conversation
     */
    @Nullable
    @Override
    public Prompt acceptInput(@NotNull ConversationContext conversationContext, @Nullable String s) {
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        if (s != null) {
            Player player = (Player) conversationContext.getForWhom();
            InputManager.successfulTextInput(inputData, s, player);
        }
        return END_OF_CONVERSATION;
    }




}
