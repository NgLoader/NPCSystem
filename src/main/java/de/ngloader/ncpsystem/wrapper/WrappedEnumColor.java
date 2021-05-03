package de.ngloader.ncpsystem.wrapper;

import net.minecraft.server.v1_16_R3.EnumColor;

public enum WrappedEnumColor {

    WHITE(EnumColor.WHITE),
    ORANGE(EnumColor.ORANGE),
    MAGENTA(EnumColor.MAGENTA),
    LIGHT_BLUE(EnumColor.LIGHT_BLUE),
    YELLOW(EnumColor.YELLOW),
    LIME(EnumColor.LIME),
    PINK(EnumColor.PINK),
    GRAY(EnumColor.GRAY),
    LIGHT_GRAY(EnumColor.LIGHT_GRAY),
    CYAN(EnumColor.CYAN),
    PURPLE(EnumColor.PURPLE),
    BLUE(EnumColor.BLUE),
    BROWN(EnumColor.BROWN),
    GREEN(EnumColor.GREEN),
    RED(EnumColor.RED),
    BLACK(EnumColor.BLACK);

    private final byte colorIndex;

    private WrappedEnumColor(EnumColor color) {
        this.colorIndex = (byte) color.getColorIndex();
    }

    public byte getColorIndex() {
        return this.colorIndex;
    }
}
