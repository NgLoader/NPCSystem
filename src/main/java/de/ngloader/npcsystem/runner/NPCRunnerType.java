package de.ngloader.npcsystem.runner;

import java.util.function.Function;

import de.ngloader.npcsystem.NPCRegistry;
import de.ngloader.npcsystem.runner.type.NPCDistanceCheckRunner;
import de.ngloader.npcsystem.runner.type.NPCLookRunner;
import de.ngloader.npcsystem.runner.type.tablist.NPCTabListRunner;

public enum NPCRunnerType {
	
	TABLIST((registry) -> new NPCTabListRunner(registry)),
	LOOK((registry) -> new NPCLookRunner(registry)),
	DISTANCE_CHECK((registry) -> new NPCDistanceCheckRunner(registry));

	private Function<NPCRegistry, NPCRunner<?>> factory;

	private NPCRunnerType(Function<NPCRegistry, NPCRunner<?>> factory) {
		this.factory = factory;
	}

	NPCRunner<?> newInstance(NPCRegistry registry) {
		return this.factory.apply(registry);
	}
}