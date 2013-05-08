package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.items.ItemFragment;
import redmagic.tileentities.TileEntityMold;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoulMold extends BlockContainer{

	protected BlockSoulMold(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.MOLD_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMold(12);
	}

	 public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		 
		 ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
		 if(current != null && current.getItem() instanceof ItemFragment){
			 TileEntityMold mold = (TileEntityMold) par1World.getBlockTileEntity(par2, par3, par4);
			 mold.addFragment(current);
			 if(!par5EntityPlayer.capabilities.isCreativeMode)par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
			 return true;
		 }
		 return false;
	 }
	 
	 public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
			TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
			
			
			
			super.breakBlock(par1World, par2, par3, par4, par5, par6);
	 }
	
}
