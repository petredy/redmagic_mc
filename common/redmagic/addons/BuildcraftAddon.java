package redmagic.addons;

import redmagic.core.Logger;
import net.minecraft.src.ModLoader;

public class BuildcraftAddon {
	public static void init(){
		
		if(ModLoader.isModLoaded("BuildCraft|Core")){
			Logger.log("BuildCraft was found.");
			
			
			
		}
		
	}
	
	public static void registerRecipes(){
		
	}
}
