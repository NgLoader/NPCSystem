package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import de.ngloader.npcsystem.NPCRegistry;

public class NPCWaterAnimal extends NPCPathfinderMob {

	public NPCWaterAnimal(NPCRegistry registry, double eyeHeight, Location location, EntityType id) {
		super(registry, eyeHeight, location, id);
	}
}