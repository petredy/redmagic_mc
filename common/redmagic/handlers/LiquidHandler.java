package redmagic.handlers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.blocks.BlockManager;
import redmagic.configuration.Texture;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidHandler {
	public static LiquidStack essence;
	
	public static void init(){
		essence = LiquidDictionary.getOrCreateLiquid("Essence", new LiquidStack(BlockManager.essence, 1));
		
	}
	
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
		if (event.map == Minecraft.getMinecraft().renderEngine.textureMapBlocks) {
			LiquidDictionary.getCanonicalLiquid("Essence").setRenderingIcon(BlockManager.essence.getBlockTextureFromSide(1)).setTextureSheet(Texture.ESSENCE);
		} else {
			// Items
		}
	}
}
