package redmagic.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.helpers.BlockHelper;

public class ItemSoulAxe extends ItemFlintIronAxe{
	
	public ItemSoulAxe(int par1){
		super(par1);
		this.setUnlocalizedName(ItemIndex.SOUL_AXE_NAME);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SOUL_AXE_NAME);
	}
	
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
		super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
		if(par7EntityLiving instanceof EntityPlayer && !((EntityPlayer)par7EntityLiving).isSneaking()){
			this.destroyAdjacentWoodAndLeave(par2World, par1ItemStack, par4 + 1, par5, par6, par7EntityLiving, 1);
			this.destroyAdjacentWoodAndLeave(par2World, par1ItemStack, par4 - 1, par5, par6, par7EntityLiving, 1);
			this.destroyAdjacentWoodAndLeave(par2World, par1ItemStack, par4, par5 + 1, par6, par7EntityLiving, 1);
			this.destroyAdjacentWoodAndLeave(par2World, par1ItemStack, par4, par5 -1, par6, par7EntityLiving, 1);
			this.destroyAdjacentWoodAndLeave(par2World, par1ItemStack, par4, par5, par6 + 1, par7EntityLiving, 1);
			this.destroyAdjacentWoodAndLeave(par2World, par1ItemStack, par4, par5, par6 - 1, par7EntityLiving, 1);
		}
		return true;
    }

	private void destroyAdjacentWoodAndLeave(World world, ItemStack stack, int x, int y, int z, EntityLiving entity, int layer) {
		int id = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		if(layer < 10 && stack.getItemDamage() < stack.getMaxDamage() && id > 0 && !Block.blocksList[id].hasTileEntity(metadata) && (Block.blocksList[id].blockMaterial == Material.wood || world.getBlockMaterial(x, y, z) == Material.leaves)){
			BlockHelper.breakBlock(world, x, y, z, 10000, true);
			stack.damageItem(1, entity);
			this.destroyAdjacentWoodAndLeave(world, stack, x + 1, y, z, entity, layer + 1);
			this.destroyAdjacentWoodAndLeave(world, stack, x - 1, y, z, entity, layer + 1);
			this.destroyAdjacentWoodAndLeave(world, stack, x, y + 1, z, entity, layer + 1);
			this.destroyAdjacentWoodAndLeave(world, stack, x, y - 1, z, entity, layer + 1);
			this.destroyAdjacentWoodAndLeave(world, stack, x, y, z + 1, entity, layer + 1);
			this.destroyAdjacentWoodAndLeave(world, stack, x, y, z - 1, entity, layer + 1);
		}
	}
}
