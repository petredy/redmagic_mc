package redmagic.lib.senses;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import redmagic.configuration.SenseIndex;
import redmagic.helpers.LogHelper;
import redmagic.lib.gods.GodManager;
import redmagic.lib.gods.Sphere;

public class PlayerSense extends Sense{

	public PlayerSense(){
		super();
		this.name = SenseIndex.PLAYER;
	}

	@Override
	public void perceive(Object[] tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		if(player != null){
			List<Sphere> spheres = GodManager.getSpheres();
			Iterator<Sphere> it = spheres.iterator();
			while(it.hasNext()){
				Sphere sphere = it.next();
				if(sphere.isInRange((int)player.posX, (int)player.posY, (int)player.posZ)){
					sphere.interactWithPlayer(player);
				}
			}
		}
	}
	
}
