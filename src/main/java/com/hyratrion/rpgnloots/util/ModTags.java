package com.hyratrion.rpgnloots.util;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import com.hyratrion.rpgnloots.item.GemDictionary;
import com.hyratrion.rpgnloots.item.Gems;
import com.hyratrion.rpgnloots.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModTags
{



    public static StringTag CreateTagFromItem( Item item)
    {
        return StringTag.valueOf(item.getRegistryName().getPath());
    }

    public static final StringTag DEFAULT_TAG_VALUE = StringTag.valueOf("none");
    public static final String RPGNLOOT_MODIFIER = "rpgnloot_modifier";


    public static boolean ItemSupportedByMod(ItemStack itemStack)
    {
        return itemStack.getOrCreateTag().contains(RPGNLOOT_MODIFIER);
    }

    /*public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_VALUABLES =
                tag("gem_valuables");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RPGLOOT.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }*/


    public static class Items
    {
        public static List<TagKey<Item>> GEM_PERCENT = new ArrayList<>();
        public static List<TagKey<Item>> GEM_RAW = new ArrayList<>();
        public static List<TagKey<Item>> GEM_ALL = new ArrayList<>();

        public static final TagKey<Item> GEM_TYPE_DAMAGE = tag("gems/gem_type_damage", true);
        public static final TagKey<Item> GEM_TYPE_SHIELD = tag("gems/gem_type_shield", false);
        public static final TagKey<Item> GEM_TYPE_MAX_LIFE = tag("gems/gem_type_max_life", false);
        public static final TagKey<Item> GEM_TYPE_CHANCE = tag("gems/gem_type_chance", false);
        public static final TagKey<Item> GEM_TYPE_CRITICAL_CHANCE = tag("gems/gem_type_critical_chance", true);
        public static final TagKey<Item> GEM_TYPE_CRITICAL_DAMAGE = tag("gems/gem_type_critical_damage", true);
        public static final TagKey<Item> GEM_TYPE_REPARATION = tag("gems/gem_type_reparation", false);
        public static final TagKey<Item> GEM_TYPE_SPEED = tag("gems/gem_type_speed", true);

        private static TagKey<Item> tag(String name, boolean percent)
        {
            TagKey<Item> result = ItemTags.create(new ResourceLocation(RPGNLOOT.MOD_ID, name));
            if(percent)
            {
                GEM_PERCENT.add(result);
            }
            else
            {
                GEM_RAW.add(result);
            }

            GEM_ALL.add(result);
            return result;
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }



}
//Generate random number x=(1/5)
//Generate stuffTier"x"
//Get "x" propertytag
//Add propertytag to generated item
