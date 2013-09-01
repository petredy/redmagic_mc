package redmagic.lib.gods;

import redmagic.configuration.PathIndex;
import redmagic.lib.influences.life.RegenerationInfluence;

public class LifeGod extends God{

	public LifeGod(){
		super();
		this.name = PathIndex.LIFE;
		this.passiveInfluences.add(new RegenerationInfluence());
	}
	
}
