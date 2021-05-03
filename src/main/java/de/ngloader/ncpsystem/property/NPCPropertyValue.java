package de.ngloader.ncpsystem.property;

public enum NPCPropertyValue {

	NPC_LOOK_RANGE("npc_look_range"),
	NPC_LOOK_GLOBAL("npc_look_global");

	private final String key;

	private NPCPropertyValue(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}
}
