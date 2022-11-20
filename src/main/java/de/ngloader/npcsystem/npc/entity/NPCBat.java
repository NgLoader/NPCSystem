package de.ngloader.npcsystem.npc.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCAmbientCreature;
import de.ngloader.npcsystem.wrapper.EntityFlag;

public class NPCBat extends NPCAmbientCreature {

	public NPCBat(NPCRegistry registry, Location location) {
		super(registry, 0, location, EntityType.BAT);
	}

	public void setIsHanging(boolean hanging) {
		this.setFlag(EntityFlag.BAT_IS_HANGING, hanging);
	}
}