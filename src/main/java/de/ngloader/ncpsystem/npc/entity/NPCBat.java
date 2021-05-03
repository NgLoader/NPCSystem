package de.ngloader.ncpsystem.npc.entity;

import org.bukkit.Location;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.npc.type.NPCAmbientCreature;
import de.ngloader.ncpsystem.wrapper.EntityFlag;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;

public class NPCBat extends NPCAmbientCreature {

	public NPCBat(NPCRegistry registry, Location location) {
		super(registry, 0, location, WrappedEntityId.BAT);
	}

	public void setIsHanging(boolean hanging) {
		this.setFlag(EntityFlag.BAT_IS_HANGING, hanging);
	}
}