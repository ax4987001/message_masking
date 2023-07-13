package com.ax4987.message_masking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DrawButton extends GuiButton {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Message_masking.MODID + ":textures/gui/masking.png");
    DrawButton(int id, int x, int y, int w, int h, String name){
        super(id,x,y,w,h,name);
    }
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
    public void setXY(int x1,int y1,int w1,int h1,String name){
        x = x1;
        y = y1;
        width = w1;
        height = h1;
        displayString = name;
    }
}
