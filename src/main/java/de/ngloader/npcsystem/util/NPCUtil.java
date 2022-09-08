package de.ngloader.npcsystem.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import de.ngloader.npcsystem.npc.type.NPCEntityLiving;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class NPCUtil {

	private static final ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();

	public static final WrappedChatComponent EMPTY_CHAT_COMPONENT = WrappedChatComponent.fromText("");
	public static final BaseComponent EMPTY_BASE_COMPONENT = new TextComponent();

	public static String createHashCode(Object object) {
		return Integer.toString(System.identityHashCode(object), 16).replaceAll("(.)", "§$1");
	}

	public static BaseComponent mergeBaseComponent(BaseComponent... components) {
		return switch (components.length) {
		case 0 -> EMPTY_BASE_COMPONENT;
		case 1 -> components[0];
		default -> {
			BaseComponent parent = components[0];
			for (int i = 1; i < components.length; i++) {
				parent.addExtra(components[i]);
			}
			yield parent;
		}
		};
	}

	public static WrappedChatComponent mergeBaseComponentWrapped(BaseComponent... components) {
		return WrappedChatComponent.fromJson(ComponentSerializer.toString(mergeBaseComponent(components)));
	}

	public static void lookAt(Player player, NPCEntityLiving entity, Location location) {
		double xDiff = location.getX() - entity.getLocation().getX();
		double yDiff = location.getY() - entity.getLocation().getY() - entity.getEyeHeight();
		double zDiff = location.getZ() - entity.getLocation().getZ();

		double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

		double yaw = Math.toDegrees(Math.atan2(zDiff, xDiff)) - 90;
		double pitch = -Math.toDegrees(Math.atan2(yDiff, distanceXZ));

		PacketContainer packet = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.ENTITY_LOOK);
		packet.getIntegers().write(0, entity.getEntityId());
		packet.getBytes().write(0, (byte) ((int) (yaw * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (pitch * 256F / 360F)));
		packet.getBooleans().write(0, true);

		PacketContainer headRotation = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
		headRotation.getIntegers().write(0, entity.getEntityId());
		headRotation.getBytes().write(0, (byte) ((int) (yaw * 256F / 360F)));

		PROTOCOL_MANAGER.sendServerPacket(player, packet);
		PROTOCOL_MANAGER.sendServerPacket(player, headRotation);
	}
}
