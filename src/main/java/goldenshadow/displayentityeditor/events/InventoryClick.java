package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.conversation.InputData;
import goldenshadow.displayentityeditor.conversation.InputManager;
import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class InventoryClick implements Listener {


    /**
     * Used to listen for when a player clicks on a gui item
     * @param event The event
     */
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Block Display GUI") || player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Item Display GUI") || player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Text Display GUI")) {
            Display entity = DisplayEntityEditor.currentEditMap.get(player.getUniqueId());
            if (event.getClickedInventory() != null && !event.getClickedInventory().equals(player.getInventory())) {
                if (event.getCurrentItem() != null && Utilities.hasDataKey(event.getCurrentItem())) {
                    event.setCancelled(true);
                    String value = Utilities.getToolValue(event.getCurrentItem());
                    if (value != null) {
                        switch (value) {
                            case "GUIName" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createTextInput(player, "Please enter the new name it chat! You can use '&' for color codes.", new InputData(entity, InputType.NAME, null));
                                } else {
                                    entity.setCustomNameVisible(false);
                                    entity.setCustomName(null);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().name(null));
                                }
                            }
                            case "GUIGlow" -> {
                                if (event.isLeftClick()) {
                                    boolean b = !entity.isGlowing();
                                    entity.setGlowing(b);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().glowing(b));
                                }
                            }
                            case "GUIGlowColor" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createTextInput(player, "Please enter the new rgb values it chat! Use the format: R, G, B", new InputData(entity, InputType.GLOW_COLOR, null));
                                }
                            }
                            case "GUILRNormalize" -> {
                                if (event.isLeftClick()) {
                                    boolean b = !Utilities.getData(entity, "GUILRNormalize");
                                    Utilities.setData(entity, "GUILRNormalize", b);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().leftRotNormalize(b));
                                }
                            }
                            case "GUIRRNormalize" -> {
                                if (event.isLeftClick()) {
                                    boolean b = !Utilities.getData(entity, "GUIRRNormalize");
                                    Utilities.setData(entity, "GUIRRNormalize", b);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().rightRotNormalize(b));
                                }
                            }
                            case "GUIViewRange" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createFloatInput(player, "Please enter the value in chat!", new InputData(entity, InputType.VIEW_RANGE, null));
                                }
                            }
                            case "GUIWidth" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createFloatInput(player, "Please enter the value in chat!", new InputData(entity, InputType.DISPLAY_WIDTH, null));
                                }
                            }
                            case "GUIHeight" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createFloatInput(player, "Please enter the value in chat!", new InputData(entity, InputType.DISPLAY_HEIGHT, null));
                                }
                            }
                            case "GUIBillboard" -> {
                                if (event.isLeftClick()) {
                                    Display.Billboard billboard = entity.getBillboard();
                                    billboard = Display.Billboard.values()[(billboard.ordinal()+1) % Display.Billboard.values().length];
                                    entity.setBillboard(billboard);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().billboard(billboard));
                                }
                            }
                            case "GUIShadowRadius" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createFloatInput(player, "Please enter the value in chat!", new InputData(entity, InputType.SHADOW_RADIUS, null));

                                }
                            }
                            case "GUIShadowStrength" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createFloatInput(player, "Please enter the value in chat!", new InputData(entity, InputType.SHADOW_STRENGTH, null));
                                }
                            }
                            case "GUILock" -> {
                                if (event.isLeftClick()) {
                                    entity.addScoreboardTag("dee:locked");
                                    player.sendMessage(Utilities.getInfoMessageFormat("Display entity locked! Use the unlock item to unlock it again!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUISkyLight" -> {
                                if (event.isLeftClick()) {
                                    Display.Brightness b;
                                    if (entity.getBrightness() != null) {
                                        b = new Display.Brightness(entity.getBrightness().getBlockLight(), (entity.getBrightness().getSkyLight()+1) % 16);
                                    } else {
                                        b = new Display.Brightness(0,0);
                                    }
                                    entity.setBrightness(b);
                                    player.getOpenInventory().setItem(8, DisplayEntityEditor.inventoryFactory.getGuiItems().skyLight(b.getSkyLight()));
                                    player.getOpenInventory().setItem(17, DisplayEntityEditor.inventoryFactory.getGuiItems().blockLight(b.getBlockLight()));
                                }
                                if (event.isRightClick()) {
                                    entity.setBrightness(null);
                                    player.getOpenInventory().setItem(8, DisplayEntityEditor.inventoryFactory.getGuiItems().skyLight(-1));
                                    player.getOpenInventory().setItem(17, DisplayEntityEditor.inventoryFactory.getGuiItems().blockLight(-1));
                                }
                            }
                            case "GUIBlockLight" -> {
                                if (event.isLeftClick()) {
                                    Display.Brightness b;
                                    if (entity.getBrightness() != null) {
                                        b = new Display.Brightness((entity.getBrightness().getBlockLight() + 1) % 16, entity.getBrightness().getSkyLight());
                                    } else {
                                        b = new Display.Brightness(0,0);
                                    }
                                    entity.setBrightness(b);
                                    player.getOpenInventory().setItem(8, DisplayEntityEditor.inventoryFactory.getGuiItems().skyLight(b.getSkyLight()));
                                    player.getOpenInventory().setItem(17, DisplayEntityEditor.inventoryFactory.getGuiItems().blockLight(b.getBlockLight()));
                                }
                                if (event.isRightClick()) {
                                    entity.setBrightness(null);
                                    player.getOpenInventory().setItem(8, DisplayEntityEditor.inventoryFactory.getGuiItems().skyLight(-1));
                                    player.getOpenInventory().setItem(17, DisplayEntityEditor.inventoryFactory.getGuiItems().blockLight(-1));
                                }
                            }
                            case "GUIDelete" -> {
                                if (event.isLeftClick()) {
                                    entity.remove();
                                    player.closeInventory();
                                    player.sendMessage(Utilities.getInfoMessageFormat("Display entity deleted!"));
                                }
                            }
                            case "GUIItemDisplayTransform" -> {
                                if (event.isLeftClick()) {
                                    ItemDisplay itemDisplay = (ItemDisplay) entity;
                                    ItemDisplay.ItemDisplayTransform transform = itemDisplay.getItemDisplayTransform();
                                    transform = ItemDisplay.ItemDisplayTransform.values()[(transform.ordinal()+1) % ItemDisplay.ItemDisplayTransform.values().length];
                                    itemDisplay.setItemDisplayTransform(transform);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().itemDisplayTransform(transform));
                                }
                            }
                            case "GUIBlockState" -> {
                                if (event.isLeftClick()) {

                                    player.closeInventory();
                                    InputManager.createTextInput(player, "Please enter the new block state! You can either use f3 or an online tool to help generate it.", new InputData(entity, InputType.BLOCK_STATE, ((BlockDisplay) entity).getBlock().getMaterial()));

                                    player.closeInventory();
                                }
                                if (event.isRightClick()) {
                                    BlockDisplay b = (BlockDisplay) entity;
                                    b.setBlock(Bukkit.createBlockData(b.getBlock().getMaterial()));
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().blockState(b.getBlock().getAsString(true)));
                                }
                            }
                            case "GUITextOpacity" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createByteInput(player, "Please enter the value in chat!", new InputData(entity, InputType.TEXT_OPACITY, null));
                                }
                            }
                            case "GUITextLineWidth" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createIntegerInput(player, "Please enter the value in chat!", new InputData(entity, InputType.LINE_WIDTH, null));
                                }
                            }
                            case "GUITextDefaultBackground" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay t = (TextDisplay) entity;
                                    boolean b = !t.isDefaultBackground();
                                    t.setDefaultBackground(b);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().textDefaultBackground(b));
                                }
                            }
                            case "GUITextSeeThrough" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay t = (TextDisplay) entity;
                                    boolean b = !t.isSeeThrough();
                                    t.setSeeThrough(b);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().textSeeThrough(b));
                                }
                            }
                            case "GUITextShadow" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay t = (TextDisplay) entity;
                                    boolean b = !t.isShadowed();
                                    t.setShadowed(b);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().textShadow(b));
                                }
                            }
                            case "GUITextBackgroundColor" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createTextInput(player, "Please enter the new rgb values it chat! Use the format: R, G, B", new InputData(entity, InputType.BACKGROUND_COLOR, null));
                                }
                            }
                            case "GUITextBackgroundOpacity" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createByteInput(player, "Please enter the value in chat!", new InputData(entity, InputType.BACKGROUND_OPACITY, null));
                                }
                            }
                            case "GUITextAlignment" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay textDisplay = (TextDisplay) entity;
                                    TextDisplay.TextAlignment alignment = textDisplay.getAlignment();
                                    alignment = TextDisplay.TextAlignment.values()[(alignment.ordinal()+1) % TextDisplay.TextAlignment.values().length];
                                    textDisplay.setAlignment(alignment);
                                    player.getOpenInventory().setItem(event.getSlot(), DisplayEntityEditor.inventoryFactory.getGuiItems().textAlignment(alignment));
                                }
                            }
                            case "GUIText" -> {
                                if (event.isLeftClick()) {
                                    player.closeInventory();
                                    InputManager.createTextInput(player, "Please enter the new text in chat! You can use '&' for color codes and \\n to create line breaks.", new InputData(entity, InputType.TEXT, null));
                                }
                            }
                        }
                    }
                }
            }
            if (player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Block Display GUI")) {

                BlockDisplay blockDisplay = (BlockDisplay) entity;
                Bukkit.getScheduler().scheduleSyncDelayedTask(DisplayEntityEditor.getPlugin(), () -> {
                    ItemStack itemStack = player.getOpenInventory().getItem(10);
                    if (itemStack != null) {
                        if (itemStack.getType().isBlock()) {
                            BlockData data = Bukkit.createBlockData(itemStack.getType());
                            blockDisplay.setBlock(data);
                        } else {
                            BlockData data = Bukkit.createBlockData(Material.AIR);
                            blockDisplay.setBlock(data);
                            player.sendMessage(Utilities.getErrorMessageFormat("Invalid block! The item must be a block item!"));
                            player.getOpenInventory().setItem(10, null);
                        }
                    } else {
                        blockDisplay.setBlock(Bukkit.createBlockData(Material.AIR));
                    }
                });


            } else if (player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Item Display GUI")) {
                ItemDisplay itemDisplay = (ItemDisplay) entity;
                Bukkit.getScheduler().scheduleSyncDelayedTask(DisplayEntityEditor.getPlugin(), () -> itemDisplay.setItemStack(player.getOpenInventory().getItem(10)),1L);
            }
        }
    }
}
