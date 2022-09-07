package de.ngloader.npcsystem.api.scoreboard;

import java.util.Set;

import org.bukkit.entity.Player;

public interface ScoreboardBase {

	void deleteInstance();

	ScoreboardBase addToPlayers(Player player, Player... players);

	ScoreboardBase removeFromPlayers(Player player, Player... players);

	Set<Player> getPlayers();

	ScoreboardManager getManager();
}
