package com.ax4987.message_masking;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber

public class Chat_masking {
    @SubscribeEvent
    public static void onChatReceive(ClientChatReceivedEvent event){
        String message = event.getMessage().getUnformattedText();
        String playerName = getPlayerName(message);
        event.setCanceled(isBaned(playerName));
    }
    private static String getPlayerName(String message){
        String[] part1 = message.split(">");
        String[] part2 = part1[0].split("<");
        String result;
        if (part2.length ==1 && part2[0].equals(part1[0])){
            result = "ax4987_114514_ax49987";
        }else {
            result = part2[1];
        }
        return result;
    }
    private static boolean isBaned(String player){
        return MaskingGui.isBan.containsKey(player);
    }
}
