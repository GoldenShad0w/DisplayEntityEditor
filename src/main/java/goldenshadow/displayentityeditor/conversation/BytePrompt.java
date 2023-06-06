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

    public BytePrompt(String message) {
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

    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        if (input instanceof Integer i) {
            return 0 <= i && i <= 255;
        }
        return false;
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
