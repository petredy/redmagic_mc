package redmagic.helpers;

import java.util.Random;

import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.items.ItemManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FragmentHelper {
	public static final String INTELLIGENCE = Reference.MOD_ID + "_intelligence";
	public static final String STRENGTH = Reference.MOD_ID + "_strength";
	public static final String CAPACITY = Reference.MOD_ID + "_capacity";
	public static final String ILLUSION = Reference.MOD_ID + "_illusion";
	public static final String SATISFACTION = Reference.MOD_ID + "_satisfaction";
	
	public static ItemStack createNewFragment(int meta){
		ItemStack stack = new ItemStack(ItemManager.fragment, 1, meta);
		Random rand = new Random();
		switch(meta){
		case ItemIndex.FRAGMENT_INTELLIGENCE_ITEMDAMAGE:
			setIntelligence(stack, rand.nextInt(LogicIndex.SOUL_START_INTELLIGENCE - 1) + 1);
			break;
		case ItemIndex.FRAGMENT_STRENGTH_ITEMDAMAGE:
			setStrength(stack, rand.nextInt(LogicIndex.SOUL_START_STRENGTH - 1) + 1);
			break;
		case ItemIndex.FRAGMENT_CAPACITY_ITEMDAMAGE:
			setCapacity(stack, rand.nextInt(LogicIndex.SOUL_START_CAPACITY - 1) + 1);
			break;
		case ItemIndex.FRAGMENT_ILLUSION_ITEMDAMAGE:
			setIllusion(stack, rand.nextInt(LogicIndex.SOUL_START_ILLUSION - 1) + 1);
			break;
		case ItemIndex.FRAGMENT_SATISFACTION_ITEMDAMAGE:
			setSatisfaction(stack, rand.nextInt(LogicIndex.SOUL_START_SATISFACTION - 1) + 1);
			break;
		case ItemIndex.FRAGMENT_ALL_ITEMDAMAGE:
			setIntelligence(stack, rand.nextInt(LogicIndex.SOUL_START_INTELLIGENCE - 1) + 1);
			setStrength(stack, rand.nextInt(LogicIndex.SOUL_START_STRENGTH - 1) + 1);
			setCapacity(stack, rand.nextInt(LogicIndex.SOUL_START_CAPACITY - 1) + 1);
			setIllusion(stack, rand.nextInt(LogicIndex.SOUL_START_ILLUSION - 1) + 1);
			setSatisfaction(stack, rand.nextInt(LogicIndex.SOUL_START_SATISFACTION - 1) + 1);
			break;
		}
		return stack;
	}
	
	public static void setIntelligence(ItemStack stack, int amount){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(INTELLIGENCE, amount);
	}
	
	public static int getIntelligence(ItemStack stack){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(INTELLIGENCE);
	}
	
	public static void setStrength(ItemStack stack, int amount){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(STRENGTH, amount);
	}
	
	public static int getStrength(ItemStack stack){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(STRENGTH);
	}
	
	public static void setCapacity(ItemStack stack, int amount){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(CAPACITY, amount);
	}
	
	public static int getCapacity(ItemStack stack){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(CAPACITY);
	}
	
	public static void setIllusion(ItemStack stack, int amount){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(ILLUSION, amount);
	}
	
	public static int getIllusion(ItemStack stack){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(ILLUSION);
	}
	
	public static void setSatisfaction(ItemStack stack, int amount){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(SATISFACTION, amount);
	}
	
	public static int getSatisfaction(ItemStack stack){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(SATISFACTION);
	}
}
