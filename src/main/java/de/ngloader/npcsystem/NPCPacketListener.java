package de.ngloader.npcsystem;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.WrappedEnumEntityUseAction;

import de.ngloader.npcsystem.event.NPCInteractEvent;
import de.ngloader.npcsystem.npc.entity.NPCPlayer;
import de.ngloader.npcsystem.runner.NPCRunnerType;

public class NPCPacketListener extends PacketAdapter {

	private final NPCSystem system;
	private final ProtocolManager protocolManager;
	private final PluginManager pluginManager;

	private final Set<Player> firstMoveFinish = Collections.newSetFromMap(new WeakHashMap<>());
	private final Map<Player, Location> firstMove = new WeakHashMap<>();

	public NPCPacketListener(NPCSystem system) {
		super(system.getPlugin(), PacketType.Play.Client.USE_ENTITY, PacketType.Play.Client.POSITION, PacketType.Play.Server.NAMED_ENTITY_SPAWN);
		this.system = system;
		this.pluginManager = Bukkit.getPluginManager();
		this.protocolManager = ProtocolLibrary.getProtocolManager();

		this.protocolManager.addPacketListener(this);
	}

	public void unregister() {
		this.protocolManager.removePacketListener(this);
	}

	public void onPlayerDisconnect(Player player) {
		this.firstMoveFinish.remove(player);
		this.firstMove.remove(player);
	}

	@Override
	public void onPacketReceiving(PacketEvent event) {
		PacketContainer packet = event.getPacket();
		if (event.getPacketType() == PacketType.Play.Client.POSITION) {
			Player player = event.getPlayer();
			if (!firstMoveFinish.contains(player)) {
				Location location = this.firstMove.get(player);
				if (location == null) {
					this.firstMove.put(player, player.getLocation());
				} else if (!location.getWorld().equals(player.getWorld())) {
					this.firstMove.put(player, player.getLocation());
				} else if (!location.equals(event.getPlayer().getLocation())) {
					this.firstMove.remove(player);
					this.firstMoveFinish.add(player);

					this.system.callFirstMove(player);
				}
			}
		} else {
			NPC npc = this.system.npcByEntityId.get(packet.getIntegers().read(0));
			if (npc != null) {
				event.setCancelled(true);

				WrappedEnumEntityUseAction useAction = packet.getEnumEntityUseActions().read(0); 
				EntityUseAction action = useAction.getAction();                
				Hand hand = action == EnumWrappers.EntityUseAction.ATTACK ? EnumWrappers.Hand.MAIN_HAND : useAction.getHand();
				this.callEvent(new NPCInteractEvent(event.getPlayer(), npc, action, hand));
			}
		}
	}

	@Override
	public void onPacketSending(PacketEvent event) {
		PacketContainer packet = event.getPacket();
		NPC npc = this.system.npcByEntityId.get(packet.getIntegers().read(0));
		if (npc != null && npc instanceof NPCPlayer npcPlayer) {
//			npcPlayer.getRegistry().queueUpdate(event.getPlayer(), NPCRunnerType.TABLIST, PlayerInfoAction.REMOVE_PLAYER, npcPlayer);
		}
	}

	private void callEvent(Event event) {
		Bukkit.getScheduler().runTask(this.plugin, () -> this.pluginManager.callEvent(event));
	}
}