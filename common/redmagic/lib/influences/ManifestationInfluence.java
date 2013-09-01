package redmagic.lib.influences;

import redmagic.Redmagic;
import redmagic.configuration.Texture;
import redmagic.helpers.LogHelper;
import redmagic.lib.gods.God;
import redmagic.lib.gods.GodManager;
import redmagic.lib.gods.Sphere;
import redmagic.lib.talent.Talent;
import net.minecraft.world.World;

public class ManifestationInfluence extends ClientInfluence{

	@Override
	public void act(World world, God god){
		for(Sphere sphere: GodManager.getSpheres()){
			if(god.name.equals(Talent.life.unlockableName)){
				Redmagic.proxy.addEffect("custom", world, (double)sphere.x + 0.5, (double)sphere.y + 0.5, (double)sphere.z + 0.5, new Object[]{Texture.MANIFESTATION_FX});
			}
		}	
	}
	
}
