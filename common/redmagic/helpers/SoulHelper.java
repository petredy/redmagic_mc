package redmagic.helpers;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import redmagic.api.frame.ISoul;
import redmagic.api.frame.ISoulFrame;
import redmagic.blocks.BlockManager;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.items.ItemManager;
import redmagic.tileentities.machines.TileEntityMachineFurnace;

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
		soul.setSatisfaction(stack, rand.nextInt(LogicIndex.SOUL_START_RESISTENCE));
		return stack;
	}

	public static int getTypeByItem(ItemStack soul) {
		if(soul.getItem() instanceof ISoul){
			return soul.getItemDamage();
		}
		return 0;
	}
	
	public static ItemStack getCrystalBySoul(ItemStack soulStack){
		ItemStack crystal = new ItemStack(ItemManager.crystal);
		ISoul soul = (ISoul)soulStack.getItem();
		int intelligence = soul.getIntelligence(soulStack);
		int strength = soul.getStrength(soulStack);
		int capacity = soul.getCapacity(soulStack);
		int illusion = soul.getIllusion(soulStack);
		int satisfaction = soul.getSatisfaction(soulStack);
		
		crystal.stackTagCompound = new NBTTagCompound();
		if(intelligence >= strength && intelligence >= capacity && intelligence >= illusion && intelligence >= satisfaction){
			crystal.setItemDamage(ItemIndex.CRYSTAL_INTELLIGENCE_ITEMDAMAGE);
			crystal.stackTagCompound.setInteger(ItemIndex.CRYSTAL_INTELLIGENCE_NAME, intelligence);
		}else if(strength >= intelligence && strength >= capacity && strength >= illusion && strength >= satisfaction){
			crystal.setItemDamage(ItemIndex.CRYSTAL_STRENGTH_ITEMDAMAGE);
			crystal.stackTagCompound.setInteger(ItemIndex.CRYSTAL_STRENGTH_NAME, strength);
		}else if(capacity >= intelligence && capacity >= strength && capacity >= illusion && capacity >= satisfaction){
			crystal.setItemDamage(ItemIndex.CRYSTAL_CPACITY_ITEMDAMAGE);
			crystal.stackTagCompound.setInteger(ItemIndex.CRYSTAL_CAPACITY_NAME, capacity);
		}else if(illusion >= intelligence && illusion >= strength && illusion >= capacity && illusion >= satisfaction){
			crystal.setItemDamage(ItemIndex.CRYSTAL_ILLUSION_ITEMDAMAGE);
			crystal.stackTagCompound.setInteger(ItemIndex.CRYSTAL_ILLUSION_NAME, illusion);
		}else if(satisfaction >= intelligence && satisfaction >= strength && satisfaction >= capacity && satisfaction >= illusion){
			crystal.setItemDamage(ItemIndex.CRYSTAL_SATISFACTION_ITEMDAMAGE);
			crystal.stackTagCompound.setInteger(ItemIndex.CRYSTAL_SATISFACTION_NAME, satisfaction);
		}else{
			crystal.setItemDamage(ItemIndex.CRYSTAL_EMPTY_ITEMDAMAGE);
		}
		
		return crystal;
	}
	
	public static boolean linkSoul(ItemStack stack, World world, int x, int y, int z, EntityPlayer player){
		if(stack.getItem() instanceof ISoul && stack.stackTagCompound != null){
			world.setBlock(x, y, z, BlockManager.machine.blockID, ((ISoul)stack.getItem()).getType(stack), 3);
			handleSoulTypePlacing(stack, world, x, y, z, ((ISoul)stack.getItem()).getType(stack), player);
			if(((ISoul)stack.getItem()).getType(stack) > 0){
				ISoulFrame frame = (ISoulFrame) world.getBlockTileEntity(x, y, z);
				frame.storeSoul(stack);
				return true;
			}
		}
		return false;
	}
	
	public static void handleSoulTypePlacing(ItemStack stack, World world, int x, int y, int z, int type, EntityPlayer player){
		switch(type){
		case LogicIndex.SOUL_FURNACE:
			TileEntityMachineFurnace entity = (TileEntityMachineFurnace) world.getBlockTileEntity(x, y, z);
    		entity.side = BlockHelper.getRotation(world, x, y, z, player);
    		break;
		}
	}
	
}
