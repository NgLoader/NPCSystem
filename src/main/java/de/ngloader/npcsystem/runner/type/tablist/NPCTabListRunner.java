package de.ngloader.npcsystem.runner.type.tablist;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;

import de.ngloader.npcsystem.NPC;
import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.runner.NPCRunner;

public class NPCTabListRunner extends NPCRunner<ITabListable> implements Listener {

	private final Map<Player, NPCTabListQueue> queue = new WeakHashMap<>();

	public NPCTabListRunner(NPCRegistry registry) {
		super(registry);
	}

	@Override
	public void run() {
		for (Iterator<NPCTabListQueue> iterator = this.queue.values().iterator(); iterator.hasNext();) {
			NPCTabListQueue queue = iterator.next();
			if (!queue.player.isOnline()) {
				iterator.remove();
				return;
			}
			queue.run();
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		this.queue.remove(event.getPlayer());
	}

	public void onFirstMove(Player player) {
		NPCTabListQueue queue = this.queue.get(player);
		if (queue != null) {
			queue.sendRemovePacket = true;
		}
	}

	public void queue(Player player, PlayerInfoAction action, ITabListable tabListable) {
		NPCTabListQueue tablistQueue = this.queue.get(player);
		if (tablistQueue == null) {
			tablistQueue = new NPCTabListQueue(this, player);
			this.queue.put(player, tablistQueue);
		}

		tablistQueue.queue(action, tabListable);
	}

	public void destroy() {
		this.queue.clear();
	}

	@Override
	public boolean addNPC(NPC npc) {
		if (npc instanceof ITabListable) {
			return this.addNPC((ITabListable) npc);
		}
		return false;
	}

	@Override
	public boolean addNPC(ITabListable npc) {
		return false;
	}

	@Override
	public boolean removeNPC(ITabListable npc) {
		return false;
	}
}
