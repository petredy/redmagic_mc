package redmagic.addons;

import redmagic.core.Logger;
import net.minecraft.src.ModLoader;

public class BuildcraftAddon {
	public static void init(){
		
		if(ModLoader.isModLoaded("BuildCraft|Core")){
			Logger.log("BuildCraft was found.");
			
			
			
		}else{
			Logger.log("Buildcraft was not found. There will be problems while playing.");
		}
		
	}
	
	public static void registerRecipes(){
		
	}
}
