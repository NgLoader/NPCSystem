package de.ngloader.npcsystem.api.scoreboard;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;

import net.md_5.bungee.api.chat.BaseComponent;

public interface ScoreboardObjective extends ScoreboardBase {

	ScoreboardObjective assignDisplaySlot(DisplaySlot slot, Player... players);

	ScoreboardObjective assignDisplaySlot(DisplaySlot slot, DisplaySlot slot2, Player... players);

	ScoreboardObjective assignDisplaySlot(DisplaySlot slot, DisplaySlot slot2, DisplaySlot slot3, Player... players);

	ScoreboardObjectiveExtendedScore createExtendedScore(BaseComponent displayName, int score);

	ScoreboardObjectiveScore createScore(Entity entity, int score);

	ScoreboardObjectiveScore createScore(Entity entity);

	ScoreboardObjectiveScore createScore(String displayName, int score);

	ScoreboardObjectiveScore createScore(String displayName);

	ScoreboardObjectiveScore getScore(String displayName);

	ScoreboardObjectiveScore getScore(int score);

	List<ScoreboardObjectiveScore> getScores(String displayName);

	List<ScoreboardObjectiveScore> getScores(int score);

	ScoreboardObjective deleteScore(ScoreboardObjectiveScore scores);

	String getName();

	BaseComponent getTitle();

	ScoreboardObjective setTitle(BaseComponent displayName);

	ScoreboardObjective setTitle(String displayName);

	RenderType getRenderType();

	ScoreboardObjective setRenderType(RenderType renderType);

}