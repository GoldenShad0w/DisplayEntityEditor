package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class IntegerPrompt extends NumericPrompt {

    private final String message;

    /**
     * Used to create a new integer prompt
     * @param message The message that should be displayed
     */
    public IntegerPrompt(String message) {
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
    @Nullable
    @Override
    protected Prompt acceptValidatedInput(@NotNull ConversationContext conversationContext, @NotNull Number number) {
        int i = number.intValue();
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        InputManager.successfulIntegerInput(inputData, i, (Player) conversationContext.getForWhom());
        return END_OF_CONVERSATION;
    }

    /**
     * Used to check if a given number is valid
     * @param context The conversation context
     * @param input The input
     * @return True if it is an integer, false otherwise
     */
    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        return input instanceof Integer;
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
