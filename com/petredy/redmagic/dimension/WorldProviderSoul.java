package com.petredy.redmagic.dimension;

import com.petredy.redmagic.lib.Dimensions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderSoul extends WorldProvider{

	public WorldProviderSoul(){
		super();
	}
	
	@Override
	public String getDimensionName() {
		return Dimensions.SOUL_DIMENSION_NAME;			
	}

	protected void registerWorldChunkManager(){
        worldChunkMgr = new ChunkManagerSoul(worldObj);
    }

    public IChunkProvider createChunkGenerator(){
        return new ChunkProviderSoul(worldObj);
    }
    
    public float calculateCelestialAngle(long par1, float par3){
        return 0.0F;
    }
    
    public float[] calcSunriseSunsetColors(float par1, float par2){
        return null;
    }
    
    public Vec3 getFogColor(float par1, float par2){
        int i = 10518688;
        float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = (float)(i >> 16 & 255) / 255.0F;
        float f4 = (float)(i >> 8 & 255) / 255.0F;
        float f5 = (float)(i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double)f3, (double)f4, (double)f5);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored(){
        return false;
    }
    
    public boolean isSurfaceWorld(){
        return false;
    }
    
    public float getCloudHeight(){
        return 0.0F;
    }
    
    public int getAverageGroundLevel(){
        return 128;
    }
    
    public boolean doesXZShowFog(int par1, int par2){
        return true;
    }
    
    public ChunkCoordinates getSpawnPoint(){
        return new ChunkCoordinates(0, 129, 0);
    }
	
}
