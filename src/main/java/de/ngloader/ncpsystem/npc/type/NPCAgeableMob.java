package de.ngloader.ncpsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;

public class NPCAgeableMob extends NPCMob {

	public NPCAgeableMob(NPCRegistry registry, double eyeHeight, Location location, WrappedEntityId id) {
		super(registry, eyeHeight, location, id);
	}

	public void setIsBaby(boolean baby) {
		this.setMetadata(15, Boolean.class, baby);
	}
}