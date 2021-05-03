package de.ngloader.npcsystem.npc;

import org.bukkit.Location;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCEntityLiving;
import de.ngloader.npcsystem.wrapper.WrappedEntityId;

public class NPCSpawnEntityLiving extends NPCEntityLiving {

	private final int id;

	public NPCSpawnEntityLiving(NPCRegistry registry, double eyeHeight, Location location, WrappedEntityId id) {
		super(registry, eyeHeight, location);
		this.id = id.getId();
	}

	@Override
	protected void createSpawnPackets() {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
		packet.getIntegers().write(0, this.entityId);
		packet.getUUIDs().write(0, this.uuid);
		packet.getIntegers().write(1, this.id);
		packet.getDoubles().write(0, this.location.getX());
		packet.getDoubles().write(1, this.location.getY());
		packet.getDoubles().write(2, this.location.getZ());
		packet.getIntegers().write(2, 0);
		packet.getIntegers().write(3, 0);
		packet.getIntegers().write(4, 0);
		packet.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (this.location.getPitch() * 256F / 360F)));
		packet.getBytes().write(2, (byte) 0);
		this.spawnPackets.add(packet);

		super.createSpawnPackets();
	}
}