package de.ngloader.npcsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.NPCSpawnEntity;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import net.minecraft.world.entity.EntityType;

public class NPCMob extends NPCSpawnEntity {

	public NPCMob(NPCRegistry registry, double eyeHeight, Location location, EntityType<?> id) {
		super(registry, eyeHeight, location, id);
	}

	public void setNoAI(boolean noAI) {
		this.setFlag(EntityFlag.MOB_NO_AI, noAI);
	}

	public void setIsLeftHanded(boolean leftHanded) {
		this.setFlag(EntityFlag.MOB_IS_LEFT_HANDED, leftHanded);
	}

	public void setIsAgressive(boolean agressive) {
		this.setFlag(EntityFlag.MOB_IS_AGRESSIVE, agressive);
	}
}