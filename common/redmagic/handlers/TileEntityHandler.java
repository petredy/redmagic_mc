package redmagic.handlers;

import redmagic.tileentities.*;
import redmagic.tileentities.bank.TileEntityBank;
import redmagic.tileentities.education.TileEntityExtractorBasic;
import redmagic.tileentities.education.TileEntityExtractorCollector;
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
		
		GameRegistry.registerTileEntity(TileEntityExtractorCollector.class, "redmagic.TileEntityExtractorCollector");
		GameRegistry.registerTileEntity(TileEntityExtractorBasic.class, "redmagic.TileEntityExtractorBasic");

		GameRegistry.registerTileEntity(TileEntityCollector.class, "redmagic.TileEntityCollector");
		
		GameRegistry.registerTileEntity(TileEntityBlockEntity.class, "redmagic.TileEntityBlockEntity");
		GameRegistry.registerTileEntity(TileEntityBank.class, "redmagic.TileEntityBank");
		GameRegistry.registerTileEntity(TileEntitySoulTrap.class, "redmagic.TileEntitySoulTrap");
		GameRegistry.registerTileEntity(TileEntityWorkTable.class, "redmagic.TileEntityWorkTable");
	}
}
