package redmagic.client.guis.talent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;

import redmagic.configuration.PathIndex;
import redmagic.configuration.Texture;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.path.PathManager;
import redmagic.lib.talent.Talent;
import redmagic.network.PacketHandler;
import redmagic.network.PacketTalentUnlocked;

public class GuiTalentPage extends GuiScreen{
	/** The top x coordinate of the talent map */
    private static final int guiMapTop = Talent.minDisplayColumn * 24 - 112;

    /** The left y coordinate of the talent map */
    private static final int guiMapLeft = Talent.minDisplayRow * 24 - 112;

    /** The bottom x coordinate of the talent map */
    private static final int guiMapBottom = Talent.maxDisplayColumn * 24 - 77;

    /** The right y coordinate of the talent map */
    private static final int guiMapRight = Talent.maxDisplayRow * 24 - 77;
    private static final ResourceLocation backgroundLocation = new ResourceLocation("textures/gui/achievement/achievement_background.png");
    protected int talentsPaneWidth = 256;
    protected int talentsPaneHeight = 202;

    /** The current mouse x coordinate */
    protected int mouseX;

    /** The current mouse y coordinate */
    protected int mouseY;
    protected double field_74117_m;
    protected double field_74115_n;

    /** The x position of the talent map */
    protected double guiMapX;

    /** The y position of the talent map */
    protected double guiMapY;
    protected double field_74124_q;
    protected double field_74123_r;

    /** Whether the Mouse Button is down or not */
    private int isMouseButtonDown;
    private PathManager pathManager;
    private EntityPlayer player;

    private int currentPage = -1;
    private GuiSmallButton button;
    private LinkedList<Talent> talents = new LinkedList<Talent>();
    
    private String state = null;

    public GuiTalentPage(EntityPlayer player)
    {
    	this.player = player;
        this.pathManager = PlayerInformationHelper.getPlayerInformation(player).pathManager;
        short short1 = 141;
        short short2 = 141;
        this.field_74117_m = this.guiMapX = this.field_74124_q = (double)(Talent.life.col * 24 - short1 / 2 - 12);
        this.field_74115_n = this.guiMapY = this.field_74123_r = (double)(Talent.life.row * 24 - short2 / 2);
        talents.clear();
        talents.addAll(Talent.talents);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiSmallButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, I18n.func_135053_a("gui.done")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 1)
        {
        	if(this.state == null){
	            this.mc.displayGuiScreen((GuiScreen)null);
	            this.mc.setIngameFocus();
	        }else{
	    		this.state = null;
	    	}
        }

