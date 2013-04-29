package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.api.items.IWrench;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemWrench extends Item implements IWrench{

	public ItemWrench(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.WRENCH_NAME);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.WRENCH_NAME);
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
		if(position != null){
			int id = par2World.getBlockId(position.blockX, position.blockY, position.blockZ);
			if(Block.blocksList[id] instanceof IWrenchable){
				int metadata = par2World.getBlockMetadata(position.blockX, position.blockY, position.blockZ);
				((IWrenchable)Block.blocksList[id]).onWrench(par3EntityPlayer, par2World, position.blockX, position.blockY, position.blockZ, metadata);
			}
		}
        return par1ItemStack;
    }

}
