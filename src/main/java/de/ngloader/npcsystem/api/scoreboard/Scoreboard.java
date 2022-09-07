package de.ngloader.npcsystem.api.scoreboard;

import java.util.List;

public interface Scoreboard extends ScoreboardBase {

	/**
	 * Delete the current sidebar and replace it with the new one.
	 * 
	 * @param sidebar
	 * @return
	 */
	Scoreboard setSidebar(ScoreboardSidebar sidebar);

	/**
	 * Create a new sidebar and delete the current one.
	 * 
	 * @param name
	 * @return
	 */
	ScoreboardSidebar createSidebar(String name);

	/**
	 * Get the current sidebar.
	 * 
	 * @return
	 */
	ScoreboardSidebar getSidebar();

	/**
	 * Delete the current sidebar.
	 * 
	 * @return
	 */
	Scoreboard deleteSidebar();

	/**
	 * Add a team to the scoreboard.
	 * This will automatic be add all players containing in the scoreboard.
	 * 
	 * @param team
	 * @return
	 */
	Scoreboard addTeam(ScoreboardTeam team);

	/**
	 * Create a new team.
	 * This will automatic call {@link addTeam}
	 * 
	 * @param name
	 * @return
	 */
	ScoreboardTeam createTeam(String name);

	/**
	 * Return the first team with the exact name.
	 * 
	 * @param name
	 * @return
	 */
	ScoreboardTeam getTeam(String name);

	/**
	 * Return all defined teams in this scoreboard.
	 * 
	 * @return
	 */
	List<ScoreboardTeam> getTeams();

	/**
	 * Remove the team from the scoreboard.
	 * This will automatic remove all players from team which is contains in the scoreboard.
	 * 
	 * @param team
	 * @return
	 */
	Scoreboard removeTeam(ScoreboardTeam team);

	/**
	 * Remove the team from the scoreboard and delete it.
	 * 
	 * @param team
	 * @return
	 */
	Scoreboard deleteTeam(ScoreboardTeam team);

	/**
	 * Add a objective to the scoreboard.
	 * This will automatic add all players containing in the scoreboard.
	 * 
	 * @param team
	 * @return
	 */
	Scoreboard addObjective(ScoreboardObjective objective);

	/**
	 * Create a new objective.
	 * This will automatic call {@link addObjective}
	 * 
	 * @param name
	 * @return
	 */
	ScoreboardObjective createObjective(String name);

	/**
	 * Return the first objective with the exact name.
	 * 
	 * @param name
	 * @return
	 */
	ScoreboardObjective getObjective(String name);

	/**
	 * Return all defined objectives in this scoreboard.
	 * 
	 * @return
	 */
	List<ScoreboardObjective> getObjectives();

	/**
	 * Remove the objective from the scoreboard.
	 * This will automatic remove all players from the objective which is contains in the scoreboard.
	 * 
	 * @param objective
	 * @return
	 */
	Scoreboard removeObjective(ScoreboardObjective objective);

	/**
	 * Remove the objective from the scoreboard and delete it.
	 * 
	 * @param objective
	 * @return
	 */
	Scoreboard deleteObjective(ScoreboardObjective objective);
}