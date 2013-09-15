package com.petredy.redmagic.tasks;

import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.lib.Patterns;

public class TaskStay extends Task {

	public String name = Patterns.STOP;
	
	public void update(EntitySoulman soulman){
		soulman.getNavigator().tryMoveToXYZ(soulman.activeRune.x, soulman.activeRune.y, soulman.activeRune.z, soulman.getMoveHelper().getSpeed());
	};
	
}
