package redmagic.lib.souls;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.SoulHelper;
import redmagic.lib.tree.SoulBlock;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulFilter extends Soul{

	@Override
	public void init(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}

	@Override
	public void onUpdate(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		entity.store(LiquidDictionary.getLiquid("Essence", 10));
		
	}

	@Override
	public void onWrench(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		Logger.log("wrench");
		
	}

	@Override
	public void remove(TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

}
