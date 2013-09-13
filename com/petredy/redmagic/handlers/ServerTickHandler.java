package com.petredy.redmagic.handlers;

import java.util.Collection;
import java.util.EnumSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcher;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.WORLD) && tickData[0] instanceof World){
			Collection<Integer> ids = StructureSoulCatcher.structures.keySet();
			for(Integer id: ids){
				if(StructureSoulCatcher.structures.get(id)){
					StructureSoulCatcher structure = StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData((World) tickData[0], StructureSoulCatcher.TOKEN_PREFIX + id));
					if(structure != null){
						structure.update((World) tickData[0]);
						NBTTagCompound tag = new NBTTagCompound();
						structure.writeToNBT(tag);
						WorldSavedDataUtils.saveData((World) tickData[0], StructureSoulCatcher.TOKEN_PREFIX + id, tag);
					}else{
						StructureSoulCatcher.structures.put(id, false);
					}
				}
			}
		}
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return ServerTickHandler.class.getSimpleName();
	}

}
