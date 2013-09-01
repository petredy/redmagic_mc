package redmagic.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketHelper {

	
	public static NBTTagCompound readFromNBT(DataInput par0DataInput) throws IOException
	{
		short short1 = par0DataInput.readShort();
		
		if (short1 < 0)
		{
			return null;
		}
		else
		{
			byte[] abyte = new byte[short1];
			par0DataInput.readFully(abyte);
			return CompressedStreamTools.decompress(abyte);
		}
	}

	public static void writeToNBT(NBTTagCompound par0NBTTagCompound, DataOutput par1DataOutput) throws IOException
	{
		if (par0NBTTagCompound == null)
		{
			par1DataOutput.writeShort(-1);
		}
		else
		{
			byte[] abyte = CompressedStreamTools.compress(par0NBTTagCompound);
			par1DataOutput.writeShort((short)abyte.length);
			par1DataOutput.write(abyte);
		}
	}
}
