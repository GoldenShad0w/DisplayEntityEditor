package goldenshadow.displayentityeditor.conversation;

import goldenshadow.displayentityeditor.enums.InputType;
import org.bukkit.Material;
import org.bukkit.entity.Display;

import javax.annotation.Nullable;

/**
 * A record class used to store data about an awaited text input
 * @param entity The entity being edited
 * @param inputType What is being edited
 * @param blockMaterial The block material if the block state is being edited, otherwise null
 */
public record InputData(Display entity, InputType inputType, @Nullable Material blockMaterial) {}
