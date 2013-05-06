package redmagic.tileentities;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockEntity extends TileEntity{
	public int side;
	public int type;
	
	public TileEntityBlockEntity(int meta){
		this.type = meta;
	}

}
