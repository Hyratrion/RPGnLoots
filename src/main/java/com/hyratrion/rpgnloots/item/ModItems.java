package com.hyratrion.rpgnloots.item;

import com.hyratrion.rpgnloots.RPGNLOOT;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RPGNLOOT.MOD_ID);

    public static final RegistryObject<Item> PLIERS = ITEMS.register("pliers",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 0
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_0 = ITEMS.register("gem_damage_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_0 = ITEMS.register("gem_shield_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_0 = ITEMS.register("gem_max_life_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_0 = ITEMS.register("gem_chance_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_0 = ITEMS.register("gem_critical_chance_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_0 = ITEMS.register("gem_critical_damage_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_0 = ITEMS.register("gem_reparation_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_0 = ITEMS.register("gem_speed_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 1
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_1 = ITEMS.register("gem_damage_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_1 = ITEMS.register("gem_shield_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_1 = ITEMS.register("gem_max_life_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_1 = ITEMS.register("gem_chance_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_1 = ITEMS.register("gem_critical_chance_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_1 = ITEMS.register("gem_critical_damage_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_1 = ITEMS.register("gem_reparation_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_1 = ITEMS.register("gem_speed_lvl_1",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 2
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_2 = ITEMS.register("gem_damage_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_2 = ITEMS.register("gem_shield_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_2 = ITEMS.register("gem_max_life_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_2 = ITEMS.register("gem_chance_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_2 = ITEMS.register("gem_critical_chance_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_2 = ITEMS.register("gem_critical_damage_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_2 = ITEMS.register("gem_reparation_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_2 = ITEMS.register("gem_speed_lvl_2",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 3
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_3 = ITEMS.register("gem_damage_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_3 = ITEMS.register("gem_shield_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_3 = ITEMS.register("gem_max_life_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_3 = ITEMS.register("gem_chance_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_3 = ITEMS.register("gem_critical_chance_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_3 = ITEMS.register("gem_critical_damage_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_3 = ITEMS.register("gem_reparation_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_3 = ITEMS.register("gem_speed_lvl_3",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 4
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_4 = ITEMS.register("gem_damage_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_4 = ITEMS.register("gem_shield_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_4 = ITEMS.register("gem_max_life_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_4 = ITEMS.register("gem_chance_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_4 = ITEMS.register("gem_critical_chance_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_4 = ITEMS.register("gem_critical_damage_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_4 = ITEMS.register("gem_reparation_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_4 = ITEMS.register("gem_speed_lvl_4",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    //Gemmes lvl 5
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_5 = ITEMS.register("gem_damage_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SHIELD_LVL_5 = ITEMS.register("gem_shield_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_5 = ITEMS.register("gem_max_life_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CHANCE_LVL_5 = ITEMS.register("gem_chance_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_5 = ITEMS.register("gem_critical_chance_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_5 = ITEMS.register("gem_critical_damage_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_REPARATION_LVL_5 = ITEMS.register("gem_reparation_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> GEM_SPEED_LVL_5 = ITEMS.register("gem_speed_lvl_5",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static Item[] GEM_LVL_0;
    public static Item[] GEM_LVL_1;
    public static Item[] GEM_LVL_2;
    public static Item[] GEM_LVL_3;
    public static Item[] GEM_LVL_4;
    public static Item[] GEM_LVL_5;

    public static Map<Item, Float> GEMS_VALUES = new HashMap<>();
    public static Map<String, Item> GEMS_REFERENCES = new HashMap<>();

    public static final String GEM_TYPE = "gem_type";

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

    public static void initGemsValuesAndReferences()
    {
        //GEMS_VALUES

        GEMS_VALUES.put(GEM_DAMAGE_LVL_1.get(), 15f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_CHANCE_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_1.get(), 15f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_1.get(), 15f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_SPEED_LVL_1.get(), 15f);

        GEMS_VALUES.put(GEM_DAMAGE_LVL_2.get(), 30f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_CHANCE_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_2.get(), 30f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_2.get(), 30f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_SPEED_LVL_2.get(), 30f);

        GEMS_VALUES.put(GEM_DAMAGE_LVL_3.get(), 45f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_CHANCE_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_3.get(), 45f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_3.get(), 45f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_SPEED_LVL_3.get(), 45f);

        GEMS_VALUES.put(GEM_DAMAGE_LVL_4.get(), 70f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_4.get(), 4f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_4.get(), 4f);
        GEMS_VALUES.put(GEM_CHANCE_LVL_4.get(), 4f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_4.get(), 70f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_4.get(), 70f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_4 .get(), 4f);
        GEMS_VALUES.put(GEM_SPEED_LVL_4.get(), 70f);

        GEMS_VALUES.put(GEM_DAMAGE_LVL_5.get(), 100f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_CHANCE_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_5.get(), 100f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_5.get(), 100f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_SPEED_LVL_5.get(), 100f);


        //GEMS_REFERENCES

        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_1.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_1.get().getRegistryName().getPath(), GEM_SHIELD_LVL_1.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_1.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_CHANCE_LVL_1.get().getRegistryName().getPath(), GEM_CHANCE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_1.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_1.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_1.get().getRegistryName().getPath(), GEM_REPARATION_LVL_1.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_1.get().getRegistryName().getPath(), GEM_SPEED_LVL_1.get());

        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_2.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_2.get().getRegistryName().getPath(), GEM_SHIELD_LVL_2.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_2.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_CHANCE_LVL_2.get().getRegistryName().getPath(), GEM_CHANCE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_2.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_2.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_2.get().getRegistryName().getPath(), GEM_REPARATION_LVL_2.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_2.get().getRegistryName().getPath(), GEM_SPEED_LVL_2.get());

        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_3.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_3.get().getRegistryName().getPath(), GEM_SHIELD_LVL_3.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_3.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_CHANCE_LVL_3.get().getRegistryName().getPath(), GEM_CHANCE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_3.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_3.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_3.get().getRegistryName().getPath(), GEM_REPARATION_LVL_3.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_3.get().getRegistryName().getPath(), GEM_SPEED_LVL_3.get());

        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_4.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_4.get().getRegistryName().getPath(), GEM_SHIELD_LVL_4.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_4.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_CHANCE_LVL_4.get().getRegistryName().getPath(), GEM_CHANCE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_4.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_4.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_4.get().getRegistryName().getPath(), GEM_REPARATION_LVL_4.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_4.get().getRegistryName().getPath(), GEM_SPEED_LVL_4.get());

        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_5.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_5.get().getRegistryName().getPath(), GEM_SHIELD_LVL_5.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_5.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_CHANCE_LVL_5.get().getRegistryName().getPath(), GEM_CHANCE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_5.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_5.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_5.get().getRegistryName().getPath(), GEM_REPARATION_LVL_5.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_5.get().getRegistryName().getPath(), GEM_SPEED_LVL_5.get());

        
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
        
        GEM_LVL_2 = new Item[]
        {
            GEM_DAMAGE_LVL_2.get(), GEM_SHIELD_LVL_2.get(), GEM_MAX_LIFE_LVL_2.get(), GEM_CHANCE_LVL_2.get(), GEM_CRITICAL_CHANCE_LVL_2.get(), GEM_CRITICAL_DAMAGE_LVL_2.get(), GEM_REPARATION_LVL_2.get(), GEM_SPEED_LVL_2.get()
        };
        GEM_LVL_3 = new Item[]
        {
            GEM_DAMAGE_LVL_3.get(), GEM_SHIELD_LVL_3.get(), GEM_MAX_LIFE_LVL_3.get(), GEM_CHANCE_LVL_3.get(), GEM_CRITICAL_CHANCE_LVL_3.get(), GEM_CRITICAL_DAMAGE_LVL_3.get(), GEM_REPARATION_LVL_3.get(), GEM_SPEED_LVL_3.get()
        };

        GEM_LVL_4 = new Item[]
        {
            GEM_DAMAGE_LVL_4.get(), GEM_SHIELD_LVL_4.get(), GEM_MAX_LIFE_LVL_4.get(), GEM_CHANCE_LVL_4.get(), GEM_CRITICAL_CHANCE_LVL_4.get(), GEM_CRITICAL_DAMAGE_LVL_4.get(), GEM_REPARATION_LVL_4.get(), GEM_SPEED_LVL_4.get()
        };
        GEM_LVL_5 = new Item[]
        {
            GEM_DAMAGE_LVL_5.get(), GEM_SHIELD_LVL_5.get(), GEM_MAX_LIFE_LVL_5.get(), GEM_CHANCE_LVL_5.get(), GEM_CRITICAL_CHANCE_LVL_5.get(), GEM_CRITICAL_DAMAGE_LVL_5.get(), GEM_REPARATION_LVL_5.get(), GEM_SPEED_LVL_5.get()
        };
    }
}
