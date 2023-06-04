package goldenshadow.displayentityeditor.enums;

import org.bukkit.Color;


public enum PresetColor {

    WHITE (Color.WHITE),
    SILVER (Color.SILVER),
    GRAY (Color.GRAY),
    BLACK (Color.BLACK),
    RED (Color.RED),
    MAROON (Color.MAROON),
    YELLOW (Color.YELLOW),
    OLIVE (Color.OLIVE),
    LIME (Color.LIME),
    GREEN (Color.GREEN),
    AQUA (Color.AQUA),
    TEAL (Color.TEAL),
    BLUE (Color.BLUE),
    NAVY (Color.NAVY),
    FUCHSIA (Color.FUCHSIA),
    PURPLE (Color.PURPLE),
    ORANGE (Color.ORANGE);

    private final Color color;

    PresetColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

