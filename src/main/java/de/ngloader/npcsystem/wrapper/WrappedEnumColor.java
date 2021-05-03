package de.ngloader.npcsystem.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.ngloader.npcsystem.util.MCReflectionUtil;

public enum WrappedEnumColor {

	WHITE,
	ORANGE,
	MAGENTA,
	LIGHT_BLUE,
	YELLOW,
	LIME,
	PINK,
	GRAY,
	LIGHT_GRAY,
	CYAN,
	PURPLE,
	BLUE,
	BROWN,
	GREEN,
	RED,
	BLACK;

	private byte colorIndex = -1;

	private WrappedEnumColor() {
		Class<?> clazz = MCReflectionUtil.getMinecraftServerClass("EnumColor");
		try {
			Method method = clazz.getDeclaredMethod("values");
			Object enums = method.invoke(null);
			for (Enum<?> obj : (Enum[]) enums) {
				if (obj.name().equals(this.name())) {
					Method colorIndex = obj.getClass().getDeclaredMethod("getColorIndex");
					colorIndex.setAccessible(true);
					this.colorIndex = Byte.valueOf(Integer.toString((int) colorIndex.invoke(obj)));
					break;
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public byte getColorIndex() {
		return this.colorIndex;
	}
}
