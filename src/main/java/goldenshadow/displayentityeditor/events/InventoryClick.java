package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.enums.InputType;
import goldenshadow.displayentityeditor.items.GUIItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class InventoryClick implements Listener {

    public static HashMap<UUID, Display> currentEditMap = new HashMap<>();

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Block Display GUI") || player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Item Display GUI") || player.getOpenInventory().getTitle().equals(ChatColor.BOLD + "Text Display GUI")) {
            Display entity = currentEditMap.get(player.getUniqueId());
            if (event.getClickedInventory() != null && !event.getClickedInventory().equals(player.getInventory())) {
                if (event.getCurrentItem() != null && Utilities.hasDataKey(event.getCurrentItem())) {
                    event.setCancelled(true);
                    String value = Utilities.getToolValue(event.getCurrentItem());
                    if (value != null) {
                        switch (value) {
                            case "GUIName" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.NAME, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the new name it chat! You can use '&' for color codes."));
                                    player.closeInventory();
                                } else {
                                    entity.setCustomNameVisible(false);
                                    entity.setCustomName(null);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.name(null));
                                }
                            }
                            case "GUIGlow" -> {
                                if (event.isLeftClick()) {
                                    boolean b = !entity.isGlowing();
                                    entity.setGlowing(b);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.glowing(b));
                                }
                            }
                            case "GUIGlowColor" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.GLOW_COLOR, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the new rgb values it chat! Use the format: R, G, B"));
                                    player.closeInventory();
                                }
                            }
                            case "GUILRNormalize" -> {
                                if (event.isLeftClick()) {
                                    boolean b = !Utilities.getData(entity, "GUILRNormalize");
                                    Utilities.setData(entity, "GUILRNormalize", b);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.leftRotNormalize(b));
                                }
                            }
                            case "GUIRRNormalize" -> {
                                if (event.isLeftClick()) {
                                    boolean b = !Utilities.getData(entity, "GUIRRNormalize");
                                    Utilities.setData(entity, "GUIRRNormalize", b);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.rightRotNormalize(b));
                                }
                            }
                            case "GUIViewRange" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.VIEW_RANGE, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUIWidth" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.DISPLAY_WIDTH, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUIHeight" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.DISPLAY_HEIGHT, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUIBillboard" -> {
                                if (event.isLeftClick()) {
                                    Display.Billboard billboard = entity.getBillboard();
                                    billboard = Display.Billboard.values()[(billboard.ordinal()+1) % Display.Billboard.values().length];
                                    entity.setBillboard(billboard);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.billboard(billboard));
                                }
                            }
                            case "GUIShadowRadius" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.SHADOW_RADIUS, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUIShadowStrength" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.SHADOW_STRENGTH, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
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
                                    player.getOpenInventory().setItem(8, GUIItems.skyLight(b.getSkyLight()));
                                    player.getOpenInventory().setItem(17, GUIItems.blockLight(b.getBlockLight()));
                                }
                                if (event.isRightClick()) {
                                    entity.setBrightness(null);
                                    player.getOpenInventory().setItem(8, GUIItems.skyLight(-1));
                                    player.getOpenInventory().setItem(17, GUIItems.blockLight(-1));
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
                                    player.getOpenInventory().setItem(8, GUIItems.skyLight(b.getSkyLight()));
                                    player.getOpenInventory().setItem(17, GUIItems.blockLight(b.getBlockLight()));
                                }
                                if (event.isRightClick()) {
                                    entity.setBrightness(null);
                                    player.getOpenInventory().setItem(8, GUIItems.skyLight(-1));
                                    player.getOpenInventory().setItem(17, GUIItems.blockLight(-1));
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
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.itemDisplayTransform(transform));
                                }
                            }
                            case "GUIBlockState" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.BLOCK_STATE, ((BlockDisplay) entity).getBlock().getMaterial(), getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the new block state! You can either use f3 or an online tool to help generate it."));
                                    player.closeInventory();
                                }
                                if (event.isRightClick()) {
                                    BlockDisplay b = (BlockDisplay) entity;
                                    b.setBlock(Bukkit.createBlockData(b.getBlock().getMaterial()));
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.blockState(b.getBlock().getAsString(true)));
                                }
                            }
                            case "GUITextOpacity" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.TEXT_OPACITY, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUITextLineWidth" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.LINE_WIDTH, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUITextDefaultBackground" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay t = (TextDisplay) entity;
                                    boolean b = !t.isDefaultBackground();
                                    t.setDefaultBackground(b);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.textDefaultBackground(b));
                                }
                            }
                            case "GUITextSeeThrough" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay t = (TextDisplay) entity;
                                    boolean b = !t.isSeeThrough();
                                    t.setSeeThrough(b);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.textSeeThrough(b));
                                }
                            }
                            case "GUITextShadow" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay t = (TextDisplay) entity;
                                    boolean b = !t.isShadowed();
                                    t.setShadowed(b);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.textShadow(b));
                                }
                            }
                            case "GUITextBackgroundColor" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.BACKGROUND_COLOR, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the new rgb values it chat! Use the format: R, G, B"));
                                    player.closeInventory();
                                }
                            }
                            case "GUITextBackgroundOpacity" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.BACKGROUND_OPACITY, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the new value in chat!"));
                                    player.closeInventory();
                                }
                            }
                            case "GUITextAlignment" -> {
                                if (event.isLeftClick()) {
                                    TextDisplay textDisplay = (TextDisplay) entity;
                                    TextDisplay.TextAlignment alignment = textDisplay.getAlignment();
                                    alignment = TextDisplay.TextAlignment.values()[(alignment.ordinal()+1) % TextDisplay.TextAlignment.values().length];
                                    textDisplay.setAlignment(alignment);
                                    player.getOpenInventory().setItem(event.getSlot(), GUIItems.textAlignment(alignment));
                                }
                            }
                            case "GUIText" -> {
                                if (event.isLeftClick()) {
                                    PlayerChat.inputDataMap.put(player.getUniqueId(), new PlayerChat.InputData(entity, InputType.TEXT, null, getDecayTime()));
                                    player.sendMessage(Utilities.getInfoMessageFormat("Please enter the new text in chat! You can use '&' for color codes and \\n to create line breaks."));
                                    player.closeInventory();
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


    private static long getDecayTime() {
        return System.currentTimeMillis() + 20000;
    }
}
