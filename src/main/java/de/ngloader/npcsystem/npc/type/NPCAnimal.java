package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import net.minecraft.world.entity.EntityType;

public class NPCAnimal extends NPCAgeableMob {

	public NPCAnimal(NPCRegistry registry, double eyeHeight, Location location, EntityType<?> id) {
		super(registry, eyeHeight, location, id);
	}
}