package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.api.frame.ISoul;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import redmagic.configuration.RenderIndex;
import redmagic.helpers.GlassesHelper;
import redmagic.items.ItemGlasses;
import redmagic.tileentities.TileEntitySoulForge;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSoulForge extends BlockContainer{

	public Icon side, top;
	
	protected BlockSoulForge(int par1){
		super(par1, Material.piston);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SOUL_FORGE_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulForge();
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = side = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_FORGE_NAME + "_side");
		top = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_FORGE_NAME + "_top");
	}
	
	@Override
	public Icon getIcon(int par1, int par2){
		if(par1 == 1)return top;
        return side;
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        ItemStack current = player.getCurrentEquippedItem();
        
        if(current != null && current.getItem() instanceof ISoul){
        	TileEntitySoulForge forge = (TileEntitySoulForge) world.getBlockTileEntity(x, y, z);
        	if(!forge.hasSoul()){
        		forge.storeSoul(current);
        		if(!player.capabilities.isCreativeMode)player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        	}
        }
        if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() instanceof ItemGlasses && GlassesHelper.getMode(player.getCurrentArmor(3)) != GlassesHelper.OFFLINE){
        	TileEntitySoulForge forge = (TileEntitySoulForge) world.getBlockTileEntity(x, y, z);
        	forge.view(player, player.getCurrentArmor(3));
        }
        
		return false;
    }
	
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	if(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)){
    		((TileEntitySoulForge)par1World.getBlockTileEntity(par2, par3, par4)).forge();
    	}
    }

}
