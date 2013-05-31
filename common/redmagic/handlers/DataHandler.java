package redmagic.handlers;

import redmagic.configuration.BankIndex;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.ItemIndex;
import redmagic.core.Logger;
import redmagic.items.ItemManager;
import redmagic.addons.ForestryAddon;
import redmagic.addons.IndustrialCraftAddon;
import redmagic.addons.RailcraftAddon;
import redmagic.addons.ThaumcraftAddon;
import redmagic.api.bank.*;
import net.minecraftforge.common.Configuration;

public class DataHandler {
	
	public static void init(){
		
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
		BankData.register(20, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 7F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Lapis Block
		BankData.register(22, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4.5F, BankIndex.TAX, BankIndex.BUYING);
		// Dispenser
		BankData.register(23, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);
		// Sandstone
		BankData.register(24, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 75F / 64F, BankIndex.TAX, BankIndex.BUYING);
		// NoteBlock
		BankData.register(25, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);
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
		BankData.register(46, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);

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
		//BankData.register(53, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		//BankData.register(53, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		//BankData.register(53, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Cobblestone Stair
		BankData.register(67, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6 / 64F, BankIndex.TAX, BankIndex.BUYING);
		// Brick Stairs
		BankData.register(108, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		// Stone Brick Stairs
		BankData.register(109, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
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
		BankData.register(84, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);

		// Fence
		BankData.register(85, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Pumpkin
		BankData.register(86, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Netherrack
		BankData.register(87, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F / 64F, BankIndex.TAX, BankIndex.BUYING);

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
		BankData.register(101, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6 / 16F, BankIndex.TAX, BankIndex.BUYING);

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
		BankData.register(133, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 300F, BankIndex.TAX, BankIndex.BUYING);

		// Beacon
		BankData.register(138, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 500F, BankIndex.TAX, BankIndex.BUYING);

		// Cobblestone Wall
		BankData.register(139, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Wooden Button
		BankData.register(143, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 18F / 64F, BankIndex.TAX, BankIndex.BUYING);

		// Anvil
		BankData.register(145, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);

		// ------------------
		// 1.5
		// ------------------
		
		//Weighted Pressure Plate(Light)
		BankData.register(147, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Weighted Pressure Plate(Heavy)
		BankData.register(148, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);
		
		//Daylight Sensor
		BankData.register(151, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Block Redstone
		BankData.register(152, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
		
		//Nether Quartz Ore
		BankData.register(153, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
		
		//Hopper
		BankData.register(154, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);

		//Block Quartz
		BankData.register(155, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Quartz Stairs
		BankData.register(156, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6F, BankIndex.TAX, BankIndex.BUYING);
		
		//Activator Rail
		BankData.register(157, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Dropper
		BankData.register(158, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.1F, BankIndex.TAX, BankIndex.BUYING);		
		
		//---------------------------------
		//1.6
		//---------------------------------
		
		//Stained Clay
		//BankData.register(159, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Hay Block
		//BankData.register(170, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Carpet
		//BankData.register(171, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Hardened Clay
		//BankData.register(172, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Block Coal
		//BankData.register(173, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//---------------------------------
		// Items
		//---------------------------------
		
		//Iron Shovel
		BankData.register(256, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
		
		//Iron Pickaxe
		BankData.register(257, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Iron Axe
		BankData.register(258, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Flint and Steel
		BankData.register(259, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Apple
		BankData.register(260, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Bow
		BankData.register(261, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);
		
		//Arrow
		BankData.register(262, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Coal
		BankData.register(263, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.75F, BankIndex.TAX, BankIndex.BUYING);
		//Charcoal
		BankData.register(263, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond
		BankData.register(264, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);
		
		// Iron ingot
		BankData.register(265, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
		
		//Gold Ingot
		BankData.register(266, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Iron Sword
		BankData.register(267, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Wooden Sword
		BankData.register(268, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);

		//Wooden Shovel
		BankData.register(269, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.25F, BankIndex.TAX, BankIndex.BUYING);

		//Wooden Pickaxe
		BankData.register(270, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.75F, BankIndex.TAX, BankIndex.BUYING);

		//Wooden Axe
		BankData.register(271, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.75F, BankIndex.TAX, BankIndex.BUYING);

		//Stone Sword
		BankData.register(272, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Stone Shovel
		BankData.register(273, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Stone Pickaxe
		BankData.register(274, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Stone Axe
		BankData.register(275, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond Sword
		BankData.register(276, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 200F, BankIndex.TAX, BankIndex.BUYING);
		
		//Diamond Shovel
		BankData.register(277, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond Pickaxe
		BankData.register(278, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 300F, BankIndex.TAX, BankIndex.BUYING);
		
		//Diamond Axe
		BankData.register(279, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 300F, BankIndex.TAX, BankIndex.BUYING);

		//Stick
		BankData.register(280, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4 / 64F, BankIndex.TAX, BankIndex.BUYING);
		
		//Bowl
		BankData.register(281, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.75F, BankIndex.TAX, BankIndex.BUYING);

		//Mushroom Stew
		BankData.register(282, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Sword
		BankData.register(283, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
		
		//Golden Shovel
		BankData.register(284, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Pickaxe
		BankData.register(285, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Axe
		BankData.register(286, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
		
		//String
		BankData.register(287, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Feather
		BankData.register(288, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.1F, BankIndex.TAX, BankIndex.BUYING);
		
		//Gunpowder
		BankData.register(289, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
		
		//Wooden Hoe
		BankData.register(290, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);

		//Stone Hoe
		BankData.register(291, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8 / 64F, BankIndex.TAX, BankIndex.BUYING);
		
		//Iron Hoe
		BankData.register(292, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond Hoe
		BankData.register(293, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 200F, BankIndex.TAX, BankIndex.BUYING);
		
		//Golden Hoe
		BankData.register(294, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);

		//Seeds
		BankData.register(295, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.1F, BankIndex.TAX, BankIndex.BUYING);
		
		//Wheat
		BankData.register(296, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Bread
		BankData.register(297, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);
		
		//Leather Cap
		BankData.register(298, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 25F, BankIndex.TAX, BankIndex.BUYING);
		
		//Leather Tunic
		BankData.register(299, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);

		//Leather Pants
		BankData.register(300, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 35F, BankIndex.TAX, BankIndex.BUYING);

		//Leather Boots
		BankData.register(301, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);

		//Chain Helment
		BankData.register(302, 0, BankIndex.AMOUNT, !BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Chain Chestplate
		BankData.register(303, 0, BankIndex.AMOUNT, !BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Chain Leggings
		BankData.register(304, 0, BankIndex.AMOUNT, !BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Chain Boots
		BankData.register(305, 0, BankIndex.AMOUNT, !BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Iron Helmet
		BankData.register(306, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);

		//Iron Chestplate
		BankData.register(307, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);

		//Iron Leggings
		BankData.register(308, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 7F, BankIndex.TAX, BankIndex.BUYING);

		//Iron Boots
		BankData.register(309, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond Helmet
		BankData.register(310, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 500F, BankIndex.TAX, BankIndex.BUYING);
		
		//Diamond Chestplate
		BankData.register(311, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 800F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond Leggings
		BankData.register(312, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 700F, BankIndex.TAX, BankIndex.BUYING);

		//Diamond Boots
		BankData.register(313, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 400F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Helmet
		BankData.register(314, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Chestplate
		BankData.register(315, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 80F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Leggings
		BankData.register(316, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 70F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Boots
		BankData.register(317, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);
		
		//Flint
		BankData.register(318, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);

		//Raw Porkchop
		BankData.register(319, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Cooked Porkchop
		BankData.register(320, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.3F, BankIndex.TAX, BankIndex.BUYING);

		//Painting
		BankData.register(321, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Apple
		BankData.register(322, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F, BankIndex.TAX, BankIndex.BUYING);

		//Sign
		BankData.register(323, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Wooden Dor
		BankData.register(324, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Bucket
		BankData.register(325, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Water Bucket
		BankData.register(326, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3.2F, BankIndex.TAX, BankIndex.BUYING);

		//Lava Bucket
		BankData.register(327, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);

		//Minecart
		BankData.register(328, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);

		//Saddle
		BankData.register(329, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);
		
		//Iron Door
		BankData.register(330, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6F, BankIndex.TAX, BankIndex.BUYING);

		//Redstone
		BankData.register(331, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);

		//Snowball
		BankData.register(332, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Boat
		BankData.register(333, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Leather
		BankData.register(334, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
		
		//Milk
		BankData.register(335, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Brick
		BankData.register(336, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Clay
		BankData.register(337, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Sugar Canes
		BankData.register(338, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Paper
		BankData.register(339, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Book
		BankData.register(340, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6F, BankIndex.TAX, BankIndex.BUYING);

		//Slimeball
		BankData.register(341, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Chest-Minecart
		BankData.register(342, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

		//Furnace-Minecart
		BankData.register(343, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);

		//Egg
		BankData.register(344, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Compass
		BankData.register(345, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);

		//Fishing Rod
		BankData.register(346, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Clock
		BankData.register(347, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);

		//Glowstone Dust
		BankData.register(348, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Raw Fish
		BankData.register(349, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		
		//Cooked Fish
		BankData.register(350, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F, BankIndex.TAX, BankIndex.BUYING);

		//Dye
		BankData.register(351, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 6, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 7, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 8, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 9, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 10, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 11, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 12, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 13, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 14, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(351, 15, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
		
		//Bone
		BankData.register(352, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Sugar
		BankData.register(353, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Cake
		BankData.register(354, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Bed
		BankData.register(355, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Redstone Repeater
		BankData.register(356, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Cookie
		BankData.register(357, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Shears
		BankData.register(359, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

		//Melon
		BankData.register(360, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.4F, BankIndex.TAX, BankIndex.BUYING);

		//Pumpkin Seeds
		BankData.register(361, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.1F, BankIndex.TAX, BankIndex.BUYING);

		//Melon Seeds
		BankData.register(362, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

		//Raw Beef
		BankData.register(363, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
		
		//Steak
		BankData.register(364, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);

		//Raw Chicken
		BankData.register(365, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);

		//Cooked Chicken
		BankData.register(366, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);

		//Rotten Flesh
		BankData.register(367, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

		
		//Ender Pearl
		BankData.register(368, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 101F, BankIndex.TAX, BankIndex.BUYING);

		//Blaze Rod
		BankData.register(369, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);

		//Ghast Tear
		BankData.register(370, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, BankIndex.TAX, BankIndex.BUYING);

		//Gold Nugget
		BankData.register(371, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Nether Wart
		BankData.register(372, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Potions
		BankData.register(373, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
		//TODO
		
		//Glass Bottle
		BankData.register(374, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 7 / 64F, BankIndex.TAX, BankIndex.BUYING);

		//Spider Eye
		BankData.register(375, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Fermented Spider Eye
		BankData.register(376, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);

		//Blaze Powder
		BankData.register(377, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 25F, BankIndex.TAX, BankIndex.BUYING);

		//Magma Cream
		BankData.register(378, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, BankIndex.TAX, BankIndex.BUYING);

		//Brewing Stand
		BankData.register(379, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

		//Cauldron
		BankData.register(380, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);

		//Eye of Ender
		BankData.register(381, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 150F, BankIndex.TAX, BankIndex.BUYING);

		//Glistering Melon
		BankData.register(382, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Fire Charge
		BankData.register(385, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);

		//Book and Quill
		BankData.register(386, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);

		//Emerald
		//BankData.register(388, 0, BankIndex.AMOUNT, !BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Item Frame
		BankData.register(389, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

		//Flower Pot
		BankData.register(390, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Carrot
		BankData.register(391, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);

		//Potato
		BankData.register(392, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);

		//Baked Potato
		BankData.register(393, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);

		//Poisonous Potato
		BankData.register(394, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);

		//Empty Map
		BankData.register(395, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);

		//Golden Carrot
		BankData.register(396, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Mob head
		BankData.register(397, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(397, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(397, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(397, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(397, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);

		//Carrot on a Stick
		BankData.register(398, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);

		//Nether Star
		BankData.register(399, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1000F, BankIndex.TAX, BankIndex.BUYING);

		//Pumpkin Pie
		BankData.register(400, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Firework Rocket
		BankData.register(401, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Firework Star
		BankData.register(402, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Redstone Comparator
		BankData.register(404, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

		//Nether Brick
		BankData.register(405, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Nether Quartz
		BankData.register(406, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);

		//Minecart with TNT
		BankData.register(407, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, BankIndex.TAX, BankIndex.BUYING);

		//Minecart with Hopper
		BankData.register(408, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 14F, BankIndex.TAX, BankIndex.BUYING);

		//----------------------------------
		// 1.6
		//----------------------------------
		
		
		
		
		//-----------------------------------
		// Redmagic
		//-----------------------------------
		
		//Blocks
		BankData.register(BlockIndex.EXTRACTOR_ID, BlockIndex.EXTRACTOR_BASIC_METADATA, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(BlockIndex.EXTRACTOR_ID, BlockIndex.EXTRACTOR_COLLECTOR_METADATA, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(BlockIndex.PIPE_ID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(BlockIndex.SOUL_BLOCK_ID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 18F, BankIndex.TAX, BankIndex.BUYING);
		
		//Items
				
		BankData.register(ItemManager.axe.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.broom.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
		
		BankData.register(ItemManager.crafting.itemID, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.1F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.crafting.itemID, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.crafting.itemID, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		
		BankData.register(ItemManager.hoe.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.pickaxe.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.shovel.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		
		BankData.register(ItemManager.soulCrystal.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.soulNectar.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);
		BankData.register(ItemManager.stick.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
		
		//-------------------------------------------------
		// Addons
		//-------------------------------------------------
		ThaumcraftAddon.loadDefault();
		ForestryAddon.loadDefault();
		RailcraftAddon.loadDefault();
		IndustrialCraftAddon.loadDefault();
	}
	
	
}
