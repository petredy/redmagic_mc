package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.knowledge.IArtifact;
import com.petredy.redmagic.client.guis.knowledge.GuiKnowledges;
import com.petredy.redmagic.items.ItemGlasses;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;
import com.petredy.redmagic.utils.GlassesUtils;
import com.petredy.redmagic.utils.InventoryUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockKnowledgeTransceiver extends BlockContainer{

	protected Icon open;
	
	protected BlockKnowledgeTransceiver(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.KNOWLEDGE_TRANSCEIVER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.KNOWLEDGE_TRANSCEIVER_NAME);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
        ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
        TileEntityKnowledgeTransceiver entity = (TileEntityKnowledgeTransceiver) par1World.getBlockTileEntity(par2, par3, par4);
        if(entity != null){
	        if(current != null && current.getItem() instanceof IArtifact && entity.artifact == null){
	        	entity.artifact = current.copy();
	        	par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
	        }else if(current == null && entity.artifact != null){
	        	par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, entity.artifact.copy());
	        	entity.artifact = null;
	        }else{
	        	Minecraft.getMinecraft().displayGuiScreen(new GuiKnowledges(entity));
	        }
	        return true;
        }
		return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityKnowledgeTransceiver();
	}

	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		TileEntityKnowledgeTransceiver entity = (TileEntityKnowledgeTransceiver) par1World.getBlockTileEntity(par2, par3, par4);
		if(entity != null && entity.artifact != null)InventoryUtils.dropItemStack(entity.artifact, par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	
}
