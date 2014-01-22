package com.petredy.redmagic.utils;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationUtils {

	public static ResourceLocation getResourceLocation(String path) {

		return new ResourceLocation(Reference.MOD_ID.toLowerCase(), path);
	}
}
