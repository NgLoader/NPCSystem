package de.ngloader.npcsystem.runner.type.tablist;

import java.util.Collection;
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
import de.ngloader.npcsystem.event.NPCPacketCreateEvent;
import de.ngloader.npcsystem.runner.NPCRunner;

public class NPCTabListRunner extends NPCRunner<NPCTabListable> implements Listener {

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

	@EventHandler
	public void onPacketCreate(NPCPacketCreateEvent event) {
		
	}

	public void onFirstMove(Player player) {
		NPCTabListQueue queue = this.queue.get(player);
		if (queue != null) {
			queue.sendRemovePacket = true;
		}
	}
	public void queue(Collection<Player> players, PlayerInfoAction action, NPCTabList tabListable) {
		players.forEach(player -> this.queue(player, action, tabListable.getTabListable()));
	}

	public void queue(Collection<Player> players, PlayerInfoAction action, NPCTabListable tabListable) {
		players.forEach(player -> this.queue(player, action, tabListable));
	}

	public void queue(Player player, PlayerInfoAction action, NPCTabList tabListable) {
		this.queue(player, action, tabListable.getTabListable());
	}

	public void queue(Player player, PlayerInfoAction action, NPCTabListable tabListable) {
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
		return false;
	}

	@Override
	public boolean addNPC(NPCTabListable npc) {
		return false;
	}

	@Override
	public boolean removeNPC(NPC npc) {
		return false;
	}

	@Override
	public boolean removeNPC(NPCTabListable npc) {
		return false;
	}
}
