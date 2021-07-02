package de.ngloader.npcsystem.wrapper;

import java.util.Optional;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;

public class EntityData {

	public static final EntityDataAccessor<Byte> DATA_SHARED_FLAGS_ID;
	public static final EntityDataAccessor<Integer> DATA_AIR_SUPPLY_ID;
	public static final EntityDataAccessor<Optional<Component>> DATA_CUSTOM_NAME;
	public static final EntityDataAccessor<Boolean> DATA_CUSTOM_NAME_VISIBLE;
	public static final EntityDataAccessor<Boolean> DATA_SILENT;
	public static final EntityDataAccessor<Boolean> DATA_NO_GRAVITY;
	public static final EntityDataAccessor<Pose> DATA_POSE;
	public static final EntityDataAccessor<Integer> DATA_TICKS_FROZEN;

	static {
		DATA_SHARED_FLAGS_ID = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BYTE);
		DATA_AIR_SUPPLY_ID = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.INT);
		DATA_CUSTOM_NAME = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.OPTIONAL_COMPONENT);
		DATA_CUSTOM_NAME_VISIBLE = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BOOLEAN);
		DATA_SILENT = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BOOLEAN);
		DATA_NO_GRAVITY = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BOOLEAN);
		DATA_POSE = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.POSE);
		DATA_TICKS_FROZEN = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.INT);
	}
}
