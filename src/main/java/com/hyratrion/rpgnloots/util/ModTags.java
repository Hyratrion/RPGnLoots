package com.hyratrion.rpgnloots.util;

import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.lang.model.element.UnknownElementException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ModTags
{
    public static void AddGemTag(ItemStack itemStack, StringTag tagItem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            tag.put(ModItems.GEM_TYPE, new ListTag());
        }

        CompoundTag compoundtag = new CompoundTag();
        compoundtag.putString("Name", tagItem.toString());

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        listtag.add(compoundtag);
    }

    public static String[] GetGemTags(CompoundTag tag)
    {
        if (tag.contains(ModItems.GEM_TYPE))
        {
            ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
            if(listtag == null)
            {
                return null;
            }

            List<String> result = new ArrayList<>();
            for(int i = 0; i < listtag.size(); i++)
            {
                result.add(listtag.getCompound(i).get("Name").toString().replace("\"", "").replace("'", ""));
            }

            return result.toArray(new String[result.size() - 1]);
        }
        return null;
    }

    public static String[] GetGemTags(ItemStack itemStack)
    {
        return GetGemTags(itemStack.getOrCreateTag());
    }

    public static Item GetGem(String tag)
    {
        return ModItems.GEMS_REFERENCES.get(tag);
    }

    public static Item[] GetGems(@Nonnull String[] allGemsEquiped)
    {
        List<Item> result = new ArrayList<>();
        for(int i = 0; i < allGemsEquiped.length; i++)
        {
            result.add(ModItems.GEMS_REFERENCES.get(allGemsEquiped[i]));
        }
        return result.toArray(new Item[result.size()-1]);
    }

    public static Item[] GetGems(ItemStack itemStack)
    {
        return GetGems(GetGemTags(itemStack));
    }


    public static boolean GemEqualTag(Item gem, String tag)
    {
        return GetGem(tag).equals(gem);
    }

    public static boolean GemIsType(Item gem, TagKey<Item> type)
    {
        for(TagKey<Item> gem_type : Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(gem).get()).tags().toList())
        {
            if(gem_type.equals(type))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean GemIsType(String tag, TagKey<Item> type)
    {
        return GemIsType(GetGem(tag), type);
    }

    public static boolean HaveGemOfType(@Nonnull String[] allGemsEquiped, TagKey<Item> type)
    {
        Item[] gemTags = GetGems(allGemsEquiped);
        for(int i = 0; i < gemTags.length; i++)
        {
            if(GemIsType(gemTags[i], type))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean HaveGemOfType(ItemStack itemStack, TagKey<Item> type)
    {
        return HaveGemOfType(GetGemTags(itemStack), type);
    }


    public static float GetGemValue(String tag)
    {
        return ModItems.GEMS_VALUES.get(GetGem(tag));
    }

    public static Float[] GetGemValueOfType(@Nonnull String[] allGemsEquiped, TagKey<Item> type)
    {
        List<Float> result = new ArrayList<>();
        for(int i = 0; i < allGemsEquiped.length; i++)
        {
            if(GemIsType(allGemsEquiped[i], type))
            {
                result.add(GetGemValue(allGemsEquiped[i]));
            }
        }
        return result.toArray(new Float[result.size()-1]);   }

    public static Float[] GetGemValueOfType(ItemStack itemStack, TagKey<Item> type)
    {
        return GetGemValueOfType(GetGemTags(itemStack), type);
    }


    public static float GetGemTotalValueOfType(@Nonnull String[] allGemsEquiped, TagKey<Item> type)
    {
        Float[] allGemsValue = GetGemValueOfType(allGemsEquiped, type);
        float result = 0;

        for(int i = 0; i < allGemsValue.length; i++)
        {
            result += allGemsValue[i];
        }
        return result;
    }

    public static float GetGemTotalValueOfType(ItemStack itemStack, TagKey<Item> type)
    {
        return GetGemTotalValueOfType(GetGemTags(itemStack), type);
    }



    public static StringTag CreateTagFromItem(Item item)
    {
        return StringTag.valueOf(item.getRegistryName().getPath());
    }

    public static final StringTag DEFAULT_TAG_VALUE = StringTag.valueOf("none");



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
        public static final TagKey<Item> GEM_TYPE_DAMAGE = forgeTag("gems/gem_type_damage");
        public static final TagKey<Item> GEM_TYPE_SHIELD = forgeTag("gems/gem_type_shield");
        public static final TagKey<Item> GEM_TYPE_MAX_LIFE = forgeTag("gems/gem_type_max_life");
        public static final TagKey<Item> GEM_TYPE_CHANCE = forgeTag("gems/gem_type_chance");
        public static final TagKey<Item> GEM_TYPE_CRITICAL_CHANCE = forgeTag("gems/gem_type_critical_chance");
        public static final TagKey<Item> GEM_TYPE_CRITICAL_DAMAGE = forgeTag("gems/gem_type_critical_damage");
        public static final TagKey<Item> GEM_TYPE_REPARATION = forgeTag("gems/gem_type_reparation");
        public static final TagKey<Item> GEM_TYPE_SPEED = forgeTag("gems/gem_type_speed");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RPGNLOOT.MOD_ID, name));
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
