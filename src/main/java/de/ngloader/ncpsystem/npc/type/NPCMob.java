package de.ngloader.ncpsystem.npc.type;

import org.bukkit.Location;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.npc.NPCSpawnEntityLiving;
import de.ngloader.ncpsystem.wrapper.EntityFlag;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;

public class NPCMob extends NPCSpawnEntityLiving {

	public NPCMob(NPCRegistry registry, double eyeHeight, Location location, WrappedEntityId id) {
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