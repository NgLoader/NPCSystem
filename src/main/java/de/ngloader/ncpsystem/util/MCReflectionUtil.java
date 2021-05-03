package de.ngloader.ncpsystem.util;

import org.bukkit.Bukkit;

public class MCReflectionUtil extends ReflectionUtil {

	private static final String MINECRAFTSERVER_PATH = "net.minecraft.server";
	private static final String CRAFTBUKKIT_PATH = "org.bukkit.craftbukkit";

	private static final String SERVER_VERSION;

	static {
		String version = Bukkit.getServer().getClass().getName().substring(MCReflectionUtil.CRAFTBUKKIT_PATH.length());
		SERVER_VERSION = version.substring(0, version.length() - "CraftServer".length());
	}

	public static String getMinecraftServer(String className) {
		return MCReflectionUtil.MINECRAFTSERVER_PATH + MCReflectionUtil.SERVER_VERSION + className;
	}

	public static Class<?> getMinecraftServerClass(String className) {
		try {
			return Class.forName(MCReflectionUtil.getMinecraftServer(className));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getCraftBukkit(String className) {
		return MCReflectionUtil.CRAFTBUKKIT_PATH + MCReflectionUtil.SERVER_VERSION + className;
	}

	public static Class<?> getCraftBukkitClass(String className) {
		try {
			return Class.forName(MCReflectionUtil.getCraftBukkit(className));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
