package com.petredy.redmagic.dimension;

import com.petredy.redmagic.lib.Dimensions;
import com.petredy.redmagic.player.PlayerInformation;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.PlayerUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterSoul extends Teleporter{
	
	public WorldServer server;
	
	public TeleporterSoul(WorldServer server) {
		super(server);
		this.server = server;
	}
	
	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
		if(!placeInExistingPortal(par1Entity, par2, par4, par6, par8)){
			makePortal(par1Entity);
			placeInExistingPortal(par1Entity, par2, par4, par6, par8);
		}
    }

	@Override
	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
		if(server.isAirBlock(0, 128, 0)) return false;
		par1Entity.setPosition(0.5, 129, 0.5);
		par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
		return true;
		
    }
	
	@Override
	public boolean makePortal(Entity par1Entity)
    {
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				for(int k = 128; k < 131; k++){
					if(k == 128){
						server.setBlock(i, k, j, Block.bedrock.blockID, 0, 2);
					}else{
						server.setBlockToAir(i, k, j);
					}
				}
			}
		}
		return !server.isAirBlock(0, 128, 0);
		
    }

}
