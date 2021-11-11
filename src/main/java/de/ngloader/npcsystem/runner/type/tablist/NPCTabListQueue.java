package de.ngloader.npcsystem.runner.type.tablist;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;

public class NPCTabListQueue implements Runnable {

	private final ProtocolManager protocolManager;

	public final Player player;

	public final Map<PlayerInfoAction, Set<ITabListable>> queue = new HashMap<>();

	public NPCTabListQueue(NPCTabListRunner tabListManager, Player player) {
		this.player = player;

		this.protocolManager = ProtocolLibrary.getProtocolManager();
	}

	@Override
	public void run() {
		for (Iterator<Entry<PlayerInfoAction, Set<ITabListable>>> iterator = this.queue.entrySet().iterator(); iterator.hasNext();) {
			Entry<PlayerInfoAction, Set<ITabListable>> entry = iterator.next();
			Set<ITabListable> queue = entry.getValue();
			if (queue == null || queue.isEmpty()) {
				iterator.remove();
				return;
			}

			Set<ITabListable> tabListableRemoved = new HashSet<ITabListable>(queue);
			this.handle(entry.getKey(), tabListableRemoved);
			queue.removeAll(tabListableRemoved);
		}
	}

	private void handle(PlayerInfoAction action, Set<ITabListable> queue) {
		if (queue.isEmpty()) {
			return;
		}

		List<PlayerInfoData> playerInfoDatas = new ArrayList<>();
		for (ITabListable tabListable : queue) {
			playerInfoDatas.add(tabListable.getPlayerInfoData());
		}

		PacketContainer packetContainer = this.protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
		packetContainer.getPlayerInfoAction().write(0, action);
		packetContainer.getPlayerInfoDataLists().write(0, playerInfoDatas);

		if (action == PlayerInfoAction.REMOVE_PLAYER) {
			try {
				this.protocolManager.sendServerPacket(player, packetContainer);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return;
		}

		try {
			this.protocolManager.sendServerPacket(player, packetContainer);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void queue(PlayerInfoAction action, ITabListable tabListable) {
		Set<ITabListable> actionQueue = this.queue.get(action);
		if (actionQueue == null) {
			actionQueue = new HashSet<>();
			this.queue.put(action, actionQueue);
		}
		actionQueue.add(tabListable);
	}
}