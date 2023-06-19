package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.Color;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class BytePrompt extends NumericPrompt {

    private final String message;

    /**
     * Used to create a new byte prompt
     * @param message The message that should be displayed
     */
    public BytePrompt(String message) {
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
     * @param number The value that was given
     * @return End of the conversation
     */
    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    protected Prompt acceptValidatedInput(@NotNull ConversationContext conversationContext, @NotNull Number number) {
        int integer = number.intValue();
        Player player = (Player) conversationContext.getForWhom();
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        switch (inputData.inputType()) {
            case TEXT_OPACITY -> {
                byte b = (byte) integer;
                ((TextDisplay) inputData.entity()).setTextOpacity(b);
                player.sendRawMessage(Utilities.getInfoMessageFormat("Text opacity set!"));
            }
            case BACKGROUND_OPACITY -> {

                TextDisplay t = (TextDisplay) inputData.entity();
                if (t.getBackgroundColor() != null) {
                    t.setBackgroundColor(Color.fromARGB(integer, t.getBackgroundColor().getRed(), t.getBackgroundColor().getGreen(), t.getBackgroundColor().getBlue()));
                } else {
                    t.setBackgroundColor(Color.fromARGB(integer,0,0,0));
                }
                player.sendRawMessage(Utilities.getInfoMessageFormat("Background opacity set!"));
            }
        }
        return END_OF_CONVERSATION;
    }

    /**
     * Used to check if a given number is valid
     * @param context The conversation context
     * @param input The input
     * @return True if it is an integer between 0 and 255, false otherwise
     */
    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        if (input instanceof Integer i) {
            return 0 <= i && i <= 255;
        }
        return false;
    }

    /**
     * Used for when an invalid non number input was given
     * @param context The conversation context
     * @param invalidInput The invalid input
     * @return An error message
     */
    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
        return Utilities.getErrorMessageFormat("The value needs to be an integer (whole number)!");
    }

    /**
     * Used for when an invalid number was given
     * @param context The conversation context
     * @param invalidInput The invalid input
     * @return An error message
     */
    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return Utilities.getErrorMessageFormat("The value needs to be an integer (whole number)!");
    }
}
