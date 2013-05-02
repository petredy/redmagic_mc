package redmagic.lib.sacrifice;

import java.util.List;

import redmagic.api.frame.ISoul;
import redmagic.core.Logger;
import redmagic.helpers.ModifierHelper;
import net.minecraft.item.ItemStack;

public class SacrificeChangesType extends Sacrifice{

	public int type;
	
	public SacrificeChangesType(ItemStack input, int type) {
		super(input);
		this.type = type;
	}
	
	@Override
	public void sacrifice(ItemStack soulStack, ISoul soul, ItemStack input, List<ModifierHelper> modifiers){
		soul.setType(soulStack, type);
	}
}
