package com.hyratrion.rpgnloots.util;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
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
    public static EquipmentSlot[] GetEquipmentSlotOf( Item item)
    {
        List<EquipmentSlot> result = new ArrayList<>();

        if (item instanceof ArmorItem armorItem)
        {
            result.add(armorItem.getSlot());
        } else if (item instanceof TieredItem)
        {
            result.add(EquipmentSlot.MAINHAND);
        } else if (item instanceof FishingRodItem || item instanceof ShearsItem ||
                item instanceof ShieldItem || item instanceof TridentItem ||
                item instanceof CrossbowItem || item instanceof BowItem)
        {
            result.add(EquipmentSlot.MAINHAND);
            result.add(EquipmentSlot.OFFHAND);
        } else if (item instanceof ElytraItem)
        {
            result.add(EquipmentSlot.CHEST);
        }

        return result.toArray(new EquipmentSlot[result.size() - 1]);
    }

    public static EquipmentSlot[] GetEquipmentSlotOf( ItemStack itemStack)
    {
        return GetEquipmentSlotOf(itemStack.getItem());
    }

    public static boolean AddGemTag( ItemStack itemStack,  String tagItem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            tag.put(ModItems.GEM_TYPE, new ListTag());
        }

        ListTag listtag = (ListTag) tag.get(ModItems.GEM_TYPE);

        int gemCountFill = CountGem(itemStack, false);
        int maxSlotGem = 0;

        EquipmentSlot[] equipmentSlots = GetEquipmentSlotOf(itemStack);
        boolean gem_slot_loaded = false;
        boolean more_gem_slot_loaded = false;

        for (int i = 0; i < equipmentSlots.length; i++)
        {
            Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(equipmentSlots[i]);

            if (!gem_slot_loaded && attributeModifiers.containsKey(CustomAttributes.GEM_SLOT.get()))
            {
                maxSlotGem += StaticClass.GetValueFromAttributeModifierMap(itemStack, CustomAttributes.GEM_SLOT.get());
                gem_slot_loaded = true;
            }

            if (!more_gem_slot_loaded && attributeModifiers.containsKey(CustomAttributes.MORE_GEM_SLOT.get()))
            {
                maxSlotGem += StaticClass.GetValueFromAttributeModifierMap(itemStack, CustomAttributes.MORE_GEM_SLOT.get());
                more_gem_slot_loaded = true;
            }
        }


        if (maxSlotGem > gemCountFill)
        {
            CompoundTag compoundtag = new CompoundTag();
            compoundtag.putString("Name", tagItem);
            listtag.add(compoundtag);
            return true;
        }

        return false;
    }

    public static boolean AddGemTag( ItemStack itemStack,  StringTag tagItem)
    {
        return AddGemTag(itemStack, tagItem.toString().replace("\"", ""));
    }

    public static boolean RemoveGemTag( ItemStack itemStack, int index)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);

        if (!tag.contains(ModItems.GEM_TYPE) || listtag.size() > index)
        {
            return false;
        }

        listtag.remove(index);

        return true;
    }

    public static boolean RemoveGemTag( ItemStack itemStack,  Item gem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        String gemTag = GetGemTag(gem);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(value.equals(gemTag))
            {
                listtag.remove(i);
                result = true;
            }
        }

        return result;
    }

    public static boolean RemoveGemTag( ItemStack itemStack,  TagKey<Item> type)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(GemIsType(value, type))
            {
                listtag.remove(i);
                result = true;
            }
        }

        return result;
    }

    public static boolean ReplaceGemTag( ItemStack itemStack, int index,  Item newGem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);

        if (!tag.contains(ModItems.GEM_TYPE) || listtag.size() < index)
        {
            return false;
        }

        return ReplaceInListTag(listtag, index, StringTag.valueOf(GetGemTag(newGem)));
    }

    public static boolean ReplaceGemTag( ItemStack itemStack,  Item gem,  Item newGem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        String gemTag = GetGemTag(gem);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(value.equals(gemTag))
            {
                result = ReplaceInListTag(listtag, i, StringTag.valueOf(GetGemTag(newGem)));
            }
        }
        return result;
    }

    public static boolean ReplaceGemTag( ItemStack itemStack,  TagKey<Item> type,  Item newGem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(GemIsType(value, type))
            {
                result = ReplaceInListTag(listtag, i, StringTag.valueOf(GetGemTag(newGem)));
            }
        }

        return result;
    }

    public static boolean ReplaceGemTagByEmpty( ItemStack itemStack, int index)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);

        if (!tag.contains(ModItems.GEM_TYPE) || listtag.size() > index)
        {
            return false;
        }

        return ReplaceInListTag(listtag, index, DEFAULT_TAG_VALUE);
    }

    public static boolean ReplaceGemTagByEmpty( ItemStack itemStack,  Item gem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        String gemTag = GetGemTag(gem);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(value.equals(gemTag))
            {
                result = ReplaceInListTag(listtag, i, DEFAULT_TAG_VALUE);
            }
        }
        return result;
    }

    public static boolean ReplaceGemTagByEmpty( ItemStack itemStack,  TagKey<Item> type)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));
            if(GemIsType(value, type))
            {
                result = ReplaceInListTag(listtag, i, DEFAULT_TAG_VALUE);
            }
        }

        return result;
    }

    private static boolean ReplaceInListTag( ListTag listtag, int index,  Tag newTag)
    {
        boolean result = false;
        try
        {
            Field listOfTag = ListTag.class.getDeclaredField("list");
            listOfTag.setAccessible(true);

            List<Tag> listToEdit = (List<Tag>)listOfTag.get(listtag);
            CompoundTag tag = new CompoundTag();
            tag.put("Name", newTag);
            listToEdit.set(index, tag);

            result = true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }


    public static int CountGem( CompoundTag tag, boolean isEmpty)
    {
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return -1;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        int result = 0;
        for(int i = 0; i < listtag.size(); i++)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(isEmpty == value.equals(DEFAULT_TAG_VALUE.toString()))
            {
                result++;
            }
        }
        return result;
    }

    public static int CountGem( ItemStack itemStack, boolean isEmpty)
    {
        return CountGem(itemStack.getOrCreateTag(), isEmpty);
    }

    public static int CountGem( ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return -1;
        }

        return ((ListTag)tag.get(ModItems.GEM_TYPE)).size();
    }

    public static String GetGemTag(Item gem)
    {
        return StaticClass.GetKeyByValue(ModItems.GEMS_REFERENCES, gem);
    }

    @Nullable
    public static String[] GetGemTags( CompoundTag tag)
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
                result.add(StringValue(listtag.getCompound(i).get("Name")));
            }

            return result.toArray(new String[result.size() - 1]);
        }
        return null;
    }

    public static String[] GetGemTags( ItemStack itemStack)
    {
        return GetGemTags(itemStack.getOrCreateTag());
    }

    @Nullable
    public static Item GetGem( String tag)
    {
        return ModItems.GEMS_REFERENCES.get(tag);
    }

    @Nullable
    public static Item[] GetGems( String[] allGemsEquiped)
    {
        if(allGemsEquiped.length == 0)
        {
            return null;
        }

        List<Item> result = new ArrayList<>();
        for(int i = 0; i < allGemsEquiped.length; i++)
        {
            result.add(GetGem(allGemsEquiped[i]));
        }
        return result.toArray(new Item[result.size()-1]);
    }

    @Nullable
    public static Item[] GetGems( ItemStack itemStack)
    {
        return GetGems(GetGemTags(itemStack));
    }


    public static boolean GemEqualTag( Item gem, String tag)
    {
        Item tagGem = GetGem(tag);
        if(tagGem == null)
        {
            return false;
        }
        return tagGem.equals(gem);
    }

    public static boolean GemIsType(Item gem,  TagKey<Item> type)
    {
        if(gem == null)
        {
            return false;
        }

        for(TagKey<Item> gem_type : Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(gem).get()).tags().toList())
        {
            if(gem_type.equals(type))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean GemIsType( String tag, TagKey<Item> type)
    {
        return GemIsType(GetGem(tag), type);
    }

    public static boolean HaveGemOfType( String[] allGemsEquiped,  TagKey<Item> type)
    {
        if(allGemsEquiped.length == 0)
        {
            return false;
        }

        Item[] gemTags = GetGems(allGemsEquiped);
        for(int i = 0; i < gemTags.length; i++)
        {
            if(gemTags[i] != null && GemIsType(gemTags[i], type))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean HaveGemOfType( ItemStack itemStack,  TagKey<Item> type)
    {
        return HaveGemOfType(GetGemTags(itemStack), type);
    }

    public static boolean HaveGem( ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        return tag.contains(ModItems.GEM_TYPE);
    }

    public static float GetGemValue(String tag)
    {
        return GetGemValue(GetGem(tag));
    }

    public static float GetGemValue(Item item)
    {
        return ModItems.GEMS_VALUES.get(item);
    }

    public static Float[] GetGemValueOfType( String[] allGemsEquiped, TagKey<Item> type)
    {
        if(allGemsEquiped.length == 0)
        {
            return null;
        }

        List<Float> result = new ArrayList<>();
        for(int i = 0; i < allGemsEquiped.length; i++)
        {
            if(GemIsType(allGemsEquiped[i], type))
            {
                result.add(GetGemValue(allGemsEquiped[i]));
            }
        }
        return result.toArray(new Float[result.size()-1]);   }

    public static Float[] GetGemValueOfType( ItemStack itemStack, TagKey<Item> type)
    {
        return GetGemValueOfType(GetGemTags(itemStack), type);
    }


    public static float GetGemTotalValueOfType( String[] allGemsEquiped, TagKey<Item> type)
    {
        Float[] allGemsValue = GetGemValueOfType(allGemsEquiped, type);
        float result = 0;

        for(int i = 0; i < allGemsValue.length; i++)
        {
            result += allGemsValue[i];
        }
        return result;
    }

    public static float GetGemTotalValueOfType( ItemStack itemStack, TagKey<Item> type)
    {
        return GetGemTotalValueOfType(GetGemTags(itemStack), type);
    }


    public static StringTag CreateTagFromItem( Item item)
    {
        return StringTag.valueOf(item.getRegistryName().getPath());
    }

    public static final StringTag DEFAULT_TAG_VALUE = StringTag.valueOf("none");
    public static final String RPGNLOOT_MODIFIER = "rpgnloot_modifier";


    private static String StringValue( Tag tag)
    {
        return tag.toString().replace("\"", "");//.replace("'", "");
    }

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
        public static final TagKey<Item> GEM_TYPE_DAMAGE = tag("gems/gem_type_damage");
        public static final TagKey<Item> GEM_TYPE_SHIELD = tag("gems/gem_type_shield");
        public static final TagKey<Item> GEM_TYPE_MAX_LIFE = tag("gems/gem_type_max_life");
        public static final TagKey<Item> GEM_TYPE_CHANCE = tag("gems/gem_type_chance");
        public static final TagKey<Item> GEM_TYPE_CRITICAL_CHANCE = tag("gems/gem_type_critical_chance");
        public static final TagKey<Item> GEM_TYPE_CRITICAL_DAMAGE = tag("gems/gem_type_critical_damage");
        public static final TagKey<Item> GEM_TYPE_REPARATION = tag("gems/gem_type_reparation");
        public static final TagKey<Item> GEM_TYPE_SPEED = tag("gems/gem_type_speed");

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
