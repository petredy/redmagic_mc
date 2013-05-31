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

public class ScrollStart extends Scroll{

	@Override
	public void createContainer(ContainerScroll container) {
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 85, 185));
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 102, 185));
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 120, 185));
		container.addSlot(new SlotScroll(new ItemStack(Item.paper), 85, 201));
		container.addSlot(new SlotScroll(new ItemStack(Block.workbench), 102, 201));
		container.addSlot(new SlotScroll(new ItemStack(Item.paper), 120, 201));
		container.addSlot(new SlotScroll(new ItemStack(Item.paper), 85, 219));
		container.addSlot(new SlotScroll(new ItemStack(Block.chest), 102, 219));
		container.addSlot(new SlotScroll(new ItemStack(Item.paper), 120, 219));
		
		container.addSlot(new SlotScroll(new ItemStack(BlockManager.workTable), 165, 202));
	}

	@Override
	public String getHeadline() {
		return "Beginners";
	}

	@Override
	public String getIntroduction() {
		return "To get started build a Work Table.";
	}

	@Override
	public String getDetails() {
		return "Place the table and you will see most recipes of redmagic. The colors will show you whether you have the needed items or not. Click on an orange one to switch to the recipe for the item.";
	}

	@Override
	public void drawOthers(GuiScroll gui, FontRenderer renderer, RenderEngine engine) {
		RenderHelper.renderRotatingBlockIntoGUI(renderer, engine, new ItemStack(BlockManager.workTable), 150, 50, 0, 4);
		engine.bindTexture(Texture.SCROLL_CRAFTING);
		gui.drawTexturedModalRect(85, 185, 0, 0, gui.xsize, gui.ysize);
	}

}
