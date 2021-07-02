package de.ngloader.npcsystem.npc.type;

import java.util.Optional;

import org.bukkit.entity.Pose;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.WrappedDataWatcherObject;

import de.ngloader.npcsystem.NPC;
import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.wrapper.EntityAnimation;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import de.ngloader.npcsystem.wrapper.EntityStatus;

public class NPCEntity extends NPC {

	private WrappedDataWatcher dataWatcher = new WrappedDataWatcher();

	public NPCEntity(NPCRegistry registry) {
		super(registry);
	}

	@Override
	protected void createSpawnPackets() {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
		packet.getIntegers().write(0, this.entityId);
		packet.getWatchableCollectionModifier().write(0, this.dataWatcher.getWatchableObjects());
		this.spawnPackets.add(packet);
	}

	@Override
	protected void createDespawnPackets() {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
		packet.getIntegers().write(0, this.entityId);
		this.despawnPackets.add(packet);
	}

	@Override
	public void updateLocation() {
		this.setDirty();

		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
		packet.getIntegers().write(0, this.entityId);
		packet.getDoubles().write(0, this.location.getX());
		packet.getDoubles().write(1, this.location.getY());
		packet.getDoubles().write(2, this.location.getZ());
		packet.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		packet.getBytes().write(1, (byte) ((int) (this.location.getPitch() * 256F / 360F)));
		packet.getBooleans().write(0, true);
		this.sendPacket(packet);
	}

	public void playAnimation(EntityAnimation animation) {
		this.playAnimation(animation.getAnimation());
	}

	public void playAnimation(int animation) {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ANIMATION);
		packet.getIntegers().write(0, this.getEntityId());
		packet.getIntegers().write(1, animation);
		this.sendPacket(packet);
	}

	public void changeStatus(EntityStatus status) {
		this.changeStatus(status.getStatus());
	}

	public void changeStatus(byte status) {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_STATUS);
		packet.getIntegers().write(0, this.getEntityId());
		packet.getBytes().write(0, status);
		this.sendPacket(packet);
	}

	public void updateDataWatcher() {
		this.setDirty();

		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
		packet.getIntegers().write(0, this.entityId);
		packet.getWatchableCollectionModifier().write(0, this.dataWatcher.getWatchableObjects());
		this.sendPacket(packet);
	}

	public void setCustomName(String name) {
		Optional<?> optional = name != null ? Optional.of(WrappedChatComponent.fromText(name).getHandle()) : Optional.empty();
		if (!this.dataWatcher.hasIndex(2)) {
			this.dataWatcher.setObject(new WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)), optional);
			return;
		}
		this.dataWatcher.setObject(2, optional);
	}

	public void setIsOnFire(boolean onFire) {
		this.setFlag(EntityFlag.ENTITY_IS_ON_FIRE, onFire);
	}

	public void setIsCrouching(boolean crouching) {
		this.setFlag(EntityFlag.ENTITY_IS_CROUCHING, crouching);
	}

	public void setIsSprinting(boolean sprinting) {
		this.setFlag(EntityFlag.ENTITY_IS_SPRINTING, sprinting);
	}

	public void setIsSwimming(boolean swimming) {
		this.setFlag(EntityFlag.ENTITY_IS_SWIMMING, swimming);
	}

	public void setIsInvisible(boolean invisible) {
		this.setFlag(EntityFlag.ENTITY_IS_INVISIBLE, invisible);
	}

	public void setHasGlowingEffect(boolean glowingEffect) {
		this.setFlag(EntityFlag.ENTITY_IS_HAS_GLOWING_EFFECT, glowingEffect);
	}

	public void setIsFlyingWithAnElytra(boolean flying) {
		this.setFlag(EntityFlag.ENTITY_IS_FLYING_WITH_A_ELYTRA, flying);
	}

	public void setAirTicks(int ticks) {
		this.setMetadata(1, Integer.class, ticks);
	}

	public void setCustonNameVisible(boolean visible) {
		this.setMetadata(3, Boolean.class, visible);
	}

	public void setIsSilent(boolean isSilent) {
		this.setMetadata(4, Boolean.class, isSilent);
	}

	public void setHasNoGravity(boolean noGravity) {
		this.setMetadata(5, Boolean.class, noGravity);
	}

	public void setPose(Pose pose) {
		this.setMetadata(6, Integer.class, pose.ordinal());
	}

	public void setMetadata(int index, Class<?> type, Object value) {
		if (!this.dataWatcher.hasIndex(index)) {
			this.dataWatcher.setObject(new WrappedDataWatcherObject(index, WrappedDataWatcher.Registry.get(type)), value);
			return;
		}
		this.dataWatcher.setObject(index, value);
	}

	public void setMetadata(int index, Serializer serializer, Object value) {
		if (!this.dataWatcher.hasIndex(index)) {
			this.dataWatcher.setObject(new WrappedDataWatcherObject(index, serializer), value);
			return;
		}
		this.dataWatcher.setObject(index, value);
	}

	public <T> T getMetadata(Class<T> type, int index) {
		if (this.dataWatcher.hasIndex(index)) {
			Object object = this.dataWatcher.getObject(index);
			return type.cast(object);
		}
		return null;
	}

	public void setFlag(int index, byte flag, boolean set) {
		if (!this.dataWatcher.hasIndex(index)) {
			this.dataWatcher.setObject(new WrappedDataWatcherObject(index, WrappedDataWatcher.Registry.get(Byte.class)), Byte.valueOf((byte) flag));
			return;
		}

		byte flags = this.dataWatcher.getByte(index);
		this.dataWatcher.setObject(index, (byte) (set ? (flags | flag) : (flags & ~flag)));
	}

	public void setFlag(EntityFlag bitMask, boolean set) {
		this.setFlag(bitMask.getIndex(), bitMask.getFlag(), set);
	}

	public boolean getFlagState(EntityFlag bitMask) {
		return this.getFlagState(bitMask.getIndex(), bitMask.getFlag());
	}

	public boolean getFlagState(int index, byte flag) {
		if (this.dataWatcher.hasIndex(index)) {
			return (this.dataWatcher.getByte(index) & flag) == flag;
		}
		return false;
	}

	public WrappedDataWatcher getDataWatcher() {
		return this.dataWatcher;
	}
}