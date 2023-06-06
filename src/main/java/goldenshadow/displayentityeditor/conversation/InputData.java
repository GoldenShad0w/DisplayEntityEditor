package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.Material;
import org.bukkit.entity.Display;

import javax.annotation.Nullable;

public record InputData(Display entity, InputType inputType, @Nullable Material blockMaterial) {}
