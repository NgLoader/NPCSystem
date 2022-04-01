package de.ngloader.npcsystem.runner.type.tablist;

import java.util.UUID;

import com.comphenix.protocol.wrappers.EnumWrappers.NativeGameMode;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;

import de.ngloader.npcsystem.NPC;
import de.ngloader.npcsystem.runner.NPCRunnerType;

public class NPCTabListable {

	private final NPC npc;
	private final UUID uuid;

	protected PlayerInfoData playerInfoData;

	private WrappedGameProfile gameProfile;
	private NativeGameMode gameMode = NativeGameMode.SPECTATOR;
	private int latency = 0;

	private String displayName;

	public NPCTabListable(NPC npc) {
		this.npc = npc;
		this.uuid = npc.getManager().generateUUID();
		this.displayName = this.uuid.toString().substring(0, 15);

		this.gameProfile = new WrappedGameProfile(this.uuid, this.displayName);
	}

	public NPCTabListable(NPC npc, WrappedGameProfile gameProfile) {
		this(npc);
		this.gameProfile = gameProfile;
		this.displayName = this.gameProfile.getName();
	}

	public NPCTabListable(NPC npc, WrappedGameProfile gameProfile, NativeGameMode gameMode) {
		this(npc, gameProfile);
		this.gameMode = gameMode;
	}

	public NPCTabListable(NPC npc, WrappedGameProfile gameProfile, NativeGameMode gameMode, int latency) {
		this(npc, gameProfile, gameMode);
		this.latency = latency;
	}

	public NPCTabListable(NPC npc, WrappedGameProfile gameProfile, NativeGameMode gameMode, int latency, String displayName) {
		this(npc, gameProfile, gameMode, latency);
		this.displayName = displayName;
	}

	private void update() {
		this.playerInfoData = new PlayerInfoData(this.gameProfile, this.latency, this.gameMode, WrappedChatComponent.fromText(this.displayName));
	}

	public void setTextures(String value, String signature) {
		this.gameProfile.getProperties().removeAll("textures");
		this.gameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", value, signature));
	}

	public void destroy() {
		this.npc.getManager().giveBackUUID(this.uuid);
	}

	public WrappedGameProfile getGameProfile() {
		return this.gameProfile;
	}

	public void updateGameProfile() {
		this.update();
		this.npc.getRegistry().queueUpdate(this.npc.getVisible(), NPCRunnerType.TABLIST, PlayerInfoAction.UPDATE_GAME_MODE, this);
	}

	public void setGameProfile(WrappedGameProfile gameProfile) {
		this.gameProfile = gameProfile;
	}

	public NativeGameMode getGameMode() {
		return this.gameMode;
	}

	public void updateGameMode() {
		this.update();
		this.npc.getRegistry().queueUpdate(this.npc.getVisible(), NPCRunnerType.TABLIST, PlayerInfoAction.UPDATE_GAME_MODE, this);
	}

	public void setGameMode(NativeGameMode gameMode) {
		this.gameMode = gameMode;
	}

	public int getLatency() {
		return this.latency;
	}

	public void updateLatency() {
		this.update();
		this.npc.getRegistry().queueUpdate(this.npc.getVisible(), NPCRunnerType.TABLIST, PlayerInfoAction.UPDATE_LATENCY, this);
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void updateDisplayName() {
		this.update();
		this.npc.getRegistry().queueUpdate(this.npc.getVisible(), NPCRunnerType.TABLIST, PlayerInfoAction.UPDATE_DISPLAY_NAME, this);
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public PlayerInfoData getPlayerInfoData() {
		return this.playerInfoData;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public NPC getNPC() {
		return this.npc;
	}
}