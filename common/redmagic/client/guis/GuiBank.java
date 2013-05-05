package redmagic.client.guis;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import redmagic.client.guis.button.ButtonCustom;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerBank;
import redmagic.helpers.BankHelper;
import redmagic.lib.bank.BankManager;
import redmagic.network.PacketHandler;
import redmagic.tileentities.bank.TileEntityBank;

public class GuiBank extends GuiContainer{
	public TileEntityBank entity;
	public ContainerBank container;
	
	private float currentScroll = 0.0F;
	public EntityPlayer player;
	
	public GuiTextField searchField;
	
	public GuiBank(EntityPlayer player, TileEntityBank tileEntity) {
		super(new ContainerBank(player,tileEntity));
		this.entity = tileEntity;
		this.player = player;
		this.xSize = 194;
		this.ySize = 192;
		this.allowUserInput = true;
	}
	
	public void initGui()
    {
		super.initGui();
		this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.searchField = new GuiTextField(this.fontRenderer, this.guiLeft + 81, this.guiTop + 5, 89, this.fontRenderer.FONT_HEIGHT);
        this.searchField.setMaxStringLength(15);
        this.searchField.setEnableBackgroundDrawing(false);
        this.searchField.setVisible(true);
        this.searchField.setTextColor(16777215);
    }
	
	public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
	
	protected void actionPerformed(GuiButton button) {
		
		
	}
	
	 public void handleMouseInput()
    {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();

        if (i != 0)
        {
            int j = BankManager.getAllItems().size() / 9 - 5 + 1;

            if (i > 0)
            {
                i = 1;
            }

            if (i < 0)
            {
                i = -1;
            }

            this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((ContainerBank)this.inventorySlots).scrollTo(this.currentScroll);
        }
    }

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){		
		fontRenderer.drawString(this.entity.getInvName(), 8, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.BANK_GUI);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		
		this.searchField.drawTextBox();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int i1 = this.guiLeft + 175;
        int k = this.guiTop + 18;
        int l = k + 112;
		this.mc.renderEngine.bindTexture("/gui/allitems.png");
        this.drawTexturedModalRect(i1, k + (int)((float)(l - k - 17) * this.currentScroll), 232 + 12, 0, 12, 15);
	}
	
	protected void keyTyped(char par1, int par2)
    {
		if (!this.checkHotbarKeys(par2))
        {
            if (this.searchField.textboxKeyTyped(par1, par2))
            {
                this.updateSearch();
            }
            else
            {
                super.keyTyped(par1, par2);
            }
        }   
    }

	private void updateSearch() {
		ContainerBank bank = (ContainerBank)this.inventorySlots;
		
		Iterator iterator = bank.list.iterator();
		String s = this.searchField.getText().toLowerCase();
		
		while(iterator.hasNext()){
			
		}
	}
	
	protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3)
    {
        List list = par1ItemStack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
       
        
        //Add Costs to the tooltip of every item
        list.add("Amount: " + BankManager.getItemAmount(par1ItemStack.itemID, par1ItemStack.getItemDamage()));
        list.add("Costs");
        list.add("One: " + BankManager.getItemPrice(par1ItemStack.itemID, par1ItemStack.getItemDamage()) + "C");
        list.add("Stack: " + (BankManager.getItemPrice(par1ItemStack.itemID, par1ItemStack.getItemDamage()) * par1ItemStack.getMaxStackSize()) + "C");
        
        
        for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, "\u00a7" + Integer.toHexString(par1ItemStack.getRarity().rarityColor) + (String)list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
            }
        }

        FontRenderer font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        drawHoveringText(list, par2, par3, (font == null ? fontRenderer : font));
    }
}

