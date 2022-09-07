package de.ngloader.npcsystem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import de.ngloader.npcsystem.api.scoreboard.ScoreboardManager;
import de.ngloader.npcsystem.runner.NPCRunnerManager;
import de.ngloader.npcsystem.runner.NPCRunnerType;
import de.ngloader.npcsystem.runner.type.tablist.NPCTabListRunner;
import de.ngloader.npcsystem.scoreboard.SharedScoreboardManager;
import net.minecraft.world.entity.Entity;

public class NPCSystem implements Listener {

	private static Field fieldEntityCount;

	static {
		for (Field field : Entity.class.getDeclaredFields()) {
			if (field.getType().isAssignableFrom(AtomicInteger.class)) {
				field.setAccessible(true);
				fieldEntityCount = field;
				break;
			}
		}
	}

	private final Plugin plugin;

	private final Random random = new Random();

	private final AtomicInteger entityCount;
	protected final Set<Integer> entityCountIds = new HashSet<>();

	private final Set<Long> uuidInUse = new HashSet<>();
	protected final Map<Integer, NPC> npcByEntityId = new HashMap<>();

	protected final List<NPCRegistry> registries = new ArrayList<>();
	private final NPCRegistry defaultRegistry;

	private final NPCPacketListener packetListener;

	private final ScoreboardManager scoreboardManager;

	public NPCSystem(Plugin plugin) throws IllegalArgumentException, IllegalAccessException {
		this.plugin = plugin;

		this.entityCount = (AtomicInteger) fieldEntityCount.get(null);

		this.defaultRegistry = new NPCRegistry(this);
		NPCRunnerManager runnerManager = this.defaultRegistry.getRunnerManager();
		for (NPCRunnerType type : NPCRunnerType.values()) {
			runnerManager.addRunner(type);
		}
		runnerManager.startRunner();
		this.registries.add(this.defaultRegistry);
		
		this.packetListener = new NPCPacketListener(this);
		this.scoreboardManager = new SharedScoreboardManager(this.plugin);

		Bukkit.getServicesManager().register(ScoreboardManager.class, this.scoreboardManager, this.plugin, ServicePriority.Normal);

		Bukkit.getPluginManager().registerEvents(this, this.plugin);

		new Metrics((JavaPlugin) this.plugin, 11227);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.defaultRegistry.showAll(event.getPlayer());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		this.packetListener.onPlayerDisconnect(player);
		this.registries.forEach(registry -> registry.hideAll(player));
	}

	void callFirstMove(Player player) {
		this.registries.forEach(registry -> {
			NPCTabListRunner runner = registry.getRunnerManager().getRunner(NPCRunnerType.TABLIST);
			if (runner != null) {
				runner.onFirstMove(player);
			}
		});
	}

	public NPCRegistry createRegestry() {
		NPCRegistry registry = new NPCRegistry(this);
		this.registries.add(registry);
		return registry;
	}

	public UUID generateUUID() {
		long mostSigBits = System.currentTimeMillis();
		while (!uuidInUse.add(mostSigBits)) {
			mostSigBits = this.random.nextLong();
		}
		return new UUID(mostSigBits, 0);
	}

	public boolean giveBackUUID(UUID uuid) {
		return this.uuidInUse.remove(uuid.getMostSignificantBits());
	}

	protected int nextEntityCount() {
		Iterator<Integer> iterator = this.entityCountIds.iterator();
		if (iterator.hasNext()) {
			int entityId = iterator.next();
			iterator.remove();
			return entityId;
		}
		return this.entityCount.incrementAndGet();
	}

	protected void disable() {
		this.packetListener.unregister();
		List<NPCRegistry> registries = new ArrayList<>(this.registries);
		registries.forEach(NPCRegistry::remove);
		this.registries.clear();
	}

	public NPCRegistry getDefaultRegistry() {
		return this.defaultRegistry;
	}

	public List<NPCRegistry> getRegistries() {
		return this.registries;
	}

	public NPCPacketListener getPacketListener() {
		return this.packetListener;
	}

	public ScoreboardManager getScoreboardManager() {
		return this.scoreboardManager;
	}

	public Plugin getPlugin() {
		return this.plugin;
	}
}
