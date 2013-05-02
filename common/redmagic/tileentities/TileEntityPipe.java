package redmagic.tileentities;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import redmagic.api.essence.IPipe;
import redmagic.api.essence.IStorage;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.PipeHelper;

public class TileEntityPipe extends TileEntityStorage implements IPipe{
	
	public static final int MODE_PIPE = 0;
	public static final int MODE_EXTRACTOR = 1;
	public static final int MODE_FILLER = 2;
	
	public int mode = 0;
	public int essencesPerTick;
	public boolean left, right, back, front, bottom, top;
	
	public TileEntityPipe(){
		super(LogicIndex.PIPE_MAX_ESSENCES);
		this.essencesPerTick = LogicIndex.PIPE_ESSENCES_PER_TICK;
	}
	
	
	public void updateEntity(){
		super.updateEntity();
		if(this.isExtractor()){
			this.updateAsExtractor();
			this.updateAsPipe();
		}else if(this.isFiller()){
			this.updateAsFiller();
		}else{
			this.updateAsPipe();
		}
	}
	
	private void updateAsExtractor() {
		ITankContainer[] storages = this.getConnectedStorages();
		if(storages != null){
			int essencesPerConnection = this.essencesPerTick / storages.length;
			int count = 0;
			while(count < storages.length && this.getEssences() < this.getMaxEssences()){
				if(storages[count] != null){
					int rest = this.extractEssence(storages[count], essencesPerConnection, ForgeDirection.getOrientation(count));
					LiquidStack stack = this.tank.getLiquid();
					if(stack != null){
						stack = stack.copy();
						stack.amount = rest;
					}
					storages[count].fill(ForgeDirection.UNKNOWN, stack, true);
					essencesPerConnection += rest / storages.length - count;
				}
				count++;
			}
		}
	}


	private void updateAsFiller() {
		ITankContainer[] storages = this.getConnectedStorages();
		if(storages != null && this.getEssences() > 0 ){
			int essencesPerConnection = ((this.essencesPerTick > this.getEssences())? this.getEssences() : this.essencesPerTick) / storages.length;
			int count = 0;
			while(count < storages.length && this.getEssences() > 0){
				if(storages[count] != null){
					LiquidStack rest = this.fillEssence(storages[count], essencesPerConnection, ForgeDirection.getOrientation(count));
					if(rest != null){
						this.store(rest);
						essencesPerConnection += rest.amount / (storages.length - count);
					}
				}
				count++;
			}
		}
	}


	private void updateAsPipe() {
		PipeHelper[] pipes = this.getConnectedPipes();
		if(pipes != null && this.getEssences() > 0){
			int essencesPerConnection = ((this.essencesPerTick > this.getEssences()) ? this.getEssences() : this.essencesPerTick) / pipes.length;
			int count = 0;
			while(count < pipes.length && this.getEssences() > 0){
				LiquidStack rest = this.transportEssence(pipes[count], essencesPerConnection);
				if(rest != null){
					this.store(rest);
					essencesPerConnection += rest.amount / (pipes.length - count);
				}
				count++;
			}
		}
		
	}


	@Override
	public PipeHelper[] getConnectedPipes() {
		PipeHelper[] pipes = new PipeHelper[6];
		int count = 0;
		PipeHelper pipeLeft = new PipeHelper(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord);
		if(pipeLeft.isPipe())pipes[count++] = pipeLeft;
		PipeHelper pipeRight = new PipeHelper(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord);
		if(pipeRight.isPipe())pipes[count++] = pipeRight;
		PipeHelper pipeBack = new PipeHelper(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1);
		if(pipeBack.isPipe())pipes[count++] = pipeBack;
		PipeHelper pipeFront = new PipeHelper(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1);
		if(pipeFront.isPipe())pipes[count++] = pipeFront;
		PipeHelper pipeBottom = new PipeHelper(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord);
		if(pipeBottom.isPipe())pipes[count++] = pipeBottom;
		PipeHelper pipeTop = new PipeHelper(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord);
		if(pipeTop.isPipe())pipes[count++] = pipeTop;
		
		if(count > 0){
			PipeHelper[] rtnPipes = new PipeHelper[count];
			int tmpCount = 0;
			for(int i = 0; i < pipes.length; i++){
				if(pipes[i] != null){
					rtnPipes[tmpCount++] = pipes[i];
				}
			}
			return rtnPipes;
		}
		
		return null;
	}


