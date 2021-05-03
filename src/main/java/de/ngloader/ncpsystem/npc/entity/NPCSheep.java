package de.ngloader.ncpsystem.npc.entity;

import org.bukkit.Location;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.npc.type.NPCAnimal;
import de.ngloader.ncpsystem.wrapper.EntityFlag;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;
import de.ngloader.ncpsystem.wrapper.WrappedEnumColor;

public class NPCSheep extends NPCAnimal {

	public NPCSheep(NPCRegistry registry, Location location) {
		super(registry, 0.62d, location, WrappedEntityId.SHEEP);
	}

	public void setSheared(boolean sheared) {
		this.setFlag(EntityFlag.SHEEP_IS_SHEARED, sheared);
	}

	public void setColor(WrappedEnumColor color) {
		this.setMetadata(EntityFlag.SHEEP_COLOR_ID.getIndex(), Byte.class, color.getColorIndex());
	}
}