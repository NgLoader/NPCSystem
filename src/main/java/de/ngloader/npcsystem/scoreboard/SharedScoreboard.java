package de.ngloader.npcsystem.scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.entity.Player;

import de.ngloader.npcsystem.api.scoreboard.Scoreboard;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardBase;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardManager;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardObjective;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardSidebar;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardTeam;

public class SharedScoreboard implements Scoreboard {

	private final SharedScoreboardManager manager;
	private final Set<ScoreboardBase> entries = new HashSet<>();
	private final Set<Player> players = Collections.newSetFromMap(new WeakHashMap<>());

	private ScoreboardSidebar sidebar;

	public SharedScoreboard(SharedScoreboardManager manager) {
		this.manager = manager;
	}

	private void addEntry(ScoreboardBase entry) {
		this.entries.add(entry);
		for (Player player : this.players) {
			entry.addToPlayers(player);
		}
	}

	private void removeEntry(ScoreboardBase entry) {
		this.entries.remove(entry);
		for (Player player : this.players) {
			entry.removeFromPlayers(player);
		}
	}

	@Override
	public Scoreboard addToPlayers(Player player, Player... players) {
		Objects.requireNonNull(player);

		for (ScoreboardBase base : this.entries) {
			base.addToPlayers(player, players);
		}
		return this;
	}

	@Override
	public Scoreboard removeFromPlayers(Player player, Player... players) {
		Objects.requireNonNull(player);

		for (ScoreboardBase base : this.entries) {
			base.removeFromPlayers(player, players);
		}
		return this;
	}

	@Override
	public Scoreboard setSidebar(ScoreboardSidebar sidebar) {
		Objects.requireNonNull(sidebar);

		if (this.sidebar != null) {
			this.sidebar.deleteInstance();
		}

		this.sidebar = sidebar;
		this.addEntry(sidebar);
		return this;
	}

	@Override
	public ScoreboardSidebar createSidebar(String name) {
		Objects.requireNonNull(name);

		ScoreboardSidebar sidebar = this.manager.createSidebar(name);
		this.setSidebar(sidebar);
		return sidebar;
	}

	@Override
	public ScoreboardSidebar getSidebar() {
		return this.sidebar;
	}

	@Override
	public Scoreboard deleteSidebar() {
		if (this.sidebar != null) {
			this.sidebar.deleteInstance();
			this.sidebar = null;
		}
		return this;
	}

	@Override
	public Scoreboard addTeam(ScoreboardTeam team) {
		Objects.requireNonNull(team);

		this.addEntry(team);
		return this;
	}

	@Override
	public ScoreboardTeam createTeam(String name) {
		Objects.requireNonNull(name);

		ScoreboardTeam team = this.manager.createTeam(name);
		this.addEntry(team);
		return team;
	}

	@Override
	public ScoreboardTeam getTeam(String name) {
		for (ScoreboardBase base : this.entries) {
			if (base instanceof ScoreboardTeam team && team.getName().equals(name)) {
				return team;
			}
		}
		return null;
	}

	@Override
	public List<ScoreboardTeam> getTeams() {
		List<ScoreboardTeam> teams = new ArrayList<>();
		for (ScoreboardBase base : this.entries) {
			if (base instanceof ScoreboardTeam team) {
				teams.add(team);
			}
		}
		return teams;
	}

	@Override
	public Scoreboard removeTeam(ScoreboardTeam team) {
		Objects.requireNonNull(team);

		this.removeEntry(team);
		return this;
	}

	@Override
	public Scoreboard deleteTeam(ScoreboardTeam team) {
		Objects.requireNonNull(team);

		this.removeTeam(team);
		team.deleteInstance();
		return this;
	}

	@Override
	public Scoreboard addObjective(ScoreboardObjective objective) {
		Objects.requireNonNull(objective);

		this.removeEntry(objective);
		return this;
	}

	@Override
	public ScoreboardObjective createObjective(String name) {
		Objects.requireNonNull(name);

		ScoreboardObjective objective = this.manager.createObjective(name);
		this.addEntry(objective);
		return objective;
	}

	@Override
	public ScoreboardObjective getObjective(String name) {
		for (ScoreboardBase base : this.entries) {
			if (base instanceof ScoreboardObjective objective && objective.getName().equals(name)) {
				return objective;
			}
		}
		return null;
	}

	@Override
	public List<ScoreboardObjective> getObjectives() {
		List<ScoreboardObjective> objectives = new ArrayList<>();
		for (ScoreboardBase base : this.entries) {
			if (base instanceof ScoreboardObjective objective) {
				objectives.add(objective);
			}
		}
		return objectives;
	}

	@Override
	public Scoreboard removeObjective(ScoreboardObjective objective) {
		Objects.requireNonNull(objective);

		this.removeEntry(objective);
		return this;
	}

	@Override
	public Scoreboard deleteObjective(ScoreboardObjective objective) {
		Objects.requireNonNull(objective);

		this.removeObjective(objective);
		objective.deleteInstance();
		return this;
	}

	@Override
	public void deleteInstance() {
		for (ScoreboardBase base : this.entries) {
			base.deleteInstance();
		}
	}

	@Override
	public Set<Player> getPlayers() {
		return Collections.unmodifiableSet(this.players);
	}

	@Override
	public ScoreboardManager getManager() {
		return this.manager;
	}
}