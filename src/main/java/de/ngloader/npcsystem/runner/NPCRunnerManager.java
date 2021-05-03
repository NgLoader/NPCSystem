package de.ngloader.npcsystem.runner;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import de.ngloader.npcsystem.NPCRegistry;

public class NPCRunnerManager implements Runnable {

	private final NPCRegistry registry;
	private final Plugin plugin;

	private final Map<NPCRunnerType, NPCRunner<?>> runnerByType = new EnumMap<>(NPCRunnerType.class);
	private final Set<NPCRunner<?>> runners = new HashSet<>();
	protected BukkitTask task;

	public NPCRunnerManager(NPCRegistry registry) {
		this.registry = registry;
		this.plugin = this.registry.getNPCSystem().getPlugin();
	}

	@Override
	public void run() {
		for (Iterator<NPCRunner<?>> iterator = this.runners.iterator(); iterator.hasNext();) {
			NPCRunner<?> runner = iterator.next();
			try {
				runner.run();
			} catch (Exception e) {
				e.printStackTrace();
				iterator.remove();
			}
		}
	}

	public void startRunner() {
		if (this.task == null) {
			this.task = Bukkit.getScheduler().runTaskTimer(this.plugin, this, 0, 2);
		}
	}

	public void stopRunner() {
		if (this.task != null) {
			this.task.cancel();
			this.task = null;
		}
	}

	public boolean addRunner(NPCRunnerType type) {
		if (this.runnerByType.containsKey(type)) {
			return false;
		}

		NPCRunner<?> runner = type.newInstance(this.registry);
		this.runnerByType.put(type, runner);
		this.runners.add(runner);
		return true;
	}

	@SuppressWarnings("unchecked")
	public <T extends NPCRunner<?>> T getRunner(NPCRunnerType type) {
		NPCRunner<?> runner = this.runnerByType.get(type);
		if (runner == null) {
			return null;
		}
		//TODO fix this NILS
		return (T) runner;
	}

	public boolean removeRunner(NPCRunnerType type) {
		NPCRunner<?> runner = this.runnerByType.remove(type);
		if (runner == null) {
			return false;
		}

		this.runners.remove(runner);
		return true;
	}

	public boolean removeRunner(NPCRunner<?> runner) {
		return this.runners.remove(runner);
	}

	public void destroy() {
		this.runnerByType.clear();
		this.runners.forEach(NPCRunner::destroy);
		this.runners.clear();
	}
}
