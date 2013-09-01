package redmagic.items;

import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import net.minecraft.item.Item;

public class ItemConstructionTool extends Item{

	public ItemConstructionTool(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.CONSTRUCTION_TOOL_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}

}
