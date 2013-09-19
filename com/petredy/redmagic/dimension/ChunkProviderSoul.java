package com.petredy.redmagic.dimension;

import java.util.List;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderSoul implements IChunkProvider {

	public World world;
	
	public ChunkProviderSoul(World world){
		this.world = world;
	}
	
	@Override
	public boolean chunkExists(int x, int y) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int y) {
		Chunk chunk = new Chunk(world, x, y);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public Chunk loadChunk(int x, int y) {
		return provideChunk(x,y);
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "ChunkProviderSoul";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
		return null;
	}

	@Override
	public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
		
	}

	@Override
	public void saveExtraData() {
		
	}

}
