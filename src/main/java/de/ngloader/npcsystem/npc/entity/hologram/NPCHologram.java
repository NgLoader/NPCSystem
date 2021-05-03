package de.ngloader.npcsystem.npc.entity.hologram;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.npc.entity.NPCArmorStand;

public class NPCHologram {

	public static final double DEFAULT_SPACE = 0.235;

	private final NPCRegistry registry;

	private Location location;
	private double defaultSpace = DEFAULT_SPACE;

	private List<NPCHologramLine> lines = new ArrayList<>();

	public NPCHologram(NPCRegistry registry, Location location) {
		this.registry = registry;
		this.location = location;
	}

	public NPCHologram addLine(String text, int space) {
		this.addLine(text);
		this.lines.get(this.lines.size() - 1).setSpace(space);
		return this;
	}

	public NPCHologram addLine(String... texts) {
		for (String text : texts) {
			NPCArmorStand npc = new NPCArmorStand(this.registry, this.location);
			npc.setCustonNameVisible(true);
			npc.setHasNoGravity(true);
			npc.setIsInvisible(true);
			npc.create();

			NPCHologramLine line = new NPCHologramLine(this, npc, 0, text);
			this.lines.add(line);
		}

		this.rescale();
		return this;
	}

	public NPCHologram setLine(int line, String text) {
		NPCHologramLine hologramLine = this.getLine(line);
		if (hologramLine != null) {
			hologramLine.setText(text);
			return this;
		}

		this.addLine(text);
		return this;
	}

	public void removeLine(int line) {
		NPCHologramLine hologramLine = this.getLine(line);
		if (hologramLine != null)
			this.removeLine(hologramLine);
	}

	public void removeLine(NPCHologramLine line) {
		this.lines.remove(line);
		line.getNPC().destroy();
	}

	public NPCHologramLine getLine(int line) {
		if (line > -1 && this.lines.size() <= line)
			return null;

		return this.lines.get(line);
	}

	public void rescale() {
		Location location = this.location.clone();

		for (int i = this.lines.size() - 1; i >= 0; i--) {
			NPCHologramLine line = this.lines.get(i);
			NPCArmorStand npc = line.getNPC();

			if (!npc.getLocation().equals(location))
				npc.setLocation(location.clone());

			npc.setCustomName(line.getText());
			npc.updateDataWatcher();

			location.add(0, line.getSpace() != 0 ? line.getSpace() : this.defaultSpace, 0);
		}
	}

	public double getDefaultSpace() {
		return this.defaultSpace;
	}

	public void setDefaultSpace(double defaultSpace) {
		this.defaultSpace = defaultSpace;
		this.rescale();
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
		this.rescale();
	}

	public List<NPCHologramLine> getLines() {
		return this.lines;
	}

	public NPCRegistry getRegistry() {
		return this.registry;
	}
}
