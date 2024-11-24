package net.azowata.oregenerator.gui;

import net.azowata.oregenerator.containers.ContainerOreGenerator;
import net.azowata.oregenerator.tileentities.TileEntityOreGenerator;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import oregenerator.Tags;
import org.lwjgl.input.Keyboard;

public class GuiOreGenerator extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Tags.MODID, "textures/gui/ore_generator.png");
    private final TileEntityOreGenerator tileEntityOreGenerator;

    private final InventoryPlayer playerInventory;

    public GuiOreGenerator(InventoryPlayer playerInventory, TileEntityOreGenerator tileEntity) {
        super(new ContainerOreGenerator(playerInventory, tileEntity));
        this.playerInventory = playerInventory;
        this.tileEntityOreGenerator = tileEntity;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(GUI_TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileEntityOreGenerator.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, (this.xSize - this.fontRenderer.getStringWidth(s)) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

        String currentOreName = this.tileEntityOreGenerator.getCurrentOreName();
        this.fontRenderer.drawString("Producing: " + currentOreName, (this.xSize - this.fontRenderer.getStringWidth("Producing: " + currentOreName)) / 2, 24, 4210752);

        String isActive = this.tileEntityOreGenerator.isActive() ? "Active" : "Inactive";
        int color = this.tileEntityOreGenerator.isActive() ? 65280 : 16711680;
        this.fontRenderer.drawString(isActive, 32, 56, color);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
}
