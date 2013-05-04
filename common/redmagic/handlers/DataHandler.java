package redmagic.handlers;

import redmagic.configuration.BankIndex;
import redmagic.core.Logger;
import redmagic.api.bank.*;
import net.minecraftforge.common.Configuration;

public class DataHandler {
	
	public static void init(Configuration config){
		
	}

	public static void loadDefault() {
		Logger.log("Didn't found BankData. Default will be used.");
		// Stone
		BankData.register(1, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Grass Block
		BankData.register(2, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Dirt
		BankData.register(3, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Cobblestone
		BankData.register(4, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Planks
		BankData.register(5, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 16F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(5, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 16F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(5, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 16F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(5, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 16F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Sapling
		BankData.register(6, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(6, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(6, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(6, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Sand
		BankData.register(12, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Gravel
		BankData.register(13, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Gold Ore
		BankData.register(14, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
		// Iron Ore
		BankData.register(15, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Wood
		BankData.register(17, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(17, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(17, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(17, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);
		// Leaves
		BankData.register(18, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Glass
		BankData.register(20, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 32F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Lapis Block
		BankData.register(22, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);
		// Dispenser
		BankData.register(23, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, BankIndex.TAX, BankIndex.BUYING);
		// Sandstone
		BankData.register(24, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 75F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// NoteBlock
		BankData.register(25, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, 0.1F, BankIndex.BUYING);
		// Bed
		BankData.register(26, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
		// Powered Rail
		BankData.register(27, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F, BankIndex.TAX, BankIndex.BUYING);
		// Detector Rail
		BankData.register(28, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		// Sticky Pistion
		BankData.register(29, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F, BankIndex.TAX, BankIndex.BUYING);
		// Pistion
		BankData.register(33, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F, BankIndex.TAX, BankIndex.BUYING);
		// Wool
		BankData.register(35, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 6, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 7, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 8, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 9, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 10, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 11, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 12, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 13, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 14, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(35, 15, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
		// Yellow Flower
		BankData.register(37, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Rose
		BankData.register(38, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Brown Mushroom
		BankData.register(39, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 80F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Red Mushroom
		BankData.register(40, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 80F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Gold Block
		BankData.register(41, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);
		// Iron Block
		BankData.register(42, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		// Slaps
		BankData.register(44, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(44, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(44, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(44, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(44, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(44, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(44, 6, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Wooden Slabs
		BankData.register(126, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(126, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(126, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(126, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F / 64F, BankIndex.TAX, BankIndex.BUYING);

		
		
		// Bricks
		BankData.register(45, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		// TNT
		BankData.register(46, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);

		// Bookshelf TODO
		BankData.register(47, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0F, BankIndex.TAX, BankIndex.BUYING);

		// Moss Stone
		BankData.register(48, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Obsidian
		BankData.register(49, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);

		// Torch
		BankData.register(50, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Stairs
		BankData.register(53, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(53, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(53, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(53, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Cobblestone Stair
		BankData.register(67, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Brick Stairs
		BankData.register(108, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Stone Brick Stairs
		BankData.register(109, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Nether Brick Stairs
		BankData.register(114, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Sandstone Stairs
		BankData.register(128, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Wood Stairs
		BankData.register(134, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(135, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(136, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		
		// Chest
		BankData.register(54, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);
		
		// Diamond Ore
		BankData.register(56, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 200F, BankIndex.TAX, BankIndex.BUYING);

		// Diamond Block
		BankData.register(57, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1000F, BankIndex.TAX, BankIndex.BUYING);

		// Crafting Table
		BankData.register(58, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		// Ladders
		BankData.register(65, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		// Rail
		BankData.register(66, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, BankIndex.TAX, BankIndex.BUYING);

		// Lever
		BankData.register(69, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Stone Pressure Plate
		BankData.register(70, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Wooden Pressure Plate
		BankData.register(72, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 35F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Redstone Torch
		BankData.register(76, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Stone Button
		BankData.register(77, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Ice
		BankData.register(79, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);

		// Snow Block
		BankData.register(80, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 16F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Cactus
		BankData.register(81, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 16F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Clay Block
		BankData.register(82, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.4F, BankIndex.TAX, BankIndex.BUYING);

		// Juke Box
		BankData.register(84, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 150F, BankIndex.TAX, BankIndex.BUYING);

		// Fence
		BankData.register(85, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Pumpkin
		BankData.register(86, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Netherrack
		BankData.register(87, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Soul Sand
		BankData.register(88, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 32F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Glowstone
		BankData.register(89, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 52F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Jack o Lantern
		BankData.register(91, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 25F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Trapdoor
		BankData.register(96, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);

		// Stone Bricks
		BankData.register(98, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Iron Bars
		BankData.register(101, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);

		// Glass Pane
		BankData.register(102, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2.5F / 16, BankIndex.TAX, BankIndex.BUYING);

		// Melone
		BankData.register(103, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 54F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Fence Gate
		BankData.register(107, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 58F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Lily Pad
		BankData.register(111, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Nether Brick
		BankData.register(112, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Nether Brick Fence
		BankData.register(113, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 60F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Enchantment Table
		BankData.register(116, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 200F, BankIndex.TAX, BankIndex.BUYING);

		// End Portal Frame
		BankData.register(120, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 300F, BankIndex.TAX, BankIndex.BUYING);

		// End Stone
		BankData.register(121, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Dragon Egg
		BankData.register(122, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10000F, BankIndex.TAX, BankIndex.BUYING);

		// Redstone Lamp
		BankData.register(123, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2.5F, BankIndex.TAX, BankIndex.BUYING);

		// Ender Chest
		BankData.register(130, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);

		// Trip Wire Hook
		BankData.register(131, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Emerald Block
		BankData.register(132, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 300F, BankIndex.TAX, BankIndex.BUYING);

		// Beacon
		BankData.register(138, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 500F, BankIndex.TAX, BankIndex.BUYING);

		// Cobblestone Wall
		BankData.register(139, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Wooden Button
		BankData.register(140, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 18F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Anvil
		BankData.register(145, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);

		// ------------------
		// 1.5
		// ------------------
		
		//---------------------------------
		// Items
		//---------------------------------
		
		
		
		// Iron ingot
		BankData.register(265, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
	}
	
	
}
