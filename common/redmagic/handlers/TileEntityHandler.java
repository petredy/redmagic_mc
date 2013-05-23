package redmagic.handlers;

import redmagic.tileentities.*;
import redmagic.tileentities.bank.TileEntityBank;
import redmagic.tileentities.extractor.TileEntityExtractorBasic;
import redmagic.tileentities.extractor.TileEntityExtractorCollector;
import redmagic.tileentities.tree.TileEntityTreeLeaves;
import redmagic.tileentities.tree.TileEntityTreeWood;
import redmagic.tileentities.tree.fragment.TileEntityFragmentTree;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void init(){
		
		GameRegistry.registerTileEntity(TileEntityStorage.class, "redmagic.TileEntityStorage");
		GameRegistry.registerTileEntity(TileEntityProducer.class, "redmagic.TileEntityProducer");
		GameRegistry.registerTileEntity(TileEntityConsumer.class, "redmagic.TileEntityConsumer");
		
		GameRegistry.registerTileEntity(TileEntityInventory.class, "redmagic.TileEntityInventory");
		
		GameRegistry.registerTileEntity(TileEntityPipe.class, "redmagic.TileEntityPipe");
		
		GameRegistry.registerTileEntity(TileEntityExtractorCollector.class, "redmagic.TileEntityExtractorCollector");
		GameRegistry.registerTileEntity(TileEntityExtractorBasic.class, "redmagic.TileEntityExtractorBasic");
		
		GameRegistry.registerTileEntity(TileEntityBlockEntity.class, "redmagic.TileEntityBlockEntity");
		GameRegistry.registerTileEntity(TileEntityBank.class, "redmagic.TileEntityBank");
		GameRegistry.registerTileEntity(TileEntityWorkTable.class, "redmagic.TileEntityWorkTable");
		GameRegistry.registerTileEntity(TileEntitySoulForge.class, "redmagic.TileEntitySoulForge");
		GameRegistry.registerTileEntity(TileEntityBag.class, "redmagic.TileEntityBag");
		
		GameRegistry.registerTileEntity(TileEntityTreeWood.class, "redmagic.TileEntityTreeWood");
		GameRegistry.registerTileEntity(TileEntityTreeLeaves.class, "redmagic.TileEntityTreeLeaves");
		GameRegistry.registerTileEntity(TileEntityFragmentTree.class, "redmagic.TileEntityFragmentTree");
	}
}
