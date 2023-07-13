package com.ax4987.message_masking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class PlayerData {
    public static final String IS_BAN = "is_ban";
    public static NBTTagCompound modData;
    public static String playerName;
    public static EntityPlayer player;
    public static boolean isTick = true;
    public static List<String> players;
    @SubscribeEvent
    public static void onPlayerLoggedIn(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START && Minecraft.getMinecraft().world != null && isTick) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            PlayerData.player = player;
            playerName = player.getName();
            NBTTagCompound playerData = player.getEntityData();
            if (!playerData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
                playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
            }
            System.out.print("\np1");
            NBTTagCompound modData_0 = playerData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            if (!modData_0.hasKey(IS_BAN)) {
                modData_0.setTag(IS_BAN, new NBTTagCompound());
            }
            PlayerData.modData = modData_0;
            System.out.print("\np2\n");
            Minecraft mc = Minecraft.getMinecraft();
            Collection<NetworkPlayerInfo> playerInfoCollection = Objects.requireNonNull(mc.getConnection()).getPlayerInfoMap();
            List<NetworkPlayerInfo> playerInfoList = new ArrayList<>(playerInfoCollection);
            players = new ArrayList<>();
            for (int i = 1;i <= playerInfoList.size();i++) {
                NetworkPlayerInfo playerInfo = playerInfoList.get(i-1);
                String playerName = playerInfo.getGameProfile().getName();
                players.add(playerName);
            }
            System.out.print(players);
            System.out.print("\nend\n");
            isTick = false;
        }
    }
}
