package de.ngloader.npcsystem.npc.type;

import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Location;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import de.ngloader.npcsystem.wrapper.EntityIndex;

public class NPCEntityLiving extends NPCEntity {

	protected UUID uuid;
	protected double eyeHeight;

	public NPCEntityLiving(NPCRegistry registry, double eyeHeight, Location location) {
		super(registry);
		this.eyeHeight = eyeHeight;
		this.location = location;

		this.uuid = this.manager.generateUUID();
		this.setHealth(1);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		this.manager.giveBackUUID(this.uuid);
	}

	public void updateEntityLook(float yaw, float pitch) {
		this.location.setYaw(yaw);
		this.location.setPitch(pitch);
		this.updateEntityLook();
	}

	public void updateEntityLook() {
		this.setDirty();

		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_LOOK);
		packet.getIntegers().write(0, this.entityId);
		packet.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (this.location.getPitch() * 256F / 360F)));
		packet.getBooleans().write(0, true);

		PacketContainer headRotation = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
		headRotation.getIntegers().write(0, this.entityId);
		headRotation.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		this.sendPacket(packet, headRotation);
	}

	public void updateRelEntityMove(short x, short y, short z) {
		this.setDirty();

		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.REL_ENTITY_MOVE);
		packet.getIntegers().write(0, this.entityId);
		packet.getShorts().write(0, x);
		packet.getShorts().write(1, y);
		packet.getShorts().write(2, z);
		packet.getBooleans().write(0, true);
		this.sendPacket(packet);
	}

	public void updateRelEntityMoveLook(short x, short y, short z, float yaw, float pitch) {
		this.setDirty();

		this.location.setYaw(yaw);
		this.location.setPitch(pitch);

		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.REL_ENTITY_MOVE);
		packet.getIntegers().write(0, this.entityId);
		packet.getShorts().write(0, x);
		packet.getShorts().write(1, y);
		packet.getShorts().write(2, z);
		packet.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (this.location.getPitch() * 256F / 360F)));
		packet.getBooleans().write(0, true);

		PacketContainer headRotation = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
		headRotation.getIntegers().write(0, this.entityId);
		headRotation.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		this.sendPacket(packet, headRotation);
	}

	public void lookAt(Location location) {
		double xDiff = location.getX() - this.location.getX();
		double yDiff = location.getY() - this.location.getY() - this.eyeHeight;
		double zDiff = location.getZ() - this.location.getZ();

		double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

		double yaw = Math.toDegrees(Math.atan2(zDiff, xDiff)) - 90;
		double pitch = -Math.toDegrees(Math.atan2(yDiff, distanceXZ));
		this.updateEntityLook((float) yaw, (float) pitch);
	}

	public void setIsHandActive(boolean active) {
		this.setFlag(EntityFlag.LIVING_ENTITY_IS_HAND_ACTIVE, active);
	}

	public void setActiveHand(boolean offHand) {
		this.setFlag(EntityFlag.LIVING_ENTITY_ACTIVE_HAND, offHand);
	}

	public void setIsInRiptideSpinAttack(boolean spinAttack) {
		this.setFlag(EntityFlag.LIVING_ENTITY_IS_IN_RIPTIDE_SPIN_ATTACK, spinAttack);
	}

	public void setHealth(float health) {
		this.setMetadata(EntityIndex.LIVING_ENTITY_HEALTH_9, Float.class, health);
	}

	public void setPotionEffectColor(Color color) {
		this.setPotionEffectColor(color.asRGB());
	}

	public void setPotionEffectColor(int color) {
		this.setMetadata(EntityIndex.LIVING_ENTITY_POTION_EFFECT_COLOR_10, Integer.class, color);
	}

	public void setPotionEffectAmbient(boolean ambient) {
		this.setMetadata(EntityIndex.LIVING_ENTITY_IS_POTION_EFFECT_AMBIENT_11, Boolean.class, ambient);
	}

	public void setNumberOfArrowsInEntity(int arrows) {
		this.setMetadata(EntityIndex.LIVING_ENTITY_NUMBER_OF_ARROWS_IN_ENTITY_12, Integer.class, arrows);
	}

	public void setNumberOfBeeStringers(int arrows) {
		this.setMetadata(EntityIndex.LIVING_ENTITY_NUMBER_OF_BEE_STRINGERS_IN_ENTITY_13, Integer.class, arrows);
	}

	public void setBedLocation(Location location) {
		this.setBedLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void setBedLocation(int x, int y, int z) {
		this.setBedLocation(new BlockPosition(x, y, z));
	}

	public void setBedLocation(BlockPosition position) {
		this.setMetadata(EntityIndex.LIVING_ENTITY_LOCATION_OF_THE_BED_14, BlockPosition.class, position);
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
		this.setDirty();
		this.respawn();
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public double getEyeHeight() {
		return this.eyeHeight;
	}
}