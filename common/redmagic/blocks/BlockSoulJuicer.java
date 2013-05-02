package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.ItemHelper;
import redmagic.tileentities.TileEntityJuicer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;

public class BlockSoulJuicer extends BlockContainer{

	public Icon side, top;
	
	protected BlockSoulJuicer(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.JUICER_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityJuicer();
	}
	
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.side = this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.JUICER_NAME + "_side");
		this.top = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.JUICER_NAME + "_top");
    }
	
	public Icon getIcon(int side, int metadata)
    {
		switch(side){
		case 1:
			return this.top;
		default:
			return this.side;
		}
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
		LiquidStack liquid = LiquidContainerRegistry.getLiquidForFilledItem(current);
		TileEntityJuicer entity = (TileEntityJuicer) par1World.getBlockTileEntity(par2, par3, par4);
		if(liquid != null && liquid.isLiquidEqual(LiquidDictionary.getCanonicalLiquid("Water"))){
			entity.fill(ForgeDirection.UNKNOWN, liquid, true);
			par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, ItemHelper.consumeItem(current));
			return true;
		}else if(liquid == null && current != null && current.getItem() instanceof ItemBucket){
			LiquidStack available = entity.getTanks(ForgeDirection.UNKNOWN)[0].getLiquid();
			if (available != null) {
				ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(available, current);

				liquid = LiquidContainerRegistry.getLiquidForFilledItem(filled);

				if (current.stackSize > 1) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(filled))return false;
					else par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, ItemHelper.consumeItem(current));
				} else {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, ItemHelper.consumeItem(current));
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, filled);
				}
				
				if (liquid != null){
					entity.drain(ForgeDirection.UNKNOWN, liquid.amount, true);
				}
				return true;
			}
		}else{
			par5EntityPlayer.openGui(Redmagic.instance, GuiIndex.JUICER, par1World, par2, par3, par4);
			return true;
		}
		return false;
	}
}
