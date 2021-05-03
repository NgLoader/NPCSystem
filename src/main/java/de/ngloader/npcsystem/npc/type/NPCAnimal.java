package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.wrapper.WrappedEntityId;

public class NPCAnimal extends NPCAgeableMob {

	public NPCAnimal(NPCRegistry registry, double eyeHeight, Location location, WrappedEntityId id) {
		super(registry, eyeHeight, location, id);
	}
}