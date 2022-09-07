package de.ngloader.npcsystem.api.scoreboard;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;

import net.md_5.bungee.api.chat.BaseComponent;

public interface ScoreboardTeam extends ScoreboardBase {

	BaseComponent getDisplayName();

	ScoreboardTeam setDisplayName(BaseComponent... displayName);

	ScoreboardTeam setDisplayName(String displayName);

	BaseComponent getPrefix();

	ScoreboardTeam setPrefix(BaseComponent... prefix);

	ScoreboardTeam setPrefix(String prefix);

	BaseComponent getSuffix();

	ScoreboardTeam setSuffix(BaseComponent... suffix);

	ScoreboardTeam setSuffix(String suffix);

	ChatColor getColor();

	ScoreboardTeam setColor(ChatColor color);

	ScoreboardTeamRule getNameTagVisiblity();

	ScoreboardTeam setNameTagVisiblity(ScoreboardTeamRule nameTagVisiblity);

	ScoreboardTeamRule getCollisionRule();

	ScoreboardTeam setCollisionRule(ScoreboardTeamRule collisionRule);

	boolean isCanSeeFriendlyInvisible();

	ScoreboardTeam setCanSeeFriendlyInvisible(boolean canSeeFriendlyInvisible);

	boolean isAllowFirendlyFire();

	ScoreboardTeam setAllowFirendlyFire(boolean isAllowFirendlyFire);

	Set<String> getEntrys();

	ScoreboardTeam addEntry(Entity entity);

	ScoreboardTeam addEntry(String entry);

	ScoreboardTeam removeEntry(Entity entity);

	ScoreboardTeam removeEntry(String entry);

	ScoreboardTeam clearEntrys();

	ScoreboardTeam setEntrys(Set<String> entrys);

	boolean hasEntry(Entity entity);

	boolean hasEntry(String entry);

	String getName();

}