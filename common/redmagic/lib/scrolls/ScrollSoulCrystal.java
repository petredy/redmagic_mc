package redmagic.lib.scrolls;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redmagic.blocks.BlockManager;
import redmagic.client.guis.GuiScroll;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerScroll;
import redmagic.helpers.RenderHelper;
import redmagic.items.ItemManager;
import redmagic.slots.SlotScroll;

public class ScrollSoulCrystal extends Scroll{

	@Override
	public void createContainer(ContainerScroll container) {
		container.addSlot(new SlotScroll(new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), 100, 180));
		container.addSlot(new SlotScroll(new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), 117, 180));
		container.addSlot(new SlotScroll(new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), 133, 180));
		container.addSlot(new SlotScroll(new ItemStack(Item.stick), 100, 196));
		container.addSlot(new SlotScroll(new ItemStack(Block.thinGlass), 117, 196));
		container.addSlot(new SlotScroll(new ItemStack(Item.stick), 133, 196));
		container.addSlot(new SlotScroll(new ItemStack(Item.stick), 100, 214));
		container.addSlot(new SlotScroll(null, 117, 214));
		container.addSlot(new SlotScroll(new ItemStack(Item.stick), 133, 214));
		
		container.addSlot(new SlotScroll(new ItemStack(ItemManager.glasses), 180, 196));
	}

	@Override
	public String getHeadline() {
		return "Mining";
	}

	@Override
	public String getIntroduction() {
		return "To get started mine at any level below 50 and find some Soul Crystals.";
	}

	@Override
	public String getDetails() {
		return "You can crush this crystals in the WorkTable to Soul Powder. When Soul Powder is burned you get Soul Ingots for more machines and tools. Afterwards craft some Virtual Reality Glasses!";
	}

	@Override
	public void drawOthers(GuiScroll gui, FontRenderer renderer, RenderEngine engine) {
		RenderHelper.renderRotatingBlockIntoGUI(renderer, engine, new ItemStack(BlockManager.soulCrystalOre), 150, 50, 0, 4);
		engine.bindTexture(Texture.SCROLL_CRAFTING);
		gui.drawTexturedModalRect(100, 180, 0, 0, gui.xsize, gui.ysize);
	}

}
