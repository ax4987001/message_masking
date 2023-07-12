package com.ax4987.message_masking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class PlayerData {
    public static final String IS_BAN = "is_ban";
    public static NBTTagCompound modData;
    public static String playerName;
    public static EntityPlayer player;

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        PlayerData.player = player;
        playerName = player.getName();
        NBTTagCompound playerData = player.getEntityData();
        if (!playerData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
        }
        NBTTagCompound modData_0 = playerData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        if (!modData_0.hasKey(IS_BAN)) {
            modData_0.setTag(IS_BAN, new NBTTagCompound());
        }
        PlayerData.modData = modData_0;
        System.out.print(modData);
    }
}
