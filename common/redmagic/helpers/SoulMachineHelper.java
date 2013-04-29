package redmagic.helpers;

import redmagic.api.frame.ISoul;
import redmagic.api.frame.ISoulFrame;
import redmagic.blocks.BlockManager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SoulMachineHelper {
	public static boolean linkSoul(ItemStack stack, World world, int x, int y, int z){
		if(stack.getItem() instanceof ISoul && stack.stackTagCompound != null){
			world.setBlock(x, y, z, BlockManager.machine.blockID, ((ISoul)stack.getItem()).getType(stack) + 1, 3);
			ISoulFrame frame = (ISoulFrame) world.getBlockTileEntity(x, y, z);
			frame.storeSoul(stack);
			return true;
		}
		return false;
	}
}
