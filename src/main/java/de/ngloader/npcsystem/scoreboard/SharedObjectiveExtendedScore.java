package de.ngloader.npcsystem.scoreboard;

import java.util.List;

import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.api.scoreboard.ScoreboardObjectiveExtendedScore;
import de.ngloader.npcsystem.util.NPCUtil;
import net.md_5.bungee.api.chat.BaseComponent;

public class SharedObjectiveExtendedScore extends SharedObjectiveScore implements ScoreboardObjectiveExtendedScore {

	private final SharedTeam team;

	private boolean dirtyScore = false;

	public SharedObjectiveExtendedScore(SharedObjective objective, BaseComponent name, int score) {
		super(objective, "", score);
		this.name = NPCUtil.createHashCode(this);
		this.team = new SharedTeam(this.getManager(), this.getName());
		this.team.addEntry(this.getName());
		this.team.setPrefix(name);
	}

	@Override
	protected void createSpawnPackets(List<PacketContainer> packets) {
		super.createSpawnPackets(packets);
		this.team.createSpawnPackets(packets);
	}

	@Override
	protected void createUpdatePackets(List<PacketContainer> packets) {
		this.team.createUpdatePackets(packets);

		if (this.dirtyScore) {
			super.createUpdatePackets(packets);
			this.dirtyScore = false;
		}
	}

	@Override
	protected void createDeletePackets(List<PacketContainer> packets) {
		super.createDeletePackets(packets);
		this.team.createDeletePackets(packets);
	}

	@Override
	void delete() {
		super.delete();
		this.team.delete();
	}

	@Override
	public SharedObjectiveExtendedScore setDisplayName(BaseComponent name) {
		this.team.setPrefix(name);
		this.setDirty();
		return this;
	}

	@Override
	public SharedObjectiveExtendedScore setDisplayName(String name) {
		this.team.setPrefix(name);
		this.setDirty();
		return this;
	}

	@Override
	public SharedObjectiveScore setScore(int score) {
		this.dirtyScore = true;
		return super.setScore(score);
	}
}
