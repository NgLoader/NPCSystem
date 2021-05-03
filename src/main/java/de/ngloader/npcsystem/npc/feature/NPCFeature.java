package de.ngloader.npcsystem.npc.feature;

import java.util.function.Consumer;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.NPC;

public class NPCFeature {

	protected final NPC npc;
	protected final Consumer<PacketContainer> sendPacket;

	protected final ProtocolManager protocolManager;

	public NPCFeature(NPC npc, Consumer<PacketContainer> sendPacket) {
		this.npc = npc;
		this.sendPacket = sendPacket;

		this.protocolManager = ProtocolLibrary.getProtocolManager();
	}
}