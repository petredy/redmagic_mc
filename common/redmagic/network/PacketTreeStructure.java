package redmagic.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import redmagic.api.items.IKeyBound;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.PacketIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.lib.tree.StructureData;

public class PacketTreeStructure extends PacketRedMagic{

	public Integer id;
	public NBTTagCompound data;
	
	public PacketTreeStructure() {
		super(PacketIndex.TREE, false);
	}
	
	public PacketTreeStructure(Integer id, NBTTagCompound data) {
		super(PacketIndex.TREE, false);
		this.id = id;
		this.data = data;
	}
	
	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(id);
		this.writeNBTTagCompound(this.data, data);
	}
	
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		this.id = data.readInt();
		this.data = this.readNBTTagCompound(data);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		StructureData data = (StructureData) thePlayer.worldObj.loadItemData(StructureData.class, Reference.MOD_ID + "_tree_structure_" + id);
		if(data != null && data.structure != null){
			data.structure.readFromNBT(this.data);
			data.markDirty();
		}else{
			data = new StructureData(Reference.MOD_ID + "_tree_structure_" + id);
			data.structure = new TreeStructure();
			data.structure.id = this.id;
			data.structure.readFromNBT(this.data);
			data.markDirty();
			thePlayer.worldObj.setItemData(Reference.MOD_ID + "_tree_structure_" + id, data);
		}
	}

}
