package goldenshadow.displayentityeditor.events;

import goldenshadow.displayentityeditor.Utilities;
import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public class PlayerChat implements Listener {

    public static HashMap<UUID, InputData> inputDataMap = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        if (inputDataMap.containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            InputData inputData = inputDataMap.get(event.getPlayer().getUniqueId());
            Player player = event.getPlayer();
            switch (inputData.inputType()) {
                case NAME -> {
                    inputData.entity().setCustomNameVisible(true);
                    inputData.entity().setCustomName(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
                    player.sendMessage(Utilities.getInfoMessageFormat("Set name!"));
                }
                case TEXT -> {
                    ((TextDisplay) inputData.entity()).setText(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
                    player.sendMessage(Utilities.getInfoMessageFormat("Set text!"));
                }
                case LINE_WIDTH -> {
                    if (isInt(event.getMessage())) {
                        ((TextDisplay) inputData.entity()).setLineWidth(Integer.parseInt(event.getMessage()));
                        player.sendMessage(Utilities.getInfoMessageFormat("Line width set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be an integer!"));
                    }
                }
                case VIEW_RANGE -> {
                    if (isFloat(event.getMessage())) {
                        inputData.entity().setViewRange(Float.parseFloat(event.getMessage()));
                        player.sendMessage(Utilities.getInfoMessageFormat("Line width set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be a decimal number!"));
                    }
                }
                case TEXT_OPACITY -> {
                    if (isByte(event.getMessage())) {
                        byte b = (byte) Integer.parseInt(event.getMessage());
                        ((TextDisplay) inputData.entity()).setTextOpacity(b);
                        player.sendMessage(Utilities.getInfoMessageFormat("Text opacity!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be an integer between 0 and 255!"));
                    }
                }
                case DISPLAY_WIDTH -> {
                    if (isFloat(event.getMessage())) {
                        inputData.entity().setDisplayWidth(Float.parseFloat(event.getMessage()));
                        player.sendMessage(Utilities.getInfoMessageFormat("Display width set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be a decimal number!"));
                    }
                }
                case DISPLAY_HEIGHT -> {
                    if (isFloat(event.getMessage())) {
                        inputData.entity().setDisplayHeight(Float.parseFloat(event.getMessage()));
                        player.sendMessage(Utilities.getInfoMessageFormat("Display height set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be a decimal number!"));
                    }
                }
                case SHADOW_RADIUS -> {
                    if (isFloat(event.getMessage())) {
                        inputData.entity().setShadowRadius(Float.parseFloat(event.getMessage()));
                        player.sendMessage(Utilities.getInfoMessageFormat("Shadow radius set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be a decimal number!"));
                    }
                }
                case SHADOW_STRENGTH -> {
                    if (isFloat(event.getMessage())) {
                        float f = Float.parseFloat(event.getMessage());
                        if (0 <= f && f <= 1) {
                            inputData.entity().setShadowStrength(f);
                            player.sendMessage(Utilities.getInfoMessageFormat("Shadow strength set!"));
                        } else {
                            player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be between 0 and 1!"));
                        }
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be a decimal number!"));
                    }
                }
                case BACKGROUND_OPACITY -> {
                    if (isByte(event.getMessage())) {
                        TextDisplay t = (TextDisplay) inputData.entity();
                        if (t.getBackgroundColor() != null) {
                            t.getBackgroundColor().setAlpha(Integer.parseInt(event.getMessage()));
                        } else {
                            t.setBackgroundColor(Color.fromARGB(Integer.parseInt(event.getMessage()),0,0,0));
                        }
                        player.sendMessage(Utilities.getInfoMessageFormat("Background opacity set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs to be an integer between 0 and 255!"));
                    }
                }
                case BACKGROUND_COLOR -> {
                    int[] array = parseStringToRGB(event.getMessage());
                    if (array != null) {
                        TextDisplay t = (TextDisplay) inputData.entity();
                        if (t.getBackgroundColor() != null) {
                            t.getBackgroundColor().setRed(array[0]);
                            t.getBackgroundColor().setGreen(array[1]);
                            t.getBackgroundColor().setBlue(array[2]);
                        } else {
                            t.setBackgroundColor(Color.fromARGB(0,array[0],array[1],array[2]));
                        }
                        player.sendMessage(Utilities.getInfoMessageFormat("Background color set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs follow the format: R, G, B"));
                    }
                }
                case GLOW_COLOR -> {
                    int[] array = parseStringToRGB(event.getMessage());
                    if (array != null) {
                        inputData.entity().setGlowColorOverride(Color.fromRGB(array[0], array[1], array[2]));
                        player.sendMessage(Utilities.getInfoMessageFormat("Glow color set!"));
                    } else {
                        player.sendMessage(Utilities.getErrorMessageFormat("The value needs follow the format: R, G, B"));
                    }
                }
                case BLOCK_STATE -> {
                    if (inputData.blockMaterial() != null) {
                        try {
                            BlockData blockData = Bukkit.createBlockData(inputData.blockMaterial(), event.getMessage());
                            ((BlockDisplay) inputData.entity()).setBlock(blockData);
                            player.sendMessage(Utilities.getInfoMessageFormat("Block state set!"));
                        } catch (IllegalArgumentException e) {
                            player.sendMessage(Utilities.getErrorMessageFormat("The value given was not a valid block state! Try looking at a block with the block state you want with f3 on to see its block states or use an online tool!"));
                        }
                    }
                }
            }
            inputDataMap.remove(player.getUniqueId());
        }
    }



    public record InputData(Display entity, InputType inputType, @Nullable Material blockMaterial, long decayTime) {}

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isByte(String s) {
        if (isInt(s)) {
            int i = Integer.parseInt(s);
            return 0 <= i && i <= 255;
        }
        return false;
    }

    public static int[] parseStringToRGB(String input) {
        String[] parts = input.split(",");
        if (parts.length != 3) {
            return null;
        }

        try {
            int[] values = new int[3];
            for (int i = 0; i < 3; i++) {
                values[i] = Integer.parseInt(parts[i].trim());
            }
            return values;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
