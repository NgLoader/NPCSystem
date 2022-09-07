package de.ngloader.npcsystem.api.scoreboard;

public enum ScoreboardTeamRule {

	ALWAYS("always", "always"),
	NEVER("never", "never"),
	OTHER_TEAMS("hideForOtherTeams", "pushOtherTeams"),
	OWN_TEAM("hideForOwnTeam", "pushOwnTeam");

	private final String visibility;
	private final String collision;

	private ScoreboardTeamRule(String visibility, String collision) {
		this.visibility = visibility;
		this.collision = collision;
	}

	public String getVisibilityName() {
		return this.visibility;
	}

	public String getCollisionName() {
		return this.collision;
	}
}
