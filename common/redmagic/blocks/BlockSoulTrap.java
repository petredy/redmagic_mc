package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.InventoryHelper;
import redmagic.items.ItemManager;
import redmagic.items.ItemSoulNectar;
import redmagic.tileentities.TileEntitySoulTrap;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoulTrap extends BlockContainer{
	
	protected BlockSoulTrap(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SOUL_TRAP_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulTrap();
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_TRAP_NAME);
    }
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		InventoryHelper.dropInventory((IInventory) par1World.getBlockTileEntity(par2, par3, par4), par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

}
