package com.petredy.redmagic.blocks;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.tileentities.TileEntityCage;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCage extends BlockContainer{

	public BlockCage(int par1) {
		super(par1, Material.rock);
		this.setHardness(125.0F);
		this.setResistance(2000.0F);
		this.setUnlocalizedName(BlockIndex.CAGE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
		this.setTickRandomly(true);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.CAGE_NAME);
	}
	
	public boolean renderAsNormalBlock(){
        return false;
    }
	
	public int getRenderType(){
        return Rendering.CAGE_ID;
    }
	
	public boolean isOpaqueCube(){
        return false;
    }
	
	public static boolean isNormalCube(int par0)
    {
		return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCage();
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Items.soul.itemID;
    }
	
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(par1World.isAirBlock(par2 + 1, par3, par4) ||
		par1World.isAirBlock(par2 - 1, par3, par4) ||
		par1World.isAirBlock(par2, par3 + 1, par4) ||
		par1World.isAirBlock(par2, par3 - 1, par4) ||
		par1World.isAirBlock(par2, par3, par4 + 1) ||
		par1World.isAirBlock(par2, par3, par4 - 1)){
        	this.sparkle(par1World, par2, par3, par4);
        }
    }
	
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
		this.cry(par1World, par2, par3, par4, new Random());
	}
	
    private void cry(World par1World, int par2, int par3, int par4, Random rand) {
		par1World.playSoundEffect(par2, par3, par4, Sounds.SOUL_WHISPER, 1.0F, rand.nextFloat() * 0.1f + 0.9f);
	}

	private void sparkle(World par1World, int par2, int par3, int par4)
    {
        Random random = par1World.rand;
        double d0 = 0.0625D;

        for (int l = 0; l < 6; ++l)
        {
            double d1 = (double)((float)par2 + random.nextFloat());
            double d2 = (double)((float)par3 + random.nextFloat());
            double d3 = (double)((float)par4 + random.nextFloat());

            if (l == 0 && !par1World.isBlockOpaqueCube(par2, par3 + 1, par4))
            {
                d2 = (double)(par3 + 1) + d0;
            }

            if (l == 1 && !par1World.isBlockOpaqueCube(par2, par3 - 1, par4))
            {
                d2 = (double)(par3 + 0) - d0;
            }

            if (l == 2 && !par1World.isBlockOpaqueCube(par2, par3, par4 + 1))
            {
                d3 = (double)(par4 + 1) + d0;
            }

            if (l == 3 && !par1World.isBlockOpaqueCube(par2, par3, par4 - 1))
            {
                d3 = (double)(par4 + 0) - d0;
            }

            if (l == 4 && !par1World.isBlockOpaqueCube(par2 + 1, par3, par4))
            {
                d1 = (double)(par2 + 1) + d0;
            }

            if (l == 5 && !par1World.isBlockOpaqueCube(par2 - 1, par3, par4))
            {
                d1 = (double)(par2 + 0) - d0;
            }

            if (d1 < (double)par2 || d1 > (double)(par2 + 1) || d2 < 0.0D || d2 > (double)(par3 + 1) || d3 < (double)par4 || d3 > (double)(par4 + 1))
            {
                par1World.spawnParticle("reddust", d1, d2, d3, 1.0D, 1.0D, 1.0D);
            }
        }
    }
	
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		if(player != null)player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		return world.setBlockToAir(x, y, z);
    }

}
