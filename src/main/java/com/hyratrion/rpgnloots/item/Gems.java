package com.hyratrion.rpgnloots.item;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import com.hyratrion.rpgnloots.util.ModTags;
import com.hyratrion.rpgnloots.util.StaticClass;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Gems
{
    public static List<Item> GEM_LVL_0 = new ArrayList<>();
    public static List<Item> GEM_LVL_1 = new ArrayList<>();
    public static List<Item> GEM_LVL_2 = new ArrayList<>();
    public static List<Item> GEM_LVL_3 = new ArrayList<>();
    public static List<Item> GEM_LVL_4 = new ArrayList<>();
    public static List<Item> GEM_LVL_5 = new ArrayList<>();

    public static void registerArrayGems()
    {
        GEM_LVL_0.add(ModItems.GEM_CHANCE_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_CRITICAL_CHANCE_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_CRITICAL_DAMAGE_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_DAMAGE_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_MAX_LIFE_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_REPARATION_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_SHIELD_LVL_0.get());
        GEM_LVL_0.add(ModItems.GEM_SPEED_LVL_0.get());

        GEM_LVL_1.add(ModItems.GEM_CHANCE_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_CRITICAL_CHANCE_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_CRITICAL_DAMAGE_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_DAMAGE_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_MAX_LIFE_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_REPARATION_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_SHIELD_LVL_1.get());
        GEM_LVL_1.add(ModItems.GEM_SPEED_LVL_1.get());

        GEM_LVL_2.add(ModItems.GEM_CHANCE_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_CRITICAL_CHANCE_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_CRITICAL_DAMAGE_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_DAMAGE_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_MAX_LIFE_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_REPARATION_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_SHIELD_LVL_2.get());
        GEM_LVL_2.add(ModItems.GEM_SPEED_LVL_2.get());

        GEM_LVL_3.add(ModItems.GEM_CHANCE_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_CRITICAL_CHANCE_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_CRITICAL_DAMAGE_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_DAMAGE_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_MAX_LIFE_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_REPARATION_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_SHIELD_LVL_3.get());
        GEM_LVL_3.add(ModItems.GEM_SPEED_LVL_3.get());

        GEM_LVL_4.add(ModItems.GEM_CHANCE_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_CRITICAL_CHANCE_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_CRITICAL_DAMAGE_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_DAMAGE_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_MAX_LIFE_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_REPARATION_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_SHIELD_LVL_4.get());
        GEM_LVL_4.add(ModItems.GEM_SPEED_LVL_4.get());

        GEM_LVL_5.add(ModItems.GEM_CHANCE_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_CRITICAL_CHANCE_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_CRITICAL_DAMAGE_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_DAMAGE_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_MAX_LIFE_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_REPARATION_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_SHIELD_LVL_5.get());
        GEM_LVL_5.add(ModItems.GEM_SPEED_LVL_5.get());

    }

    //add gem slot

    public static boolean AddGemSlot(ItemStack itemStack,  String tagItem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            tag.put(ModItems.GEM_TYPE, new ListTag());
        }

        ListTag listtag = (ListTag) tag.get(ModItems.GEM_TYPE);

        int gemCountFill = CountGem(itemStack, false);
        int maxSlotGem = 0;

        EquipmentSlot[] equipmentSlots = StaticClass.GetEquipmentSlotOf(itemStack);
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

    public static boolean AddGemSlot(ItemStack itemStack,  StringTag tagItem)
    {
        return AddGemSlot(itemStack, StringValue(tagItem));
    }

    //remove gem slot

    public static boolean RemoveGemSlot(ItemStack itemStack, int index)
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

    public static boolean RemoveAllGemSlot(ItemStack itemStack,  Item gem)
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

    public static boolean RemoveAllGemSlot(ItemStack itemStack,  TagKey<Item> type)
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

    //replace gem slot

    public static boolean ReplaceGemSlot(ItemStack itemStack, int index,  Item newGem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);

        if (!tag.contains(ModItems.GEM_TYPE) || listtag.size() < index)
        {
            return false;
        }

        return ReplaceInListTag(listtag, index, StringTag.valueOf(GetGemTag(newGem)));
    }

    public static boolean ReplaceFirstEmptyGemSlot(ItemStack itemStack,  Item newGem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE) || CountGem(itemStack, true) == 0)
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        int index = -1;
        for(int i = 0; i < listtag.size(); i++)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if(value.equals(StringValue(ModTags.DEFAULT_TAG_VALUE)))
            {
                index = i;
                break;
            }
        }

        return index > -1 && ReplaceInListTag(listtag, index, StringTag.valueOf(GetGemTag(newGem)));
    }

    public static boolean ReplaceAllGemSlot(ItemStack itemStack,  Item gem,  Item newGem)
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

    public static boolean ReplaceAllGemSlot(ItemStack itemStack,  TagKey<Item> type,  Item newGem)
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

    //replace gem slot by empty

    public static boolean ReplaceGemSlotByEmpty(ItemStack itemStack, int index)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);

        if (!tag.contains(ModItems.GEM_TYPE) || listtag.size() > index)
        {
            return false;
        }

        return ReplaceInListTag(listtag, index, ModTags.DEFAULT_TAG_VALUE);
    }

    public static boolean ReplaceAllGemSlotByEmpty(ItemStack itemStack,  Item gem)
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
                result = ReplaceInListTag(listtag, i, ModTags.DEFAULT_TAG_VALUE);
            }
        }
        return result;
    }

    public static boolean ReplaceAllGemSlotByEmpty(ItemStack itemStack,  TagKey<Item> type)
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
                result = ReplaceInListTag(listtag, i, ModTags.DEFAULT_TAG_VALUE);
            }
        }

        return result;
    }

    public static boolean ReplaceAllGemSlotByEmpty(ItemStack itemStack)
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
            //String value = StringValue(listtag.getCompound(i).get("Name"));
            result = ReplaceInListTag(listtag, i, ModTags.DEFAULT_TAG_VALUE);
        }

        return result;
    }

    //method for method
    private static Field listTagField = null;

    private static boolean ReplaceInListTag(ListTag listtag, int index, Tag newTag)
    {
        boolean result = false;
        try
        {
            if(listTagField == null)
            {
                listTagField = GetListTagField("list");
                listTagField.setAccessible(true);
            }

            List<Tag> listToEdit = (List<Tag>)listTagField.get(listtag);
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


    private static Field GetListTagField(String name)
    {
        try
        {
            Field[] privateFields = ListTag.class.getDeclaredFields();
            for(Field field : privateFields)
            {
                //System.out.println("GetPrivateFieldByType fields => " + field.getName());

                if(field.getName().equals(name) || field.getGenericType().toString().equals("java.util.List<net.minecraft.nbt.Tag>"))
                {
                    return field;
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    //count gem

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

            if(isEmpty == value.equals(StringValue(ModTags.DEFAULT_TAG_VALUE)))
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

    //gem tag

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

    //get gem

    public static Item GetGem( String tag)
    {
        return ModItems.GEMS_REFERENCES.get(tag);
    }

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

    public static Item[] GetGems( ItemStack itemStack)
    {
        return GetGems(GetGemTags(itemStack));
    }

    //compare gem to tag

    public static boolean GemEqualTag( Item gem, String tag)
    {
        Item tagGem = GetGem(tag);
        if(tagGem == null)
        {
            return false;
        }
        return tagGem.equals(gem);
    }

    //gem type

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

    public static boolean GemIsType(Item gem,  TagKey<Item>[] types)
    {
        if(gem == null || types == null || types.length == 0)
        {
            return false;
        }

        for(TagKey<Item> gem_type : Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(gem).get()).tags().toList())
        {
            for (TagKey<Item> type : types)
            {
                if(gem_type.equals(type))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean GemIsType(Item gem,  List<TagKey<Item>> types)
    {
        if(gem == null || types == null || types.size() == 0)
        {
            return false;
        }
        return GemIsType(gem, types.toArray(new TagKey[types.size()]));
    }

    public static boolean GemIsType( String tag, TagKey<Item> type)
    {
        return GemIsType(GetGem(tag), type);
    }

    //has get

    public static boolean HasGemOfType( String[] allGemsEquiped,  TagKey<Item> type)
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

    public static boolean HasGemOfType(ItemStack itemStack,  TagKey<Item> type)
    {
        return HasGemOfType(GetGemTags(itemStack), type);
    }

    public static boolean HasGemSlot(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        return tag.contains(ModItems.GEM_TYPE);
    }

    //gem value

    public static float GetGemValue(String tag)
    {
        return GetGemValue(GetGem(tag));
    }

    public static float GetGemValue(Item item)
    {
        return GemDictionary.AllValues.getValue(item);
    }

    public static float GetGemValue(TagKey<Item> type, int level)
    {
        return GemDictionary.AllValues.getValue(type, level);
    }

    public static float GetGemValue(Item item, int level)
    {
        return GetGemValue(GetGemType(item), level);
    }

    public static boolean isGem(Item gem) {
        return ModItems.GEMS_REFERENCES.containsValue(gem);
    }


    //get gem type

    public static Item GetGemByType(TagKey<Item> type, List<Item> gems)
    {
        if(type == null || gems == null || gems.size() == 0)
        {
            return null;
        }

        for(Item gem : gems)
        {
            if(GemIsType(gem, type))
            {
                return gem;
            }
        }

        return null;
    }

    public static TagKey<Item> GetGemType(Item gem)
    {
        if(gem == null)
        {
            return null;
        }

        for(TagKey<Item> gem_type : Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(gem).get()).tags().toList())
        {
            for (TagKey<Item> type : ModTags.Items.GEM_ALL)
            {
                if(gem_type.equals(type))
                {
                    return type;
                }
            }
        }

        return null;
    }


    public static Float[] GetGemValueOfType( String[] allGemsEquiped, TagKey<Item> type, boolean gemLevelIncrease)
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
                float value;
                if(gemLevelIncrease)
                {
                    int level = Gems.GetGemLevel(GetGem(allGemsEquiped[i]));
                    value = GetGemValue(type, level + 1);
                }
                else
                {
                    value = GetGemValue(allGemsEquiped[i]);
                }
                result.add(value);
            }
        }
        return result.toArray(new Float[result.size()-1]);   }

    //get gem value of type

    public static Float[] GetGemValueOfType(ItemStack itemStack, TagKey<Item> type, boolean gemLevelIncrease)
    {
        return GetGemValueOfType(GetGemTags(itemStack), type, gemLevelIncrease);
    }


    public static float GetGemTotalValueOfType(String[] allGemsEquiped, TagKey<Item> type, boolean gemLevelIncrease)
    {
        Float[] allGemsValue = GetGemValueOfType(allGemsEquiped, type, gemLevelIncrease);
        float result = 0;

        for(int i = 0; i < allGemsValue.length; i++)
        {
            result += allGemsValue[i];
        }
        return result;
    }

    public static float GetGemTotalValueOfType(ItemStack itemStack, TagKey<Item> type, boolean gemLevelIncrease)
    {
        return GetGemTotalValueOfType(GetGemTags(itemStack), type, gemLevelIncrease);
    }

    //get tier

    public static int GetTier(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModTags.RPGNLOOT_MODIFIER))
        {
            return -1;
        }
        return Integer.parseInt(StringValue(tag.get(ModTags.RPGNLOOT_MODIFIER)));
    }

    //get gem level

    public static int GetGemLevel(Item gem)
    {
        if(GEM_LVL_1.contains(gem))
        {
            return 1;
        }
        else if(GEM_LVL_2.contains(gem))
        {
            return 2;
        }
        else if(GEM_LVL_3.contains(gem))
        {
            return 3;
        }
        else if(GEM_LVL_4.contains(gem))
        {
            return 4;
        }
        else if(GEM_LVL_5.contains(gem))
        {
            return 5;
        }
        else
        {
            return -1;
        }
    }

    private static String StringValue(Tag tag)
    {
        return tag.toString().replace("\"", "");//.replace("'", "");
    }


}
