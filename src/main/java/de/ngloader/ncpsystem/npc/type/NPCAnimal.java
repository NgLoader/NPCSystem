package de.ngloader.ncpsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;

public class NPCAnimal extends NPCAgeableMob {

	public NPCAnimal(NPCRegistry registry, double eyeHeight, Location location, WrappedEntityId id) {
		super(registry, eyeHeight, location, id);
	}
}