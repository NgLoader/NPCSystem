package de.ngloader.npcsystem.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCEntityLiving;

public class NPCSpawnEntity extends NPCEntityLiving {

	private final EntityType type;

	public NPCSpawnEntity(NPCRegistry registry, double eyeHeight, Location location, EntityType type) {
		super(registry, eyeHeight, location);
		this.type = type;
	}

	@Override
	protected void createSpawnPackets() {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
		packet.getIntegers().write(0, this.entityId);
		packet.getUUIDs().write(0, this.uuid);
		packet.getEntityTypeModifier().write(0, this.type);
		packet.getDoubles().write(0, this.location.getX());
		packet.getDoubles().write(1, this.location.getY());
		packet.getDoubles().write(2, this.location.getZ());
		packet.getIntegers().write(1, 0);
		packet.getIntegers().write(2, 0);
		packet.getIntegers().write(3, 0);
		packet.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (this.location.getPitch() * 256F / 360F)));
		packet.getBytes().write(2, (byte) 0);
		packet.getIntegers().write(4, 0);
		this.spawnPackets.add(packet);

		super.createSpawnPackets();
	}
}