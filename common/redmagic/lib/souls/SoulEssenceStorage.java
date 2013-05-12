package redmagic.lib.souls;

import net.minecraft.item.ItemStack;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.LogicIndex;
import redmagic.helpers.SoulHelper;
import redmagic.lib.tree.SoulBlock;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulEssenceStorage extends Soul{

	@Override
	public void init(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		if(entity.hasStructure()){
			SoulBlock block = structure.storage.getBlockAt(x, y, z);
			if(block != null){
				ItemStack soul = block.soul;
				entity.addCapacityToTank(LogicIndex.STORAGE_MAX_ESSENCES * SoulHelper.getCapacity(soul));
			}
		}
	}

	@Override
	public void onUpdate(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWrench(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		if(entity.hasStructure()){
			SoulBlock block = structure.storage.getBlockAt(x, y, z);
			if(block != null){
				ItemStack soul = block.soul;
				entity.removeCapacityFromTank(LogicIndex.STORAGE_MAX_ESSENCES * SoulHelper.getCapacity(soul));
			}
		}
	}

}
