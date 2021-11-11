package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.wrapper.EntityIndex;
import net.minecraft.world.entity.EntityType;

public class NPCAgeableMob extends NPCMob {

	public NPCAgeableMob(NPCRegistry registry, double eyeHeight, Location location, EntityType<?> id) {
		super(registry, eyeHeight, location, id);
	}

	public void setIsBaby(boolean baby) {
		this.setMetadata(EntityIndex.AGEABLE_MOB_IS_BABY_16, Boolean.class, baby);
	}
}