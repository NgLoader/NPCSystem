package de.ngloader.npcsystem.scoreboard;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.EnumWrappers.ScoreboardAction;

class ScoreboardProtocol {

	//	ClientboundSetDisplayObjectivePacket
	//	ClientboundSetObjectivePacket
	//	ClientboundSetPlayerTeamPacket
	//	ClientboundSetScorePacket

	private static final ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();
	private static final Class<?> NMS_ENUM_CHAT_COLOR = MinecraftReflection.getMinecraftClass("EnumChatFormat");

	private static int createTeamOptions(SharedTeam team) {
		int mask = 0;
		if (team.isAllowFirendlyFire()) {
			mask |= 1;
		}
		if (team.isCanSeeFriendlyInvisible()) {
			mask |= 2;
		}
		return mask;
	}

	private static InternalStructure createTeamData(PacketContainer packet, SharedTeam team) {
		Optional<InternalStructure> optional = packet.getOptionalStructures().read(0);
		if (optional.isPresent()) {
			InternalStructure structure = optional.get();
			structure.getChatComponents().write(0, team.displayNameWrapped);
			structure.getChatComponents().write(1, team.prefixWrapped);
			structure.getChatComponents().write(2, team.suffixWrapped);
			structure.getStrings().write(0, team.getNameTagVisiblity().getVisibilityName());
			structure.getStrings().write(1, team.getCollisionRule().getCollisionName());
			structure.getEnumModifier(ChatColor.class, NMS_ENUM_CHAT_COLOR).write(0, team.getColor());
			structure.getIntegers().write(0, createTeamOptions(team));
			return structure;
		}
		return null;
	}

	private static PacketContainer buildTeamPacket(SharedTeam team, TeamMethod method, Set<String> entrys) {
		PacketContainer packet = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);
		packet.getStrings().write(0, team.getName());
		packet.getIntegers().write(0, method.ordinal());
		if (method.hasParameters()) {
			packet.getOptionalStructures().write(0, Optional.ofNullable(createTeamData(packet, team)));
		}
		if (method.hasPlayerList()) {
			packet.getSpecificModifier(Collection.class).write(0, entrys != null ? entrys : team.getEntrys());
		}
		return packet;
	}

	private static PacketContainer buildObjectivePacket(SharedObjective objective, ObjectiveMethod method) {
		PacketContainer packet = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.SCOREBOARD_OBJECTIVE);
		packet.getStrings().write(0, objective.getName());
		packet.getIntegers().write(0, method.ordinal());
		if (method.hasParameters()) {
			packet.getChatComponents().write(0, objective.titleWrapped);
			packet.getEnumModifier(RenderType.class, 2).write(0, objective.getRenderType());
		}
		return packet;
	}

	private static PacketContainer buildObjectiveScorePacket(SharedObjectiveScore score, ScoreboardAction action) {
		PacketContainer packet = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.SCOREBOARD_SCORE);
		packet.getStrings().write(0, score.getName());
		packet.getScoreboardActions().write(0, action);
		packet.getStrings().write(1, score.getObjective().getName());
		if (action != ScoreboardAction.REMOVE) {
			packet.getIntegers().write(0, score.getScore());
		}
		return packet;
	}

	public static PacketContainer addTeamPacket(SharedTeam team) {
		return buildTeamPacket(team, TeamMethod.ADD, null);
	}

	public static PacketContainer changeTeamPacket(SharedTeam team) {
		return buildTeamPacket(team, TeamMethod.CHANGE, null);
	}

	public static PacketContainer joinTeamPacket(SharedTeam team, Set<String> entrys) {
		return buildTeamPacket(team, TeamMethod.JOIN, entrys);
	}

	public static PacketContainer leaveTeamPacket(SharedTeam team, Set<String> entrys) {
		return buildTeamPacket(team, TeamMethod.LEAVE, entrys);
	}

	public static PacketContainer removeTeamPacket(SharedTeam team) {
		return buildTeamPacket(team, TeamMethod.REMOVE, null);
	}

	public static PacketContainer addObjectivePacket(SharedObjective objective) {
		return buildObjectivePacket(objective, ObjectiveMethod.ADD);
	}

	public static PacketContainer changeObjectivePacket(SharedObjective objective) {
		return buildObjectivePacket(objective, ObjectiveMethod.CHANGE);
	}

	public static PacketContainer removeObjectivePacket(SharedObjective objective) {
		return buildObjectivePacket(objective, ObjectiveMethod.REMOVE);
	}

	public static PacketContainer changeObjectiveScorePacket(SharedObjectiveScore score) {
		return buildObjectiveScorePacket(score, ScoreboardAction.CHANGE);
	}

	public static PacketContainer removeObjectiveScorePacket(SharedObjectiveScore score) {
		return buildObjectiveScorePacket(score, ScoreboardAction.REMOVE);
	}

	public static PacketContainer setObjectiveDisplayPacket(SharedObjective objective, DisplaySlot slot) {
		PacketContainer packet = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.SCOREBOARD_DISPLAY_OBJECTIVE);
		packet.getStrings().write(0, objective == null ? "" : objective.getName());
		packet.getIntegers().write(0, switch (slot) {
		case PLAYER_LIST -> 0;
		case SIDEBAR -> 1;
		case BELOW_NAME -> 2;
		});
		return packet;
	}

	private enum ObjectiveMethod {
		ADD,
		REMOVE,
		CHANGE;

		public boolean hasParameters() {
			return switch (this.ordinal()) {
			case 0, 2 -> true;
			default -> false;
			};
		}
	}

	private enum TeamMethod {
		ADD,
		REMOVE,
		CHANGE,
		JOIN,
		LEAVE;

		public boolean hasPlayerList() {
			return switch (this.ordinal()) {
			case 0, 3, 4 -> true;
			default -> false;
			};
		}

		public boolean hasParameters() {
			return switch (this.ordinal()) {
			case 0, 2 -> true;
			default -> false;
			};
		}
	}
}
