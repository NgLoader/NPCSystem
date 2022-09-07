package de.ngloader.npcsystem.api.scoreboard;

import net.md_5.bungee.api.chat.BaseComponent;

public interface ScoreboardSidebar extends ScoreboardBase {

	ScoreboardSidebar fillLines(int from, int to);

	ScoreboardSidebar setLine(int line, String text);

	ScoreboardSidebar setLine(int line, BaseComponent text);

	ScoreboardSidebar deleteLine(int line);

}