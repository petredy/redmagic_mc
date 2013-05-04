package redmagic.handlers;

import redmagic.tileentities.*;
import redmagic.tileentities.bank.TileEntityBank;
import redmagic.tileentities.education.TileEntityEducationBasic;
import redmagic.tileentities.machines.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void init(){
		
		GameRegistry.registerTileEntity(TileEntityStorage.class, "redmagic.TileEntityStorage");
		GameRegistry.registerTileEntity(TileEntityProducer.class, "redmagic.TileEntityProducer");
		GameRegistry.registerTileEntity(TileEntityConsumer.class, "redmagic.TileEntityConsumer");
		
		GameRegistry.registerTileEntity(TileEntityInventory.class, "redmagic.TileEntityInventory");
		
		GameRegistry.registerTileEntity(TileEntityMachineFilter.class, "redmagic.TileEntityMachineFilter");
		GameRegistry.registerTileEntity(TileEntityMachineFurnace.class, "redmagic.TileEntityMachineFurnace");
		GameRegistry.registerTileEntity(TileEntityMachineStorage.class, "redmagic.TileEntityMachineStorage");
		
		GameRegistry.registerTileEntity(TileEntityPipe.class, "redmagic.TileEntityPipe");
		
		GameRegistry.registerTileEntity(TileEntityEducationBasic.class, "redmagic.TileEntityEducationBasic");
		
		GameRegistry.registerTileEntity(TileEntityLens.class, "redmagic.TileEntityLens");
		GameRegistry.registerTileEntity(TileEntityCollector.class, "redmagic.TileEntityCollector");
		
		GameRegistry.registerTileEntity(TileEntityBank.class, "redmagic.TileEntityBank");
		GameRegistry.registerTileEntity(TileEntitySoulTrap.class, "redmagic.TileEntitySoulTrap");
		GameRegistry.registerTileEntity(TileEntityWorkTable.class, "redmagic.TileEntityWorkTable");
		GameRegistry.registerTileEntity(TileEntityCrystalizer.class, "redmagic.TileEntityCrystalizer");
	}
}
