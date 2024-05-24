package goldenshadow.displayentityeditor.items;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;

import java.util.ArrayList;

/**
 * A utility class where all the gui items are created
 */
public class GUIItems {

    /**
     * Creates the rename gui item
     * @param name The current name
     * @return The item
     */
    public ItemStack name(String name) {
        if (name == null) name = DisplayEntityEditor.messageManager.getString("none");
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("name_name"),
                DisplayEntityEditor.messageManager.getList("name_lore"),
                "GUIName",
                name
        );
        return itemStack;
    }

    /**
     * Creates the glowing gui item
     * @param current If the entity is currently glowing
     * @return The item
     */
    public ItemStack glowing(boolean current) {
        ItemStack itemStack = new ItemStack(Material.SEA_LANTERN);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("glowing_name"),
                DisplayEntityEditor.messageManager.getList("glowing_lore"),
                "GUIGlow",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the glow color gui item
     * @param current The current glow color
     * @return The item
     */
    public ItemStack glowColor(Color current) {
        ItemStack itemStack = new ItemStack(Material.RED_DYE);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("glow_color_name"),
                DisplayEntityEditor.messageManager.getList("glow_color_lore"),
                "GUIGlowColor",
                Utilities.getColor(current)
        );
        return itemStack;
    }

    /**
     * Creates the left rotation normalised gui item
     * @param current If left rotation is currently normalised
     * @return The item
     */
    public ItemStack leftRotNormalize(boolean current) {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("left_rotation_normalize_name"),
                DisplayEntityEditor.messageManager.getList("rotation_normalize_lore"),
                "GUILRNormalize",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the right rotation normalised gui item
     * @param current If right rotation is currently normalised
     * @return The item
     */
    public ItemStack rightRotNormalize(boolean current) {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("right_rotation_normalize_name"),
                DisplayEntityEditor.messageManager.getList("rotation_normalize_lore"),
                "GUIRRNormalize",
                Boolean.toString(current)
        );
        return itemStack;
    }

    /**
     * Creates the view range gui item
     * @param current The current view range
     * @return The item
     */
    public ItemStack viewRange(float current) {
        ItemStack itemStack = new ItemStack(Material.SPYGLASS);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("view_range_name"),
                DisplayEntityEditor.messageManager.getList("view_range_lore"),
                "GUIViewRange",
                Float.toString(current)
        );
        return itemStack;
    }

    /**
     * Creates the display width gui item
     * @param current The current display width
     * @return The item
     */
    public ItemStack width(float current) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("width_name"),
                DisplayEntityEditor.messageManager.getList("width_lore"),
                "GUIWidth",
                Float.toString(current)
        );
        return itemStack;
    }

    /**
     * Creates the display height gui item
     * @param current The current display height
     * @return The item
     */
    public ItemStack height(float current) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("height_name"),
                DisplayEntityEditor.messageManager.getList("height_lore"),
                "GUIHeight",
                Float.toString(current)
        );
        return itemStack;
    }

    /**
     * Creates the billboard type gui item
     * @param current The current billboard type
     * @return The item
     */
    public ItemStack billboard(Display.Billboard current) {
        ItemStack itemStack = new ItemStack(Material.PAINTING);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("billboard_name"),
                DisplayEntityEditor.messageManager.getList("billboard_lore"),
                "GUIBillboard",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the shadow radius gui item
     * @param current The current shadow radius
     * @return The item
     */
    public ItemStack shadowRadius(float current) {
        ItemStack itemStack = new ItemStack(Material.COAL);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("shadow_radius_name"),
                DisplayEntityEditor.messageManager.getList("shadow_radius_lore"),
                "GUIShadowRadius",
                Float.toString(current)
        );
        return itemStack;
    }

    /**
     * Creates the shadow strength gui item
     * @param current The current shadow strength
     * @return The item
     */
    public ItemStack shadowStrength(float current) {
        ItemStack itemStack = new ItemStack(Material.FLINT);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("shadow_strength_name"),
                DisplayEntityEditor.messageManager.getList("shadow_strength_lore"),
                "GUIShadowStrength",
                Float.toString(current)
        );
        return itemStack;
    }

    /**
     * Creates the lock gui item
     * @return The item
     */
    public ItemStack lock() {
        ItemStack itemStack = new ItemStack(Material.STRUCTURE_VOID);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("lock_name"),
                DisplayEntityEditor.messageManager.getList("lock_lore"),
                "GUILock"
        );
        return itemStack;
    }

    /**
     * Creates the skylight gui item
     * @param current The current skylight
     * @return The item
     */
    public ItemStack skyLight(int current) {
        ItemStack itemStack = new ItemStack(Material.LIGHT);
        setBrightnessLevel(itemStack, current == -1 ? 0 : current);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("sky_light_name"),
                DisplayEntityEditor.messageManager.getList("light_lore"),
                "GUISkyLight",
                (current == -1 ? DisplayEntityEditor.messageManager.getString("default") : String.valueOf(current))
        );
        return itemStack;
    }

    /**
     * Creates the block light gui item
     * @param current The current block light
     * @return The item
     */
    public ItemStack blockLight(int current) {
        ItemStack itemStack = new ItemStack(Material.LIGHT);
        setBrightnessLevel(itemStack, current == -1 ? 0 : current);
        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("block_light_name"),
                DisplayEntityEditor.messageManager.getList("light_lore"),
                "GUIBlockLight",
                (current == -1 ? DisplayEntityEditor.messageManager.getString("default") : String.valueOf(current))
        );
        return itemStack;
    }

    /**
     * Creates the delete gui item
     * @return The item
     */
    public ItemStack delete() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("delete_name"),
                DisplayEntityEditor.messageManager.getList("delete_lore"),
                "GUIDelete"
        );
        return itemStack;
    }

    /**
     * Creates the filler gui item
     * @return The item
     */
    public ItemStack filler() {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        Utilities.setMeta(itemStack, " ", new ArrayList<>(), "GUIFiller");
        return itemStack;
    }

    /**
     * Creates the item transform gui item
     * @param current The current transform type
     * @return The item
     */
    public ItemStack itemDisplayTransform(ItemDisplay.ItemDisplayTransform current) {
        ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("item_display_transform_name"),
                DisplayEntityEditor.messageManager.getList("item_display_transform_lore"),
                "GUIItemDisplayTransform",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the text opacity gui item
     * @param current The current text opacity
     * @return The item
     */
    public ItemStack textOpacity(int current) {
        ItemStack itemStack = new ItemStack(Material.DRAGON_BREATH);
        if (current < 0) current = 0;

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_opacity_name"),
                DisplayEntityEditor.messageManager.getList("text_opacity_lore"),
                "GUITextOpacity",
                current
        );
        return itemStack;
    }

    /**
     * Creates the line width gui item
     * @param current The current line width
     * @return The item
     */
    public ItemStack textLineWidth(int current) {
        ItemStack itemStack = new ItemStack(Material.REPEATER);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_line_width_name"),
                DisplayEntityEditor.messageManager.getList("text_line_width_lore"),
                "GUITextLineWidth",
                current
        );
        return itemStack;
    }

    /**
     * Creates the default background gui item
     * @param current If the background is currently set to default
     * @return The item
     */
    public ItemStack textDefaultBackground(boolean current) {
        ItemStack itemStack = new ItemStack(Material.WHITE_STAINED_GLASS);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_default_background_name"),
                DisplayEntityEditor.messageManager.getList("text_default_background_lore"),
                "GUITextDefaultBackground",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the text see through gui item
     * @param current If the text is currently visible through blocks
     * @return The item
     */
    public ItemStack textSeeThrough(boolean current) {
        ItemStack itemStack = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_see_through_name"),
                DisplayEntityEditor.messageManager.getList("text_see_through_lore"),
                "GUITextSeeThrough",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the text shadow gui item
     * @param current If the text currently has shadows
     * @return The item
     */
    public ItemStack textShadow(boolean current) {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_shadow_name"),
                DisplayEntityEditor.messageManager.getList("text_shadow_lore"),
                "GUITextShadow",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the text background color gui item
     * @param current The current background color
     * @return The item
     */
    public ItemStack textBackgroundColor(Color current) {
        ItemStack itemStack = new ItemStack(Material.RED_BANNER);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_background_color_name"),
                DisplayEntityEditor.messageManager.getList("text_background_color_lore"),
                "GUITextBackgroundColor",
                Utilities.getColor(current)
        );
        return itemStack;
    }

    /**
     * Creates the text background opacity gui item
     * @param current The current background color
     * @return The item
     */
    public ItemStack textBackgroundOpacity(Color current) {
        ItemStack itemStack = new ItemStack(Material.END_CRYSTAL);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_background_opacity_name"),
                DisplayEntityEditor.messageManager.getList("text_background_opacity_lore"),
                "GUITextBackgroundOpacity",
                current.getAlpha()
        );
        return itemStack;
    }

    /**
     * Creates the text alignment gui item
     * @param current The current text alignment
     * @return The item
     */
    public ItemStack textAlignment(TextDisplay.TextAlignment current) {
        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_alignment_name"),
                DisplayEntityEditor.messageManager.getList("text_alignment_lore"),
                "GUITextAlignment",
                Utilities.getObjectNameMessage(current)
        );
        return itemStack;
    }

    /**
     * Creates the text gui item
     * @return The item
     * @implNote This item will not show the current text, as it could be very long and therefore be unreadable when displayed as item lore. If the user wants to see what text is currently being displayed, they should just close the gui for a second and read what's in front of them
     */
    public ItemStack text() {
        ItemStack itemStack = new ItemStack(Material.OAK_SIGN);

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("text_name"),
                DisplayEntityEditor.messageManager.getList("text_lore"),
                "GUIText"
        );
        return itemStack;
    }

    /**
     * Creates the block state gui item
     * @param current The current block state. If there is no data, it will be displayed as '[]'
     * @return The item
     */
    public ItemStack blockState(String current) {
        ItemStack itemStack = new ItemStack(Material.CHEST_MINECART);

        String currentState = current.contains("[") ? current.substring(current.indexOf('['), current.indexOf(']') + 1) : "[]";

        Utilities.setMeta(itemStack, DisplayEntityEditor.messageManager.getString("block_state_name"),
                DisplayEntityEditor.messageManager.getList("block_state_lore"),
                "GUIBlockState",
                currentState
        );
        return itemStack;
    }

    /**
     * A utility method used to set the brightness level of a light item
     * @param current The brightness level it should be set to
     */
    private void setBrightnessLevel(ItemStack itemStack, int current) {
        BlockDataMeta meta = (BlockDataMeta) itemStack.getItemMeta();
        assert meta != null;
        Levelled level = (Levelled) Bukkit.createBlockData(Material.LIGHT);
        level.setLevel(current);
        meta.setBlockData(level);
        itemStack.setItemMeta(meta);
    }


}
