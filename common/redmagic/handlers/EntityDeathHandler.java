package redmagic.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.blocks.multi.extractor.ExtractorBlock;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.FragmentHelper;
import redmagic.tileentities.education.TileEntityExtractorCollector;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityDeathHandler {

	
	@ForgeSubscribe
	public void onDeath(LivingDeathEvent event){
		EntityLiving entity = event.entityLiving;
		int meta = new Random().nextInt(LogicIndex.SOUL_FRAGMENT_TYPES.length);
		ItemStack fragment = FragmentHelper.createNewFragment(meta);
		float chance = LogicIndex.SOUL_FRAGMENT_CHANCES[meta];
		if(fragment != null && new Random().nextFloat() < chance){
			for(int i = -LogicIndex.SOUL_FRAGMENT_DROP_RANGE; i <= LogicIndex.SOUL_FRAGMENT_DROP_RANGE; i++){
				for(int j = -LogicIndex.SOUL_FRAGMENT_DROP_RANGE; j <= LogicIndex.SOUL_FRAGMENT_DROP_RANGE; j++){
					for(int k = -LogicIndex.SOUL_FRAGMENT_DROP_RANGE; k <= LogicIndex.SOUL_FRAGMENT_DROP_RANGE; k++){
						if(fragment != null && ExtractorBlock.isMultiBlock(entity.worldObj, (int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k)){
							if(this.addFragment(entity.worldObj, fragment, (int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k))return;
						}
					}
				}
			}
		}
	}

	private boolean addFragment(World world, ItemStack fragment, int x, int y, int z) {
		ExtractorBlock extractor = new ExtractorBlock(x, y, z);
		IMultiEntity tile = extractor.getBasicEntity(world);
		if(tile.hasStructure()){
			List<IMultiBlock> blocks = tile.getStructure().getBlockType(BlockIndex.EXTRACTOR_COLLECTOR_METADATA);
			Iterator<IMultiBlock> it = blocks.iterator();
			while(it.hasNext()){
				IMultiBlock block = it.next();
				TileEntityExtractorCollector entity = (TileEntityExtractorCollector) block.getBasicEntity(world);
				entity.addFragments(fragment.copy());
				fragment = null;
				return true;
			}
		}
		return false;
	}
}
