package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.EditingHandler;
import goldenshadow.displayentityeditor.Utilities;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;

public class Interact implements Listener {

    private static final DecimalFormat df = new DecimalFormat("#.####");

    private final EditingHandler editingHandler;

    public Interact(EditingHandler editingHandler) {
        this.editingHandler = editingHandler;
        df.setRoundingMode(RoundingMode.CEILING);
    }

    /**
     * Used to cycle the players inventory to make it easy to switch tools
     * @param p The player whose inventory should get cycled through
     */
    public static void cycleInventory(Player p) {
        ItemStack[] array = new ItemStack[36];
        for (int i = 0; i < 36; i++) {
            array[i] = p.getInventory().getItem(i);
        }
        ItemStack[] shiftedArray = new ItemStack[36];
        for (int i = 0; i < 36; i++) {
            int newIndex = (i + 9) % 36;
            shiftedArray[newIndex] = array[i];
        }
        for (int i = 0; i < 36; i++) {
            p.getInventory().setItem(i, shiftedArray[i]);
        }
    }

    /**
     * Used to spawn a new display entity
     * @param location The location of where it should be spawned
     * @param type The specific type of display entity
     */
    private static void spawnDisplayEntity(Location location, EntityType type) {
        assert location.getWorld() != null;
        location.setYaw(0);
        location.setPitch(0);
        if (type != EntityType.BLOCK_DISPLAY) {
            location.setX((int) location.getX() + (((location.getX()) < 0 ? -1 : 1) * 0.5));
            location.setZ((int) location.getZ() + (((location.getZ()) < 0 ? -1 : 1) * 0.5));
        }
        if (location.getY() < 0) {
            location.setY(location.getY() + 0.0001);
        }
        Display d = (Display) location.getWorld().spawnEntity(location, type, false);
        d.setVisualFire(true);

        if (d instanceof ItemDisplay) {
            ((ItemDisplay) d).setItemStack(new ItemStack(Material.DIAMOND));
        }
        if (d instanceof BlockDisplay) {
            ((BlockDisplay) d).setBlock(Bukkit.createBlockData(Material.GRASS_BLOCK));
        }
        if (d instanceof TextDisplay) {
            ((TextDisplay) d).setText("YOUR TEXT HERE");
            d.setBillboard(Display.Billboard.CENTER);
        }
    }

    /**
     * Used to highlight a specific display entity by making it glow and showing particles at its pivot point
     * @param display The entity that should be highlighted
     */
    private static void highlightEntity(Display display) {
        display.setGlowing(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(DisplayEntityEditor.getPlugin(), () -> display.setGlowing(false), 20L);
        display.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, display.getLocation(), 50, 0.2, 0.2, 0.2, 0);
    }

