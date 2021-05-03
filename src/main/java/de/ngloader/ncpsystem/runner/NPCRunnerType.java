package de.ngloader.ncpsystem.runner;

import java.util.function.Function;

import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.runner.type.NPCDistanceCheckRunner;
import de.ngloader.ncpsystem.runner.type.NPCLookRunner;
import de.ngloader.ncpsystem.runner.type.tablist.NPCTabListRunner;

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