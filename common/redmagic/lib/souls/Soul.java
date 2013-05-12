package redmagic.lib.souls;

import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.tileentities.tree.TileEntityTreeWood;
import net.minecraft.world.World;

public abstract class Soul {
	
	public abstract void init(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
	
	public abstract void onUpdate(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
	
	public abstract void onWrench(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);

	public abstract void remove(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
}