	@Override
	public ITankContainer[] getConnectedStorages() {
		ITankContainer[] storages = new ITankContainer[6];
		
		if(this.isStorage(this.xCoord, this.yCoord - 1, this.zCoord))storages[0] = this.getStorage(this.xCoord, this.yCoord -1, this.zCoord);
		if(this.isStorage(this.xCoord, this.yCoord + 1, this.zCoord))storages[1] = this.getStorage(this.xCoord, this.yCoord + 1, this.zCoord);
		if(this.isStorage(this.xCoord, this.yCoord, this.zCoord - 1))storages[2] = this.getStorage(this.xCoord, this.yCoord, this.zCoord - 1);
		if(this.isStorage(this.xCoord, this.yCoord, this.zCoord + 1))storages[3] = this.getStorage(this.xCoord, this.yCoord, this.zCoord + 1);
		if(this.isStorage(this.xCoord + 1, this.yCoord, this.zCoord))storages[4] = this.getStorage(this.xCoord + 1, this.yCoord, this.zCoord);
		if(this.isStorage(this.xCoord - 1, this.yCoord, this.zCoord))storages[5] = this.getStorage(this.xCoord - 1, this.yCoord, this.zCoord);
		
		return storages;
	}
	
	private ITankContainer getStorage(int x, int y, int z) {
		TileEntity entity = this.worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof ITankContainer)return (ITankContainer) entity;
		return null;
	}


	private boolean isStorage(int x, int y, int z){
		TileEntity entity = this.worldObj.getBlockTileEntity(x, y, z);
		return entity != null && entity instanceof ITankContainer && !(entity instanceof IPipe);
	}


	@Override
	public boolean isExtractor() {
		return this.mode == MODE_EXTRACTOR;
	}


	@Override
	public boolean isFiller() {
		return this.mode == MODE_FILLER;
	}


	@Override
	public void switchMode() {
		this.mode++;
		if(this.mode > MODE_FILLER)this.mode = MODE_PIPE;
		Logger.log("Switch to " + this.mode);
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		PacketDispatcher.sendPacketToAllPlayers(new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag));

		
	}


	@Override
	public int extractEssence(ITankContainer storage, int essencesPerConnection, ForgeDirection direction) {
		if(storage != null){
			LiquidStack stack = storage.drain(direction, essencesPerConnection, true);
			if(stack != null  && stack.amount > 0){
				int stored = this.store(stack);
				return essencesPerConnection - stored;
			}
		}
		return 0;
	}


	@Override
	public LiquidStack transportEssence(PipeHelper pipe, int essencesPerConnection) {
		if(pipe != null){
			LiquidStack extracted = this.extract(essencesPerConnection);
			if(extracted != null && extracted.amount > 0){
				int stored = pipe.store(extracted);
				extracted.amount -= stored;
				return extracted;
			}
		}
		return null;
	}
	
	@Override
	public LiquidStack fillEssence(ITankContainer storage, int essencesPerConnection, ForgeDirection direction){
		if(storage != null){
			LiquidStack extracted = this.extract(essencesPerConnection);
			Logger.log(extracted);
			if(extracted != null && extracted.amount > 0){
				int stored = storage.fill(direction, extracted, true);
				extracted.amount -= stored;
				return extracted;
			}
		}
		return null;
	}


	@Override
	public boolean isConnectedOnSide(ForgeDirection side) {
		if(side == ForgeDirection.DOWN && bottom)return true;
		if(side == ForgeDirection.UP && top)return true;
		if(side == ForgeDirection.EAST && right)return true;
		if(side == ForgeDirection.WEST && left)return true;
		if(side == ForgeDirection.NORTH && back)return true;
		if(side == ForgeDirection.SOUTH && front)return true;
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
    {
		super.readFromNBT(tag);
		this.mode = tag.getInteger("redmagic_mode");
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
    {
		super.writeToNBT(tag);
		tag.setInteger("redmagic_mode", this.mode);
    }
	
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
    }
}
