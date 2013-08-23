package redmagic.helpers;

import redmagic.configuration.Reference;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {

	public static ResourceLocation getResourceLocation(String path) {

		return new ResourceLocation(Reference.MOD_ID.toLowerCase(), path);
	}
}
