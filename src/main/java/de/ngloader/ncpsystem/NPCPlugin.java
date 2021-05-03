package de.ngloader.ncpsystem;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class NPCPlugin extends JavaPlugin implements Listener {

	private static NPCSystem npcSystem;

	public static NPCSystem getInstance() {
		return npcSystem;
	}

	@Override
	public void onEnable() {
		try {
			this.sendConsoleMessage("§aEnabling");
			
			if (this.getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
				this.sendConsoleMessage("ProtocolLib is not found§8! §7Plugin cannot be enabled");
				return;
			}

			NPCPlugin.npcSystem = new NPCSystem(this);
			Bukkit.getServicesManager().register(NPCSystem.class, npcSystem, this, ServicePriority.Normal);

			this.sendConsoleMessage("§aEnabled");
		} catch (Exception e) {
			this.sendConsoleMessage("An error occurred while enabling plugin");
			e.printStackTrace();

			Bukkit.getServer().getPluginManager().registerEvent(PluginEnableEvent.class, this, EventPriority.NORMAL, this::onEnableFailed, this);
		}
	}

	@Override
	public void onDisable() {
		this.sendConsoleMessage("§cDisabling");
		if (npcSystem != null) {
			npcSystem.disable();
		}
		this.sendConsoleMessage("§cDisabled");
	}

	public void sendConsoleMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(String.format("§8[§aNPCSystem§8] §7%s§8.", message));
	}

	public void onEnableFailed(Listener listener, Event event) {
		PluginEnableEvent enableEvent = (PluginEnableEvent) event;
		if (enableEvent.getPlugin() == this) {
			HandlerList.unregisterAll(listener);
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}
}