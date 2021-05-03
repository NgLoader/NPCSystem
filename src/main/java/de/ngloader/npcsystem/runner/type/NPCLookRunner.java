package de.ngloader.npcsystem.runner.type;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.NPC;
import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCEntityLiving;
import de.ngloader.npcsystem.property.NPCPropertyValue;
import de.ngloader.npcsystem.runner.NPCRunner;

public class NPCLookRunner extends NPCRunner<NPCEntityLiving> {

	private final ProtocolManager protocolManager;

	public NPCLookRunner(NPCRegistry registry) {
		super(registry);

		this.protocolManager = ProtocolLibrary.getProtocolManager();
	}

	@Override
	public void run() {
		for (NPCEntityLiving npc : this.npcs) {
			Location npcLocation = npc.getLocation();
			Location look = null;

			double range = npc.getProperties().getDouble(NPCPropertyValue.NPC_LOOK_RANGE.getKey());
			boolean global = npc.getProperties().getBoolean(NPCPropertyValue.NPC_LOOK_GLOBAL.getKey());

			for (Player inRange : npc.getInRange()) {
				if (range > 0) {
					if (inRange.getLocation().distance(npcLocation) > range) {
						continue;
					}
				}

				if (!global) {
					this.lookAt(inRange, npc, inRange.getEyeLocation());
					continue;
				}

				if (look == null) {
					look = inRange.getEyeLocation();
					continue;
				}
				if (look.distanceSquared(npcLocation) > inRange.getLocation().distanceSquared(npcLocation)) {
					look = inRange.getEyeLocation();
				}
			}

			if (look != null) {
				npc.lookAt(look);
			}
		}
	}

	public void lookAt(Player player, NPCEntityLiving entity, Location location) {
		double xDiff = location.getX() - entity.getLocation().getX();
		double yDiff = location.getY() - entity.getLocation().getY() - entity.getEyeHeight();
		double zDiff = location.getZ() - entity.getLocation().getZ();

		double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

		double yaw = Math.toDegrees(Math.atan2(zDiff, xDiff)) - 90;
		double pitch = -Math.toDegrees(Math.atan2(yDiff, distanceXZ));

		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_LOOK);
		packet.getIntegers().write(0, entity.getEntityId());
		packet.getBytes().write(0, (byte) ((int) (yaw * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (pitch * 256F / 360F)));
		packet.getBooleans().write(0, true);

		PacketContainer headRotation = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
		headRotation.getIntegers().write(0, entity.getEntityId());
		headRotation.getBytes().write(0, (byte) ((int) (yaw * 256F / 360F)));

		try {
			this.protocolManager.sendServerPacket(player, packet);
			this.protocolManager.sendServerPacket(player, headRotation);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addNPC(NPC npc) {
		if (npc instanceof NPCEntityLiving) {
			return this.addNPC((NPCEntityLiving) npc);
		}
		return false;
	}
}