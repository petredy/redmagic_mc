package redmagic.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.RenderIndex;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.LogHelper;
import redmagic.helpers.MarkerHelper;
import redmagic.items.ItemManager;
import redmagic.lib.rune.Marker;
import redmagic.tileentities.TileEntityRune;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockRune extends BlockContainer{

	
	
	protected BlockRune(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName(BlockIndex.RUNE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	

	 public TileEntity createTileEntity(World world, int metadata){
		 return createNewTileEntity(world);
	 }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityRune();
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
        return RenderIndex.RUNE;
    }
	
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ)
    {
		TileEntityRune rune = (TileEntityRune) world.getBlockTileEntity(x, y, z);
		int count = MarkerHelper.getHitIndex(offX, offZ);
		if(count >= 0){
			ItemStack current = player.getCurrentEquippedItem();
			if(rune.isMarker(count) && ((current != null && current.itemID == BlockManager.rune.blockID && current.getItemDamage() > BlockIndex.RUNE_TABLE_METADATA) || current == null || (current != null && (current.getItem() instanceof ItemBlock)) == false)){
				if(!world.isRemote)InventoryHelper.dropItemStack(new ItemStack(BlockManager.rune, 1, rune.getMarkerId(count)), world, offX, offY, offZ);
				rune.removeMarker(count);
			}
			if(current != null && current.itemID == BlockManager.rune.blockID && current.getItemDamage() > BlockIndex.RUNE_TABLE_METADATA){
				rune.setMarker(count, current.getItemDamage());
				if(!player.capabilities.isCreativeMode)player.inventory.decrStackSize(player.inventory.currentItem, 1);
			}
		}

        return false;
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        TileEntityRune rune = (TileEntityRune) par1World.getBlockTileEntity(par2, par3, par4);
        if(rune.getBlockMetadata() == BlockIndex.RUNE_TABLE_METADATA){
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
        }
    }

	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < ItemBlockRune.subNames.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
    }
}
