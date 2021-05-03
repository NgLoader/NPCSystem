package de.ngloader.npcsystem.npc.entity;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCAnimal;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import de.ngloader.npcsystem.wrapper.WrappedEntityId;
import de.ngloader.npcsystem.wrapper.WrappedEnumColor;

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