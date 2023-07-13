package com.ax4987.message_masking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;


//To let the player be a traveling god who plays yin-yang magic.

@Mod(modid = Message_masking.MODID, name = Message_masking.NAME, version = Message_masking.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
@Mod.EventBusSubscriber
public class Message_masking {

    public static final String MODID = "message_masking";
    public static final String NAME = "message_masking";
    public static final String VERSION = "1.1.0";
    public static KeyBinding keyBinding;
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        // 创建热键
        keyBinding = new KeyBinding("key.message_masking.custom_action", Keyboard.KEY_V, "key.categories.message_masking");
        // 注册热键
        ClientRegistry.registerKeyBinding(keyBinding);
        MinecraftForge.EVENT_BUS.register(new Chat_masking());
        MinecraftForge.EVENT_BUS.register(new PlayerData());
        System.out.print("\nInit");
    }
    //
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        //
        if (Message_masking.keyBinding.isPressed()) {
            PlayerData.isTick = true;
            Minecraft.getMinecraft().displayGuiScreen(new MaskingGui(PlayerData.players));
        }
    }





}