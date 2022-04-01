
package de.ngloader.npcsystem.event;

import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.NPC;

public class NPCPacketCreateEvent extends Event {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final NPC npc;
	private final PacketType type;
	private final PacketState state;
	private final List<PacketContainer> packets;

	public NPCPacketCreateEvent(NPC npc, PacketType type, PacketState state, List<PacketContainer> packets) {
		this.npc = npc;
		this.type = type;
		this.state = state;
		this.packets = packets;
	}

	public NPC getNPC() {
		return this.npc;
	}

	public PacketType getType() {
		return this.type;
	}

	public PacketState getState() {
		return this.state;
	}

	public List<PacketContainer> getPackets() {
		return this.packets;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	public enum PacketState {
		PRE,
		AFTER
	}

	public enum PacketType {
		SPAWN,
		DESPAWN
	}
}
