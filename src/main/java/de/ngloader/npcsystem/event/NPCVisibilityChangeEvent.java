package de.ngloader.npcsystem.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.ngloader.npcsystem.NPC;

public class NPCVisibilityChangeEvent extends Event {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final Player player;
	private final NPC npc;

	private final boolean visibility;

	public NPCVisibilityChangeEvent(Player player, NPC npc, boolean visibility) {
		this.player = player;
		this.npc = npc;
		this.visibility = visibility;
	}

	public Player getPlayer() {
		return this.player;
	}

	public NPC getNPC() {
		return this.npc;
	}

	public boolean getVisibility() {
		return this.visibility;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}
}
