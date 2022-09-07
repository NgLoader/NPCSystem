package de.ngloader.npcsystem.scoreboard;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.api.scoreboard.Scoreboard;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardBase;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardManager;

public class SharedScoreboardManager implements Runnable, Listener, ScoreboardManager {

	private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

	private final List<SharedBase<?>> entries = new CopyOnWriteArrayList<>();

	private final SharedScoreboard globalScoreboard = new SharedScoreboard(this);

	public SharedScoreboardManager(Plugin plugin) {
		Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 0);
		Bukkit.getPluginManager().registerEvents(this, plugin);

		for (Player player : Bukkit.getOnlinePlayers()) {
			this.globalScoreboard.addToPlayers(player);
		}
	}

	void sendPacket(Set<Player> players, List<PacketContainer> packets) {
		for (Player player : players) {
			try {
				for (PacketContainer packet : packets) {
						this.protocolManager.sendServerPacket(player, packet);
				}
			} catch (InvocationTargetException e) {
				throw new IllegalStateException("Unable to send packet to " + player.getName(), e);
			}
		}
	}

	void sendPacket(Player player, List<PacketContainer> packets) {
		try {
			for (PacketContainer packet : packets) {
					this.protocolManager.sendServerPacket(player, packet);
			}
		} catch (InvocationTargetException e) {
			throw new IllegalStateException("Unable to send packet to " + player.getName(), e);
		}
	}

	void sendPacket(Player[] players, PacketContainer... packets) {
		for (Player player : players) {
			try {
				for (PacketContainer packet : packets) {
						this.protocolManager.sendServerPacket(player, packet);
				}
			} catch (InvocationTargetException e) {
				throw new IllegalStateException("Unable to send packet to " + player.getName(), e);
			}
		}
	}

	void sendPacket(Set<Player> players, PacketContainer... packets) {
		this.sendPacket(players, Arrays.asList(packets));
	}

	void addPlayer(SharedBase<?> base, Player player, Player... players) {
		base.addPlayer(player);
		for (Player entry : players) {
			base.addPlayer(entry);
		}
	}

	void removePlayer(SharedBase<?> base, Player player, Player... players) {
		base.removePlayer(player);
		for (Player entry : players) {
			base.removePlayer(entry);
		}
	}

	@Override
	public void run() {
		for (SharedBase<?> base : this.entries) {
			base.run();
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.globalScoreboard.addToPlayers(event.getPlayer());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		this.globalScoreboard.removeFromPlayers(event.getPlayer());
	}

	private SharedScoreboardManager unassignDisplaySlot(DisplaySlot[] slots, Player[] players) {
		for (DisplaySlot slot : slots) {
			if (slot != null) {
				this.sendPacket(players, ScoreboardProtocol.setObjectiveDisplayPacket(null, slot));
			}
		}
		return this;
	}

	@Override
	public SharedScoreboardManager unassignDisplaySlot(DisplaySlot slot, Player... players) {
		return this.unassignDisplaySlot(slot, null, null, players);
	}

	@Override
	public SharedScoreboardManager unassignDisplaySlot(DisplaySlot slot, DisplaySlot slot2, Player... players) {
		return this.unassignDisplaySlot(slot, slot2, null, players);
	}

	@Override
	public SharedScoreboardManager unassignDisplaySlot(DisplaySlot slot, DisplaySlot slot2, DisplaySlot slot3, Player... players) {
		return this.unassignDisplaySlot(new DisplaySlot[] { slot, slot2, slot3 }, players);
	}

	@Override
	public Scoreboard createScoreboard() {
		return new SharedScoreboard(this);
	}

	@Override
	public SharedTeam createTeam(String name) {
		SharedTeam team = new SharedTeam(this, name);
		this.entries.add(team);
		return team;
	}

	@Override
	public SharedSidebar createSidebar(String name) {
		SharedSidebar sidebar = new SharedSidebar(this, name);
		this.entries.add(sidebar);
		return sidebar;
	}

	@Override
	public SharedObjective createObjective(String name) {
		SharedObjective objective = new SharedObjective(this, name);
		this.entries.add(objective);
		return objective;
	}

	@Override
	public SharedScoreboardManager delete(ScoreboardBase base) {
		this.entries.remove(base);
		if (base instanceof SharedBase<?> realBase) {
			realBase.delete();
		}
		return this;
	}

	@Override
	public Scoreboard getGlobalScoreboard() {
		return this.globalScoreboard;
	}
}