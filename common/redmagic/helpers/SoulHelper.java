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
import redmagic.core.Logger;
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
		soul.setSatisfaction(stack, rand.nextInt(LogicIndex.SOUL_START_SATISFACTION));
		return stack;
	}

	public static int getTypeByItem(ItemStack soul) {
		if(soul.getItem() instanceof ISoul){
			return soul.getItemDamage();
		}
		return 0;
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

	public static ItemStack createSoul(int intelligence, int strength, int capacity, int illusion, int satisfaction) {
		ItemStack soulStack = new ItemStack(ItemManager.soul);
		ISoul soul = (ISoul)soulStack.getItem();
		Logger.log(intelligence);
		Logger.log(strength);
		Logger.log(capacity);
		Logger.log(illusion);
		soul.setIntelligence(soulStack, intelligence);
		soul.setStrength(soulStack, strength);
		soul.setCapacity(soulStack, capacity);
		soul.setIllusion(soulStack, illusion);
		soul.setSatisfaction(soulStack, satisfaction);
		return soulStack;
	}
	
}