    private static void sendActionbarMessage(Player p, String message) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(net.md_5.bungee.api.ChatColor.DARK_AQUA + message));
    }

    /**
     * Used to clone a display entity
     * @param clone The clone
     * @param template The template
     */
    @SuppressWarnings("deprecation")
    private static void cloneEntity(Display clone, Display template) {
        clone.setBrightness(template.getBrightness());
        clone.setBillboard(template.getBillboard());
        clone.setCustomName(template.getCustomName());
        clone.setGlowColorOverride(template.getGlowColorOverride());
        clone.setGlowing(template.isGlowing());
        clone.setCustomNameVisible(template.isCustomNameVisible());
        clone.setShadowStrength(template.getShadowStrength());
        clone.setShadowRadius(template.getShadowRadius());
        clone.setDisplayHeight(template.getDisplayHeight());
        clone.setDisplayWidth(template.getDisplayWidth());
        clone.setViewRange(template.getViewRange());
        clone.setTransformation(template.getTransformation());
        clone.getLocation().setPitch(template.getLocation().getPitch());
        clone.getLocation().setYaw(template.getLocation().getYaw());
        if (clone instanceof ItemDisplay itemDisplay) {
            itemDisplay.setItemStack(((ItemDisplay) template).getItemStack());
            itemDisplay.setItemDisplayTransform(((ItemDisplay) template).getItemDisplayTransform());
        }
        if (clone instanceof BlockDisplay blockDisplay) {
            blockDisplay.setBlock(((BlockDisplay) template).getBlock());
        }
        if (clone instanceof TextDisplay textDisplay) {
            TextDisplay templateText = (TextDisplay) template;
            textDisplay.setText(templateText.getText());
            textDisplay.setBackgroundColor(templateText.getBackgroundColor());
            textDisplay.setShadowed(templateText.isShadowed());
            textDisplay.setAlignment(templateText.getAlignment());
            textDisplay.setTextOpacity(templateText.getTextOpacity());
            textDisplay.setSeeThrough(templateText.isSeeThrough());
            textDisplay.setDefaultBackground(templateText.isDefaultBackground());
            textDisplay.setLineWidth(templateText.getLineWidth());
        }
    }

    private static void updateItems(Player p) {
        for (int i = 0; i < p.getInventory().getContents().length; i++) {
            ItemStack it = p.getInventory().getContents()[i];
            if (it != null) {
                if (Utilities.hasDataKey(it)) {
                    String s = Utilities.getToolValue(it);
                    if (s != null) {
                        switch (s) {
                            case "InventoryRotateYaw" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().rotateYaw(p));
                            case "InventoryRotatePitch" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().rotatePitch(p));
                            case "InventoryMoveX" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().moveX(p));
                            case "InventoryMoveY" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().moveY(p));
                            case "InventoryMoveZ" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().moveZ(p));
                            case "InventoryLRX" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().leftRotationX(p));
                            case "InventoryLRY" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().leftRotationY(p));
                            case "InventoryLRZ" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().leftRotationZ(p));
                            case "InventoryRRX" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().rightRotationX(p));
                            case "InventoryRRY" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().rightRotationY(p));
                            case "InventoryRRZ" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().rightRotationZ(p));
                            case "InventoryTX" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().translationX(p));
                            case "InventoryTY" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().translationY(p));
                            case "InventoryTZ" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().translationZ(p));
                            case "InventorySX" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().scaleX(p));
                            case "InventorySY" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().scaleY(p));
                            case "InventorySZ" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().scaleZ(p));
                            case "InventoryGroupSelect" ->
                                    p.getInventory().setItem(i, DisplayEntityEditor.inventoryFactory.getInventoryItems().groupSelectTool(p));
                        }
                    }
                }
            }
        }
    }

    /**
     * Used to listener for when a player uses a tool
     * @param event The event
     */
    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Player player = event.getPlayer();
        if (!Utilities.hasDataKey(player.getInventory().getItemInMainHand())) {
            return;
        }

        event.setCancelled(true);

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            cycleInventory(player);
            event.setCancelled(true);
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        event.setCancelled(true);
        String toolValue = Utilities.getToolValue(item);

        if (toolValue == null) {
            return;
        }

        if (toolValue.equals("InventorySpawnItem")) {
            spawnDisplayEntity(player.getLocation(), EntityType.ITEM_DISPLAY);
            player.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("item_display_spawned")));
            return;
        }

        if (toolValue.equals("InventorySpawnBlock")) {
            spawnDisplayEntity(player.getLocation(), EntityType.BLOCK_DISPLAY);
            player.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("block_display_spawned")));
            return;
        }

        if (toolValue.equals("InventorySpawnText")) {
            spawnDisplayEntity(player.getLocation(), EntityType.TEXT_DISPLAY);
            player.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("text_display_spawned")));
            return;
        }

        if (toolValue.equals("InventoryUnlock")) {
            Collection<Display> displays = editingHandler.getEditingDisplays(player, false);

            if (displays == null) {
                player.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("unlock_fail")));
                return;
            }

            displays.forEach(display -> {
                //Please do not replace these scoreboard tag locks with persistent data storage! This is an intentional design choice so that you can use vanilla commands to target locked displays
                display.getScoreboardTags().remove("dee:locked");
                highlightEntity(display);
            });

            player.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("unlock_success")));
            return;
        }

        if (toolValue.equals("InventoryToolPrecision")) {
            double d = Utilities.getToolPrecision(player);
            if (player.isSneaking()) {
                if (d > 1) {
                    d = Math.max(0.1, d - 1);
                } else {
                    d = Math.max(0.1, d - 0.1);
                }
            } else {
                if (d < 1) {
                    d = Math.min(10, d + 0.1);
                } else {
                    d = Math.min(10, d + 1);
                }
            }
            d = (double) Math.round(d * 1000) / 1000;
            player.getPersistentDataContainer().set(DisplayEntityEditor.toolPrecisionKey, PersistentDataType.DOUBLE, d);
            updateItems(player);
            sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("tool_precision").formatted(df.format(d)));
            return;
        }

        Collection<Display> displays = editingHandler.getEditingDisplays(player, true);

        if (displays == null) {
            player.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
            return;
        }

        switch (toolValue) {
            case "InventoryGUI" -> {
                if (displays.size() != 1) {
                    player.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("gui_only_single_displays")));
                    return;
                }

                Display display = displays.iterator().next();

                if (DisplayEntityEditor.currentEditMap.containsValue(display)) {
                    player.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("gui_open_fail")));
                    return;
                }

                DisplayEntityEditor.currentEditMap.put(player.getUniqueId(), display);

                if (display instanceof ItemDisplay) {
                    player.openInventory(DisplayEntityEditor.inventoryFactory.createItemDisplayGUI((ItemDisplay) display));
                } else if (display instanceof BlockDisplay) {
                    player.openInventory(DisplayEntityEditor.inventoryFactory.createBlockDisplayGUI((BlockDisplay) display));
                } else {
                    player.openInventory(DisplayEntityEditor.inventoryFactory.createTextDisplayGUI((TextDisplay) display));
                }
            }
            case "InventoryRotateYaw" -> {
                if (player.isSneaking()) {
                    displays.forEach(display -> {
                        display.setRotation((float) (display.getLocation().getYaw() - 1 * Utilities.getToolPrecision(player)), display.getLocation().getPitch());
                        sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("yaw").formatted(df.format(display.getLocation().getYaw())));
                    });

                    return;
                }

                displays.forEach(display -> {
                    display.setRotation((float) (display.getLocation().getYaw() + 1 * Utilities.getToolPrecision(player)), display.getLocation().getPitch());
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("yaw").formatted(df.format(display.getLocation().getYaw())));
                });
            }
            case "InventoryRotatePitch" -> {
                if (player.isSneaking()) {
                    displays.forEach(display -> {
                        display.setRotation(display.getLocation().getYaw(), (float) (display.getLocation().getPitch() - 1 * Utilities.getToolPrecision(player)));
                        sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("pitch").formatted(df.format(display.getLocation().getPitch())));
                    });
                    return;
                }

                displays.forEach(display -> {
                    display.setRotation(display.getLocation().getYaw(), (float) (display.getLocation().getPitch() + 1 * Utilities.getToolPrecision(player)));
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("pitch").formatted(df.format(display.getLocation().getPitch())));
                });
            }
            case "InventoryMoveX" -> {
                if (player.isSneaking()) {
                    displays.forEach(display -> {
                        display.teleport(display.getLocation().add(-0.1 * Utilities.getToolPrecision(player), 0, 0));

                        sendActionbarMessage(player, "X: " + df.format(display.getLocation().getX()));
                        sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("move_x").formatted(df.format(display.getLocation().getX())));
                    });

                    return;
                }

                displays.forEach(display -> {
                    display.teleport(display.getLocation().add(0.1 * Utilities.getToolPrecision(player), 0, 0));
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("move_x").formatted(df.format(display.getLocation().getX())));
                });


            }
            case "InventoryMoveY" -> {
                if (player.isSneaking()) {
                    displays.forEach(display -> {
                        display.teleport(display.getLocation().add(0, -0.1 * Utilities.getToolPrecision(player), 0));
                        sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("move_y").formatted(df.format(display.getLocation().getY())));
                    });

                    return;
                }
                displays.forEach(display -> {
                    display.teleport(display.getLocation().add(0, 0.1 * Utilities.getToolPrecision(player), 0));
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("move_y").formatted(df.format(display.getLocation().getY())));
                });

            }
            case "InventoryMoveZ" -> {
                if (player.isSneaking()) {
                    displays.forEach(display -> {
                        display.teleport(display.getLocation().add(0, 0, -0.1 * Utilities.getToolPrecision(player)));
                        sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("move_z").formatted(df.format(display.getLocation().getZ())));
                    });

                    return;
                }
                displays.forEach(display -> {
                    display.teleport(display.getLocation().add(0, 0, 0.1 * Utilities.getToolPrecision(player)));
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("move_z").formatted(df.format(display.getLocation().getZ())));
                });

            }
            case "InventoryHighlight" -> {
                displays.forEach(Interact::highlightEntity);
            }
            case "InventoryCenterPivot" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (display instanceof BlockDisplay) {
                        t.getTranslation().set(-1 * (t.getScale().x() / 2), -1 * (t.getScale().y() / 2), -1 * (t.getScale().z() / 2));
                    } else {
                        t.getTranslation().set(0, 0, 0);
                    }
                    display.setTransformation(t);
                });
                sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("center_pivot"));
            }
            case "InventoryTX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (player.isSneaking()) {
                        t.getTranslation().add((float) (-0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    } else {
                        t.getTranslation().add((float) (0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("translation_x").formatted(df.format(t.getTranslation().x())));
                    display.setTransformation(t);
                });
            }
            case "InventoryTY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (player.isSneaking()) {
                        t.getTranslation().add(0, (float) (-0.1f * Utilities.getToolPrecision(player)), 0);
                    } else {
                        t.getTranslation().add(0, (float) (0.1f * Utilities.getToolPrecision(player)), 0);
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("translation_y").formatted(df.format(t.getTranslation().y())));
                    display.setTransformation(t);
                });
            }
            case "InventoryTZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (player.isSneaking()) {
                        t.getTranslation().add(0, 0, (float) (-0.1f * Utilities.getToolPrecision(player)));
                    } else {
                        t.getTranslation().add(0, 0, (float) (0.1f * Utilities.getToolPrecision(player)));
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("translation_z").formatted(df.format(t.getTranslation().z())));
                    display.setTransformation(t);
                });
            }
            case "InventorySX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (player.isSneaking()) {
                        t.getScale().add((float) (-0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    } else {
                        t.getScale().add((float) (0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("scale_x").formatted(df.format(t.getScale().x())));
                    display.setTransformation(t);
                });
            }
            case "InventorySY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (player.isSneaking()) {
                        t.getScale().add(0, (float) (-0.1f * Utilities.getToolPrecision(player)), 0);
                    } else {
                        t.getScale().add(0, (float) (0.1f * Utilities.getToolPrecision(player)), 0);
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("scale_y").formatted(df.format(t.getScale().y())));
                    display.setTransformation(t);
                });
            }
            case "InventorySZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    if (player.isSneaking()) {
                        t.getScale().add(0, 0, (float) (-0.1f * Utilities.getToolPrecision(player)));
                    } else {
                        t.getScale().add(0, 0, (float) (0.1f * Utilities.getToolPrecision(player)));
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("scale_z").formatted(df.format(t.getScale().z())));
                    display.setTransformation(t);
                });
            }
            case "InventoryLRX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    boolean b = Utilities.getData(display, "GUILRNormalize");
                    if (player.isSneaking()) {
                        t.getLeftRotation().add((float) (-0.1f * Utilities.getToolPrecision(player)), 0, 0, 0);
                    } else {
                        t.getLeftRotation().add((float) (0.1f * Utilities.getToolPrecision(player)), 0, 0, 0);
                    }
                    if (b) {
                        t.getLeftRotation().normalize();
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("left_rot_x").formatted((b ? DisplayEntityEditor.messageManager.getString("normalized") : ""), df.format(t.getLeftRotation().x())));
                    display.setTransformation(t);
                });
            }
            case "InventoryLRY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    boolean b = Utilities.getData(display, "GUILRNormalize");
                    if (player.isSneaking()) {
                        t.getLeftRotation().add(0, (float) (-0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    } else {
                        t.getLeftRotation().add(0, (float) (0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    }
                    if (b) {
                        t.getLeftRotation().normalize();
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("left_rot_y").formatted((b ? DisplayEntityEditor.messageManager.getString("normalized") : ""), df.format(t.getLeftRotation().y())));
                    display.setTransformation(t);
                });
            }
            case "InventoryLRZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    boolean b = Utilities.getData(display, "GUILRNormalize");
                    if (player.isSneaking()) {
                        t.getLeftRotation().add(0, 0, (float) (-0.1f * Utilities.getToolPrecision(player)), 0);
                    } else {
                        t.getLeftRotation().add(0, 0, (float) (0.1f * Utilities.getToolPrecision(player)), 0);
                    }
                    if (b) {
                        t.getLeftRotation().normalize();
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("left_rot_z").formatted((b ? DisplayEntityEditor.messageManager.getString("normalized") : ""), df.format(t.getLeftRotation().z())));
                    display.setTransformation(t);
                });
            }
            case "InventoryRRX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    boolean b = Utilities.getData(display, "GUIRRNormalize");
                    if (player.isSneaking()) {
                        t.getRightRotation().add((float) (-0.1f * Utilities.getToolPrecision(player)), 0, 0, 0);
                    } else {
                        t.getRightRotation().add((float) (0.1f * Utilities.getToolPrecision(player)), 0, 0, 0);
                    }
                    if (b) {
                        t.getRightRotation().normalize();
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("right_rot_x").formatted((b ? DisplayEntityEditor.messageManager.getString("normalized") : ""), df.format(t.getRightRotation().x())));
                    display.setTransformation(t);
                });
            }
            case "InventoryRRY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    boolean b = Utilities.getData(display, "GUIRRNormalize");
                    if (player.isSneaking()) {
                        t.getRightRotation().add(0, (float) (-0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    } else {
                        t.getRightRotation().add(0, (float) (0.1f * Utilities.getToolPrecision(player)), 0, 0);
                    }
                    if (b) {
                        t.getRightRotation().normalize();
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("right_rot_y").formatted((b ? DisplayEntityEditor.messageManager.getString("normalized") : ""), df.format(t.getRightRotation().y())));
                    display.setTransformation(t);
                });
            }
            case "InventoryRRZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    boolean b = Utilities.getData(display, "GUIRRNormalize");
                    if (player.isSneaking()) {
                        t.getRightRotation().add(0, 0, (float) (-0.1f * Utilities.getToolPrecision(player)), 0);
                    } else {
                        t.getRightRotation().add(0, 0, (float) (0.1f * Utilities.getToolPrecision(player)), 0);
                    }
                    if (b) {
                        t.getRightRotation().normalize();
                    }
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("right_rot_z").formatted((b ? DisplayEntityEditor.messageManager.getString("normalized") : ""), df.format(t.getRightRotation().z())));
                    display.setTransformation(t);
                });
            }
            case "InventoryCenterBlock" -> {
                displays.forEach(display -> {
                    if (display instanceof BlockDisplay) {
                        Transformation t = display.getTransformation();
                        t.getTranslation().set(-1 * (t.getScale().x() / 2), -1 * (t.getScale().y() / 2), -1 * (t.getScale().z() / 2));
                        display.setTransformation(t);
                    }

                    Location loc = display.getLocation();
                    loc.setX((int) loc.getX() + (((loc.getX()) < 0 ? -1 : 1) * 0.5));
                    loc.setZ((int) loc.getZ() + (((loc.getZ()) < 0 ? -1 : 1) * 0.5));
                    if (!player.isSneaking()) {
                        loc.setY((int) loc.getY() + (((loc.getY()) < 0 ? -1 : 1) * 0.5));
                    }
                    display.teleport(loc);
                    sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("center_block").formatted(loc.getX(), loc.getY(), loc.getZ()));
                });

            }
            case "InventoryClone" -> {
                displays.forEach(display -> {
                    Display clone = (Display) display.getWorld().spawnEntity(display.getLocation(), display.getType(), false);
                    cloneEntity(clone, display);
                });
                sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("clone"));
            }
            case "InventoryGroupSelect" -> {
                if (!player.isSneaking()) {
                    Collection<Display> group = new HashSet<>();
                    double distance = Utilities.getToolPrecision(player);
                    for (Entity e : player.getNearbyEntities(distance,distance,distance)) {
                        if (e instanceof Display d) {
                            if (!d.getScoreboardTags().contains("dee:locked")) {
                                group.add(d);
                                highlightEntity(d);
                            }
                        }
                    }
                    if (!group.isEmpty()) {
                        editingHandler.setEditingDisplays(player, group);
                        player.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("group_select_success").formatted(group.size())));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("group_select_fail")));
                    }
                } else {
                    editingHandler.removeEditingDisplays(player);
                    player.sendMessage(Utilities.getInfoMessageFormat(DisplayEntityEditor.messageManager.getString("group_select_clear")));
                }
            }
        }
    }

}
