package com.petredy.redmagic.blocks;

import java.util.List;
import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.IScrewdriver;
import com.petredy.redmagic.api.machinery.IMachineIconProvider;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.machinery.IconHandler;
import com.petredy.redmagic.machinery.MachineHandler;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketUpdateMachineOnSide;
import com.petredy.redmagic.tileentities.TileEntityMachinery;
import com.petredy.redmagic.tileentities.TileEntityMachineryCore;
import com.petredy.redmagic.tileentities.TileEntityMachineryDrive;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockMachinery extends BlockContainer {

	protected IconHandler[] iconHandlers = new IconHandler[MachineHandler.getMachineAmount()];
	protected Icon core, drive, hull, coreInactive, driveInactive, hullInactive;
	
	protected BlockMachinery(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.MACHINERY_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		switch(metadata){
			case BlockIndex.MACHINERY_CORE_METADATA: return new TileEntityMachineryCore();
			case BlockIndex.MACHINERY_DRIVE_METADATA: return new TileEntityMachineryDrive();
			case BlockIndex.MACHINERY_HULL_METADATA: return new TileEntityMachinery();
			default: return createNewTileEntity(world);
		}
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		core = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINERY_NAME + "." + BlockIndex.MACHINERY_CORE_NAME + ".active");
		coreInactive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINERY_NAME + "." + BlockIndex.MACHINERY_CORE_NAME + ".inactive");
		drive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINERY_NAME + "." + BlockIndex.MACHINERY_DRIVE_NAME + ".active");
		driveInactive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINERY_NAME + "." + BlockIndex.MACHINERY_DRIVE_NAME + ".inactive");
		hull = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINERY_NAME + "." + BlockIndex.MACHINERY_HULL_NAME + ".active");
		hullInactive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINERY_NAME + "." + BlockIndex.MACHINERY_HULL_NAME + ".inactive");
		
		for(int count = 0; count < MachineHandler.getMachineAmount(); count++){
        	try{
        		Machine machine = MachineHandler.getMachine(count);
        		if(machine != null){
        			this.iconHandlers[count] = new IconHandler(count, par1IconRegister, (IMachineIconProvider)machine);
        		}
        	}catch(Exception e){
        		LogUtils.warning("Machine " + count + " can't initialise IconHandler");
        	}
		}
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.getMultiIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4), par1IBlockAccess.getBlockTileEntity(par2, par3, par4));
    }
	
	private Icon getMultiIcon(int par5, int blockMetadata, TileEntity blockTileEntity) {
		switch(blockMetadata){
			case BlockIndex.MACHINERY_HULL_METADATA: return getIconForHull(par5, blockMetadata, (TileEntityMachinery)blockTileEntity);
			case BlockIndex.MACHINERY_DRIVE_METADATA: return getIconForDrive(par5, blockMetadata, (TileEntityMachineryDrive)blockTileEntity);
			case BlockIndex.MACHINERY_CORE_METADATA: return core;
			default: return getIcon(par5, blockMetadata);
		}
	}
	
	private Icon getIconForDrive(int side, int blockMetadata, TileEntityMachineryDrive entity) {
		if(entity.reference != null){
			TileEntity tmp = entity.worldObj.getBlockTileEntity(entity.reference.x, entity.reference.y, entity.reference.z);
			if(tmp instanceof TileEntityMachineryCore){
				TileEntityMachineryCore core = (TileEntityMachineryCore)tmp;
				Machine machine = core.getMachineOnSide(side);
				if(machine != null){
					IconHandler iconHandler = iconHandlers[machine.getMetadata()];
					int position = getPositionForBlockBySide(entity.position, side);
					if(position >= 0 && position < 9){
						if(machine.active)return iconHandler.getLargeMachineActiveForPosition(position);
						else return iconHandler.getLargeMachineInactiveForPosition(position);
					}
				}else{
					return drive;
				}
			}else{
				entity.reset();
			}
		}
		return driveInactive;
	}

	private Icon getIconForHull(int side, int blockMetadata, TileEntityMachinery entity) {
		if(entity.reference != null){
			TileEntity tmp = entity.worldObj.getBlockTileEntity(entity.reference.x, entity.reference.y, entity.reference.z);
			if(tmp instanceof TileEntityMachineryCore){
				TileEntityMachineryCore core = (TileEntityMachineryCore)tmp;
				Machine machine = core.getMachineOnSide(side);
				if(machine != null){
					IconHandler iconHandler = iconHandlers[machine.getMetadata()];
					int position = getPositionForBlockBySide(entity.position, side);
					if(position >= 0 && position < 9){
						if(machine.active)return iconHandler.getLargeMachineActiveForPosition(position);
						else return iconHandler.getLargeMachineInactiveForPosition(position);
					}
				}else{
					return hull;
				}
			}else{
				entity.reset();
			}
		}
		return hullInactive;
	}

	private int getPositionForBlockBySide(int position, int side) {
		switch(side){
		case 1: 
			return position - 8;
		case 2:
			switch(position){
			case 2: return 3;
			case 1: return 4;
			case 0: return 5;
			case 10: return 0;
			case 9: return 1;
			case 8: return 2;
			}
			break;
		case 3:
			switch(position){
			case 5: return 3;
			case 6: return 4;
			case 7: return 5;
			case 14: return 0;
			case 15: return 1;
			case 16: return 2;
			}
			break;
		case 4: 
			switch(position){
			case 0: return 3;
			case 3: return 4;
			case 5: return 5;
			case 8: return 0;
			case 11: return 1;
			case 14: return 2;
			}
			break;
		case 5:
			switch(position){
			case 7: return 3;
			case 4: return 4;
			case 2: return 5;
			case 16: return 0;
			case 13: return 1;
			case 10: return 2;
			}
			break;
		}
		return 0;
	}

	public Icon getIcon(int side, int metadata)
    {
		switch(metadata){
		case BlockIndex.MACHINERY_CORE_METADATA: return coreInactive;
		case BlockIndex.MACHINERY_DRIVE_METADATA: return driveInactive;
		case BlockIndex.MACHINERY_HULL_METADATA: return hullInactive;
		default: return blockIcon;
		}
    }

	public int idDropped(int metadata, Random par2Random, int par3)
    {
		return this.blockID;
    }
	
	public int damageDropped(int metadata)
    {
		return metadata;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockMachinery.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		if(metadata == BlockIndex.MACHINERY_CORE_METADATA)return onCoreActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
		else if(metadata == BlockIndex.MACHINERY_HULL_METADATA)return onHullActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
		else if(metadata == BlockIndex.MACHINERY_DRIVE_METADATA)return onDriveActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
		return false;
    }

	private boolean onDriveActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ) {
		TileEntityMachinery machinery = (TileEntityMachinery) world.getBlockTileEntity(x, y, z);
		ItemStack current = player.getCurrentEquippedItem();
		if(machinery != null && machinery.reference != null){
			TileEntity entity = world.getBlockTileEntity(machinery.reference.x, machinery.reference.y, machinery.reference.z);
			if(entity instanceof TileEntityMachineryCore){
				TileEntityMachineryCore core = (TileEntityMachineryCore)entity;
				if(current != null && current.getItem() instanceof IMachineItem){
					
					// Adds machine
					PacketDispatcher.sendPacketToAllInDimension(PacketHandler.populatePacket(new PacketUpdateMachineOnSide(core.xCoord, core.yCoord, core.zCoord, side, ((IMachineItem)current.getItem()).getMetadata(current), true)), world.provider.dimensionId);
					if(!world.isRemote)core.addMachine(((IMachineItem)current.getItem()).getMetadata(current), side, player);
				}else if(current != null && current.getItem() instanceof IScrewdriver){
					if(player.isSneaking()){
						
						// Removes machine
						PacketDispatcher.sendPacketToAllInDimension(PacketHandler.populatePacket(new PacketUpdateMachineOnSide(core.xCoord, core.yCoord, core.zCoord, side, 0, false)), world.provider.dimensionId);
						if(!world.isRemote)core.removeMachine(side, player);
					}else{
						// Tribological gui
						player.openGui(Redmagic.instance, Guis.TRIBOLOGICAL, world, core.xCoord, core.yCoord, core.zCoord);
					}
				}else{
					return core.activate(side, player, offX, offY, offZ);
				}
			}
		}
		return false;
	}

	private boolean onHullActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ) {
		TileEntityMachinery machinery = (TileEntityMachinery) world.getBlockTileEntity(x, y, z);
		ItemStack current = player.getCurrentEquippedItem();
		if(machinery != null && machinery.reference != null){
			TileEntity entity = world.getBlockTileEntity(machinery.reference.x, machinery.reference.y, machinery.reference.z);
			if(entity instanceof TileEntityMachineryCore){
				TileEntityMachineryCore core = (TileEntityMachineryCore)entity;
				return core.activate(side, player, offX, offY, offZ);
			}
		}
		return false;
	}

	private boolean onCoreActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.MACHINE, world, x, y, z);
		return true;
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		if(par6 == BlockIndex.MACHINERY_CORE_METADATA){
			TileEntityMachineryCore core = (TileEntityMachineryCore) par1World.getBlockTileEntity(par2, par3, par4);
			if(core != null){
				core.removeAllMachines();
			}
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

}
