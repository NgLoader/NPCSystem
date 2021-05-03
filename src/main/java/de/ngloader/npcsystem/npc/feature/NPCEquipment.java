package de.ngloader.npcsystem.npc.feature;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BukkitConverters;
import com.comphenix.protocol.wrappers.EnumWrappers;

import de.ngloader.npcsystem.NPC;
import de.ngloader.npcsystem.util.ReflectionUtil;

public class NPCEquipment extends NPCFeature {

	private static final Constructor<?> CLASS_PAIR = ReflectionUtil.getConstructor(ReflectionUtil.getClass("com.mojang.datafixers.util.Pair"), Object.class, Object.class);
	private static final ItemStack ITEM_AIR = new ItemStack(Material.AIR);

	private final Map<EnumWrappers.ItemSlot, ItemStack> equipment = new HashMap<>();

	public NPCEquipment(NPC npc, Consumer<PacketContainer> sendPacket) {
		super(npc, sendPacket);
	}

	private PacketContainer createPacket() {
		PacketContainer packet = this.protocolManager.createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT);
		packet.getIntegers().write(0, this.npc.getEntityId());

		List<Object> list = new ArrayList<>();
		for (Entry<EnumWrappers.ItemSlot, ItemStack> entry : this.equipment.entrySet()) {
			Object genericItemSlot = EnumWrappers.getItemSlotConverter().getGeneric(entry.getKey());
			Object genericItemStack = BukkitConverters.getItemStackConverter().getGeneric(entry.getValue());

			try {
				list.add(CLASS_PAIR.newInstance(genericItemSlot, genericItemStack));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		packet.getModifier().write(1, list);
		return packet;
	}

	public void addPackets(List<PacketContainer> packets) {
		if (!this.equipment.isEmpty()) {
			packets.add(this.createPacket());
		}
	}

	public void updateEquipment() {
		this.sendPacket.accept(this.createPacket());
	}

	public void clear() {
		this.setHelpmet(ITEM_AIR);
		this.setChestplate(ITEM_AIR);
		this.setLeggings(ITEM_AIR);
		this.setBoots(ITEM_AIR);
		this.setMainHand(ITEM_AIR);
		this.setOffHand(ITEM_AIR);
	}

	public void setHelpmet(ItemStack item) {
		this.equipment.put(EnumWrappers.ItemSlot.HEAD, item);
	}

	public void setChestplate(ItemStack item) {
		this.equipment.put(EnumWrappers.ItemSlot.CHEST, item);
	}

	public void setLeggings(ItemStack item) {
		this.equipment.put(EnumWrappers.ItemSlot.LEGS, item);
	}

	public void setBoots(ItemStack item) {
		this.equipment.put(EnumWrappers.ItemSlot.FEET, item);
	}

	public void setMainHand(ItemStack item) {
		this.equipment.put(EnumWrappers.ItemSlot.MAINHAND, item);
	}

	public void setOffHand(ItemStack item) {
		this.equipment.put(EnumWrappers.ItemSlot.OFFHAND, item);
	}

	public void set(EnumWrappers.ItemSlot slot, ItemStack item) {
		this.equipment.put(slot, item);
	}
}