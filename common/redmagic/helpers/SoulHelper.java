package redmagic.helpers;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redmagic.api.frame.ISoul;
import redmagic.api.frame.ISoulFrame;
import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.items.ItemManager;
import redmagic.lib.souls.*;

public class SoulHelper {

	public static ItemStack createNewSoul(){
		ItemStack stack = new ItemStack(ItemManager.soul);
		stack.stackTagCompound = new NBTTagCompound();
		ISoul soul = (ISoul)stack.getItem();
		Random rand = new Random();
		soul.setIntelligence(stack, rand.nextInt(LogicIndex.SOUL_START_INTELLIGENCE));
		soul.setStrength(stack, rand.nextInt(LogicIndex.SOUL_START_STRENGTH));
		soul.setCapacity(stack, rand.nextInt(LogicIndex.SOUL_START_CAPACITY));
		soul.setIllusion(stack, rand.nextInt(LogicIndex.SOUL_START_ILLUSION));
		soul.setSatisfaction(stack, rand.nextInt(LogicIndex.SOUL_START_SATISFACTION));
		return stack;
	}

	public static ItemStack createSoul(int intelligence, int strength, int capacity, int illusion, int satisfaction) {
		ItemStack soulStack = new ItemStack(ItemManager.soul);
		ISoul soul = (ISoul)soulStack.getItem();
		soul.setIntelligence(soulStack, intelligence);
		soul.setStrength(soulStack, strength);
		soul.setCapacity(soulStack, capacity);
		soul.setIllusion(soulStack, illusion);
		soul.setSatisfaction(soulStack, satisfaction);
		return soulStack;
	}

	public static int getCapacity(ItemStack soul) {
		if(soul != null && soul.getItem() instanceof ISoul){
			return ((ISoul)soul.getItem()).getCapacity(soul);
		}
		return 0;
	}
	
	public static void setCapacity(ItemStack soul, int value) {
		if(soul != null && soul.getItem() instanceof ISoul){
			((ISoul)soul.getItem()).setCapacity(soul, value);
		}
	}
	
	
	public static int getType(ItemStack soul) {
		if(soul != null && soul.getItem() instanceof ISoul){
			return ((ISoul)soul.getItem()).getType(soul);
		}
		return 0;
	}
	
	public static void setType(ItemStack soul, int type) {
		if(soul != null && soul.getItem() instanceof ISoul){
			((ISoul)soul.getItem()).setType(soul, type);
		}
	}
	
	public static Soul getSoulByStack(ItemStack soul){
		switch(getType(soul)){
		case LogicIndex.SOUL_FILTER:
			return new SoulFilter();
		case LogicIndex.SOUL_STORAGE:
			return new SoulEssenceStorage();
		case LogicIndex.SOUL_FURNACE:
			return new SoulFurnace();
		case LogicIndex.SOUL_CRAFTING:
			return new SoulCrafting();
		}
		return null;
	}

	public static int getIntelligence(ItemStack soul) {
		if(soul != null && soul.getItem() instanceof ISoul){
			return ((ISoul)soul.getItem()).getIntelligence(soul);
		}
		return 0;
	}
	
	public static void setIntelligence(ItemStack soul, int value) {
		if(soul != null && soul.getItem() instanceof ISoul){
			((ISoul)soul.getItem()).setIntelligence(soul, value);
		}
	}

	public static int getStrength(ItemStack soul) {
		if(soul != null && soul.getItem() instanceof ISoul){
			return ((ISoul)soul.getItem()).getStrength(soul);
		}
		return 0;
	}
	
	public static void setStrength(ItemStack soul, int value) {
		if(soul != null && soul.getItem() instanceof ISoul){
			((ISoul)soul.getItem()).setStrength(soul, value);
		}
	}
	
	public static int getIllusion(ItemStack soul) {
		if(soul != null && soul.getItem() instanceof ISoul){
			return ((ISoul)soul.getItem()).getIllusion(soul);
		}
		return 0;
	}
	
	public static void setIllusion(ItemStack soul, int value) {
		if(soul != null && soul.getItem() instanceof ISoul){
			((ISoul)soul.getItem()).setIllusion(soul, value);
		}
	}
	
	public static int getSatisfaction(ItemStack soul) {
		if(soul != null && soul.getItem() instanceof ISoul){
			return ((ISoul)soul.getItem()).getSatisfaction(soul);
		}
		return 0;
	}
	
	public static void setSatisfaction(ItemStack soul, int value) {
		if(soul != null && soul.getItem() instanceof ISoul){
			((ISoul)soul.getItem()).setSatisfaction(soul, value);
		}
	}
	
	public static void addItemStacks(ItemStack soul, String key, ItemStack[] stacks){
		if(soul.stackTagCompound == null)soul.stackTagCompound = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < stacks.length; i++){
			if(stacks[i] != null){
				NBTTagCompound tag = new NBTTagCompound();
				stacks[i].writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		soul.stackTagCompound.setTag(key, list);
	}
	
	public static ItemStack[] getItemStacks(ItemStack soul, String key){
		if(soul.stackTagCompound != null){
			NBTTagList list = soul.stackTagCompound.getTagList(key);
			ItemStack[] stacks = new ItemStack[list.tagCount()];
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound item = (NBTTagCompound) list.tagAt(i);
				stacks[i] = ItemStack.loadItemStackFromNBT(item);
			}
			return stacks;
		}
		return null;
	}
	
	public static void setInteger(ItemStack soul, String key, int data){
		if(soul.stackTagCompound == null)soul.stackTagCompound = new NBTTagCompound();
		soul.stackTagCompound.setInteger(key, data);
	}
	
	public static int getInteger(ItemStack soul, String key){
		if(soul.stackTagCompound != null){
			return soul.stackTagCompound.getInteger(key);
		}
		return 0;
	}

	public static void controlBasicSoul(ItemStack soul) {
		if(SoulHelper.getIntelligence(soul) > 30)SoulHelper.setIntelligence(soul, 30);
		if(SoulHelper.getStrength(soul) > 30)SoulHelper.setStrength(soul, 30);
		if(SoulHelper.getCapacity(soul) > 30)SoulHelper.setCapacity(soul, 30);
		if(SoulHelper.getIllusion(soul) > 30)SoulHelper.setIllusion(soul, 30);
		if(SoulHelper.getSatisfaction(soul) > 30)SoulHelper.setSatisfaction(soul, 30);
	}

	

	
	
}
