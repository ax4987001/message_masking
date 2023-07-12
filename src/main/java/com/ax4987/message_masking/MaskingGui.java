package com.ax4987.message_masking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaskingGui extends GuiScreen {
    public static List<String> playerNames;
    public static Map<String,Integer> isBan;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Message_masking.MODID + ":textures/gui/masking.png");

    public MaskingGui(List<String> playerNames) {
        MaskingGui.playerNames = playerNames;
        MaskingGui.playerNames.remove(PlayerData.playerName);
        isBan = new HashMap<>();
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        for (int i = 1;i <= playerNames.size();i++){
            String playerName = playerNames.get(i - 1);
            if (PlayerData.modData.getCompoundTag(PlayerData.IS_BAN).hasKey(playerName)) {
                isBan.put(playerName,PlayerData.modData.getCompoundTag(PlayerData.IS_BAN).getInteger(playerName));
            }else {
                isBan.put(playerName, 0);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int buttonId = button.id;
        String playerName = playerNames.get(buttonId - 1);
        isBan.put(playerName, isBan.get(playerName) == 0?1:0);
        PlayerData.modData.getCompoundTag(PlayerData.IS_BAN).setInteger(playerName,isBan.get(playerName));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();
        int X = this.width;
        int yPos = this.height / 3;
        int count = 2;
        for (int i=1;i<= playerNames.size();i++){

            if(yPos >= (this.height * 2 / 3)){
                yPos = this.height / 3 - this.fontRenderer.FONT_HEIGHT;
                count += 1;
            }
            yPos += this.fontRenderer.FONT_HEIGHT;
        }
        int xPos = this.width / count;
        yPos = this.height / 3;
        int id = 1;
        for (int i=1;i<= playerNames.size();i++){
            GuiButton button = new GuiButton(id,xPos + X/ count / 4,yPos,this.fontRenderer.FONT_HEIGHT * 12 / 10,this.fontRenderer.FONT_HEIGHT * 12 / 10,playerNames.get(i-1)){
                @Override
                public void drawButton(Minecraft mc, int isBan, int size, float p_drawButton_4_){
                    if (this.visible){
                        mc.getTextureManager().bindTexture(TEXTURE);
                        GlStateManager.color(1F,1F,1F);
                        if (isBan == 0){
                            drawModalRectWithCustomSizedTexture(x-size/4,y-size/4,0,0,2*size,2*size,16*size,16*size);
                        }else if(isBan == 1) {
                            drawModalRectWithCustomSizedTexture(x-size/4-1,y-size/4,17,0,2*size,2*size,16*size,16*size);
                        }
                    }
                }
            };
            buttonList.add(id - 1,button);
            if (yPos >= (this.height * 2 / 3)) {
                yPos = this.height / 3 - this.fontRenderer.FONT_HEIGHT;
                xPos += X / count;
            }
            yPos += this.fontRenderer.FONT_HEIGHT;
            id++;
        }
        xPos = this.width / count * 3 / 4;
        yPos = this.height / 3;
        for (String playerName : playerNames) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            buttonList.get(playerNames.indexOf(playerName)).drawButton(mc, isBan.get(playerName),fontRenderer.FONT_HEIGHT,0);
            this.drawCenteredString(this.fontRenderer, playerName, xPos, yPos, 0xFFFFFF);
            if (yPos >= (this.height * 2 / 3)) {
                yPos = this.height / 3 - this.fontRenderer.FONT_HEIGHT;
                xPos += X / count;
            }
            yPos += this.fontRenderer.FONT_HEIGHT;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
