package de.ngloader.ncpsystem.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.ngloader.ncpsystem.NPC;

public class NPCCreatedEvent extends Event {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final NPC npc;

	public NPCCreatedEvent(NPC npc) {
		this.npc = npc;
	}

	public NPC getNPC() {
		return this.npc;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}
}
