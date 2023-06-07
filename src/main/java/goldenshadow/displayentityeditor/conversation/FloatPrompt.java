package goldenshadow.displayentityeditor.conversation;

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

    public FloatPrompt(String message) {
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
        float f = number.floatValue();
        Player player = (Player) conversationContext.getForWhom();
        InputData inputData = (InputData) conversationContext.getSessionData("data");
        assert inputData != null;
        switch (inputData.inputType()) {
            case VIEW_RANGE -> {
                inputData.entity().setViewRange(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat("View range set!"));
            }
            case DISPLAY_WIDTH -> {
                inputData.entity().setDisplayWidth(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat("Display width set!"));
            }
            case DISPLAY_HEIGHT -> {
                inputData.entity().setDisplayHeight(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat("Display height set!"));
            }
            case SHADOW_RADIUS -> {
                inputData.entity().setShadowRadius(f);
                player.sendRawMessage(Utilities.getInfoMessageFormat("Shadow radius set!"));
            }
            case SHADOW_STRENGTH -> {
                if (0 <= f && f <= 1) {
                    inputData.entity().setShadowStrength(f);
                    player.sendRawMessage(Utilities.getInfoMessageFormat("Shadow strength set!"));
                } else {
                    player.sendRawMessage(Utilities.getErrorMessageFormat("The value needs to be between 0 and 1!"));
                }
            }
        }
        return END_OF_CONVERSATION;
    }

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

    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return Utilities.getErrorMessageFormat("The value needs to be an decimal number!");
    }

    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
        InputData data = (InputData) context.getSessionData("data");
        assert data != null;
        if (data.inputType() == InputType.SHADOW_STRENGTH) {
            return Utilities.getErrorMessageFormat("The value needs to be an decimal number between 0 and 1 (inclusive)!");
        }
        return Utilities.getErrorMessageFormat("The value needs to be an decimal number!");
    }
}
