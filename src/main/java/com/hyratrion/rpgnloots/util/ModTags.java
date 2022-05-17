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
    public static EquipmentSlot[] GetEquipmentSlotOf(Item item)
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

    public static EquipmentSlot[] GetEquipmentSlotOf(ItemStack itemStack)
    {
        return GetEquipmentSlotOf(itemStack.getItem());
    }

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

    public static boolean AddGemSlot(ItemStack itemStack,  StringTag tagItem)
    {
        return AddGemSlot(itemStack, StringValue(tagItem));
    }

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

            if(value.equals(StringValue(DEFAULT_TAG_VALUE)))
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

    public static boolean ReplaceGemSlotByEmpty(ItemStack itemStack, int index)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);

        if (!tag.contains(ModItems.GEM_TYPE) || listtag.size() > index)
        {
            return false;
        }

        return ReplaceInListTag(listtag, index, DEFAULT_TAG_VALUE);
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
                result = ReplaceInListTag(listtag, i, DEFAULT_TAG_VALUE);
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
                result = ReplaceInListTag(listtag, i, DEFAULT_TAG_VALUE);
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
            result = ReplaceInListTag(listtag, i, DEFAULT_TAG_VALUE);
        }

        return result;
    }


    private static boolean ReplaceInListTag(ListTag listtag, int index, Tag newTag)
    {
        boolean result = false;
        try
        {
            Field listOfTag = GetListTagField("list");
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

            if(isEmpty == value.equals(StringValue(DEFAULT_TAG_VALUE)))
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

    public static float GetGemValue(String tag)
    {
        return GetGemValue(GetGem(tag));
    }

    public static float GetGemValue(Item item)
    {
        return ModItems.GEMS_VALUES.get(item);
    }

    public static float GetGemValue(TagKey<Item> type, int level)
    {
        Item[] arr;

        switch (level)
        {
            case 0 :
                arr = ModItems.GEM_LVL_0;
                break;

            case 1 :
                arr = ModItems.GEM_LVL_1;
                break;

            case 2 :
                arr = ModItems.GEM_LVL_2;
                break;

            case 3 :
                arr = ModItems.GEM_LVL_3;
                break;

            case 4 :
                arr = ModItems.GEM_LVL_4;
                break;

            case 5 :
                arr = ModItems.GEM_LVL_5;
                break;

            default:
                return 0;
        }

        Item gem = GetGemByType(type, arr);

        return ModItems.GEMS_VALUES.get(gem);
    }

    public static Item GetGemByType(TagKey<Item> type, Item[] gems)
    {
        if(type == null || gems == null || gems.length == 0)
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
            for (TagKey<Item> type : Items.GEM_ALL)
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
                float value = 0;
                if(gemLevelIncrease)
                {
                    value += GetGemValue(type, 1);
                }
                value += GetGemValue(allGemsEquiped[i]);
                result.add(value);
            }
        }
        return result.toArray(new Float[result.size()-1]);   }

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


    public static int GetTier(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(RPGNLOOT_MODIFIER))
        {
            return -1;
        }
        return Integer.parseInt(StringValue(tag.get(RPGNLOOT_MODIFIER)));
    }


    public static StringTag CreateTagFromItem( Item item)
    {
        return StringTag.valueOf(item.getRegistryName().getPath());
    }

    public static final StringTag DEFAULT_TAG_VALUE = StringTag.valueOf("none");
    public static final String RPGNLOOT_MODIFIER = "rpgnloot_modifier";


    private static String StringValue(Tag tag)
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
