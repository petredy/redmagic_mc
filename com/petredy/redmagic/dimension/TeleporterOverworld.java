package com.petredy.redmagic.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterOverworld extends Teleporter {

	public TeleporterOverworld(WorldServer server) {
		super(server);
	}
	
	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
		if(par1Entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)par1Entity;
			ChunkCoordinates bed = player.getBedLocation(0);
			if(bed != null){
				player.setPosition(bed.posX + 0.5, bed.posY, bed.posZ + 0.5);
			}else{
				ChunkCoordinates spawn = player.worldObj.getSpawnPoint();
				player.setPosition(spawn.posX + 0.5, spawn.posY, spawn.posZ + 0.5);
			}
			player.motionX = player.motionY = player.motionZ = 0;

		}
    }

}
