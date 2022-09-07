package de.ngloader.npcsystem.scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.entity.Player;

import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.api.scoreboard.ScoreboardBase;

abstract class SharedBase<This extends SharedBase<?>> implements ScoreboardBase, Runnable {

	protected final SharedScoreboardManager manager;
	protected final Set<Player> players = Collections.newSetFromMap(new WeakHashMap<>());

	protected List<PacketContainer> createPacket = new ArrayList<>();
	protected List<PacketContainer> deletePacket = new ArrayList<>();
	protected List<PacketContainer> updatePacket = new ArrayList<>();

	boolean dirty = false;
	boolean dirtyCreate = true;

	public SharedBase(SharedScoreboardManager manager) {
		this.manager = manager;
	}

	protected abstract void createSpawnPackets(List<PacketContainer> packets);
	protected abstract void createUpdatePackets(List<PacketContainer> packets);
	protected abstract void createDeletePackets(List<PacketContainer> packets);

	@Override
	public void run() {
		this.generateUpdatePackets(this.updatePacket);
		if (!this.updatePacket.isEmpty()) {
			this.sendPacket(this.updatePacket);
			this.updatePacket.clear();
		}
	}

	void generateSpawnPackets(List<PacketContainer> packets) {
		if (this.dirtyCreate) {
			this.createSpawnPackets(packets);
			this.dirtyCreate = false;
		}
	}

	void generateUpdatePackets(List<PacketContainer> packets) {
		if (this.dirty) {
			this.createUpdatePackets(packets);
			this.dirty = true;
		}
	}

	void generateDeletePackets(List<PacketContainer> packets) {
		this.createDeletePackets(packets);
	}

	boolean addPlayer(Player player) {
		if (this.players.add(player)) {
			this.generateSpawnPackets(this.createPacket);

			this.manager.sendPacket(player, this.createPacket);
			return true;
		}
		return false;
	}

	boolean removePlayer(Player player) {
		if (this.players.remove(player)) {
			if (this.deletePacket.isEmpty()) {
				this.generateDeletePackets(this.deletePacket);
			}

			this.manager.sendPacket(player, this.deletePacket);
			return true;
		}
		return false;
	}

	void sendPacket(List<PacketContainer> packets) {
		this.manager.sendPacket(this.players, packets);
	}

	void sendPacket(PacketContainer... packets) {
		this.manager.sendPacket(this.players, packets);
	}

	void delete() {
		for (Iterator<Player> iterator = this.players.iterator(); iterator.hasNext();) {
			this.removePlayer(iterator.next());
		}

		this.createPacket.clear();
		this.deletePacket.clear();
		this.updatePacket.clear();
		this.dirty = false;
	}

	void setDirty() {
		this.dirty = true;
		this.dirtyCreate = true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public This addToPlayers(Player player, Player... players) {
		this.manager.addPlayer(this, player, players);
		return (This) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public This removeFromPlayers(Player player, Player... players) {
		this.manager.addPlayer(this, player, players);
		return (This) this;
	}

	@Override
	public void deleteInstance() {
		this.manager.delete(this);
	}

	@Override
	public Set<Player> getPlayers() {
		return Collections.unmodifiableSet(this.players);
	}

	@Override
	public SharedScoreboardManager getManager() {
		return this.manager;
	}
}
