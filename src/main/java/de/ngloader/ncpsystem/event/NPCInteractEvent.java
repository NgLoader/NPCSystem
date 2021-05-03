package de.ngloader.ncpsystem.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.comphenix.protocol.wrappers.EnumWrappers;

import de.ngloader.ncpsystem.NPC;

public class NPCInteractEvent extends Event implements Cancellable {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final Player player;
	private final NPC npc;
	private final EnumWrappers.EntityUseAction action;
	private final EnumWrappers.Hand hand;

	private boolean cancelled = false;

	public NPCInteractEvent(Player player, NPC npc, EnumWrappers.EntityUseAction action, EnumWrappers.Hand hand) {
		this.player = player;
		this.npc = npc;
		this.action = action;
		this.hand = hand;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Player getPlayer() {
		return this.player;
	}

	public NPC getNPC() {
		return this.npc;
	}

	public EnumWrappers.EntityUseAction getAction() {
		return this.action;
	}

	public EnumWrappers.Hand getHand() {
		return this.hand;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}
}