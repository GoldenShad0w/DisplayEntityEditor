package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.inventories.InventoryFactory;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;

import javax.annotation.Nullable;

public class Interact implements Listener {

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

                        Display display = getNearestDisplayEntity(player.getLocation(), true);
                        if (display == null) {
                            player.sendMessage(Utilities.getErrorMessageFormat("There is no unlocked display entity within 5 blocks!"));
                            return;
                        }
                        switch (toolValue) {
                            case "InventoryGUI" -> {

                                if (InventoryClick.currentEditMap.containsValue(display)) {
                                    player.sendMessage(Utilities.getErrorMessageFormat("Someone else is editing this entity at the moment!"));
                                    return;
                                }
                                InventoryClick.currentEditMap.put(player.getUniqueId(), display);

                                if (display instanceof ItemDisplay) {
                                    player.openInventory(InventoryFactory.createItemDisplayGUI((ItemDisplay) display));
                                } else if (display instanceof BlockDisplay) {
                                    player.openInventory(InventoryFactory.createBlockDisplayGUI((BlockDisplay) display));
                                } else {
                                    player.openInventory(InventoryFactory.createTextDisplayGUI((TextDisplay) display));
                                }
                            }
                            case "InventoryRotateYaw" -> {
                                if (player.isSneaking()) {
                                    display.setRotation(display.getLocation().getYaw()-1, display.getLocation().getPitch());
                                    return;
                                }
                                display.setRotation(display.getLocation().getYaw()+1, display.getLocation().getPitch());
                            }
                            case "InventoryRotatePitch" -> {
                                if (player.isSneaking()) {
                                    display.setRotation(display.getLocation().getYaw(), display.getLocation().getPitch()-1);
                                    return;
                                }
                                display.setRotation(display.getLocation().getYaw(), display.getLocation().getPitch()+1);
                            }
                            case "InventoryMoveX" -> {
                                if (player.isSneaking()) {
                                    display.teleport(display.getLocation().add(-0.1,0,0));
                                    return;
                                }
                                display.teleport(display.getLocation().add(0.1,0,0));
                            }
                            case "InventoryMoveY" -> {
                                if (player.isSneaking()) {
                                    display.teleport(display.getLocation().add(0,-0.1,0));
                                    return;
                                }
                                display.teleport(display.getLocation().add(0,0.1,0));
                            }
                            case "InventoryMoveZ" -> {
                                if (player.isSneaking()) {
                                    display.teleport(display.getLocation().add(0,0,-0.1));
                                    return;
                                }
                                display.teleport(display.getLocation().add(0,0,0.1));
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
                                    t.getTranslation().add(-0.1f,0,0);
                                } else {
                                    t.getTranslation().add(0.1f,0,0);
                                }
                                display.setTransformation(t);
                            }
                            case "InventoryTY" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getTranslation().add(0,-0.1f,0);
                                } else {
                                    t.getTranslation().add(0,0.1f,0);
                                }
                                display.setTransformation(t);
                            }
                            case "InventoryTZ" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getTranslation().add(0,0,-0.1f);
                                } else {
                                    t.getTranslation().add(0,0,0.1f);
                                }
                                display.setTransformation(t);
                            }
                            case "InventorySX" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getScale().add(-0.1f,0,0);
                                } else {
                                    t.getScale().add(0.1f,0,0);
                                }
                                display.setTransformation(t);
                            }
                            case "InventorySY" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getScale().add(0,-0.1f,0);
                                } else {
                                    t.getScale().add(0,0.1f,0);
                                }
                                display.setTransformation(t);
                            }
                            case "InventorySZ" -> {
                                Transformation t = display.getTransformation();
                                if (player.isSneaking()) {
                                    t.getScale().add(0,0,-0.1f);
                                } else {
                                    t.getScale().add(0,0,0.1f);
                                }
                                display.setTransformation(t);
                            }
                            case "InventoryLRX" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getLeftRotation().add(-0.1f,0,0,0);
                                } else {
                                    t.getLeftRotation().add(0.1f,0,0,0);
                                }
                                if (b) t.getLeftRotation().normalize();
                                display.setTransformation(t);
                            }
                            case "InventoryLRY" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getLeftRotation().add(0,-0.1f,0,0);
                                } else {
                                    t.getLeftRotation().add(0,0.1f,0,0);
                                }
                                if (b) t.getLeftRotation().normalize();
                                display.setTransformation(t);
                            }
                            case "InventoryLRZ" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getLeftRotation().add(0,0,-0.1f,0);
                                } else {
                                    t.getLeftRotation().add(0,0,0.1f,0);
                                }
                                if (b) t.getLeftRotation().normalize();
                                display.setTransformation(t);
                            }
                            case "InventoryRRX" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getRightRotation().add(-0.1f,0,0,0);
                                } else {
                                    t.getRightRotation().add(0.1f,0,0,0);
                                }
                                if (b) t.getRightRotation().normalize();
                                display.setTransformation(t);
                            }
                            case "InventoryRRY" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getRightRotation().add(0,-0.1f,0,0);
                                } else {
                                    t.getRightRotation().add(0,0.1f,0,0);
                                }
                                if (b) t.getRightRotation().normalize();
                                display.setTransformation(t);
                            }
                            case "InventoryRRZ" -> {
                                Transformation t = display.getTransformation();
                                boolean b = Utilities.getData(display, "GUIRRNormalize");
                                if (player.isSneaking()) {
                                    t.getRightRotation().add(0,0,-0.1f,0);
                                } else {
                                    t.getRightRotation().add(0,0,0.1f,0);
                                }
                                if (b) t.getRightRotation().normalize();
                                display.setTransformation(t);
                            }
                        }
                    }
                }
            }
        }
    }


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

    private static void highlightEntity(Display display) {
        display.setGlowing(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(DisplayEntityEditor.getPlugin(), () -> display.setGlowing(false), 20L);
        display.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, display.getLocation(), 50,0.2,0.2,0.2,0);
    }
}
