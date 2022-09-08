package de.ngloader.npcsystem.wrapper;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import net.minecraft.world.entity.vehicle.MinecartFurnace;

public enum EntityIndex {

	ENTITY_FLAG_0(Entity.class),
	ENTITY_AIR_TICKS_1(Entity.class),
	ENTITY_CUSTOM_NAME_2(Entity.class),
	ENTITY_IS_CUSTOM_NAME_VISIBLE_3(Entity.class),
	ENTITY_IS_SILENT_4(Entity.class),
	ENTITY_HAS_NO_GRAVITY_5(Entity.class),
	ENTITY_POSE_6(Entity.class),
	ENTITY_TICKS_FROZEN_IN_POWDERED_SNOW_7(Entity.class),

	THROWN_EGG_ITEM_8(ThrownEgg.class),

	THROWN_ENDER_PEARL_8(ThrownEnderpearl.class),

	THROWN_EXPERIENCE_BOTTLE_8(ThrownExperienceBottle.class),

	THROWN_POTION_WHICH_8(ThrownPotion.class),

	SNOWBALL_ITEM_8(Snowball.class),

	EYE_OF_ENDER_ITEM_8(EyeOfEnder.class),

	FALLING_BLOCK_SPAWN_POSITION_8(FallingBlockEntity.class),

	AREA_EFFECT_CLOUD_RADIUS_8(AreaEffectCloud.class),
	AREA_EFFECT_CLOUD_COLOR_9(AreaEffectCloud.class),
	AREA_EFFECT_CLOUD_IGNORE_RADIUS_10(AreaEffectCloud.class),
	AREA_EFFECT_CLOUD_PARTICLE_11(AreaEffectCloud.class),

	FISHING_HOOK_HOOKED_ENTITY_ID_8(FishingHook.class),
	FISHING_HOOK_IS_CATCHABLE_9(FishingHook.class),

	ABSTRACT_ARROW_FLAG_8(AbstractArrow.class),
	ABSTRACT_ARROW_PIERCING_LEVEL_9(AbstractArrow.class),

	ARROW_COLOR_10(Arrow.class),

	THROWN_TRIDENT_LOYALTY_LEVEL_10(ThrownTrident.class),
	THROWN_TRIDENT_HAS_ENCHANTMENT_GLINT_11(ThrownTrident.class),

	BOAT_TIME_SINCE_LAST_HIT_8(Boat.class),
	BOAT_FORWARD_DIRECTION_9(Boat.class),
	BOAT_DAMAGE_TAKEN_10(Boat.class),
	BOAT_TYPE_11(Boat.class),
	BOAT_IS_LEFT_PADDLE_TURNING_12(Boat.class),
	BOAT_IS_RIGHT_PADDLE_TURNING_13(Boat.class),
	BOAT_SPLASH_TIMER_14(Boat.class),

	END_CRYSTAL_BEAM_TARGET_8(EndCrystal.class),
	END_CRYSTAL_SHOW_BOTTOM_9(EndCrystal.class),

	SMALL_FIREBALL_ITEM_8(SmallFireball.class),

	FIREBALL_ITEM_8(Fireball.class),

	WITHER_SKULL_IS_INVULNERABLE_8(WitherSkull.class),

	FIREWORK_ROCKET_ENTITY_FIREWORK_INFO_8(FireworkRocketEntity.class),
	FIREWORK_ROCKET_ENTITY_ENTITY_ID_9(FireworkRocketEntity.class),
	FIREWORK_ROCKET_ENTITY_IS_SHOT_AT_ANGLE_10(FireworkRocketEntity.class),

	ITEM_FRAME_ITEM_8(ItemFrame.class),
	ITEM_FRAME_ROTATION_9(ItemFrame.class),

	PAINTING_VARIANT_8(Painting.class),

	ITEM_ENTITY_ITEM_8(ItemEntity.class),

	LIVING_ENTITY_FLAG_8(LivingEntity.class),
	LIVING_ENTITY_HEALTH_9(LivingEntity.class),
	LIVING_ENTITY_POTION_EFFECT_COLOR_10(LivingEntity.class),
	LIVING_ENTITY_IS_POTION_EFFECT_AMBIENT_11(LivingEntity.class),
	LIVING_ENTITY_NUMBER_OF_ARROWS_IN_ENTITY_12(LivingEntity.class),
	LIVING_ENTITY_NUMBER_OF_BEE_STRINGERS_IN_ENTITY_13(LivingEntity.class),
	LIVING_ENTITY_LOCATION_OF_THE_BED_14(LivingEntity.class),

	PLAYER_ADDITIONAL_HEARTS_15(Player.class),
	PLAYER_SCORE_16(Player.class),
	PLAYER_SKIN_PARTS_17(Player.class),
	PLAYER_MAIN_HAND_18(Player.class),
	PLAYER_LEFT_SHOULDER_ENTITY_DATA_19(Player.class),
	PLAYER_RIGHT_SHOULDER_ENTITY_DATA_20(Player.class),

