package de.ngloader.ncpsystem.npc.entity;

import org.bukkit.Location;

import com.comphenix.protocol.wrappers.Vector3F;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.npc.type.NPCWaterAnimal;
import de.ngloader.ncpsystem.wrapper.WrappedEntityId;

public class NPCDolphin extends NPCWaterAnimal {

	private static final Serializer VECTOR3F_SERIALIZER = WrappedDataWatcher.Registry.getVectorSerializer();

	public NPCDolphin(NPCRegistry registry, Location location) {
		super(registry, 0, location, WrappedEntityId.DOLPHIN);
	}

	public void setTreasurePosition(Vector3F position) {
		this.setMetadata(15, VECTOR3F_SERIALIZER, position);
	}

	public void setCanFindTreasure(boolean canFind) {
		this.setMetadata(16, Boolean.class, canFind);
	}

	public void setHasFish(boolean hasFish) {
		this.setMetadata(17, Boolean.class, hasFish);
	}
}