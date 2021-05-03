package de.ngloader.ncpsystem.runner.type;

import java.util.List;

import de.ngloader.ncpsystem.NPC;
import de.ngloader.ncpsystem.NPCRegistry;
import de.ngloader.ncpsystem.runner.NPCRunner;

public class NPCDistanceCheckRunner extends NPCRunner<NPC> {

	private static final int MAX_NPC_CHECK = 4;

	protected int taskIndex = 0;

	public NPCDistanceCheckRunner(NPCRegistry registry) {
		super(registry);
	}

	@Override
	public void run() {
		List<NPC> npcs = this.getRegistry().getNpcs();
		if (npcs.isEmpty()) {
			return;
		}

		int npcSize = npcs.size();
		for (int i = 0; i < MAX_NPC_CHECK; i++) {
			if (this.taskIndex > npcSize - 1) {
				this.taskIndex = 0;
				break;
			}

			NPC npc = npcs.get(this.taskIndex);
			npc.checkInRange();

			if (npcSize > 1) {
				this.taskIndex++;
			}
		}
	}
}