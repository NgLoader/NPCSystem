package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import de.ngloader.npcsystem.NPCRegistry;

public class NPCPathfinderMob extends NPCMob {

	public NPCPathfinderMob(NPCRegistry registry, double eyeHeight, Location location, EntityType id) {
		super(registry, eyeHeight, location, id);
	}
}