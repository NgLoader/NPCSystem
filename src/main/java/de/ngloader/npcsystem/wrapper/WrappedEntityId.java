package de.ngloader.npcsystem.wrapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.ngloader.npcsystem.util.MCReflectionUtil;

public enum WrappedEntityId {

    AREA_EFFECT_CLOUD,
    ARMOR_STAND,
    ARROW,
    BAT,
    BLAZE,
    BOAT,
    CAT,
    CAVE_SPIDER,
    CHICKEN,
    COD,
    COW,
    CREEPER,
    DOLPHIN,
    DONKEY,
    DRAGON_FIREBALL,
    DROWNED,
    ELDER_GUARDIAN,
    END_CRYSTAL,
    ENDER_DRAGON,
    ENDERMAN,
    ENDERMITE,
    EVOKER,
    EVOKER_FANGS,
    EXPERIENCE_ORB,
    EYE_OF_ENDER,
    FALLING_BLOCK,
    FIREWORK_ROCKET,
    FOX,
    GHAST,
    GIANT,
    GUARDIAN,
    HORSE,
    HUSK,
    ILLUSIONER,
    IRON_GOLEM,
    ITEM,
    ITEM_FRAME,
    FIREBALL,
    LEASH_KNOT,
    LIGHTNING_BOLT,
    LLAMA,
    LLAMA_SPIT,
    MAGMA_CUBE,
    MINECART,
    CHEST_MINECART,
    COMMAND_BLOCK_MINECART,
    FURNACE_MINECART,
    HOPPER_MINECART,
    SPAWNER_MINECART,
    TNT_MINECART,
    MULE,
    MOOSHROOM,
    OCELOT,
    PAINTING,
    PANDA,
    PARROT,
    PHANTOM,
    PIG,
    PILLAGER,
    POLAR_BEAR,
    TNT,
    PUFFERFISH,
    RABBIT,
    RAVAGER,
    SALMON,
    SHEEP,
    SHULKER,
    SHULKER_BULLET,
    SILVERFISH,
    SKELETON,
    SKELETON_HORSE,
    SLIME,
    SMALL_FIREBALL,
    SNOW_GOLEM,
    SNOWBALL,
    SPECTRAL_ARROW,
    SPIDER,
    SQUID,
    STRAY,
    EGG,
    ENDER_PEARL,
    EXPERIENCE_BOTTLE,
    POTION,
    TRIDENT,
    TRADER_LLAMA,
    TROPICAL_FISH,
    TURTLE,
    VEX,
    VILLAGER,
    VINDICATOR,
    WANDERING_TRADER,
    WITCH,
    WITHER,
    WITHER_SKELETON,
    WITHER_SKULL,
    WOLF,
    ZOMBIE,
    ZOMBIE_HORSE,
    ZOMBIE_VILLAGER,
    PLAYER,
    FISHING_BOBBER;

    private int id = -1;

    private WrappedEntityId() {
		Class<?> iRegestryClass = MCReflectionUtil.getMinecraftServerClass("IRegistry");
		Class<?> entityTypesClass = MCReflectionUtil.getMinecraftServerClass("EntityTypes");
		try {
			Field field = iRegestryClass.getField("ENTITY_TYPE");
			Object entityTypes = field.get(null);
			for (Method method : entityTypes.getClass().getDeclaredMethods()) {
				if (method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(entityTypesClass) && method.getReturnType() == int.class) {
					Method entityTypesValuesMethod = entityTypesClass.getDeclaredMethod("values");
					Object entityTypesArray = entityTypesValuesMethod.invoke(null);
					for (Enum<?> obj : (Enum[]) entityTypesArray) {
						if (obj.name().equals(this.name())) {
							this.id = (int) method.invoke(entityTypesArray, obj);
							break;
						}
					}
					break;
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
    }

    public int getId() {
        return this.id;
    }
}
