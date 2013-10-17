package com.petredy.redmagic.structure;

import java.util.List;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;

public class CrafterTest implements ICrafting{

	@Override
	public void sendContainerAndContentsToPlayer(Container container, List list) {
		
	}

	@Override
	public void sendSlotContents(Container container, int i, ItemStack itemstack) {
		
	}

	@Override
	public void sendProgressBarUpdate(Container container, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
