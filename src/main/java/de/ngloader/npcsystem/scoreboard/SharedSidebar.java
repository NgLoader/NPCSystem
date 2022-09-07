package de.ngloader.npcsystem.scoreboard;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import de.ngloader.npcsystem.api.scoreboard.ScoreboardSidebar;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class SharedSidebar extends SharedObjective implements ScoreboardSidebar {

	public SharedSidebar(SharedScoreboardManager manager, String name) {
		super(manager, name);
	}

	@Override
	boolean addPlayer(Player player) {
		if (super.addPlayer(player)) {
			this.assignDisplaySlot(DisplaySlot.SIDEBAR, player);
			return true;
		}
		return false;
	}

	@Override
	boolean removePlayer(Player player) {
		if (super.removePlayer(player)) {
			this.manager.unassignDisplaySlot(DisplaySlot.SIDEBAR, player);
			return true;
		}
		return false;
	}

	@Override
	public SharedSidebar fillLines(int from, int to) {
		Objects.checkIndex(from, 16);
		Objects.checkIndex(to, 16);

		for (int i = from; i < to + 1; i++) {
			this.setLine(i, "");
		}
		return this;
	}

	@Override
	public SharedSidebar setLine(int line, String text) {
		return this.setLine(line, new TextComponent(TextComponent.fromLegacyText(text)));
	}

	@Override
	public SharedSidebar setLine(int line, BaseComponent text) {
		Objects.checkIndex(line, 16);

		if (text == null) {
			return this.deleteLine(line);
		}

		line--;
		SharedObjectiveScore entry = this.getEntry(line);
		if (entry != null) {
			((SharedObjectiveExtendedScore) entry).setDisplayName(text);
		} else {
			this.createExtendedScore(text, 15 - line);
		}
		return this;
	}

	@Override
	public SharedSidebar deleteLine(int line) {
		Objects.checkIndex(line, 16);

		this.deleteScore(this.getEntry(line - 1));
		return this;
	}
}
