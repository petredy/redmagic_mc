package com.petredy.redmagic.forge.helper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTTagHelper {

	public static NBTTagCompound getTagAt(NBTTagList list, int index){
		return list.func_150305_b(index);
	}
	
}
