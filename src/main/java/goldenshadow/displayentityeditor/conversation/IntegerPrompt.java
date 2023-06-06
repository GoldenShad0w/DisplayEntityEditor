package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class IntegerPrompt extends NumericPrompt {

    private final String message;

    public IntegerPrompt(String message) {
        this.message = message;
    }

    @NotNull
    @Override
    public String getPromptText(@NotNull ConversationContext conversationContext) {
        return message;
    }

    @Nullable
    @Override
    protected Prompt acceptValidatedInput(@NotNull ConversationContext conversationContext, @NotNull Number number) {
        int i = number.intValue();
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        if (inputData.inputType() == InputType.LINE_WIDTH) {
            ((TextDisplay) inputData.entity()).setLineWidth(i);
            conversationContext.getForWhom().sendRawMessage(Utilities.getInfoMessageFormat("Line width set!"));
        }
        return END_OF_CONVERSATION;
    }

    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        return input instanceof Integer;
    }

    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
        return Utilities.getErrorMessageFormat("The value needs to be an integer (whole number)!");
    }

    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return Utilities.getErrorMessageFormat("The value needs to be an integer (whole number)!");
    }
}
