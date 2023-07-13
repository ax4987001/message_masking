package com.ax4987.message_masking;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ax4987.message_masking.PlayerData.modData;

public class MaskingGui extends GuiScreen {
    public static List<String> playerNames;
    public static Map<String,Integer> isBan;

    public MaskingGui(List<String> playerNames) {
        MaskingGui.playerNames = playerNames;
        MaskingGui.playerNames.remove(PlayerData.playerName);
        isBan = new HashMap<>();
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        for (int i = 1;i <= playerNames.size();i++) {
            String playerName = playerNames.get(i - 1);
            if (modData.getCompoundTag(PlayerData.IS_BAN).hasKey(playerName)) {
                isBan.put(playerName, modData.getCompoundTag(PlayerData.IS_BAN).getInteger(playerName));
            } else {
                isBan.put(playerName, 0);
            }
        }
        int id = 1;
        for (int i = 1;i <= playerNames.size();i++){
            DrawButton button = new DrawButton(id,0,0,0,0,playerNames.get(i-1));
            buttonList.add(id - 1,button);
            id++;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int buttonId = button.id;
        String playerName = playerNames.get(buttonId - 1);
        isBan.put(playerName, isBan.get(playerName) == 0?1:0);
        modData.getCompoundTag(PlayerData.IS_BAN).setInteger(playerName,isBan.get(playerName));
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

        for (int i=1;i<= playerNames.size();i++){

            if (yPos >= (this.height * 2 / 3)) {
                yPos = this.height / 3 - this.fontRenderer.FONT_HEIGHT;
                xPos += X / count;
            }
            yPos += this.fontRenderer.FONT_HEIGHT;
        }
        xPos = this.width / count * 3 / 4;
        yPos = this.height / 3;
        for (String playerName : playerNames) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            ((DrawButton) buttonList.get(playerNames.indexOf(playerName))).setXY(xPos + X/ count / 4,yPos,this.fontRenderer.FONT_HEIGHT * 12 / 10,this.fontRenderer.FONT_HEIGHT * 12 / 10,playerName);
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
