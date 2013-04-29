package redmagic.api.workTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ITool {
	public abstract void build(World world, int x, int y, int z, EntityPlayer player);
}
