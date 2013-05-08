package redmagic.helpers;

import java.util.Random;

import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EntityFragmentHelper {
	public static ItemStack[] getFragments(EntityLiving entity){
		Random rand = new Random();
		int[] metas = new int[]{
			rand.nextInt(LogicIndex.SOUL_FRAGMENT_TYPES.length),
			rand.nextInt(LogicIndex.SOUL_FRAGMENT_TYPES.length),
			rand.nextInt(LogicIndex.SOUL_FRAGMENT_TYPES.length)
		};
		ItemStack[] fragments = new ItemStack[3];
		for(int i = 0; i < 3; i++){
			if(rand.nextFloat() < 1){
				fragments[i] = FragmentHelper.createNewFragment(metas[i]);
			}
		}
		return fragments;
	}
}