        super.actionPerformed(par1GuiButton);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == this.mc.gameSettings.keyBindInventory.keyCode)
        {
    		this.mc.displayGuiScreen((GuiScreen)null);
    		this.mc.setIngameFocus();
        }
        else
        {
            super.keyTyped(par1, par2);
        }
    }
    
    
    protected void mouseClicked(int par1, int par2, int par3)
    {
    	super.mouseClicked(par1, par2, par3);
		int left = (this.width - this.talentsPaneWidth) / 2 + 15;
        int top = (this.height - this.talentsPaneHeight) / 2 + 17;

        double localMouseX = (par1 - left) + guiMapX;
        double localMouseY = (par2 - top) + guiMapY;
        
		Iterator<Talent> it = talents.iterator();
		while(it.hasNext()){
			Talent talent = it.next();
			int col = talent.col;
			int row = talent.row;
			int x = talent.col * 24;
			int y = talent.row * 24;
			if(localMouseX > x && localMouseX < x + 24 && localMouseY > y && localMouseY < y + 24){
				if(this.pathManager.isTalentUnlockable(talent) && !this.pathManager.isTalentUnlocked(talent) && par3 == 0){
	    				talent.unlockable.unlock(player);
	    				PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketTalentUnlocked(talent.name)));
	    	            
				}else if(par3 == 1){
					state = talent.unlockableName;
				}
			}
		}
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        if (Mouse.isButtonDown(0))
        {
            int k = (this.width - this.talentsPaneWidth) / 2;
            int l = (this.height - this.talentsPaneHeight) / 2;
            int i1 = k + 8;
            int j1 = l + 17;

            if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && par1 >= i1 && par1 < i1 + 224 && par2 >= j1 && par2 < j1 + 155)
            {
                if (this.isMouseButtonDown == 0)
                {
                    this.isMouseButtonDown = 1;
                }
                else
                {
                    this.guiMapX -= (double)(par1 - this.mouseX);
                    this.guiMapY -= (double)(par2 - this.mouseY);
                    this.field_74124_q = this.field_74117_m = this.guiMapX;
                    this.field_74123_r = this.field_74115_n = this.guiMapY;
                }

                this.mouseX = par1;
                this.mouseY = par2;
            }

            if (this.field_74124_q < (double)guiMapTop)
            {
                this.field_74124_q = (double)guiMapTop;
            }

            if (this.field_74123_r < (double)guiMapLeft)
            {
                this.field_74123_r = (double)guiMapLeft;
            }

            if (this.field_74124_q >= (double)guiMapBottom)
            {
                this.field_74124_q = (double)(guiMapBottom - 1);
            }

            if (this.field_74123_r >= (double)guiMapRight)
            {
                this.field_74123_r = (double)(guiMapRight - 1);
            }
        }
        else
        {
            this.isMouseButtonDown = 0;
        }

        this.drawDefaultBackground();
        if(this.state == null){
        	this.genTalentBackground(par1, par2, par3);
        }else{
        	this.drawHelp(Talent.getTalent(this.state));
        	super.drawScreen(par1, par2, par3);
        }
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        this.drawTitle();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

	/**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_74117_m = this.guiMapX;
        this.field_74115_n = this.guiMapY;
        double d0 = this.field_74124_q - this.guiMapX;
        double d1 = this.field_74123_r - this.guiMapY;

        if (d0 * d0 + d1 * d1 < 4.0D)
        {
            this.guiMapX += d0;
            this.guiMapY += d1;
        }
        else
        {
            this.guiMapX += d0 * 0.85D;
            this.guiMapY += d1 * 0.85D;
        }
    }

    /**
     * Draws the "Talents" title at the top of the GUI.
     */
    protected void drawTitle()
    {
        int i = (this.width - this.talentsPaneWidth) / 2;
        int j = (this.height - this.talentsPaneHeight) / 2;
        String text = "Talents";
        if(state != null){
        	text = StatCollector.translateToLocal(Talent.getTalent(state).name);
        }
        this.fontRenderer.drawString(text, i + 15, j + 5, 4210752);
    }
    
    private void drawHelp(Talent talent) {
    	int i1 = (this.width - this.talentsPaneWidth) / 2;
        int j1 = (this.height - this.talentsPaneHeight) / 2;
        
    	GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.func_110434_K().func_110577_a(Texture.TALENT_STATE_BACKGROUND);
        this.drawTexturedModalRect(i1, j1, 0, 0, this.talentsPaneWidth, this.talentsPaneHeight);
        GL11.glPopMatrix();
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        this.fontRenderer.drawSplitString(talent.unlockable.getHelp(), i1 + 20, j1 + 20, 300, 4210752);
	}

    protected void genTalentBackground(int par1, int par2, float par3)
    {
        int k = MathHelper.floor_double(this.field_74117_m + (this.guiMapX - this.field_74117_m) * (double)par3);
        int l = MathHelper.floor_double(this.field_74115_n + (this.guiMapY - this.field_74115_n) * (double)par3);

        if (k < guiMapTop)
        {
            k = guiMapTop;
        }

        if (l < guiMapLeft)
        {
            l = guiMapLeft;
        }

        if (k >= guiMapBottom)
        {
            k = guiMapBottom - 1;
        }

        if (l >= guiMapRight)
        {
            l = guiMapRight - 1;
        }

        int i1 = (this.width - this.talentsPaneWidth) / 2;
        int j1 = (this.height - this.talentsPaneHeight) / 2;
        int k1 = i1 + 16;
        int l1 = j1 + 17;
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, -200.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int i2 = k + 288 >> 4;
        int j2 = l + 288 >> 4;
        int k2 = (k + 288) % 16;
        int l2 = (l + 288) % 16;
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        Random random = new Random();
        int i3;
        int j3;
        int k3;

        for (i3 = 0; i3 * 16 - l2 < 155; ++i3)
        {
            float f1 = 0.6F - (float)(j2 + i3) / 25.0F * 0.3F;
            GL11.glColor4f(f1, f1, f1, 1.0F);

            for (k3 = 0; k3 * 16 - k2 < 224; ++k3)
            {
                random.setSeed((long)(1234 + i2 + k3));
                random.nextInt();
                j3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
                Icon icon = Block.sand.getIcon(0, 0);

                if (j3 <= 37 && j2 + i3 != 35)
                {
                    if (j3 == 22)
                    {
                        if (random.nextInt(2) == 0)
                        {
                            icon = Block.oreDiamond.getIcon(0, 0);
                        }
                        else
                        {
                            icon = Block.oreRedstone.getIcon(0, 0);
                        }
                    }
                    else if (j3 == 10)
                    {
                        icon = Block.oreIron.getIcon(0, 0);
                    }
                    else if (j3 == 8)
                    {
                        icon = Block.oreCoal.getIcon(0, 0);
                    }
                    else if (j3 > 4)
                    {
                        icon = Block.stone.getIcon(0, 0);
                    }
                    else if (j3 > 0)
                    {
                        icon = Block.dirt.getIcon(0, 0);
                    }
                }
                else
                {
                    icon = Block.bedrock.getIcon(0, 0);
                }

                this.mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
                this.drawTexturedModelRectFromIcon(k1 + k3 * 16 - k2, l1 + i3 * 16 - l2, icon, 16, 16);
            }
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        int l3;
        int i4;
        int j4;

        for (i3 = 0; i3 < talents.size(); ++i3)
        {
            Talent talent = talents.get(i3);

            if (talent.parent != null && talents.contains(talent.parent))
            {
                k3 = talent.col * 24 - k + 11 + k1;
                j3 = talent.row * 24 - l + 11 + l1;
                j4 = talent.parent.col * 24 - k + 11 + k1;
                l3 = talent.parent.row * 24 - l + 11 + l1;
                boolean flag5 = this.pathManager.isTalentUnlocked(talent);
                boolean flag6 = this.pathManager.isTalentUnlockable(talent);
                i4 = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D ? 255 : 130;
                int k4 = -16777216;

                if (flag5)
                {
                    k4 = -9408400;
                }
                else if (flag6)
                {
                    k4 = 65280 + (i4 << 24);
                }

                this.drawHorizontalLine(k3, j4, j3, k4);
                this.drawVerticalLine(j4, j3, l3, k4);
            }
        }

        Talent talent1 = null;
        RenderItem renderitem = new RenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int l4;
        int i5;

        for (k3 = 0; k3 < talents.size(); ++k3)
        {
            Talent talent2 = (Talent)talents.get(k3);
            j4 = talent2.col * 24 - k;
            l3 = talent2.row * 24 - l;

            if (j4 >= -24 && l3 >= -24 && j4 <= 224 && l3 <= 155)
            {
                float f2;
                
                if (this.pathManager.isTalentUnlocked(talent2))
                {
                    f2 = 1.0F;
                    GL11.glColor4f(f2, f2, f2, 1.0F);
                }
                else if (this.pathManager.isTalentUnlockable(talent2))
                {
                    f2 = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) < 0.6D ? 0.6F : 0.8F;
                    GL11.glColor4f(f2, f2, f2, 1.0F);
                }
                else
                {
                    f2 = 0.3F;
                    GL11.glColor4f(f2, f2, f2, 1.0F);
                }

                this.mc.func_110434_K().func_110577_a(backgroundLocation);
                i5 = k1 + j4;
                l4 = l1 + l3;

                if (talent2.isSpecial())
                {
                    this.drawTexturedModalRect(i5 - 2, l4 - 2, 26, 202, 26, 26);
                }
                else
                {
                    this.drawTexturedModalRect(i5 - 2, l4 - 2, 0, 202, 26, 26);
                }

                if (!this.pathManager.isTalentUnlockable(talent2))
                {
                    float f3 = 0.1F;
                    GL11.glColor4f(f3, f3, f3, 1.0F);
                    renderitem.renderWithColor = false;
                }

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
                renderitem.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.func_110434_K(), talent2.itemStack, i5 + 3, l4 + 3);
                GL11.glDisable(GL11.GL_LIGHTING);

                if (!this.pathManager.isTalentUnlockable(talent2))
                {
                    renderitem.renderWithColor = true;
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                if (par1 >= k1 && par2 >= l1 && par1 < k1 + 224 && par2 < l1 + 155 && par1 >= i5 && par1 <= i5 + 22 && par2 >= l4 && par2 <= l4 + 22)
                {
                    talent1 = talent2;
                }
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.func_110434_K().func_110577_a(backgroundLocation);
        this.drawTexturedModalRect(i1, j1, 0, 0, this.talentsPaneWidth, this.talentsPaneHeight);
        GL11.glPopMatrix();
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        super.drawScreen(par1, par2, par3);

        if (talent1 != null)
        {
            String s = I18n.func_135053_a(talent1.name);
            String s1 = talent1.getDescription();
            j4 = par1 + 12;
            l3 = par2 - 4;

            if (this.pathManager.isTalentUnlockable(talent1))
            {
                i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
                l4 = this.fontRenderer.splitStringWidth(s1, i5);

                if (this.pathManager.isTalentUnlocked(talent1))
                {
                	l4 += 12;
                }

                this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + l4 + 3 + 12, -1073741824, -1073741824);
                this.fontRenderer.drawSplitString(s1, j4, l3 + 12, i5, -6250336);

                if (this.pathManager.isTalentUnlocked(talent1))
                {
                    this.fontRenderer.drawStringWithShadow(I18n.func_135053_a("talent.taken"), j4, l3 + l4 + 4, -7302913);
                }
            }
            else
            {
            	i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
            	String s2;
            	if(talent1.parent != null){
            		s2 = I18n.func_135052_a("talent.requires", new Object[] {I18n.func_135053_a(talent1.parent.name)});
            	}else{
            		s2 = "Not available.";
            	}
                i4 = this.fontRenderer.splitStringWidth(s2, i5);
                this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + i4 + 12 + 3, -1073741824, -1073741824);
                this.fontRenderer.drawSplitString(s2, j4, l3 + 12, i5, -9416624);
            }

            this.fontRenderer.drawStringWithShadow(s, j4, l3, this.pathManager.isTalentUnlockable(talent1) ? (talent1.isSpecial() ? -128 : -1) : (talent1.isSpecial() ? -8355776 : -8355712));
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        RenderHelper.disableStandardItemLighting();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return true;
    }
}
