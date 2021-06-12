package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import net.minecraft.world.entity.EntityType;

public class NPCAgeableMob extends NPCMob {

	public NPCAgeableMob(NPCRegistry registry, double eyeHeight, Location location, EntityType<?> id) {
		super(registry, eyeHeight, location, id);
	}

	public void setIsBaby(boolean baby) {
		this.setMetadata(15, Boolean.class, baby);
	}
}