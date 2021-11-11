package de.ngloader.npcsystem.npc.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.NativeGameMode;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.feature.NPCEquipment;
import de.ngloader.npcsystem.npc.type.NPCEntityLiving;
import de.ngloader.npcsystem.runner.NPCRunnerType;
import de.ngloader.npcsystem.runner.type.tablist.ITabListable;
import de.ngloader.npcsystem.runner.type.tablist.NPCTabListRunner;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import de.ngloader.npcsystem.wrapper.EntityIndex;

public class NPCPlayer extends NPCEntityLiving implements ITabListable {

	private final NPCEquipment equipment = new NPCEquipment(this, this::sendPacket);

	private WrappedGameProfile gameProfile;
	private NativeGameMode gameMode = NativeGameMode.SPECTATOR;
	private int latency = 0;

	private boolean tabListVisiblity = false;

	public NPCPlayer(NPCRegistry registry, String name, Location location) {
		super(registry, 1.62d, location);
		this.gameProfile = new WrappedGameProfile(this.uuid, name);

		this.setFlag(EntityFlag.PLAYER_HAT, true);
		this.setFlag(EntityFlag.PLAYER_CAPE, true);
		this.setFlag(EntityFlag.PLAYER_JACKET, true);
		this.setFlag(EntityFlag.PLAYER_LEFT_SLEEVE, true);
		this.setFlag(EntityFlag.PLAYER_RIGHT_SLEEVE, true);
		this.setFlag(EntityFlag.PLAYER_LEFT_PANTS_LEG, true);
		this.setFlag(EntityFlag.PLAYER_RIGHT_PANTS_LEG, true);
		this.setDirty();
	}

	@Override
	protected void createSpawnPackets() {
		PacketContainer playerInfoPacket = this.protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
		playerInfoPacket.getPlayerInfoAction().write(0, PlayerInfoAction.ADD_PLAYER);
		List<PlayerInfoData> playerInfoData = new ArrayList<>();
		playerInfoData.add(new PlayerInfoData(
				this.gameProfile,
				this.latency,
				this.gameMode,
				WrappedChatComponent.fromText(this.gameProfile.getName())));
		playerInfoPacket.getPlayerInfoDataLists().write(0, playerInfoData);
		this.spawnPackets.add(playerInfoPacket);

		PacketContainer namedEntitySpawnPacket = this.protocolManager.createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN);
		namedEntitySpawnPacket.getIntegers().write(0, this.entityId);
		namedEntitySpawnPacket.getUUIDs().write(0, this.uuid);
		namedEntitySpawnPacket.getDoubles().write(0, this.location.getX());
		namedEntitySpawnPacket.getDoubles().write(1, this.location.getY());
		namedEntitySpawnPacket.getDoubles().write(2, this.location.getZ());
		namedEntitySpawnPacket.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		namedEntitySpawnPacket.getBytes().write(1, (byte) ((int) (this.location.getPitch() * 256F / 360F)));
		this.spawnPackets.add(namedEntitySpawnPacket);

		PacketContainer entityHeadRotationPacket = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
		entityHeadRotationPacket.getIntegers().write(0, this.entityId);
		entityHeadRotationPacket.getBytes().write(0, (byte) ((int) (this.location.getYaw() * 256F / 360F)));
		this.spawnPackets.add(entityHeadRotationPacket);

		super.createSpawnPackets();

		this.equipment.addPackets(this.spawnPackets);
	}

	@Override
	protected void createDespawnPackets() {
		PacketContainer playerInfoPacket = this.protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
		playerInfoPacket.getPlayerInfoAction().write(0, PlayerInfoAction.REMOVE_PLAYER);
		playerInfoPacket.getPlayerInfoDataLists().write(0, Arrays.asList(this.getPlayerInfoData()));
		this.despawnPackets.add(playerInfoPacket);

		super.createDespawnPackets();
	}

	@Override
	protected void onSpawn(Player player) {
		if (!this.tabListVisiblity) {
			NPCTabListRunner tabListRunner = this.registry.getRunnerManager().getRunner(NPCRunnerType.TABLIST);
			if (tabListRunner != null) {
				tabListRunner.queue(player, PlayerInfoAction.REMOVE_PLAYER, this);
			}
		}
	}

	@Override
	public void onDespawn(Player player) {
		if (!this.tabListVisiblity) {
			NPCTabListRunner tabListRunner = this.registry.getRunnerManager().getRunner(NPCRunnerType.TABLIST);
			if (tabListRunner != null) {
				tabListRunner.queue(player, PlayerInfoAction.REMOVE_PLAYER, this);
			}
		}
	}

	@Override
	public PlayerInfoData getPlayerInfoData() {
		return new PlayerInfoData(this.getGameProfile(), this.latency, this.gameMode, WrappedChatComponent.fromText(this.getGameProfile().getName()));
	}

	public void setAdditionalHearts(float hearts) {
		this.setMetadata(EntityIndex.PLAYER_ADDITIONAL_HEARTS_15, Float.class, hearts);
	}

	public void setScore(int score) {
		this.setMetadata(EntityIndex.PLAYER_SCORE_16, Integer.class, score);
	}

	public void setSkinPartCape(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_CAPE, enabled);
	}

	public void setSkinPartJacket(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_JACKET, enabled);
	}

	public void setSkinPartLeftSleeve(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_LEFT_SLEEVE, enabled);
	}

	public void setSkinPartRightSleeve(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_RIGHT_SLEEVE, enabled);
	}

	public void setSkinPartLeftPantsLeg(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_LEFT_PANTS_LEG, enabled);
	}

	public void setSkinPartRightPantsLeg(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_RIGHT_PANTS_LEG, enabled);
	}

	public void setSkinPartHat(boolean enabled) {
		this.setFlag(EntityFlag.PLAYER_HAT, enabled);
	}

	public void setMainHand(EnumWrappers.Hand hand) {
		this.setMetadata(EntityIndex.PLAYER_MAIN_HAND_18, Byte.class, (byte) hand.ordinal());
	}

	public void setGameMode(NativeGameMode gameMode) {
		this.gameMode = gameMode;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}

	public void setTextures(String value, String signature) {
		this.gameProfile.getProperties().removeAll("textures");
		this.gameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", value, signature));
	}

	public void updateGameMode() {
		this.setDirty();

		NPCTabListRunner tabListRunner = this.registry.getRunnerManager().getRunner(NPCRunnerType.TABLIST);
		if (tabListRunner != null) {
			for (Player player : this.getVisible()) {
				tabListRunner.queue(player, PlayerInfoAction.UPDATE_GAME_MODE, this);
			}
		}
	}

	public void updateLatancy() {
		this.setDirty();

		NPCTabListRunner tabListRunner = this.registry.getRunnerManager().getRunner(NPCRunnerType.TABLIST);
		if (tabListRunner != null) {
			for (Player player : this.getVisible()) {
				tabListRunner.queue(player, PlayerInfoAction.UPDATE_LATENCY, this);
			}
		}
	}

	public void updateGameProfile() {
		this.setDirty();
		this.respawn();
	}

	public void setTabListVisiblity(boolean tabListVisiblity) {
		this.tabListVisiblity = tabListVisiblity;
	}

	public boolean isTabListVisiblity() {
		return tabListVisiblity;
	}

	public int getLatency() {
		return this.latency;
	}

	public NativeGameMode getGameMode() {
		return this.gameMode;
	}

	public WrappedGameProfile getGameProfile() {
		return this.gameProfile;
	}

	public NPCEquipment getEquipment() {
		return this.equipment;
	}
}