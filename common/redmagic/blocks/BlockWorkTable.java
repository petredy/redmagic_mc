package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.api.workTable.ITool;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.BlockHelper;
import redmagic.tileentities.TileEntityWorkTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockWorkTable extends BlockContainer{

	public Icon side, top, bottom;
	
	public BlockWorkTable(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.WORK_TABLE_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWorkTable();
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.top = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WORK_TABLE_NAME+"_top");
		this.side = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WORK_TABLE_NAME+"_side");
		this.bottom = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WORK_TABLE_NAME+"_bottom");
    }
	
	public Icon getIcon(int side, int metadata)
    {
		switch(side){
		case 1:
			return this.top;
		case 0:
			return this.bottom;
		default:
			return this.side;
		}
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		ItemStack currentItem = par5EntityPlayer.getCurrentEquippedItem();
		if(currentItem != null && currentItem.getItem() instanceof ITool){
			ITool tool = (ITool)currentItem.getItem();
			tool.build(par1World, par2, par3, par4, par5EntityPlayer);
		}else{
			par5EntityPlayer.openGui(Redmagic.instance, GuiIndex.WORK_TABLE, par1World, par2, par3, par4);
		}
		return true;
    }
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		BlockHelper.dropItems(par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

}
