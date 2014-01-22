package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.es.Environment;
import com.petredy.redmagic.es.EnvironmentSphere;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.api.es.IESContainer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityESLoader extends TileEntityInventory {

	public TileEntityESLoader() {
		super(BlockIndex.ES_LOADER_NAME, 1);
	}
	
	public void toggle(){
		if(this.getStackInSlot(0) != null){
			if(this.canStore()){
				load();
			}else{
				unload();
			}
		}
	}
	
	public void load(){
		if(this.getStackInSlot(0) != null){
			EnvironmentSphere sphere = new EnvironmentSphere(xCoord, yCoord, zCoord);
			sphere.search(worldObj);
			Environment env = sphere.getEnvironment(worldObj, ((IESContainer)this.getStackInSlot(0).getItem()).getMaxEnergy(this.getStackInSlot(0)));
			((IESContainer)this.getStackInSlot(0).getItem()).setEnvironment(this.getStackInSlot(0), env);
			if(env.valide)sphere.deleteContent(worldObj);
		}
	}

	public void unload() {
		if(this.getStackInSlot(0) != null){
			EnvironmentSphere sphere = new EnvironmentSphere(xCoord, yCoord, zCoord);
			sphere.search(worldObj);
			Environment env = ((IESContainer)this.getStackInSlot(0).getItem()).getEnvironment(this.getStackInSlot(0));
			if(env.valide){
				((IESContainer)this.getStackInSlot(0).getItem()).setEnvironment(this.getStackInSlot(0), new Environment());
				if(env != null && env.valide)sphere.place(worldObj, env);
			}
		}
	}
	
	public boolean canStore() {
		LogUtils.log(this.getStackInSlot(0));
		if(this.getStackInSlot(0) != null){
			Environment env = ((IESContainer)this.getStackInSlot(0).getItem()).getEnvironment(this.getStackInSlot(0));
			if(env == null || (env != null && !env.valide))return true;
		}
		return false;
	}
}
