package de.ngloader.npcsystem.npc.entity;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCAnimal;
import de.ngloader.npcsystem.wrapper.EntityFlag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

public class NPCSheep extends NPCAnimal {

	public NPCSheep(NPCRegistry registry, Location location) {
		super(registry, 0.62d, location, EntityType.SHEEP);
	}

	public void setSheared(boolean sheared) {
		this.setFlag(EntityFlag.SHEEP_IS_SHEARED, sheared);
	}

	public void setColor(DyeColor color) {
		this.setMetadata(EntityFlag.SHEEP_COLOR_ID.getIndex(), Byte.class, color.getId());
	}
}