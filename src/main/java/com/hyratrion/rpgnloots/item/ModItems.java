package com.hyratrion.rpgnloots.item;

import com.hyratrion.rpgnloots.RPGLOOT;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RPGLOOT.MOD_ID);

    //Gemmes lvl 0
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_0 = ITEMS.register( "gem_damage_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_0 = ITEMS.register( "gem_shield_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_0 = ITEMS.register( "gem_max_life_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_0 = ITEMS.register( "gem_chance_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_0 = ITEMS.register( "gem_critical_chance_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_0 = ITEMS.register( "gem_critical_damage_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_0 = ITEMS.register( "gem_reparation_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_0 = ITEMS.register( "gem_speed_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 1
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_1 = ITEMS.register( "gem_damage_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_1 = ITEMS.register( "gem_shield_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_1 = ITEMS.register( "gem_max_life_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_1 = ITEMS.register( "gem_chance_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_1 = ITEMS.register( "gem_critical_chance_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_1 = ITEMS.register( "gem_critical_damage_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_1 = ITEMS.register( "gem_reparation_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_1 = ITEMS.register( "gem_speed_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 2
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_2 = ITEMS.register( "gem_damage_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_2 = ITEMS.register( "gem_shield_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_2 = ITEMS.register( "gem_max_life_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_2 = ITEMS.register( "gem_chance_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_2 = ITEMS.register( "gem_critical_chance_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_2 = ITEMS.register( "gem_critical_damage_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_2 = ITEMS.register( "gem_reparation_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_2 = ITEMS.register( "gem_speed_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 3
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_3 = ITEMS.register( "gem_damage_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_3 = ITEMS.register( "gem_shield_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_3 = ITEMS.register( "gem_max_life_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_3 = ITEMS.register( "gem_chance_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_3 = ITEMS.register( "gem_critical_chance_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_3 = ITEMS.register( "gem_critical_damage_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_3 = ITEMS.register( "gem_reparation_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_3 = ITEMS.register( "gem_speed_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 4
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_4 = ITEMS.register( "gem_damage_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_4 = ITEMS.register( "gem_shield_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_4 = ITEMS.register( "gem_max_life_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_4 = ITEMS.register( "gem_chance_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_4 = ITEMS.register( "gem_critical_chance_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_4 = ITEMS.register( "gem_critical_damage_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_4 = ITEMS.register( "gem_reparation_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_4 = ITEMS.register( "gem_speed_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 5
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_5 = ITEMS.register( "gem_damage_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_5 = ITEMS.register( "gem_shield_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_5 = ITEMS.register( "gem_max_life_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_5 = ITEMS.register( "gem_chance_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_5 = ITEMS.register( "gem_critical_chance_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_5 = ITEMS.register( "gem_critical_damage_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_5 = ITEMS.register( "gem_reparation_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_5 = ITEMS.register( "gem_speed_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 6
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_6 = ITEMS.register( "gem_damage_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_6 = ITEMS.register( "gem_shield_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_6 = ITEMS.register( "gem_max_life_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_6 = ITEMS.register( "gem_chance_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_6 = ITEMS.register( "gem_critical_chance_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_6 = ITEMS.register( "gem_critical_damage_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_6 = ITEMS.register( "gem_reparation_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_6 = ITEMS.register( "gem_speed_lvl_6",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Custom sword
    public static final RegistryObject<Item> CUSTOM_SWORD = ITEMS.register( "custom_sword",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static Item[] GEM_LVL_0;
    public static Item[] GEM_LVL_1;

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
    public static void createArrayGems()
    {
        GEM_LVL_0 = new Item[]
        {
            GEM_DAMAGE_LVL_0.get(), GEM_SHIELD_LVL_0.get(), GEM_MAX_LIFE_LVL_0.get(), GEM_CHANCE_LVL_0.get(), GEM_CRITICAL_CHANCE_LVL_0.get(), GEM_CRITICAL_DAMAGE_LVL_0.get(), GEM_REPARATION_LVL_0.get(), GEM_SPEED_LVL_0.get()
        };
        GEM_LVL_1 = new Item[]
        {
            GEM_DAMAGE_LVL_1.get(), GEM_SHIELD_LVL_1.get(), GEM_MAX_LIFE_LVL_1.get(), GEM_CHANCE_LVL_1.get(), GEM_CRITICAL_CHANCE_LVL_1.get(), GEM_CRITICAL_DAMAGE_LVL_1.get(), GEM_REPARATION_LVL_1.get(), GEM_SPEED_LVL_1.get()
        };
    }
}
