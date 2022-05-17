package com.hyratrion.rpgnloots.util;

import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.screen.ModMenuTypes;
import com.hyratrion.rpgnloots.screen.SocketingTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class ModStats extends Stats{

 /*   public static final ForgeRegistry<Stats> STATS =
            DeferredRegister.create(ForgeRegistries.STAT_TYPES, RPGNLOOT.MOD_ID);*/

    public static final ResourceLocation INTERACT_WITH_SOCKETING_TABLE = makeCustomStat("interact_with_socketing_table", StatFormatter.DEFAULT);

    private static ResourceLocation makeCustomStat(String p_13008_, StatFormatter p_13009_) {
        ResourceLocation resourcelocation = new ResourceLocation(p_13008_);
        Registry.register(Registry.CUSTOM_STAT, p_13008_, resourcelocation);
        CUSTOM.get(resourcelocation, p_13009_);
        return resourcelocation;
    }


/*    public static void register(IEventBus eventBus)

    {
        STATS.register(eventBus);
    }*/

}
/*
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.SOCKETING_TABLE_MENU_TYPE, SocketingTableScreen::new);
        });
    }
 */