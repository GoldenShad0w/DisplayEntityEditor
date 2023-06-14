package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
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

import javax.annotation.Nullable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Interact implements Listener {

    private static final DecimalFormat df = new DecimalFormat("#.####");

    public Interact() {
        df.setRoundingMode(RoundingMode.CEILING);
    }

    /**
     * Used to listener for when a player uses a tool
     * @param event The event
     */
    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            if (Utilities.hasDataKey(player.getInventory().getItemInMainHand())) {
                event.setCancelled(true);
                if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    cycleInventory(player);
                    event.setCancelled(true);
                    return;
                }
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    event.setCancelled(true);
                    String toolValue = Utilities.getToolValue(item);
                    if (toolValue != null) {
                        if (toolValue.equals("InventorySpawnItem")) {
                            spawnDisplayEntity(player.getLocation(), EntityType.ITEM_DISPLAY);
                            player.sendMessage(Utilities.getInfoMessageFormat("Spawned new item display entity!"));
                            return;
                        }
                        if (toolValue.equals("InventorySpawnBlock")) {
                            spawnDisplayEntity(player.getLocation(), EntityType.BLOCK_DISPLAY);
                            player.sendMessage(Utilities.getInfoMessageFormat("Spawned new block display entity!"));
                            return;
                        }
                        if (toolValue.equals("InventorySpawnText")) {
                            spawnDisplayEntity(player.getLocation(), EntityType.TEXT_DISPLAY);
                            player.sendMessage(Utilities.getInfoMessageFormat("Spawned new text display entity!"));
                            return;
                        }
                        if (toolValue.equals("InventoryUnlock")) {
                            Display display = getNearestDisplayEntity(player.getLocation(), false);
                            if (display == null) {
                                player.sendMessage(Utilities.getErrorMessageFormat("There is no locked display entity within 5 blocks!"));
                                return;
                            }
                            display.getScoreboardTags().remove("dee:locked");
                            highlightEntity(display);
                            player.sendMessage(Utilities.getInfoMessageFormat("Display entity unlocked!"));
                            return;
                        }
                        if (toolValue.equals("InventoryToolPrecision")) {
                            double d = getToolPrecision(player);
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
                            sendActionbarMessage(player, "Tool Precision: " + df.format(d));
                            return;
                        }

                        Display display = getNearestDisplayEntity(player.getLocation(), true);
                        if (display == null) {
                            player.sendMessage(Utilities.getErrorMessageFormat("There is no unlocked display entity within 5 blocks!"));
                            return;
                        }
                        switch (toolValue) {
                            case "InventoryGUI" -> {

                                if (DisplayEntityEditor.currentEditMap.containsValue(display)) {
                                    player.sendMessage(Utilities.getErrorMessageFormat("Someone else is editing this entity at the moment!"));
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
                                    display.setRotation((float) (display.getLocation().getYaw()-1 * getToolPrecision(player)), display.getLocation().getPitch());
                                    sendActionbarMessage(player, "Yaw: " + df.format(display.getLocation().getYaw()));
                                    return;
                                }
                                display.setRotation((float) (display.getLocation().getYaw()+1  * getToolPrecision(player)), display.getLocation().getPitch());
                                sendActionbarMessage(player, "Yaw: " + df.format(display.getLocation().getYaw()));
                            }
                            case "InventoryRotatePitch" -> {
                                if (player.isSneaking()) {
                                    display.setRotation(display.getLocation().getYaw(), (float) (display.getLocation().getPitch()-1  * getToolPrecision(player)));
                                    sendActionbarMessage(player, "Pitch: " + df.format(display.getLocation().getPitch()));
                                    return;
                                }
                                display.setRotation(display.getLocation().getYaw(), (float) (display.getLocation().getPitch()+1 * getToolPrecision(player)));
                                sendActionbarMessage(player, "Pitch: " + df.format(display.getLocation().getPitch()));
                            }
                            case "InventoryMoveX" -> {
                                if (player.isSneaking()) {
                                    display.teleport(display.getLocation().add(-0.1 * getToolPrecision(player),0,0));
                                    sendActionbarMessage(player, "X: " + df.format(display.getLocation().getX()));
                                    return;
                                }
                                display.teleport(display.getLocation().add(0.1 * getToolPrecision(player),0,0));
                                sendActionbarMessage(player, "X: " + df.format(display.getLocation().getX()));
                            }
                            case "InventoryMoveY" -> {
                                if (player.isSneaking()) {
                                    display.teleport(display.getLocation().add(0,-0.1 * getToolPrecision(player),0));
                                    sendActionbarMessage(player, "Y: " + df.format(display.getLocation().getY()));
                                    return;
                                }
                                display.teleport(display.getLocation().add(0,0.1 * getToolPrecision(player),0));
                                sendActionbarMessage(player, "Y: " + df.format(display.getLocation().getY()));
                            }
                            case "InventoryMoveZ" -> {
                                if (player.isSneaking()) {
                                    display.teleport(display.getLocation().add(0,0,-0.1 * getToolPrecision(player)));
                                    sendActionbarMessage(player, "Z: " + df.format(display.getLocation().getZ()));
                                    return;
                                }
                                display.teleport(display.getLocation().add(0,0,0.1 * getToolPrecision(player)));
                                sendActionbarMessage(player, "Z: " + df.format(display.getLocation().getZ()));
                            }
                            case "InventoryHighlight" -> highlightEntity(display);
                            case "InventoryCenterPivot" -> {
                                Transformation t = display.getTransformation();
                                t.getTranslation().set(-1*(t.getScale().x()/2), -1*(t.getScale().y()/2), -1*(t.getScale().z()/2));
                                display.setTransformation(t);
                                player.sendMessage(ChatColor.DARK_AQUA + "[DEE] " + ChatColor.AQUA + "Centered pivot!");
                            }
                            case "InventoryTX" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getTranslation().add((float) (-0.1f * getToolPrecision(player)),0,0);
                                } else {
                                    t.getTranslation().add((float) (0.1f * getToolPrecision(player)),0,0);
                                }
                                sendActionbarMessage(player, "Translation X: " + df.format(t.getTranslation().x()));
                                display.setTransformation(t);
                            }
                            case "InventoryTY" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getTranslation().add(0,(float) (-0.1f * getToolPrecision(player)),0);
                                } else {
                                    t.getTranslation().add(0,(float) (0.1f * getToolPrecision(player)),0);
                                }
                                sendActionbarMessage(player, "Translation Y: " + df.format(t.getTranslation().y()));
                                display.setTransformation(t);
                            }
                            case "InventoryTZ" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getTranslation().add(0,0,(float) (-0.1f * getToolPrecision(player)));
                                } else {
                                    t.getTranslation().add(0,0,(float) (0.1f * getToolPrecision(player)));
                                }
                                sendActionbarMessage(player, "Translation Z: " + df.format(t.getTranslation().z()));
                                display.setTransformation(t);
                            }
                            case "InventorySX" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getScale().add((float) (-0.1f * getToolPrecision(player)),0,0);
                                } else {
                                    t.getScale().add((float) (0.1f * getToolPrecision(player)),0,0);
                                }
                                sendActionbarMessage(player, "Scale X: " + df.format(t.getScale().x()));
                                display.setTransformation(t);
                            }
                            case "InventorySY" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getScale().add(0,(float) (-0.1f * getToolPrecision(player)),0);
                                } else {
                                    t.getScale().add(0,(float) (0.1f * getToolPrecision(player)),0);
                                }
                                sendActionbarMessage(player, "Scale Y: " + df.format(t.getScale().y()));
                                display.setTransformation(t);
                            }
                            case "InventorySZ" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getScale().add(0,0,(float) (-0.1f * getToolPrecision(player)));
                                } else {
                                    t.getScale().add(0,0,(float) (0.1f * getToolPrecision(player)));
                                }
                                sendActionbarMessage(player, "Scale Z: " + df.format(t.getScale().z()));
                                display.setTransformation(t);
                            }
                            case "InventoryLRX" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUILRNormalize");
                                if (player.isSneaking()) {
                                    t.getLeftRotation().add((float) (-0.1f * getToolPrecision(player)),0,0,0);
                                } else {
                                    t.getLeftRotation().add((float) (0.1f * getToolPrecision(player)),0,0,0);
                                }
                                if (b) t.getLeftRotation().normalize();
                                sendActionbarMessage(player, "Left Rotation X" + (b ? " (normalized)" : "") + ": " + df.format(t.getLeftRotation().x()));
                                display.setTransformation(t);
                            }
                            case "InventoryLRY" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUILRNormalize");
                                if (player.isSneaking()) {
                                    t.getLeftRotation().add(0,(float) (-0.1f * getToolPrecision(player)),0,0);
                                } else {
                                    t.getLeftRotation().add(0,(float) (0.1f * getToolPrecision(player)),0,0);
                                }
                                if (b) t.getLeftRotation().normalize();
                                sendActionbarMessage(player, "Left Rotation Y" + (b ? " (normalized)" : "") + ": " + df.format(t.getLeftRotation().y()));
                                display.setTransformation(t);
                            }
                            case "InventoryLRZ" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUILRNormalize");
                                if (player.isSneaking()) {
                                    t.getLeftRotation().add(0,0,(float) (-0.1f * getToolPrecision(player)),0);
                                } else {
                                    t.getLeftRotation().add(0,0,(float) (0.1f * getToolPrecision(player)),0);
                                }
                                if (b) t.getLeftRotation().normalize();
                                sendActionbarMessage(player, "Left Rotation Z" + (b ? " (normalized)" : "") + ": " + df.format(t.getLeftRotation().z()));
                                display.setTransformation(t);
                            }
                            case "InventoryRRX" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getRightRotation().add((float) (-0.1f * getToolPrecision(player)),0,0,0);
                                } else {
                                    t.getRightRotation().add((float) (0.1f * getToolPrecision(player)),0,0,0);
                                }
                                if (b) t.getRightRotation().normalize();
                                sendActionbarMessage(player, "Right Rotation X" + (b ? " (normalized)" : "") + ": " + df.format(t.getRightRotation().x()));
                                display.setTransformation(t);
                            }
                            case "InventoryRRY" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getRightRotation().add(0,(float) (-0.1f * getToolPrecision(player)),0,0);
                                } else {
                                    t.getRightRotation().add(0,(float) (0.1f * getToolPrecision(player)),0,0);
                                }
                                if (b) t.getRightRotation().normalize();
                                sendActionbarMessage(player, "Right Rotation Y" + (b ? " (normalized)" : "") + ": " + df.format(t.getRightRotation().y()));
                                display.setTransformation(t);
                            }
                            case "InventoryRRZ" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getRightRotation().add(0,0,(float) (-0.1f * getToolPrecision(player)),0);
                                } else {
                                    t.getRightRotation().add(0,0,(float) (0.1f * getToolPrecision(player)),0);
                                }
                                if (b) t.getRightRotation().normalize();
                                sendActionbarMessage(player, "Right Rotation Z" + (b ? " (normalized)" : "") + ": " + df.format(t.getRightRotation().z()));
                                display.setTransformation(t);
                            }
                            case "InventoryCenterBlock" -> {

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
                                sendActionbarMessage(player, "Centered at: " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
                            }
                        }
                    }
                }
            }
        }
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
     * Used to get the nearest display entity
     * @param location The location from where the nearest display entity should be gotten
     * @param lockSearchToggle If this method should look for locked or unlocked entities. If true, it will only look for unlocked entities, and if false it will only look for locked ones
     * @return The nearest display entity or null if none were found
     */
    @Nullable
    public static Display getNearestDisplayEntity(Location location, boolean lockSearchToggle) {
        Display entity = null;
        double distance = 5;
        assert location.getWorld() != null;
        for (Entity e : location.getWorld().getNearbyEntities(location, 5,5,5)) {
            if (e instanceof Display d) {
                if (lockSearchToggle) {
                    if (!d.getScoreboardTags().contains("dee:locked")) {
                        double dis = d.getLocation().distance(location);
                        if (dis < distance) {
                            entity = d;
                            distance = dis;
                        }
                    }
                } else {
                    if (d.getScoreboardTags().contains("dee:locked")) {
                        double dis = d.getLocation().distance(location);
                        if (dis < distance) {
                            entity = d;
                            distance = dis;
                        }
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Used to spawn a new display entity
     * @param location The location of where it should be spawned
     * @param type The specific type of display entity
     */
    private static void spawnDisplayEntity(Location location, EntityType type) {
        assert location.getWorld() != null;
        Display d = (Display) location.getWorld().spawnEntity(location, type, false);
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
        display.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, display.getLocation(), 50,0.2,0.2,0.2,0);
    }

    private static void sendActionbarMessage(Player p, String message) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(net.md_5.bungee.api.ChatColor.DARK_AQUA + message));
    }

    private static double getToolPrecision(Player p) {
        Double i = p.getPersistentDataContainer().get(DisplayEntityEditor.toolPrecisionKey,  PersistentDataType.DOUBLE);
        return i != null ? i : 1;
    }
}
