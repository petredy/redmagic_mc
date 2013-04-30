package redmagic.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.core.Logger;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

public class TileEntityLens extends TileEntity{
	
	public int count = 0, need = 1000;
	
	public void updateEntity(){
		if(count >= need){
			count = 0;
			this.update();
		}
		count++;
	}

	private void update() {
		if(this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)){
			
			Logger.log(this.worldObj.isDaytime());
			if(this.worldObj.isDaytime()){
				this.updateDay();
			}else{
				this.updateNight();
			}
		}
	}

	private void updateDay() {
		TileEntity entity = this.worldObj.getBlockTileEntity(xCoord, yCoord - 4, zCoord);
		Logger.log(entity);
		if(entity instanceof TileEntityCollector){
			TileEntityCollector collector = (TileEntityCollector)entity;
			collector.getDayLight();
		}
	}

	private void updateNight() {
		TileEntity entity = this.worldObj.getBlockTileEntity(xCoord, yCoord - 4, zCoord);
		if(entity instanceof TileEntityCollector){
			TileEntityCollector collector = (TileEntityCollector)entity;
			collector.getMoonLight();
		}
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;

        return bb;
    }
	
}
