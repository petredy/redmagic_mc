package redmagic.lib.gods;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import org.lwjgl.util.vector.Vector;

import redmagic.lib.influences.Influence;
import redmagic.lib.influences.PassiveInfluence;


public class Sphere {

	public int x, y, z;
	public int range;
	
	public Sphere(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.range = 30;
	}
	
	
	public boolean isInRange(int x, int y, int z) {
		return Vec3.createVectorHelper(this.x, this.y, this.z).distanceTo(Vec3.createVectorHelper(x, y, z)) < range;
	}


	public void interactWithPlayer(EntityPlayer player) {
		Collection<God> gods = GodManager.getGods();
		Iterator<God> it = gods.iterator();
		while(it.hasNext()){
			God god = it.next();
			for(PassiveInfluence influence: god.passiveInfluences){
				influence.act(player.worldObj, player);
			}
		}
	}
	
}
