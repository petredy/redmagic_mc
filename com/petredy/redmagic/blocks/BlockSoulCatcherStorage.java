package com.petredy.redmagic.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcherStorage;

public class BlockSoulCatcherStorage extends BlockSoulCatcher {

	protected BlockSoulCatcherStorage(int par1) {
		super(par1);
		this.setUnlocalizedName(BlockIndex.SOUL_CATCHER_STORAGE_NAME);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulCatcherStorage();
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = inactive = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_CATCHER_STORAGE_NAME + ".inactive");
		active = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_CATCHER_STORAGE_NAME + ".active");
	}

}
