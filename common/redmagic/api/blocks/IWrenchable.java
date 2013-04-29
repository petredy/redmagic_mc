package redmagic.api.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IWrenchable {
	public abstract void onWrench(EntityPlayer player, World world, int x, int y, int z, int metadata);
}
