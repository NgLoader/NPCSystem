package de.ngloader.npcsystem.npc.entity.hologram;

import de.ngloader.npcsystem.npc.entity.NPCArmorStand;
import de.ngloader.npcsystem.util.NPCUtil;
import net.md_5.bungee.api.chat.BaseComponent;

public class NPCHologramLine {

	private final NPCHologram hologram;
	private final NPCArmorStand npc;

	private double space;
	private BaseComponent text;

	public NPCHologramLine(NPCHologram hologram, NPCArmorStand npc, double space, BaseComponent... text) {
		this.hologram = hologram;
		this.npc = npc;
		this.space = space;
		this.text = NPCUtil.mergeBaseComponent(text);
	}

	public double getSpace() {
		return this.space;
	}

	public NPCHologramLine setSpace(double space) {
		this.space = space;
		this.hologram.rescale();
		return this;
	}

	public BaseComponent getText() {
		return this.text;
	}

	public NPCHologramLine setText(BaseComponent... text) {
		this.text = NPCUtil.mergeBaseComponent(text);

		this.npc.setCustomName(this.text);
		this.npc.updateDataWatcher();
		return this;
	}

	public NPCArmorStand getNPC() {
		return this.npc;
	}
}
