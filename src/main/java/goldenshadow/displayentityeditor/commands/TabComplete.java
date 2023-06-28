package goldenshadow.displayentityeditor.commands;

import goldenshadow.displayentityeditor.DisplayEntityEditor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {



    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            if (DisplayEntityEditor.alternateTextInput) {
                arguments = new ArrayList<>(List.of("reload", "edit"));
            } else {
                arguments = new ArrayList<>(List.of("reload"));
            }

            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2) {
            if (DisplayEntityEditor.alternateTextInput) {
                if (args[0].equalsIgnoreCase("edit")) {
                    arguments = new ArrayList<>(Arrays.asList("name", "view_range", "display_height", "display_width", "shadow_radius", "shadow_strength", "text_opacity", "line_width", "background_opacity", "background_color", "text", "text_append", "glow_color", "block_state"));
                }
            }
            else arguments.clear();
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }


        if (args.length > 2) {
            arguments.clear();
            return arguments;
        }


        return null;
    }
}
