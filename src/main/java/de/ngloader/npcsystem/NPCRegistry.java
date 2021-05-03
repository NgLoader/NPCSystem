 package de.ngloader.npcsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;

import de.ngloader.npcsystem.runner.NPCRunnerManager;

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

	public void remove() {
		this.npcSystem.registries.remove(this);

		this.runnerManager.stopRunner();
		this.runnerManager.destroy();

		for (Iterator<NPC> iterator = this.npcs.iterator(); iterator.hasNext();) {
			iterator.next().destroy();
		}
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