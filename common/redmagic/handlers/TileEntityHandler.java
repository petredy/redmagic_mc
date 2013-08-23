package redmagic.handlers;

import redmagic.tileentities.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void init(){
		/***
		 * ----------------------------------------------------------------------------------------------------------------------
		 * Templates 
		 */
		GameRegistry.registerTileEntity(TileEntityInventory.class, "redmagic.TileEntityInventory");
		
		// ----------------------------------------------------------------------------------------------------------------------

	}
}
