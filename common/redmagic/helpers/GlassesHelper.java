package redmagic.helpers;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Vector3f;

import redmagic.Redmagic;
import redmagic.blocks.BlockManager;
import redmagic.client.renderers.glasses.*;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.lib.tree.SoulBlock;
import redmagic.tileentities.tree.TileEntityTreeWood;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;

public class GlassesHelper {
	public static final String[] MODES = new String[]{
		"offline",
		"online",
		"mining"
	};
	
	public static final int SWITCHING_FINISHED = 10;
	
	public static final int OFFLINE = 0;
	public static final int ONLINE = 1;
	public static final int MINING = 2;
	
	public static void setMode(ItemStack stack, int mode){
		ItemHelper.setInteger(stack, "mode", mode);
	}
	
	public static int getMode(ItemStack stack){
		return ItemHelper.getInteger(stack, "mode");
	}
	
	public static void setSwitchingStatus(ItemStack stack, int status){
		ItemHelper.setInteger(stack, "status", status);
	}
	
	public static int getSwitchingStatus(ItemStack stack){
		return ItemHelper.getInteger(stack, "status");
	}
	
	public static void switchMode(ItemStack stack){
		int mode = getMode(stack);
		if(mode + 1 >= MODES.length)mode = 0;
		else mode++;
		Logger.log("switch mode to " + MODES[mode]);
		setMode(stack, mode);
		setSwitchingStatus(stack, 0);
	}
	
	public static GlassesRender getRenderByStack(ItemStack stack){
		int mode = getMode(stack);
		switch(mode){
		case OFFLINE:
			return new GlassesRenderOffline();
		case ONLINE:
			return new GlassesRenderOnline();
		case MINING:
			return new GlassesRenderMining();
		}
		return null;
	}
	
	public static void renderMachineName(EntityPlayer player, MovingObjectPosition position, int width, int height){
		if(position != null){
			TileEntity entity = player.worldObj.getBlockTileEntity(position.blockX, position.blockY, position.blockZ);
			if(entity instanceof TileEntityTreeWood){
				TileEntityTreeWood tree = (TileEntityTreeWood)entity;
				if(tree.hasSoul()){
					SoulBlock block = TreeHelper.loadStructure(player.worldObj, tree.structureID).storage.getBlockAt(entity.xCoord, entity.yCoord, entity.zCoord);
					if(block != null){
						int textWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(LogicIndex.SOUL_TYPES[SoulHelper.getType(block.soulStack)]);
						Minecraft.getMinecraft().fontRenderer.drawString(LogicIndex.SOUL_TYPES[SoulHelper.getType(block.soulStack)], width / 2 - textWidth / 2, height / 2 - 20, 9999999);
					}
				}
			}
		}
	}

	public static ItemStack renderOres(EntityPlayer player, int width, int height) {
		List<int[]> blocks = new ArrayList<int[]>();
		for(int i = -5; i <= 5; i++){
			for(int j = -5; j <= 5; j++){
				for(int k = -5; k <= 5; k++){
					if(player.worldObj.getBlockId((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k) == BlockManager.soulCrystalOre.blockID){
						blocks.add(new int[]{(int)player.posX + i, (int)player.posY + j, (int)player.posZ + k, player.worldObj.getBlockId((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k), player.worldObj.getBlockMetadata((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k)});
					}
				}
			}	
		}
		Iterator<int[]> it = blocks.iterator();
		int[] nearest = new int[5];
		while(it.hasNext()){
			int[] block = it.next();
			if(player.getDistance(block[0], block[1], block[2]) < player.getDistance(nearest[0], nearest[1], nearest[2])){
				nearest = block;
			}
		}
		if(nearest[3] != 0){
			Redmagic.proxy.addEffect("star", player.worldObj, player.posX, player.posY - 0.5, player.posZ, nearest[0] + 0.5, nearest[1] + 0.5, nearest[2] + 0.5);
			return new ItemStack(nearest[3], 1, nearest[4]);
		}
		return new ItemStack(Block.stone);
	}
}
