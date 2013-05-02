package redmagic.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.items.ItemManager;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TileEntityJuicer extends TileEntityInventory implements ITankContainer{

	public LiquidTank tank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * 18);
	public int count = 0, need = 10;
	public int running = 0, maxRunning = 1000;
	
	public boolean active = false;
	
	public TileEntityJuicer() {
		super(9, LanguageRegistry.instance().getStringLocalization(BlockIndex.JUICER_NAME));
	}
	
	public void updateEntity(){
		if(count > need){
			count = 0;
			update();
		}
		count++;
		if(active){
			if(this.worldObj.isAirBlock(xCoord, yCoord + 1, zCoord)){
				this.worldObj.setBlock(xCoord, yCoord + 1, zCoord, BlockManager.soulJuice.blockID);
			}
		}else{
			if(this.worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == BlockManager.soulJuice.blockID){
				this.worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
			}
		}
	}
	
	

	private void update() {
		Logger.log("update");
		if(!active && this.tank.drain(1, true) != null && InventoryHelper.reduceIDInInventory(this, ItemManager.soul.itemID, 1) == 1){
			active = true;
			this.running++;
		}
		if(this.running > this.maxRunning){
			active = false;
			this.running = 0;
		}
		if(this.running > 0)this.running++;
		
		
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
		if(type == null || type.isLiquidEqual(tank.getLiquid()))return tank;
		return null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.tank.setLiquid(LiquidStack.loadLiquidStackFromNBT(tagCompound));
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		if(this.tank.getLiquid() != null){
			this.tank.getLiquid().writeToNBT(tagCompound);
		}
	}
	
}
