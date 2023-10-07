package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FloatPrompt extends NumericPrompt {

    private final String message;

    /**
     * Used to create a new float prompt
     * @param message The message that should be displayed
     */
    public FloatPrompt(String message) {
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
        float f = number.floatValue();
        Player player = (Player) conversationContext.getForWhom();
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        InputManager.successfulFloatInput(inputData, f, player);
        return END_OF_CONVERSATION;
    }

    /**
     * Used to check if a given number is valid
     * @param context The conversation context
     * @param input The input
     * @return True if it is a float, false otherwise. Additionally, if the input type is shadow strength, the value must be between 0 and 1 for true to be returned
     */
    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        InputData data = (InputData) context.getSessionData("data");
        assert data != null;
        if (data.inputType() == InputType.SHADOW_STRENGTH) {
            float f = input.floatValue();
            return 0 <= f && f <= 1;
        }
        return true;
    }

    /**
     * Used for when an invalid non number input was given
     * @param context The conversation context
     * @param invalidInput The invalid input
     * @return An error message
     */
    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("float_fail"));
    }

    /**
     * Used for when an invalid number was given
     * @param context The conversation context
     * @param invalidInput The invalid input
     * @return An error message
     */
    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
        InputData data = (InputData) context.getSessionData("data");
        assert data != null;
        if (data.inputType() == InputType.SHADOW_STRENGTH) {
            return Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("float_shadow_strength_fail"));
        }
        return Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("float_fail"));
    }
}