	ARMORSTAND_FLAG_15(ArmorStand.class),
	ARMORSTAND_HEAD_ROTATION_16(ArmorStand.class),
	ARMORSTAND_BODY_ROTATION_17(ArmorStand.class),
	ARMORSTAND_LEFT_ARM_ROTATION_18(ArmorStand.class),
	ARMORSTAND_RIGHT_ARM_ROTATION_19(ArmorStand.class),
	ARMORSTAND_LEFT_LEG_ROTATION_20(ArmorStand.class),
	ARMORSTAND_RIGHT_LEG_ROTATION_21(ArmorStand.class),

	MOB_FLAG_15(Mob.class),

	BAT_FLAG_16(Bat.class),

	DOLPHIN_TREASURE_POSITION_16(Dolphin.class),
	DOLPHIN_CAN_FIND_TREASURE_17(Dolphin.class),
	DOLPHIN_HAS_FISH_18(Dolphin.class),

	ABSTRACT_FISH_FROM_BUCKET_16(AbstractFish.class),

	PUFFER_FISH_PUFF_STATE_17(Pufferfish.class),

	TROPICAL_FISH_VARIANT_17(TropicalFish.class),

	AGEABLE_MOB_IS_BABY_16(AgeableMob.class),

	ABSTRACT_HORSE_FLAG_17(AbstractHorse.class),
	ABSTRACT_HORSE_OWNER_18(AbstractHorse.class),

	HORSE_VARIANT_19(Horse.class),

	CHESTED_HORSE_HAS_CHEST_19(AbstractChestedHorse.class),

	LLAMA_STRENGTH_20(Llama.class),
	LLAMA_CARPET_COLOR_21(Llama.class),
	LLAMA_VARIANT_22(Llama.class),

	AXOLOTL_VARIANT_17(Axolotl.class),
	AXOLOTL_IF_IT_CURRENTLY_PLAYING_DEAD_18(Axolotl.class),
	AXOLOTL_IF_IT_WAS_SPANED_FROM_A_BUCKET_19(Axolotl.class),

	BEE_FLAG_17(Bee.class),
	BEE_ANGER_TIME_18(Bee.class),

	FOX_TYPE_17(Fox.class),
	FOX_FLAG_18(Fox.class),
	FOX_FIRST_UUID_19(Fox.class),
	FOX_SECOND_UUID_20(Fox.class),

	FROG_VARIANT_17(Frog.class),
	FROG_TONGUE_TARGET_18(Frog.class),

	OCELOT_IS_TRUSTING_17(Ocelot.class),

	PANDA_BREED_TIMER_17(Panda.class),
	PANDA_SNEEZE_TIMER_18(Panda.class),
	PANDA_EAT_TIMER_19(Panda.class),
	PANDA_MAIN_GENE_20(Panda.class),
	PANDA_HIDDEN_GENE_21(Panda.class),
	PANDA_FLAG_22(Panda.class),

	PIG_HAS_SADDLE_17(Pig.class),
	PIG_TOTAL_TIME_TO_BOOST_18(Pig.class),

	RABBIT_TYPE_17(Rabbit.class),

	TURTLE_HOME_POS_17(Turtle.class),
	TURTLE_HAS_EGG_18(Turtle.class),
	TURTLE_IS_LAYING_EGG_19(Turtle.class),
	TURTLE_TRAVEL_POS_20(Turtle.class),
	TURTLE_IS_GOING_HOME_21(Turtle.class),
	TURTLE_IS_TRAVELING_22(Turtle.class),

	POLAR_BEAR_IS_STANDING_UP_17(PolarBear.class),

	HOGLIN_IS_IMMMUNE_17(Hoglin.class),

	MOOSHROOM_VARIANT_17(MushroomCow.class),

	SHEEP_FLAG_17(Sheep.class),

	STRIDER_TOTAL_TIME_TO_BOOST_17(Strider.class),
	STRIDER_TOTAL_IS_SHAKING_18(Strider.class),
	STRIDER_TOTAL_HAS_SADDLE_19(Strider.class),

	TAMEABLE_ANIMAL_FLAG_17(TamableAnimal.class),
	TAMEABLE_ANIMAL_OWNER_18(TamableAnimal.class),

	CAT_TYPE_19(Cat.class),
	CAT_IS_LYING_20(Cat.class),
	CAT_IS_RELAXED_21(Cat.class),
	CAT_COLLAR_COLOR_22(Cat.class),

	WOLF_IS_BEGGING_19(Wolf.class),
	WOLF_COLLAR_COLOR_20(Wolf.class),
	WOLF_ANGER_TIME_21(Wolf.class),

	PARROT_VARIANT_19(Parrot.class),

	ABSTRACT_VILLAGER_VILLGER_DATA_17(AbstractVillager.class),

