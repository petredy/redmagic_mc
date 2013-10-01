package com.petredy.redmagic.utils;

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

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.client.render.glasses.GlassesRender;
import com.petredy.redmagic.client.render.glasses.GlassesRenderMining;
import com.petredy.redmagic.client.render.glasses.GlassesRenderOffline;
import com.petredy.redmagic.client.render.glasses.GlassesRenderOnline;
import com.petredy.redmagic.lib.Configs;
import com.petredy.redmagic.network.PacketEnergySyncRequest;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.Redkey;

import cpw.mods.fml.common.network.PacketDispatcher;

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
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ForgeDirection;

public class GlassesUtils {
	public static final String[] MODES = new String[]{
		"offline",
		"online",
		"mining"
	};
	
	public static final int SWITCHING_FINISHED = 12;
	
	public static final int OFFLINE = 0;
	public static final int ONLINE = 1;
	public static final int MINING = 2;
	
	public static void setMode(ItemStack stack, int mode){
		ItemUtils.setInteger(stack, "redmagic.glasses.mode", mode);
	}
	
	public static int getMode(ItemStack stack){
		return ItemUtils.getInteger(stack, "redmagic.glasses.mode");
	}
	
	public static void setSwitchingStatus(ItemStack stack, int status){
		ItemUtils.setInteger(stack, "redmagic.glasses.status", status);
	}
	
	public static int getSwitchingStatus(ItemStack stack){
		return ItemUtils.getInteger(stack, "redmagic.glasses.status");
	}
	
	public static void switchMode(ItemStack stack){
		int mode = getMode(stack);
		if(mode + 1 >= MODES.length)mode = 0;
		else mode++;
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


	public static ItemStack renderOres(EntityPlayer player, int width, int height) {
		List<int[]> blocks = new ArrayList<int[]>();
		for(int i = -5; i <= 5; i++){
			for(int j = -1; j <= 1; j++){
				for(int k = -5; k <= 5; k++){
					if(shouldFocusOnOre(player.worldObj.getBlockId((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k), player.worldObj.getBlockMetadata((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k))){
						blocks.add(new int[]{(int)player.posX + i, (int)player.posY + j, (int)player.posZ + k, player.worldObj.getBlockId((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k), player.worldObj.getBlockMetadata((int)player.posX + i, (int)player.posY + j, (int)player.posZ + k)});
					}
				}
			}	
		}
		Iterator<int[]> it = blocks.iterator();
		int[] nearest = new int[5];
		while(it.hasNext()){
			int[] block = it.next();
			if(player.worldObj.getBlockId(block[0], block[1], block[2]) == Blocks.cage.blockID){
				nearest = block;
				break;
			}
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
	
	public static boolean shouldFocusOnOre(int id, int metadata){
		for(int i = 0; i < Configs.GLASSES_ORE_DIC.length; i += 2){
			if(Configs.GLASSES_ORE_DIC[i] == id && Configs.GLASSES_ORE_DIC[i + 1] == metadata)return true;
		}
		return false;
	}

	public static int[] getOres() {
		List<Integer> ores = new ArrayList<Integer>();
		ores.add(14);
		ores.add(0);
		ores.add(15);
		ores.add(0);
		ores.add(16);
		ores.add(0);
		ores.add(21);
		ores.add(0);
		ores.add(56);
		ores.add(0);
		ores.add(73);
		ores.add(0);
		ores.add(74);
		ores.add(0);
		ores.add(129);
		ores.add(0);
		ores.add(153);
		ores.add(0);
		ores.add(Blocks.oreRhenium.blockID);
		ores.add(0);
		ores.add(Blocks.cage.blockID);
		ores.add(0);
		
		/*
		Logger.log(ores);
		ForestryAddon.addOres(ores);
		ThaumcraftAddon.addOres(ores);
		IndustrialCraftAddon.addOres(ores);
		*/
		int[] array = new int[ores.size()];
		Iterator<Integer> it = ores.iterator();
		int count = 0;
		while(it.hasNext()){
			Integer number = it.next();
			array[count++] = number;
		}
		
		return array;
	}
}
