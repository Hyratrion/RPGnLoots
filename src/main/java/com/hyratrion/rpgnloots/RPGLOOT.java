package com.hyratrion.rpgnloots;

import com.hyratrion.rpgnloots.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.stream.Collectors;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPGLOOT.MOD_ID)
public class RPGLOOT {
    public static final String MOD_ID = "rpgnloots";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogManager.getLogger();

    // Very important Comment
    public RPGLOOT() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
    /*@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class EventHandlers {
        @SubscribeEvent
        public static void runData(GatherDataEvent event)
        {
                event.getGenerator().addProvider(new DataProvider(event.getGenerator(), MOD_ID));
        }
    }*/
    /*public static class DataProvider extends GlobalLootModifierProvider
    {
        public DataProvider(DataGenerator gen, String modid)
        {
            super(gen, modid);
        }

        @Override
        protected void start()
        {
            add("loot_gemme_degat_critique", GEMME_DEGAT_CRITIQUE.get(), new ExampleModifier(
                    new LootItemCondition[] {

                            LootItemKilledByPlayerCondition.killedByPlayer().build(),
                            LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.getByName("zombie")).build()
                    },
                    1, ModItems.GEMME_DEGAT_CRITIQUE.get())
            );
        }
    }*/