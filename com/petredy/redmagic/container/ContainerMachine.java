package com.petredy.redmagic.container;

import com.petredy.redmagic.tileentities.TileEntityMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerMachine extends Container {

	
	public ContainerMachine(EntityPlayer player, TileEntityMachine machine){
		super();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
