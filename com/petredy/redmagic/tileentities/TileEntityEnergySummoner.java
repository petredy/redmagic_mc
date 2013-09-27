package com.petredy.redmagic.tileentities;

import java.util.List;
import java.util.Random;

import com.petredy.redmagic.api.redenergy.IEnergyConsumer;
import com.petredy.redmagic.api.redenergy.ILightningConsumer;
import com.petredy.redmagic.redenergy.EnergyConsumer;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.utils.EnvironmentUtils;
import com.petredy.redmagic.utils.LogUtils;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityEnergySummoner extends TileEntity implements IPowerReceptor{

	public PowerHandler handler;
	public static final int wireID = Block.netherFence.blockID;
	
	public TileEntityEnergySummoner(){
		handler = new PowerHandler(this, PowerHandler.Type.MACHINE);
		handler.configure(50, 200, 50, 2000);
	}
	
	public void updateEntity(){
		if(this.worldObj.isRaining() && this.handler.useEnergy(50, 200, true) >= 50){
			if(this.hasSurroundingBlocks()){
				int layers = this.hasWire();
				if(layers > 0){
					this.createLightningStrike(layers, yCoord);
				}
			}
			
		}
	}
	
	private void createLightningStrike(int layers, int height) {
		
		
		Random rand = new Random();
		if(rand.nextInt(100) < layers){
			int x = rand.nextInt(256 - height) * (rand.nextFloat() < 0.5 ? -1 : 1);
			int y = rand.nextInt(256 - height) * (rand.nextFloat() < 0.5 ? -1 : 1);
			int z = rand.nextInt(256 - height) * (rand.nextFloat() < 0.5 ? -1 : 1);
			List<EnergyConsumer> consumers = EnergyMap.getConsumerInNearOf(xCoord + x, y, zCoord + z);
			EntityLightningBolt bolt = null;
			if(consumers != null && consumers.size() > 0){
				boolean created = false;
				float chance = 1 / (float)consumers.size();
				for(EnergyConsumer consumer: consumers){
					if(rand.nextFloat() < chance){
						bolt = new EntityLightningBolt(worldObj, consumer.x, consumer.y, consumer.z);
						IEnergyConsumer con = (IEnergyConsumer) worldObj.getBlockTileEntity(consumer.x, consumer.y, consumer.z);
						con.consume();
						break;
					}
				}
			}else{
				bolt = new EntityLightningBolt(worldObj, xCoord + x, y, zCoord + z);
			}
			if(bolt != null)worldObj.addWeatherEffect(bolt);
			
		}
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return handler.getPowerReceiver();
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		workProvider.update();
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		handler.readFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		handler.writeToNBT(tag);
	}

	private boolean hasSurroundingBlocks(){
		ItemStack stack = EnvironmentUtils.surroundedOnLayerZByBlock(worldObj, xCoord, yCoord, zCoord);
		return stack != null && stack.isItemEqual(new ItemStack(Block.netherBrick));
	}
	
	private int hasWire() {
		int layers = 0;
		int air = EnvironmentUtils.containedBlocksInArea(worldObj, xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 1, yCoord + 50, zCoord + 1, 0, 0);
		if(air > 2 * 50){
			if(this.hasBase(yCoord)){
				layers = this.getLayers();
				return layers;
			}
		}
		return layers;
	}



	private int getLayers() {
		int y = 1;
		int count = 0;
		while(count <= 50 && yCoord + y <= 256 && worldObj.getBlockId(xCoord, yCoord + y++, zCoord) == wireID){
			count++;
		}
		if(this.hasBase(yCoord + count - 1))return count;
		return 0;
	}



	private boolean hasBase(int yCoord) {
		return worldObj.getBlockId(xCoord + 1, yCoord + 1, zCoord) == wireID &&
				worldObj.getBlockId(xCoord - 1, yCoord + 1, zCoord) == wireID &&
				worldObj.getBlockId(xCoord, yCoord + 1, zCoord + 1) == wireID &&
				worldObj.getBlockId(xCoord, yCoord + 1, zCoord - 1) ==wireID;
	}
	
}
