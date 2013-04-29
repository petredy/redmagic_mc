package redmagic.tileentities;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
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
		this.maxEssences = LogicIndex.PIPE_MAX_ESSENCES;
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
		IStorage[] storages = this.getConnectedStorages();
		if(storages != null){
			int essencesPerConnection = this.essencesPerTick / storages.length;
			int count = 0;
			while(count < storages.length && this.essences < this.maxEssences){
				int rest = this.extractEssence(storages[count], essencesPerConnection);
				storages[count].store(rest);
				essencesPerConnection += rest / storages.length - count;
				count++;
			}
		}
	}


	private void updateAsFiller() {
		IStorage[] storages = this.getConnectedStorages();
		if(storages != null && this.essences > 0){
			int essencesPerConnection = ((this.essencesPerTick > this.essences)? this.essences : this.essencesPerTick) / storages.length;
			int count = 0;
			while(count < storages.length && this.essences > 0){
				int rest = this.fillEssence(storages[count], essencesPerConnection);
				this.store(rest);
				essencesPerConnection += rest / (storages.length - count);
				count++;
			}
		}
	}


	private void updateAsPipe() {
		PipeHelper[] pipes = this.getConnectedPipes();
		if(pipes != null && this.essences > 0){
			int essencesPerConnection = ((this.essencesPerTick > this.essences) ? this.essences : this.essencesPerTick) / pipes.length;
			int count = 0;
			while(count < pipes.length && this.essences > 0){
				int rest = this.transportEssence(pipes[count], essencesPerConnection);
				this.store(rest);
				essencesPerConnection += rest / (pipes.length - count);
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
	public IStorage[] getConnectedStorages() {
		IStorage[] storages = new IStorage[6];
		int count = 0;
		if(this.isStorage(this.xCoord + 1, this.yCoord, this.zCoord))storages[count++] = this.getStorage(this.xCoord + 1, this.yCoord, this.zCoord);
		if(this.isStorage(this.xCoord - 1, this.yCoord, this.zCoord))storages[count++] = this.getStorage(this.xCoord - 1, this.yCoord, this.zCoord);
		if(this.isStorage(this.xCoord, this.yCoord + 1, this.zCoord))storages[count++] = this.getStorage(this.xCoord, this.yCoord + 1, this.zCoord);
		if(this.isStorage(this.xCoord, this.yCoord - 1, this.zCoord))storages[count++] = this.getStorage(this.xCoord, this.yCoord - 1, this.zCoord);
		if(this.isStorage(this.xCoord, this.yCoord, this.zCoord + 1))storages[count++] = this.getStorage(this.xCoord, this.yCoord, this.zCoord + 1);
		if(this.isStorage(this.xCoord, this.yCoord, this.zCoord - 1))storages[count++] = this.getStorage(this.xCoord, this.yCoord, this.zCoord - 1);
		
		if(count > 0){
			IStorage[] rtnStorages = new IStorage[count];
			int tmpCount = 0;
			for(int i = 0; i < storages.length; i++){
				if(storages[i] != null){
					rtnStorages[tmpCount++] = storages[i];
				}
			}
			return rtnStorages;
		}
		
		return null;
	}
	
	private IStorage getStorage(int x, int y, int z) {
		TileEntity entity = this.worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof IStorage)return (IStorage) entity;
		return null;
	}


	private boolean isStorage(int x, int y, int z){
		TileEntity entity = this.worldObj.getBlockTileEntity(x, y, z);
		return entity != null && entity instanceof IStorage && !(entity instanceof IPipe);
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
	public int extractEssence(IStorage storage, int essencesPerConnection) {
		if(storage != null){
			int extracted = storage.extract(essencesPerConnection);
			if(extracted > 0){
				int stored = this.store(extracted);
				return essencesPerConnection - stored;
			}
		}
		return 0;
	}


	@Override
	public int transportEssence(PipeHelper pipe, int essencesPerConnection) {
		if(pipe != null){
			int extracted = this.extract(essencesPerConnection);
			if(extracted > 0){
				int stored = pipe.store(extracted);
				return essencesPerConnection - stored;
			}
		}
		return 0;
	}
	
	@Override
	public int fillEssence(IStorage storage, int essencesPerConnection){
		if(storage != null){
			int extracted = this.extract(essencesPerConnection);
			if(extracted > 0){
				int stored = storage.store(extracted);
				return essencesPerConnection - stored;
			}
		}
		return 0;
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
