package de.ngloader.ncpsystem.npc.entity;

import org.bukkit.Location;

import com.comphenix.protocol.wrappers.Vector3F;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.npc.NPCSpawnEntityLiving;
import de.ngloader.ncpsystem.npc.feature.NPCEquipment;
import de.ngloader.ncpsystem.wrapper.EntityFlag;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;

public class NPCArmorStand extends NPCSpawnEntityLiving {

	private static final Serializer VECTOR3F_SERIALIZER = WrappedDataWatcher.Registry.getVectorSerializer();

	private final NPCEquipment equipment = new NPCEquipment(this, this::sendPacket);

	public NPCArmorStand(NPCRegistry registry, Location location) {
		super(registry, 0.97, location, WrappedEntityId.ARMOR_STAND);
	}

	public void setSmall(boolean small) {
		this.setFlag(EntityFlag.ARMORSTAND_IS_SMALL, small);
	}

	public void setHasArms(boolean arms) {
		this.setFlag(EntityFlag.ARMORSTAND_HAS_ARMS, arms);
	}

	public void setHasNoBasePlate(boolean basePlate) {
		this.setFlag(EntityFlag.ARMORSTAND_HAS_NO_BASEPLATE, basePlate);
	}

	public void setIsMarker(boolean marker) {
		this.setFlag(EntityFlag.ARMORSTAND_IS_MARKER, marker);
	}

	public void setHeadRotation(Vector3F vector) {
		this.setMetadata(15, VECTOR3F_SERIALIZER, vector);
	}

	public void seBodyRotation(Vector3F vector) {
		this.setMetadata(16, VECTOR3F_SERIALIZER, vector);
	}

	public void setLeftArmRotation(Vector3F vector) {
		this.setMetadata(17, VECTOR3F_SERIALIZER, vector);
	}

	public void setRightArmRotation(Vector3F vector) {
		this.setMetadata(18, VECTOR3F_SERIALIZER, vector);
	}

	public void setLeftLegRotation(Vector3F vector) {
		this.setMetadata(19, VECTOR3F_SERIALIZER, vector);
	}

	public void setRightLegRotation(Vector3F vector) {
		this.setMetadata(20, VECTOR3F_SERIALIZER, vector);
	}

	public NPCEquipment getEquipment() {
		return this.equipment;
	}
}