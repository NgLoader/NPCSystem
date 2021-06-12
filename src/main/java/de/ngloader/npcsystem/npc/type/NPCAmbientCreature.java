package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import net.minecraft.world.entity.EntityType;

public class NPCAmbientCreature extends NPCMob {

	public NPCAmbientCreature(NPCRegistry registry, double eyeHeight, Location location, EntityType<?> id) {
		super(registry, eyeHeight, location, id);
	}
}