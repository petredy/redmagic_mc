package redmagic.lib.gods;

import java.util.ArrayList;
import java.util.List;

import redmagic.lib.influences.*;

public class God {

	public String name;
	
	public List<PassiveInfluence> passiveInfluences = new ArrayList<PassiveInfluence>();
	public List<ClientInfluence> clientInfluences = new ArrayList<ClientInfluence>();
	
	public God(){
		this.clientInfluences.add(new ManifestationInfluence());
	}
	
}
