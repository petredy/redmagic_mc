package redmagic.items;

import java.util.List;

import redmagic.Redmagic;
import redmagic.api.items.IKeyBound;
import redmagic.configuration.ItemIndex;
import redmagic.entities.EntityRedmagicItem;
import redmagic.helpers.ArtifactHelper;
import redmagic.helpers.ItemHelper;
import redmagic.helpers.LogHelper;
import redmagic.lib.artifact.ArtifactInformation;
import redmagic.lib.talent.Talent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemArtifact extends Item implements IKeyBound{

	public ItemArtifact(int id) {
		super(id);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(ItemIndex.ARTIFACT_NAME);
		this.setFull3D();
		this.setMaxStackSize(1);
	}

	
	
	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String key) {
		
	}
	
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
		ItemHelper.spawnRedmagicItem(player, item);
        return false;
    }
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		ArtifactInformation information = ArtifactHelper.getArtifactInformation(stack);
		if(information != null){
			list.add(StatCollector.translateToLocal(information.getPath().name));
		}
	}
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(ArtifactHelper.getNewArtifact(Talent.earth));
		par3List.add(ArtifactHelper.getNewArtifact(Talent.life));
		par3List.add(ArtifactHelper.getNewArtifact(Talent.water));
    }
	
	

}
