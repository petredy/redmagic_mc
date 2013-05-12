package redmagic.helpers;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.lib.tree.StructureData;
import redmagic.network.PacketHandler;
import redmagic.network.PacketTreeStructure;

public class TreeHelper {
	public static TreeStructure loadStructure(World world, int id){
		StructureData data = (StructureData) world.loadItemData(StructureData.class, Reference.MOD_ID + "_tree_structure_" + id);

		if(data != null){
			return data.structure;
		}
		return null;
	}
	
	public static void saveStructure(World world, int id, TreeStructure structure){
		StructureData data = (StructureData) world.loadItemData(StructureData.class, Reference.MOD_ID + "_tree_structure_" + id);
		if(data != null){
			data.structure = structure;
			data.markDirty();
		}else{
			data = new StructureData(Reference.MOD_ID + "_tree_structure_" + id);
			data.structure = structure;
			data.markDirty();
			world.setItemData(Reference.MOD_ID + "_tree_structure_" + id, data);
		}
	}
	
	public static void syncClient(World world, int id){
		StructureData data = (StructureData) world.loadItemData(StructureData.class, Reference.MOD_ID + "_tree_structure_" + id);
		if(data != null && data.structure != null){
			NBTTagCompound tag = new NBTTagCompound();
			data.writeToNBT(tag);
			if(tag != null)PacketDispatcher.sendPacketToAllInDimension(PacketHandler.populatePacket(new PacketTreeStructure(id, tag)), world.getWorldInfo().getDimension());
		}
	}

	public static int getNextID(World world) {
		return world.getUniqueDataId(Reference.MOD_ID + "_tree_structure_");
	}
}
