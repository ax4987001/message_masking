package com.ax4987.message_masking;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber

public class Chat_masking {
    @SubscribeEvent
    public static void onChatReceive(ClientChatReceivedEvent event){
        String message = event.getMessage().getFormattedText();
        String playerName = getPlayerName(message);
        event.setCanceled(isBaned(playerName));


    }
    private static String getPlayerName(String message){
        String[] part1 = message.split(">",2);
        String[] part2 = part1[0].split("<",2);
        return part2[1];
    }
    private static boolean isBaned(String player){
        return MaskingGui.banPlayer.contains(player);
    }
}
