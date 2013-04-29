package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.items.ItemManager;
import redmagic.items.ItemSoulNectar;
import redmagic.tileentities.TileEntitySoulTrap;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoulTrap extends BlockContainer{
	
	protected BlockSoulTrap(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SOUL_TRAP_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulTrap();
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_TRAP_NAME);
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		TileEntitySoulTrap entity = (TileEntitySoulTrap) par1World.getBlockTileEntity(par2, par3, par4);
		if(entity == null)return false;
		ItemStack currentItem = par5EntityPlayer.getCurrentEquippedItem();
		if(currentItem != null && currentItem.getItem() instanceof ItemSoulNectar && entity.soul_nectar + 1 <= LogicIndex.SOUL_TRAP_MAX_NECTAR){
			par5EntityPlayer.inventory.consumeInventoryItem(ItemManager.soulNectar.itemID);
			entity.soul_nectar++;
		}else{
			par5EntityPlayer.openGui(Redmagic.instance, GuiIndex.SOUL_TRAP, par1World, par2, par3, par4);
		}
		return true;
    }

}
