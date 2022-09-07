package de.ngloader.npcsystem.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import de.ngloader.npcsystem.api.scoreboard.ScoreboardObjective;
import de.ngloader.npcsystem.api.scoreboard.ScoreboardObjectiveScore;
import de.ngloader.npcsystem.util.NPCUtil;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class SharedObjective extends SharedBaseParent<SharedObjective, SharedObjectiveScore> implements ScoreboardObjective {

	private final String name;

	WrappedChatComponent titleWrapped = NPCUtil.EMPTY_CHAT_COMPONENT;

	private BaseComponent title = NPCUtil.EMPTY_BASE_COMPONENT;

	private RenderType renderType = RenderType.INTEGER;

	public SharedObjective(SharedScoreboardManager manager, String name) {
		super(manager);
		this.name = name;
	}

	@Override
	protected void createSpawnPackets(List<PacketContainer> packets) {
		packets.add(ScoreboardProtocol.addObjectivePacket(this));
	}

	@Override
	protected void createDeletePackets(List<PacketContainer> packets) {
		packets.add(ScoreboardProtocol.removeObjectivePacket(this));
	}

	@Override
	protected void createUpdatePackets(List<PacketContainer> packets) {
		packets.add(ScoreboardProtocol.changeObjectivePacket(this));
	}

	private SharedObjective assignDisplaySlot(DisplaySlot[] slots, Player[] players) {
		for (DisplaySlot slot : slots) {
			if (slot != null) {
				this.manager.sendPacket(players, ScoreboardProtocol.setObjectiveDisplayPacket(this, slot));
			}
		}
		return this;
	}

	@Override
	public SharedObjective assignDisplaySlot(DisplaySlot slot, Player... players) {
		return this.assignDisplaySlot(slot, null, null, players);
	}

	@Override
	public SharedObjective assignDisplaySlot(DisplaySlot slot, DisplaySlot slot2, Player... players) {
		return this.assignDisplaySlot(slot, slot2, null, players);
	}

	@Override
	public SharedObjective assignDisplaySlot(DisplaySlot slot, DisplaySlot slot2, DisplaySlot slot3, Player... players) {
		return this.assignDisplaySlot(new DisplaySlot[] { slot, slot2, slot3 }, players);
	}

	@Override
	public SharedObjectiveExtendedScore createExtendedScore(BaseComponent displayName, int score) {
		SharedObjectiveExtendedScore scoreObjective = new SharedObjectiveExtendedScore(this, displayName, score);
		this.addEntry(scoreObjective);
		return scoreObjective;
	}

	@Override
	public SharedObjectiveScore createScore(Entity entity, int score) {
		return this.createScore(entity instanceof Player ? entity.getName() : entity.getUniqueId().toString(), score);
	}

	@Override
	public SharedObjectiveScore createScore(Entity entity) {
		return this.createScore(entity, 0);
	}

	@Override
	public SharedObjectiveScore createScore(String displayName, int score) {
		SharedObjectiveScore scoreObjective = new SharedObjectiveScore(this, displayName, score);
		this.addEntry(scoreObjective);
		return scoreObjective;
	}

	@Override
	public SharedObjectiveScore createScore(String displayName) {
		return this.createScore(displayName, 0);
	}

	@Override
	public SharedObjectiveScore getScore(String displayName) {
		return this.getEntries().stream().filter(entry -> entry.getName().equals(displayName)).findFirst().orElseGet(null);
	}

	@Override
	public SharedObjectiveScore getScore(int score) {
		return this.getEntries().stream().filter(entry -> entry.getScore() == score).findFirst().orElseGet(() -> null);
	}

	@Override
	public List<ScoreboardObjectiveScore> getScores(String name) {
		List<ScoreboardObjectiveScore> list = new ArrayList<>();
		for (SharedObjectiveScore entry : this.getEntries()) {
			if (entry.getName().equals(name)) {
				list.add(entry);
			}
		}
		return list;
	}

	@Override
	public List<ScoreboardObjectiveScore> getScores(int score) {
		List<ScoreboardObjectiveScore> list = new ArrayList<>();
		for (SharedObjectiveScore entry : this.getEntries()) {
			if (entry.getScore() == score) {
				list.add(entry);
			}
		}
		return list;
	}

	@Override
	public SharedObjective deleteScore(ScoreboardObjectiveScore score) {
		if (score instanceof SharedObjectiveScore realScore) {
			this.removeEntry(realScore);
			realScore.delete();
		}
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public BaseComponent getTitle() {
		return this.title;
	}

	@Override
	public SharedObjective setTitle(BaseComponent title) {
		this.titleWrapped = WrappedChatComponent.fromJson(ComponentSerializer.toString(title));
		this.title = title;
		this.setDirty();
		return this;
	}

	@Override
	public SharedObjective setTitle(String displayName) {
		return this.setTitle(new TextComponent(TextComponent.fromLegacyText(displayName)));
	}

	@Override
	public RenderType getRenderType() {
		return this.renderType;
	}

	@Override
	public SharedObjective setRenderType(RenderType renderType) {
		this.renderType = renderType;
		this.setDirty();
		return this;
	}
}