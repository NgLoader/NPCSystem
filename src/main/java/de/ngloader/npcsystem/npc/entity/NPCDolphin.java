package de.ngloader.npcsystem.npc.entity;

import org.bukkit.Location;

import com.comphenix.protocol.wrappers.Vector3F;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.type.NPCWaterAnimal;
import de.ngloader.npcsystem.wrapper.EntityIndex;
import net.minecraft.world.entity.EntityType;

public class NPCDolphin extends NPCWaterAnimal {

	private static final Serializer VECTOR3F_SERIALIZER = WrappedDataWatcher.Registry.getVectorSerializer();

	public NPCDolphin(NPCRegistry registry, Location location) {
		super(registry, 0, location, EntityType.DOLPHIN);
	}

	public void setTreasurePosition(Vector3F position) {
		this.setMetadata(EntityIndex.DOLPHIN_TREASURE_POSITION_16, VECTOR3F_SERIALIZER, position);
	}

	public void setCanFindTreasure(boolean canFind) {
		this.setMetadata(EntityIndex.DOLPHIN_CAN_FIND_TREASURE_17, Boolean.class, canFind);
	}

	public void setHasFish(boolean hasFish) {
		this.setMetadata(EntityIndex.DOLPHIN_HAS_FISH_18, Boolean.class, hasFish);
	}
}