	VILLAGER_DATA_18(Villager.class),

	IRON_GOLEM_FLAG_16(IronGolem.class),

	SNOW_GOLEM_FLAG_16(SnowGolem.class),

	SHULKER_DIRECTION_16(Shulker.class),
	SHULKER_ATTACHMENT_POSITION_17(Shulker.class),
	SHULKER_SHIELD_HEIGHT_18(Shulker.class),
	SHULKER_COLOR_19(Shulker.class),

	BASE_PIGLIN_IS_IMMUNE_16(AbstractPiglin.class),

	PIGLIN_IS_BABY_17(Piglin.class),
	PIGLIN_IS_CHARGING_CROSSBOW_18(Piglin.class),
	PIGLIN_IS_DANCING_19(Piglin.class),

	BLAZE_FLAG_16(Blaze.class),

	CREEPER_STATE_16(Creeper.class),
	CREEPER_IS_CHARGED_17(Creeper.class),
	CREEPER_IS_IGNITED_18(Creeper.class),

	GOAT_IS_SCREAMING_GOAT_17(Goat.class),
	GOAT_HAS_LEFT_HORN_18(Goat.class),
	GOAT_HAS_RIGHT_HORN_19(Goat.class),

	GUARDIAN_IS_RETRACTING_SPIKES_16(Guardian.class),
	GUARDIAN_TARGET_ENTITY_ID_17(Guardian.class),

	RAIDER_IS_CELEBRATING_16(Raider.class),

	PILLAGER_IS_CHARGING_17(Pillager.class),

	SPELLCASTER_ILLAGER_SPELL_17(SpellcasterIllager.class),

	WITCH_IS_DRINGING_POTION_17(Witch.class),

	VEX_FLAG_16(Vex.class),

	SPIDER_FLAG_16(Spider.class),

	WARDEN_ANGER_LEVEL_16(Warden.class),

	WITHER_CENTER_HEADS_TARGET_ID_16(WitherBoss.class),
	WITHER_LEFT_HEADS_TARGET_ID_17(WitherBoss.class),
	WITHER_RIGHT_HEADS_TARGET_ID_18(WitherBoss.class),
	WITHER_INVULNERABLE_19(WitherBoss.class),

	ZOGLIN_IS_BABY_16(Zoglin.class),

	ZOMBIE_IS_BABY_16(Zombie.class),
	ZOMBIE_UNUSED_17(Zombie.class),
	ZOMBIE_IS_BECOMING_A_DROWNED_18(Zombie.class),

	ZOMBIE_VILLAGER_IS_CONVERTING_19(ZombieVillager.class),
	ZOMBIE_VILLAGER_DATA_20(ZombieVillager.class),

	ENDERMAN_CARRIED_BLOCK_16(EnderMan.class),
	ENDERMAN_IS_SCREAMING_17(EnderMan.class),
	ENDERMAN_IS_STARING_18(EnderMan.class),

	ENDER_DRAGON_PHASE_16(EnderDragon.class),

	GHAST_IS_ATTACKING_16(Ghast.class),

	PHANTOM_SIZE_16(Phantom.class),

	SLIME_SIZE_16(Slime.class),

	ABSTRACT_MINECART_SHAKING_POWER_8(AbstractMinecart.class),
	ABSTRACT_MINECART_SHAKING_DIRECTION_9(AbstractMinecart.class),
	ABSTRACT_MINECART_SHAKING_MULTIPLIER_10(AbstractMinecart.class),
	ABSTRACT_MINECART_CUSTOM_BOCK_ID_11(AbstractMinecart.class),
	ABSTRACT_MINECART_CUSTOM_BLOCK_Y_POSITION_12(AbstractMinecart.class),
	ABSTRACT_MINECART_SHOW_CUSTOM_BLOCK_13(AbstractMinecart.class),

	MINECRAFT_FURNACE_HAS_FUEL_14(MinecartFurnace.class),

	MINECART_COMMAND_BLOCK_COMMAND_14(MinecartCommandBlock.class),
	MINECART_COMMAND_BLOCK_LAST_OUTPUT_15(MinecartCommandBlock.class),

	PRIMED_TNT_FUSE_TIME_8(PrimedTnt.class);

	private final int index;

	private EntityIndex(Class<?> entity) {
		int index = 0;
		if (EntityDatawatcher.ENTITY_TO_INDEX.containsKey(entity)) {
			index = EntityDatawatcher.ENTITY_TO_INDEX.get(entity) + 1;
		} else {
			Class<?> parent = entity;
			while (parent != Entity.class) {
				parent = parent.getSuperclass();
				if (EntityDatawatcher.ENTITY_TO_INDEX.containsKey(parent)) {
					index = EntityDatawatcher.ENTITY_TO_INDEX.get(parent) + 1;
					break;
				}
			}
		}
		EntityDatawatcher.ENTITY_TO_INDEX.put(entity, index);
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}
}
