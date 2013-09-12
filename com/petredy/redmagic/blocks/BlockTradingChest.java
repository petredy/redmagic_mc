package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.tileentities.TileEntityTradingChest;
import com.petredy.redmagic.utils.InventoryUtils;

import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockTradingChest extends BlockContainer{

	public BlockTradingChest(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.TRADING_CHEST_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTradingChest();
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.TRADING_CHEST_NAME);
	}
	
	@Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public int getRenderType() {

        return Rendering.TRADING_CHEST_ID;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        InventoryUtils.dropInventory(((IInventory)world.getBlockTileEntity(x, y, z)), world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntityTradingChest tile = (TileEntityTradingChest) world.getBlockTileEntity(x, y, z);

            if (tile != null) {
                player.openGui(Redmagic.instance, Guis.TRADING_CHEST, world, x, y, z);
            }
        }

        return true;
    }
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack){
    	byte b0 = 0;
        int l1 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l1 == 0)
        {
            b0 = 2;
        }

        if (l1 == 1)
        {
            b0 = 5;
        }

        if (l1 == 2)
        {
            b0 = 3;
        }

        if (l1 == 3)
        {
            b0 = 4;
        }
        par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
    }

}
