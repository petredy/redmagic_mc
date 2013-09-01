package redmagic.handlers;

import redmagic.lib.gods.*;
import redmagic.lib.senses.PlayerSense;

public class GodHandler {

	/**
	 * Registers gods
	 */
	public static void init(){
		/**
		 * Gods 
		 */
		
		GodManager.registerGod(new LifeGod());
		
		
		/**
		 * Senses
		 */
		GodManager.registerSense(new PlayerSense());
	}
	
}
