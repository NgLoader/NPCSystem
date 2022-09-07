package de.ngloader.npcsystem.scoreboard;

import java.util.List;

import com.comphenix.protocol.events.PacketContainer;

import de.ngloader.npcsystem.api.scoreboard.ScoreboardObjectiveScore;

public class SharedObjectiveScore extends SharedBase<SharedObjectiveScore> implements ScoreboardObjectiveScore {

	private final SharedObjective objective;

	protected String name;
	private int score;

	public SharedObjectiveScore(SharedObjective objective, String name, int score) {
		super(objective.getManager());
		this.objective = objective;
		this.name = name;
		this.score = score;
	}

	public SharedObjectiveScore(SharedObjective objective, String name) {
		this(objective, name, 0);
	}

	public SharedObjectiveScore(SharedObjective objective) {
		this(objective, null, 0);
	}

	@Override
	protected void createSpawnPackets(List<PacketContainer> packets) {
		packets.add(ScoreboardProtocol.changeObjectiveScorePacket(this));
	}

	@Override
	protected void createDeletePackets(List<PacketContainer> packets) {
		packets.add(ScoreboardProtocol.removeObjectiveScorePacket(this));
	}

	@Override
	protected void createUpdatePackets(List<PacketContainer> packets) {
		packets.add(ScoreboardProtocol.changeObjectiveScorePacket(this));
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public SharedObjectiveScore setScore(int score) {
		this.score = score;
		this.setDirty();
		return this;
	}

	@Override
	public SharedObjective getObjective() {
		return this.objective;
	}
}