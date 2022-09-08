package de.ngloader.npcsystem.npc.entity;

import org.bukkit.Location;

import com.comphenix.protocol.wrappers.Vector3F;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.NPCSpawnEntity;
import de.ngloader.npcsystem.npc.feature.NPCEquipment;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import de.ngloader.npcsystem.wrapper.EntityIndex;
import net.minecraft.world.entity.EntityType;

public class NPCArmorStand extends NPCSpawnEntity {

	private static final Serializer VECTOR3F_SERIALIZER = WrappedDataWatcher.Registry.getVectorSerializer();

	private final NPCEquipment equipment = new NPCEquipment(this, this::sendPacket);

	public NPCArmorStand(NPCRegistry registry, Location location) {
		super(registry, 0.97, location, EntityType.ARMOR_STAND);
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
		this.setMetadata(EntityIndex.ARMORSTAND_HEAD_ROTATION_16, VECTOR3F_SERIALIZER, vector);
	}

	public void seBodyRotation(Vector3F vector) {
		this.setMetadata(EntityIndex.ARMORSTAND_BODY_ROTATION_17, VECTOR3F_SERIALIZER, vector);
	}

	public void setLeftArmRotation(Vector3F vector) {
		this.setMetadata(EntityIndex.ARMORSTAND_LEFT_ARM_ROTATION_18, VECTOR3F_SERIALIZER, vector);
	}

	public void setRightArmRotation(Vector3F vector) {
		this.setMetadata(EntityIndex.ARMORSTAND_RIGHT_ARM_ROTATION_19, VECTOR3F_SERIALIZER, vector);
	}

	public void setLeftLegRotation(Vector3F vector) {
		this.setMetadata(EntityIndex.ARMORSTAND_LEFT_LEG_ROTATION_20, VECTOR3F_SERIALIZER, vector);
	}

	public void setRightLegRotation(Vector3F vector) {
		this.setMetadata(EntityIndex.ARMORSTAND_RIGHT_LEG_ROTATION_21, VECTOR3F_SERIALIZER, vector);
	}

	public NPCEquipment getEquipment() {
		return this.equipment;
	}
}