package com.hyratrion.rpgnloots.item;

import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.block.ModBlocks;
import com.hyratrion.rpgnloots.util.ModTags;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RPGNLOOT.MOD_ID);

    public static final RegistryObject<Item> PLIERS = ITEMS.register("pliers",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB)));

    public static final RegistryObject<Item> SOCKETING_TABLE = block(ModBlocks.SOCKETING_TABLE, ModCreativeModeTab.RPGNLOOTS_TAB);

    //Gemmes lvl 0
    public static final RegistryObject<Item> GEM_CHANCE_LVL_0 = ITEMS.register("gem_chance_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_0 = ITEMS.register("gem_critical_chance_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_0 = ITEMS.register("gem_critical_damage_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_DAMAGE_LVL_0 = ITEMS.register("gem_damage_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_0 = ITEMS.register("gem_max_life_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_REPARATION_LVL_0 = ITEMS.register("gem_reparation_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_SHIELD_LVL_0 = ITEMS.register("gem_shield_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });

    public static final RegistryObject<Item> GEM_SPEED_LVL_0 = ITEMS.register("gem_speed_lvl_0",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(new TranslatableComponent("tooltip.rpgnloots.gem_use_for_level_upper"));
                }
            });


    //Gemmes lvl 1

    public static final RegistryObject<Item> GEM_CHANCE_LVL_1 = GemRegister("gem_chance_lvl_1");

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_1 = GemRegister("gem_critical_chance_lvl_1");

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_1 = GemRegister("gem_critical_damage_lvl_1");

    public static final RegistryObject<Item> GEM_DAMAGE_LVL_1 = GemRegister("gem_damage_lvl_1");

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_1 = GemRegister("gem_max_life_lvl_1");

    public static final RegistryObject<Item> GEM_REPARATION_LVL_1 = GemRegister("gem_reparation_lvl_1");

    public static final RegistryObject<Item> GEM_SHIELD_LVL_1 = GemRegister("gem_shield_lvl_1");

    public static final RegistryObject<Item> GEM_SPEED_LVL_1 = GemRegister("gem_speed_lvl_1");

    //Gemmes lvl 2
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_2 = GemRegister("gem_damage_lvl_2");



    public static final RegistryObject<Item> GEM_CHANCE_LVL_2 = GemRegister("gem_chance_lvl_2");

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_2 = GemRegister("gem_critical_chance_lvl_2");

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_2 = GemRegister("gem_critical_damage_lvl_2");

    public static final RegistryObject<Item> GEM_REPARATION_LVL_2 = GemRegister("gem_reparation_lvl_2");

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_2 = GemRegister("gem_max_life_lvl_2");

    public static final RegistryObject<Item> GEM_SHIELD_LVL_2 = GemRegister("gem_shield_lvl_2");

    public static final RegistryObject<Item> GEM_SPEED_LVL_2 = GemRegister("gem_speed_lvl_2");

    //Gemmes lvl 3
    public static final RegistryObject<Item> GEM_CHANCE_LVL_3 = GemRegister("gem_chance_lvl_3");

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_3 = GemRegister("gem_critical_chance_lvl_3");

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_3 = GemRegister("gem_critical_damage_lvl_3");

    public static final RegistryObject<Item> GEM_DAMAGE_LVL_3 = GemRegister("gem_damage_lvl_3");

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_3 = GemRegister("gem_max_life_lvl_3");

    public static final RegistryObject<Item> GEM_REPARATION_LVL_3 = GemRegister("gem_reparation_lvl_3");

    public static final RegistryObject<Item> GEM_SHIELD_LVL_3 = GemRegister("gem_shield_lvl_3");

    public static final RegistryObject<Item> GEM_SPEED_LVL_3 = GemRegister("gem_speed_lvl_3");

    //Gemmes lvl 4
    public static final RegistryObject<Item> GEM_DAMAGE_LVL_4 = GemRegister("gem_damage_lvl_4");

    public static final RegistryObject<Item> GEM_CHANCE_LVL_4 = GemRegister("gem_chance_lvl_4");

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_4 = GemRegister("gem_critical_chance_lvl_4");

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_4 = GemRegister("gem_critical_damage_lvl_4");

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_4 = GemRegister("gem_max_life_lvl_4");

    public static final RegistryObject<Item> GEM_REPARATION_LVL_4 = GemRegister("gem_reparation_lvl_4");

    public static final RegistryObject<Item> GEM_SHIELD_LVL_4 = GemRegister("gem_shield_lvl_4");

    public static final RegistryObject<Item> GEM_SPEED_LVL_4 = GemRegister("gem_speed_lvl_4");

    //Gemmes lvl 5
    public static final RegistryObject<Item> GEM_CHANCE_LVL_5 = GemRegister("gem_chance_lvl_5");

    public static final RegistryObject<Item> GEM_CRITICAL_CHANCE_LVL_5 = GemRegister("gem_critical_chance_lvl_5");

    public static final RegistryObject<Item> GEM_CRITICAL_DAMAGE_LVL_5 = GemRegister("gem_critical_damage_lvl_5");

    public static final RegistryObject<Item> GEM_DAMAGE_LVL_5 = GemRegister("gem_damage_lvl_5");

    public static final RegistryObject<Item> GEM_MAX_LIFE_LVL_5 = GemRegister("gem_max_life_lvl_5");

    public static final RegistryObject<Item> GEM_REPARATION_LVL_5 = GemRegister("gem_reparation_lvl_5");

    public static final RegistryObject<Item> GEM_SHIELD_LVL_5 = GemRegister("gem_shield_lvl_5");

    public static final RegistryObject<Item> GEM_SPEED_LVL_5 = GemRegister("gem_speed_lvl_5");

    public static Item[] GEM_LVL_0;
    public static Item[] GEM_LVL_1;
    public static Item[] GEM_LVL_2;
    public static Item[] GEM_LVL_3;
    public static Item[] GEM_LVL_4;
    public static Item[] GEM_LVL_5;

    public static Map<Item, Float> GEMS_VALUES = new HashMap<>();
    public static Map<String, Item> GEMS_REFERENCES = new HashMap<>();

    public static final String GEM_TYPE = "gem_type";

    private static RegistryObject<Item> GemRegister(String name)
    {
        return ITEMS.register(name,
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPGNLOOTS_TAB))
            {
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
                {
                    components.add(gemDescription(this));
                }
            }
        );
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        System.out.println("--- Makotache --- block => "+block.getId().getPath());
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static Component gemDescription(Item gem)
    {
        Component result;
        TagKey<Item> itemType = ModTags.GetGemType(gem);

        if(itemType != null)
        {
            String text_gem_type;
            if(itemType.equals(ModTags.Items.GEM_TYPE_CHANCE))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_chance";
            }
            else if (itemType.equals(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_critical_chance";
            }
            else if (itemType.equals(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_critical_damage";
            }
            else if (itemType.equals(ModTags.Items.GEM_TYPE_DAMAGE))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_damage";
            }
            else if (itemType.equals(ModTags.Items.GEM_TYPE_MAX_LIFE))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_max_life";
            }
            else if (itemType.equals(ModTags.Items.GEM_TYPE_REPARATION))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_reparation";
            }
            else if (itemType.equals(ModTags.Items.GEM_TYPE_SHIELD))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_shield";
            }
            else //if (itemType.equals(ModTags.Items.GEM_TYPE_SPEED))
            {
                text_gem_type = "tooltip.rpgnloots.gem_of_speed";
            }

            float value = ModTags.GetGemValue(gem);

            boolean showPercent = ModTags.GemIsType(gem, ModTags.Items.GEM_PERCENT);

            String percentSpace = showPercent ? "% " : " ";

            result = (new TextComponent(ATTRIBUTE_MODIFIER_FORMAT.format(value) + percentSpace)).append(new TranslatableComponent(text_gem_type));
        }
        else
        {
            result = new TextComponent("");
        }

        return result;
    }

    public static void initGemsValuesAndReferences()
    {
        //GEMS_VALUES

        GEMS_VALUES.put(GEM_CHANCE_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_1.get(), 15f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_1.get(), 15f);
        GEMS_VALUES.put(GEM_DAMAGE_LVL_1.get(), 15f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_1.get(), 1f);
        GEMS_VALUES.put(GEM_SPEED_LVL_1.get(), 15f);

        GEMS_VALUES.put(GEM_CHANCE_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_2.get(), 30f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_2.get(), 30f);
        GEMS_VALUES.put(GEM_DAMAGE_LVL_2.get(), 30f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_2.get(), 2f);
        GEMS_VALUES.put(GEM_SPEED_LVL_2.get(), 30f);

        GEMS_VALUES.put(GEM_CHANCE_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_3.get(), 45f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_3.get(), 45f);
        GEMS_VALUES.put(GEM_DAMAGE_LVL_3.get(), 45f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_3.get(), 3f);
        GEMS_VALUES.put(GEM_SPEED_LVL_3.get(), 45f);

        GEMS_VALUES.put(GEM_CHANCE_LVL_4.get(), 4f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_4.get(), 70f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_4.get(), 70f);
        GEMS_VALUES.put(GEM_DAMAGE_LVL_4.get(), 70f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_4.get(), 4f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_4 .get(), 4f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_4.get(), 4f);
        GEMS_VALUES.put(GEM_SPEED_LVL_4.get(), 70f);

        GEMS_VALUES.put(GEM_CHANCE_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_CRITICAL_CHANCE_LVL_5.get(), 100f);
        GEMS_VALUES.put(GEM_CRITICAL_DAMAGE_LVL_5.get(), 100f);
        GEMS_VALUES.put(GEM_DAMAGE_LVL_5.get(), 100f);
        GEMS_VALUES.put(GEM_MAX_LIFE_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_REPARATION_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_SHIELD_LVL_5.get(), 5f);
        GEMS_VALUES.put(GEM_SPEED_LVL_5.get(), 100f);


        //GEMS_REFERENCES

        GEMS_REFERENCES.put(GEM_CHANCE_LVL_1.get().getRegistryName().getPath(), GEM_CHANCE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_1.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_1.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_1.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_1.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_1.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_1.get().getRegistryName().getPath(), GEM_REPARATION_LVL_1.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_1.get().getRegistryName().getPath(), GEM_SHIELD_LVL_1.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_1.get().getRegistryName().getPath(), GEM_SPEED_LVL_1.get());

        GEMS_REFERENCES.put(GEM_CHANCE_LVL_2.get().getRegistryName().getPath(), GEM_CHANCE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_2.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_2.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_2.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_2.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_2.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_2.get().getRegistryName().getPath(), GEM_REPARATION_LVL_2.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_2.get().getRegistryName().getPath(), GEM_SHIELD_LVL_2.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_2.get().getRegistryName().getPath(), GEM_SPEED_LVL_2.get());

        GEMS_REFERENCES.put(GEM_CHANCE_LVL_3.get().getRegistryName().getPath(), GEM_CHANCE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_3.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_3.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_3.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_3.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_3.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_3.get().getRegistryName().getPath(), GEM_REPARATION_LVL_3.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_3.get().getRegistryName().getPath(), GEM_SHIELD_LVL_3.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_3.get().getRegistryName().getPath(), GEM_SPEED_LVL_3.get());

        GEMS_REFERENCES.put(GEM_CHANCE_LVL_4.get().getRegistryName().getPath(), GEM_CHANCE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_4.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_4.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_4.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_4.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_4.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_4.get().getRegistryName().getPath(), GEM_REPARATION_LVL_4.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_4.get().getRegistryName().getPath(), GEM_SHIELD_LVL_4.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_4.get().getRegistryName().getPath(), GEM_SPEED_LVL_4.get());

        GEMS_REFERENCES.put(GEM_CHANCE_LVL_5.get().getRegistryName().getPath(), GEM_CHANCE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_CHANCE_LVL_5.get().getRegistryName().getPath(), GEM_CRITICAL_CHANCE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_CRITICAL_DAMAGE_LVL_5.get().getRegistryName().getPath(), GEM_CRITICAL_DAMAGE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_DAMAGE_LVL_5.get().getRegistryName().getPath(), GEM_DAMAGE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_MAX_LIFE_LVL_5.get().getRegistryName().getPath(), GEM_MAX_LIFE_LVL_5.get());
        GEMS_REFERENCES.put(GEM_REPARATION_LVL_5.get().getRegistryName().getPath(), GEM_REPARATION_LVL_5.get());
        GEMS_REFERENCES.put(GEM_SHIELD_LVL_5.get().getRegistryName().getPath(), GEM_SHIELD_LVL_5.get());
        GEMS_REFERENCES.put(GEM_SPEED_LVL_5.get().getRegistryName().getPath(), GEM_SPEED_LVL_5.get());
    }

    public static void createArrayGems()
    {
        GEM_LVL_0 = new Item[]
        {
            GEM_CHANCE_LVL_0.get(),
            GEM_CRITICAL_CHANCE_LVL_0.get(),
            GEM_CRITICAL_DAMAGE_LVL_0.get(),
            GEM_DAMAGE_LVL_0.get(),
            GEM_MAX_LIFE_LVL_0.get(),
            GEM_REPARATION_LVL_0.get(),
            GEM_SHIELD_LVL_0.get(),
            GEM_SPEED_LVL_0.get()
        };
        GEM_LVL_1 = new Item[]
        {
            GEM_CHANCE_LVL_1.get(),
            GEM_CRITICAL_CHANCE_LVL_1.get(),
            GEM_CRITICAL_DAMAGE_LVL_1.get(),
            GEM_DAMAGE_LVL_1.get(),
            GEM_MAX_LIFE_LVL_1.get(),
            GEM_REPARATION_LVL_1.get(),
            GEM_SHIELD_LVL_1.get(),
            GEM_SPEED_LVL_1.get()
        };
        
        GEM_LVL_2 = new Item[]
        {
            GEM_CHANCE_LVL_2.get(),
            GEM_CRITICAL_CHANCE_LVL_2.get(),
            GEM_CRITICAL_DAMAGE_LVL_2.get(),
            GEM_DAMAGE_LVL_2.get(),
            GEM_MAX_LIFE_LVL_2.get(),
            GEM_REPARATION_LVL_2.get(),
            GEM_SHIELD_LVL_2.get(),
            GEM_SPEED_LVL_2.get()
        };

        GEM_LVL_3 = new Item[]
        {
            GEM_CHANCE_LVL_3.get(),
            GEM_CRITICAL_CHANCE_LVL_3.get(),
            GEM_CRITICAL_DAMAGE_LVL_3.get(),
            GEM_DAMAGE_LVL_3.get(),
            GEM_MAX_LIFE_LVL_3.get(),
            GEM_REPARATION_LVL_3.get(),
            GEM_SHIELD_LVL_3.get(),
            GEM_SPEED_LVL_3.get()
        };

        GEM_LVL_4 = new Item[]
        {
            GEM_CHANCE_LVL_4.get(),
            GEM_CRITICAL_CHANCE_LVL_4.get(),
            GEM_CRITICAL_DAMAGE_LVL_4.get(),
            GEM_DAMAGE_LVL_4.get(),
            GEM_MAX_LIFE_LVL_4.get(),
            GEM_REPARATION_LVL_4.get(),
            GEM_SHIELD_LVL_4.get(),
            GEM_SPEED_LVL_4.get()
        };

        GEM_LVL_5 = new Item[]
        {
            GEM_CHANCE_LVL_5.get(),
            GEM_CRITICAL_CHANCE_LVL_5.get(),
            GEM_CRITICAL_DAMAGE_LVL_5.get(),
            GEM_DAMAGE_LVL_5.get(),
            GEM_MAX_LIFE_LVL_5.get(),
            GEM_REPARATION_LVL_5.get(),
            GEM_SHIELD_LVL_5.get(),
            GEM_SPEED_LVL_5.get()
        };
    }
}
