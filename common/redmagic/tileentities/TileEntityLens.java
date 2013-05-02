package redmagic.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.core.Logger;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumSkyBlock;

public class TileEntityLens extends TileEntity{
	
	public int count = 0, need = 50;
	public boolean active = false;
	
	public void updateEntity(){
		if(count >= need){
			count = 0;
			this.update();
		}
		count++;
	}

	private void update() {
		if(this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord) &&
				this.worldObj.isAirBlock(xCoord, yCoord - 1, zCoord) &&
				this.worldObj.isAirBlock(xCoord, yCoord - 2, zCoord) &&
				this.worldObj.isAirBlock(xCoord, yCoord - 3, zCoord)){
			active = true;
			this.worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord, 15);
		}else{
			this.worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord, 1);
			active = false;
		}
	} 
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;

        return bb;
    }
	
}
