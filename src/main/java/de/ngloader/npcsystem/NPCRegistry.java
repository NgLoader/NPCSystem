 package de.ngloader.npcsystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.entity.Player;

import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;

import de.ngloader.npcsystem.runner.NPCRunnerManager;
import de.ngloader.npcsystem.runner.NPCRunnerType;
import de.ngloader.npcsystem.runner.type.tablist.NPCTabList;
import de.ngloader.npcsystem.runner.type.tablist.NPCTabListRunner;
import de.ngloader.npcsystem.runner.type.tablist.NPCTabListable;

public class NPCRegistry {

	private final NPCSystem npcSystem;
	private final NPCRunnerManager runnerManager;

	protected final List<NPC> npcs = new ArrayList<>();

	public NPCRegistry(NPCSystem npcSystem) {
		this.npcSystem = npcSystem;

		this.runnerManager = new NPCRunnerManager(this);
	}

	public void showAll(Player player) {
		this.npcs.forEach(npc -> npc.show(player));
	}

	public void hideAll(Player player) {
		this.npcs.forEach(npc -> npc.hide(player));
	}

	public void respawnAll(Player player) {
		this.npcs.forEach(npc -> npc.respawn(player));
	}

	public void queueUpdate(Collection<Player> players, NPCRunnerType type, PlayerInfoAction action, NPCTabList npcTabList) {
		this.queueUpdate(players, type, action, npcTabList.getTabListable());
	}

	public void queueUpdate(Collection<Player> players, NPCRunnerType type, PlayerInfoAction action, NPCTabListable npcTabList) {
		NPCTabListRunner tabListRunner = this.runnerManager.getRunner(type);
		if (tabListRunner != null) {
			tabListRunner.queue(players, action, npcTabList);
		}
	}

	public void queueUpdate(Player player, NPCRunnerType type, PlayerInfoAction action, NPCTabList npcTabList) {
		this.queueUpdate(player, type, action, npcTabList.getTabListable());
	}

	public void queueUpdate(Player player, NPCRunnerType type, PlayerInfoAction action, NPCTabListable npcTabList) {
		NPCTabListRunner tabListRunner = this.runnerManager.getRunner(type);
		if (tabListRunner != null) {
			tabListRunner.queue(player, action, npcTabList);
		}
	}

	public void remove() {
		this.npcSystem.registries.remove(this);

		this.runnerManager.stopRunner();
		this.runnerManager.destroy();

		List<NPC> npcs = new ArrayList<>(this.npcs);
		npcs.forEach(NPC::destroy);
		this.npcs.clear();
	}

	public List<NPC> getNpcs() {
		return this.npcs;
	}

	public NPCSystem getNPCSystem() {
		return this.npcSystem;
	}

	public NPCRunnerManager getRunnerManager() {
		return this.runnerManager;
	}
}