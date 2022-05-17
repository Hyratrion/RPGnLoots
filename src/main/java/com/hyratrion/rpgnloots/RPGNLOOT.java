package com.hyratrion.rpgnloots;

import com.hyratrion.rpgnloots.block.ModBlocks;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import com.hyratrion.rpgnloots.item.ModItems;
import com.hyratrion.rpgnloots.screen.ModMenuTypes;
import com.hyratrion.rpgnloots.screen.SocketingTableScreen;
import com.hyratrion.rpgnloots.util.ModStats;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPGNLOOT.MOD_ID)
public class RPGNLOOT
{
    public static final String MOD_ID = "rpgnloots";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogManager.getLogger();

    // Very important Comment
    public RPGNLOOT() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        CustomAttributes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOCKETING_TABLE.get(), RenderType.cutout());

        MenuScreens.register(ModMenuTypes.SOCKETING_TABLE_MENU_TYPE, SocketingTableScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModItems.createArrayGems();
        ModItems.initGemsValuesAndReferences();
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