package com.petredy.redmagic.blocks;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.tileentities.TileEntityRune;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.ItemUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockRune extends BlockContainer {

	protected BlockRune(int par1) {
		super(par1, Material.cloth);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.RUNE_NAME);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityRune();
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RUNE_NAME);
	}
	
	public static boolean isNormalCube(int par0)
    {
		return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public int getRenderType()
    {
        return Rendering.RUNE_ID;
    }

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ)
    {
		TileEntityRune rune = (TileEntityRune) world.getBlockTileEntity(x, y, z);
		int count = BlockUtils.getHitIndex4x4(offX, offZ);
		if(rune == null){
			world.setBlockTileEntity(x, y, z, new TileEntityRune());
			rune = (TileEntityRune) world.getBlockTileEntity(x, y, z);
		}
		if(count >= 0){
			ItemStack current = player.getCurrentEquippedItem();
			if(rune.isMarker(count) && ((current != null && current.itemID == Blocks.rune.blockID) || current == null || (current != null && (current.getItem() instanceof ItemBlock)) == false)){
				if(!world.isRemote)InventoryUtils.dropItemStack(new ItemStack(Blocks.rune, 1, rune.getMarkerId(count)), world, offX, offY, offZ);
				rune.removeMarker(count);
			}
			if(current != null && current.itemID == Blocks.rune.blockID){
				rune.setMarker(count, current.getItemDamage());
				if(!player.capabilities.isCreativeMode)player.inventory.decrStackSize(player.inventory.currentItem, 1);
			}
		}

        return false;
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        TileEntityRune rune = (TileEntityRune) par1World.getBlockTileEntity(par2, par3, par4);
    	int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    	if(l == 0){
    		rune.side = 2;
    	}else if(l == 1){
    		rune.side = 5;
    	}else if(l == 2){
    		rune.side = 3;
    	}else if(l == 3){
    		rune.side = 4;
    	}
    	
    	MovingObjectPosition location = ItemUtils.getMovingObjectPosition(par1World, (EntityPlayer) par5EntityLivingBase, true);
    	if(location != null){
    		double x, y, z;
    		Vec3 vec = location.hitVec;
    		x = vec.xCoord - par2;
    		y = vec.yCoord - par3;
    		z = vec.zCoord - par4;
    		int count = BlockUtils.getHitIndex4x4((float)x, (float)z);
    		if(count > 0){
    			rune.setMarker(count, rune.getBlockMetadata());
    		}
    	}
    }

	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < ItemBlockRune.subNames.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
    }
}
