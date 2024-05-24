package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import goldenshadow.displayentityeditor.EditingHandler;
import goldenshadow.displayentityeditor.Utilities;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Collection;

public class OffhandSwap implements Listener {

    private final EditingHandler editingHandler;

    public OffhandSwap(EditingHandler handler) {
        editingHandler = handler;
    }

    @EventHandler
    public void offHand(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (!Utilities.hasDataKey(player.getInventory().getItemInMainHand())) {
            return;
        }
        event.setCancelled(true);

        ItemStack item = player.getInventory().getItemInMainHand();
        String toolValue = Utilities.getToolValue(item);
        Collection<Display> displays = editingHandler.getEditingDisplays(player, true);

        if (displays == null) {
            player.sendMessage(Utilities.getErrorMessageFormat(DisplayEntityEditor.messageManager.getString("generic_fail")));
            return;
        }

        if (toolValue == null) {
            return;
        }
        Utilities.sendActionbarMessage(player, DisplayEntityEditor.messageManager.getString("value_reset"));
        switch (toolValue) {
            case "InventoryRotateYaw" -> {
                displays.forEach(display -> display.setRotation(0, display.getLocation().getPitch()));
            }
            case "InventoryRotatePitch" -> {
                displays.forEach(display -> display.setRotation(display.getLocation().getYaw(), 0));
            }
            case "InventoryTX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(new Vector3f(0, t.getTranslation().y(), t.getTranslation().z()), t.getLeftRotation(), t.getScale(), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventoryTY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(new Vector3f(t.getTranslation().x(), 0, t.getTranslation().z()), t.getLeftRotation(), t.getScale(), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventoryTZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(new Vector3f(t.getTranslation().x(), t.getTranslation().y(), 0), t.getLeftRotation(), t.getScale(), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventorySX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), t.getLeftRotation(), new Vector3f(0, t.getScale().y(), t.getScale().z()), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventorySY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), t.getLeftRotation(), new Vector3f(t.getScale().x(), 0, t.getScale().z()), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventorySZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), t.getLeftRotation(), new Vector3f(t.getScale().x(), t.getScale().y(), 0), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventoryLRX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), new Quaternionf(0, t.getLeftRotation().y(), t.getLeftRotation().z(), t.getLeftRotation().w()), t.getScale(), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventoryLRY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), new Quaternionf(t.getLeftRotation().x(), 0, t.getLeftRotation().z(), t.getLeftRotation().w()), t.getScale(), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventoryLRZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), new Quaternionf(t.getLeftRotation().x(), t.getLeftRotation().y(), 0, t.getLeftRotation().w()), t.getScale(), t.getRightRotation());
                    display.setTransformation(t);
                });
            }
            case "InventoryRRX" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), t.getLeftRotation(), t.getScale(), new Quaternionf(0, t.getRightRotation().y(), t.getRightRotation().z(), t.getRightRotation().w()));
                    display.setTransformation(t);
                });
            }
            case "InventoryRRY" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), t.getLeftRotation(), t.getScale(), new Quaternionf(t.getRightRotation().x(), 0, t.getRightRotation().z(), t.getRightRotation().w()));
                    display.setTransformation(t);
                });
            }
            case "InventoryRRZ" -> {
                displays.forEach(display -> {
                    Transformation t = display.getTransformation();
                    t = new Transformation(t.getTranslation(), t.getLeftRotation(), t.getScale(), new Quaternionf(t.getRightRotation().x(), t.getRightRotation().y(), 0, t.getRightRotation().w()));
                    display.setTransformation(t);
                });
            }
        }
    }
}
