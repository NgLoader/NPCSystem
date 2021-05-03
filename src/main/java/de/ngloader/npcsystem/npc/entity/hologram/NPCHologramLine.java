package de.ngloader.npcsystem.npc.entity.hologram;

import de.ngloader.npcsystem.npc.entity.NPCArmorStand;

public class NPCHologramLine {

	private final NPCHologram hologram;
	private final NPCArmorStand npc;

	private double space;
	private String text;

	public NPCHologramLine(NPCHologram hologram, NPCArmorStand npc, double space, String text) {
		this.hologram = hologram;
		this.npc = npc;
		this.space = space;
		this.text = text;
	}

	public double getSpace() {
		return this.space;
	}

	public void setSpace(double space) {
		this.space = space;
		this.hologram.rescale();
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;

		this.npc.setCustomName(this.text);
		this.npc.updateDataWatcher();
	}

	public NPCArmorStand getNPC() {
		return this.npc;
	}
}
