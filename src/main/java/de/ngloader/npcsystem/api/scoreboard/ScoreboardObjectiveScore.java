package de.ngloader.npcsystem.api.scoreboard;

public interface ScoreboardObjectiveScore extends ScoreboardBase {

	String getName();

	int getScore();

	ScoreboardObjectiveScore setScore(int score);

	ScoreboardObjective getObjective();

}