package de.ngloader.npcsystem.runner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import de.ngloader.npcsystem.NPC;
import de.ngloader.npcsystem.NPCRegistry;

public abstract class NPCRunner<T> implements Runnable {

	private final NPCRegistry registry;

	protected final Set<T> npcs = new HashSet<>();

	public NPCRunner(NPCRegistry registry) {
		this.registry = registry;

		if (Listener.class.isAssignableFrom(this.getClass())) {
			Bukkit.getPluginManager().registerEvents((Listener) this, this.registry.getNPCSystem().getPlugin());
		}
	}

	public abstract boolean addNPC(NPC npc);

	public NPCRegistry getRegistry() {
		return this.registry;
	}

	public boolean addNPC(T npc) {
		return this.npcs.add(npc);
	}

	public void addNPC(T[] npcs) {
		for (T npc : npcs) {
			this.addNPC(npc);
		}
	}

	public void addNPC(List<T> npcs) {
		for (T npc : npcs) {
			this.addNPC(npc);
		}
	}

	public boolean removeNPC(T npc) {
		return this.npcs.remove(npc);
	}

	public void removeNPC(T[] npcs) {
		for (T npc : npcs) {
			this.removeNPC(npc);
		}
	}

	public void removeNPC(List<T> npcs) {
		for (T npc : npcs) {
			this.removeNPC(npc);
		}
	}

	public void destroy() {
		if (Listener.class.isAssignableFrom(this.getClass())) {
			HandlerList.unregisterAll((Listener) this);
		}
	}
}