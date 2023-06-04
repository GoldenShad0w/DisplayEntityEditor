package goldenshadow.displayentityeditor.items;

import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIItems {

    public static ItemStack name(String name) {
        if (name == null) name = "None";
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Name",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + name,
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to reset"
                ),
                "GUIName"
        );
        return itemStack;
    }

    public static ItemStack glowing(boolean current) {
        ItemStack itemStack = new ItemStack(Material.SEA_LANTERN);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Toggle Glowing",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to toggle"
                ),
                "GUIGlow"
        );
        return itemStack;
    }

    public static ItemStack glowColor(Color current) {
        ItemStack itemStack = new ItemStack(Material.RED_DYE);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Glow Color",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + Utilities.getColor(current),
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to set from RGB value"
                ),
                "GUIGlowColor"
        );
        return itemStack;
    }

    public static ItemStack leftRotNormalize(boolean current) {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Toggle Left Rotation Normalization",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Will stop the shape of the display entity from",
                        ChatColor.GRAY + "deforming when rotated. Can usually be left on true",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to toggle"
                ),
                "GUILRNormalize"
        );
        return itemStack;
    }

    public static ItemStack rightRotNormalize(boolean current) {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Toggle Right Rotation Normalization",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Will stop the shape of the display entity from",
                        ChatColor.GRAY + "deforming when rotated. Can usually be left on true",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to toggle"
                ),
                "GUIRRNormalize"
        );
        return itemStack;
    }

    public static ItemStack viewRange(float current) {
        ItemStack itemStack = new ItemStack(Material.SPYGLASS);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set View Range",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines from how far the entity will be visible.",
                        ChatColor.GRAY + "The value counts in steps of 64 blocks and also factors",
                        ChatColor.GRAY + "in the scale of the entity. Can usually be left at 1.0",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUIViewRange"
        );
        return itemStack;
    }

    public static ItemStack width(float current) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Display Width",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines the maximum render width of the entity.",
                        ChatColor.GRAY + "If set to 0, there will be not render limit.",
                        ChatColor.GRAY + "Can usually be left at 0",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUIWidth"
        );
        return itemStack;
    }

    public static ItemStack height(float current) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Display Height",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines the maximum render height of the entity.",
                        ChatColor.GRAY + "If set to 0, there will be not render limit.",
                        ChatColor.GRAY + "Can usually be left at 0",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUIHeight"
        );
        return itemStack;
    }

    public static ItemStack billboard(Display.Billboard current) {
        ItemStack itemStack = new ItemStack(Material.PAINTING);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Billboard Type",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current.toString(),
                        ChatColor.GRAY + "Defines on what axis, if any, the display entity should",
                        ChatColor.GRAY + "visually rotate in the players direction",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change"
                ),
                "GUIBillboard"
        );
        return itemStack;
    }

    public static ItemStack shadowRadius(float current) {
        ItemStack itemStack = new ItemStack(Material.COAL);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Shadow Radius",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Changes the size of the shadow. Values larger",
                        ChatColor.GRAY + "than 64 are treated as 64. Values smaller than",
                        ChatColor.GRAY + "1 mean that the entity has no shadow",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUIShadowRadius"
        );
        return itemStack;
    }

    public static ItemStack shadowStrength(float current) {
        ItemStack itemStack = new ItemStack(Material.FLINT);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Shadow Strength",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Changes how strong the shadow should be.",
                        ChatColor.GRAY + "Value must be between 0 and 1 (inclusive)",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUIShadowStrength"
        );
        return itemStack;
    }

    public static ItemStack lock() {
        ItemStack itemStack = new ItemStack(Material.STRUCTURE_VOID);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Lock Entity",
                List.of(
                        ChatColor.GRAY + "Makes the entity uneditable until unlocked",
                        ChatColor.GRAY + "with the unlock item. This is useful when you",
                        ChatColor.GRAY + "have lots of entities in a small space and don't",
                        ChatColor.GRAY + "want to accidentally edit the wrong one",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to lock"
                ),
                "GUILock"
        );
        return itemStack;
    }

    public static ItemStack skyLight(int current) {
        ItemStack itemStack = new ItemStack(Material.LIGHT);
        setBrightnessLevel(itemStack, current == -1 ? 0 : current);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Sky Brightness",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + (current == -1 ? "Default" : current),
                        ChatColor.GRAY + "Used to override the default lighting level",
                        ChatColor.GRAY + "used to illuminate the display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to reset"
                ),
                "GUISkyLight"
        );
        return itemStack;
    }

    public static ItemStack blockLight(int current) {
        ItemStack itemStack = new ItemStack(Material.LIGHT);
        setBrightnessLevel(itemStack, current == -1 ? 0 : current);
        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Block Brightness",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + (current == -1 ? "Default" : current),
                        ChatColor.GRAY + "Used to override the default lighting level",
                        ChatColor.GRAY + "used to illuminate the display entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to reset"
                ),
                "GUIBlockLight"
        );
        return itemStack;
    }

    public static ItemStack delete() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Delete Entity",
                List.of(
                        ChatColor.GRAY + "Permanently deletes this entity",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to delete"
                ),
                "GUIDelete"
        );
        return itemStack;
    }

    public static ItemStack filler() {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        Utilities.setMeta(itemStack, " ", new ArrayList<>(), "GUIFiller");
        return itemStack;
    }

    public static ItemStack itemDisplayTransform(ItemDisplay.ItemDisplayTransform current) {
        ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Item Render Type",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current.toString(),
                        ChatColor.GRAY + "Defines how the item should be rendered",
                        ChatColor.GRAY + "(as if it was on a player head, hand, inventory etc.)",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change"
                ),
                "GUIItemDisplayTransform"
        );
        return itemStack;
    }

    public static ItemStack textOpacity(int current) {
        ItemStack itemStack = new ItemStack(Material.DRAGON_BREATH);
        if (current < 0) current = 0;

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Text Opacity",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Opacity value between 1 and 255 (inclusive) or 0 for default",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUITextOpacity"
        );
        return itemStack;
    }

    public static ItemStack textLineWidth(int current) {
        ItemStack itemStack = new ItemStack(Material.REPEATER);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Line Width",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines the maximum line width.",
                        ChatColor.GRAY + "Note that \\n can also be used to split lines",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUITextLineWidth"
        );
        return itemStack;
    }

    public static ItemStack textDefaultBackground(boolean current) {
        ItemStack itemStack = new ItemStack(Material.WHITE_STAINED_GLASS);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Toggle Default Background",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines whether the default background or the",
                        ChatColor.GRAY + "chosen background color should be used",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to toggle"
                ),
                "GUITextDefaultBackground"
        );
        return itemStack;
    }

    public static ItemStack textSeeThrough(boolean current) {
        ItemStack itemStack = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Toggle Visibility Through Blocks",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines whether the text should be visible",
                        ChatColor.GRAY + "through blocks",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to toggle"
                ),
                "GUITextSeeThrough"
        );
        return itemStack;
    }

    public static ItemStack textShadow(boolean current) {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Toggle Text Shadow",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        ChatColor.GRAY + "Defines whether the text should should",
                        ChatColor.GRAY + "have a shadow",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to toggle"
                ),
                "GUITextShadow"
        );
        return itemStack;
    }

    public static ItemStack textBackgroundColor(Color current) {
        ItemStack itemStack = new ItemStack(Material.RED_BANNER);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Background Color",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + Utilities.getColor(current),
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to set from RBG value"
                ),
                "GUITextBackgroundColor"
        );
        return itemStack;
    }

    public static ItemStack textBackgroundOpacity(Color current) {
        ItemStack itemStack = new ItemStack(Material.END_CRYSTAL);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Background Opacity",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + (current != null ? current.getAlpha() : "Default"),
                        ChatColor.GRAY + "Used to change the opacity of the background color.",
                        ChatColor.GRAY + "Should be able value between 0 and 255 (inclusive)",
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value"
                ),
                "GUITextBackgroundOpacity"
        );
        return itemStack;
    }

    public static ItemStack textAlignment(TextDisplay.TextAlignment current) {
        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Text Alignment",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current.toString(),
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change"
                ),
                "GUITextAlignment"
        );
        return itemStack;
    }

    public static ItemStack text() {
        ItemStack itemStack = new ItemStack(Material.OAK_SIGN);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Text",
                List.of(
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to change"
                ),
                "GUIText"
        );
        return itemStack;
    }

    private static void setBrightnessLevel(ItemStack itemStack, int current) {
        BlockDataMeta meta = (BlockDataMeta) itemStack.getItemMeta();
        assert meta != null;
        Levelled level = (Levelled) Bukkit.createBlockData(Material.LIGHT);
        level.setLevel(current);
        meta.setBlockData(level);
        itemStack.setItemMeta(meta);
    }

    public static ItemStack blockState(String current) {
        ItemStack itemStack = new ItemStack(Material.CHEST_MINECART);

        Utilities.setMeta(itemStack, ChatColor.YELLOW + "Set Block State",
                List.of(
                        ChatColor.GRAY + "Currently: " + ChatColor.DARK_AQUA + current,
                        " ",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "LEFT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to enter new value",
                        ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "RIGHT-CLICK " + ChatColor.RESET + ChatColor.YELLOW + "to reset"
                ),
                "GUIBlockState"
        );
        return itemStack;
    }
}
