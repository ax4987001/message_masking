package com.ax4987.message_masking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ButtonHandler extends GuiButton {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Message_masking.MODID + ":textures/gui/masking.png");
    public ButtonHandler(int id,int x,int y,int width,int height,String player){
        super(id,x,y,width,height,player);
    }
    public void drawButton(Minecraft mc,boolean isBan,int size){
        if (this.visible){
            mc.getTextureManager().bindTexture(TEXTURE);
            GlStateManager.color(1F,1F,1F);
            if (!isBan){
                drawModalRectWithCustomSizedTexture(x,y,0,0,2*size,2*size,32,32);
            }else {
                drawModalRectWithCustomSizedTexture(x,y,31,0,2*size,2*size,32,32);
            }
        }
    }
}
