package redmagic.tileentities;

import cpw.mods.fml.common.network.PacketDispatcher;
import redmagic.api.essence.IStorage;
import redmagic.blocks.BlockManager;
import redmagic.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityStorage extends TileEntity implements IStorage, ITankContainer{

	private int maxEssences;
	public int updateCount = 0;
	public int updateNeed = 50;
	public LiquidTank tank;
	
	public TileEntityStorage(int maxEssences){
		tank = new LiquidTank(maxEssences);
		this.maxEssences = maxEssences;
	}
	
	@Override
	public void updateEntity(){
		if(updateCount == updateNeed){
			NBTTagCompound tag = new NBTTagCompound();
			this.writeToNBT(tag);
			PacketDispatcher.sendPacketToAllPlayers(new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag));
			updateCount = 0;
		}
		updateCount++;
	}
	
	@Override
	public int store(LiquidStack resource, Object... data) {
		int essences = this.getEssences();
		int stored = tank.fill(resource, true);
		if(this.getEssences() > this.getMaxEssences()){
			LiquidStack liquid = tank.getLiquid();
			liquid.amount = this.getMaxEssences();
			tank.setLiquid(liquid);
			return this.getMaxEssences() - essences;
		}
		return stored;
	}

	@Override
	public LiquidStack extract(int amount, Object... data) {
		return tank.drain(amount, true);
	}

	@Override
	public int getEssences(Object... data) {
		return this.tank.getLiquid() != null ? this.tank.getLiquid().amount : 0;
	}

	@Override
	public int getMaxEssences() {
		return this.tank.getCapacity();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
    {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
    {
		super.writeToNBT(tag);
		this.tank.writeToNBT(tag);
    }

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[]{tank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return tank;
	}

}
