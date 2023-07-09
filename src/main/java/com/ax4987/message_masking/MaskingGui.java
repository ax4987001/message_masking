package com.ax4987.message_masking;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.List;

public class MaskingGui extends GuiScreen {
    private final List<String> playerNames;
    private final List<ButtonHandler> buttons;
    private final List<Boolean> isBan;
    public static List<String> banPlayer;

    public MaskingGui(List<String> playerNames) {
        this.playerNames = playerNames;
        this.buttons = new ArrayList<>();
        this.isBan = new ArrayList<>();
        banPlayer = new ArrayList<>();
        for (int i = 1;i <= 19;i++){
            this.playerNames.add(String.valueOf(i));
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        buttons.clear();
        isBan.clear();
        banPlayer.clear();
        for (int i = 1;i <= this.playerNames.size();i++){
            this.isBan.add(i - 1,false);
        }
    }

    @Override
    protected void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        if (this.selectedButton != null && p_mouseReleased_3_ == 0) {
            int buttonId = selectedButton.id;
            String playerName = playerNames.get(buttonId - 1);
            banPlayer.add(playerName);
            isBan.set(buttonId, !isBan.get(buttonId));
            this.selectedButton = null;
        }

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
            ButtonHandler button = new ButtonHandler(id,xPos + X/ count / 4,yPos,this.fontRenderer.FONT_HEIGHT * 12 / 10,this.fontRenderer.FONT_HEIGHT * 12 / 10,playerNames.get(i-1));
            buttons.add(id - 1,button);
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
            buttons.get(playerNames.indexOf(playerName)).drawButton(mc,isBan.get(playerName.indexOf(playerName)),fontRenderer.FONT_HEIGHT);
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
