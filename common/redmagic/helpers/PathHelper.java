package redmagic.helpers;

import redmagic.lib.path.Path;
import redmagic.lib.path.PathManager;
import net.minecraft.nbt.NBTTagCompound;

public class PathHelper {

	public static Path loadFromNBT(NBTTagCompound tag){
		String name = tag.getString("pathname");
		if(name.length() > 0){
			Path path = PathManager.getPath(name);
			path.readFromNBT(tag);
			return path;
		}
		return null;
	}
	
}
