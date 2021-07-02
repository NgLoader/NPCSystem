package de.ngloader.npcsystem;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.EntityUseAction;
import com.comphenix.protocol.wrappers.EnumWrappers.Hand;
import com.comphenix.protocol.wrappers.WrappedEnumEntityUseAction;

import de.ngloader.npcsystem.event.NPCInteractEvent;

public class NPCPacketListener extends PacketAdapter {

	private final NPCSystem system;
	private final ProtocolManager protocolManager;
	private final PluginManager pluginManager;

	public NPCPacketListener(NPCSystem system) {
		super(system.getPlugin(), PacketType.Play.Client.USE_ENTITY);
		this.system = system;
		this.pluginManager = Bukkit.getPluginManager();
		this.protocolManager = ProtocolLibrary.getProtocolManager();

		this.protocolManager.addPacketListener(this);
	}

	public void unregister() {
		this.protocolManager.removePacketListener(this);
	}

	@Override
	public void onPacketReceiving(PacketEvent event) {
		PacketContainer packet = event.getPacket();
		NPC npc = this.system.npcByEntityId.get(packet.getIntegers().read(0));
		if (npc != null) {
			event.setCancelled(true);

			WrappedEnumEntityUseAction useAction = packet.getEnumEntityUseActions().read(0); 
			EntityUseAction action = useAction.getAction();                
			Hand hand = action == EnumWrappers.EntityUseAction.ATTACK ? EnumWrappers.Hand.MAIN_HAND : useAction.getHand();
			this.callEvent(new NPCInteractEvent(event.getPlayer(), npc, action, hand));
		}
	}

	private void callEvent(Event event) {
		Bukkit.getScheduler().runTask(this.plugin, () -> this.pluginManager.callEvent(event));
	}
}