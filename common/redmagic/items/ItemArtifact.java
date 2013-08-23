package redmagic.items;

import redmagic.Redmagic;
import redmagic.api.items.IKeyBound;
import redmagic.configuration.ItemIndex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArtifact extends Item implements IKeyBound{

	public ItemArtifact(int id) {
		super(id);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(ItemIndex.ARTIFACT_NAME);
		this.setFull3D();
		this.setMaxStackSize(1);
	}

	
	
	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack,
			String key) {
		// TODO Auto-generated method stub
		
	}
	
	

}
