package com.hyratrion.rpgnloots.util;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public final class StaticClass
{
    @Nullable
    public static AttributeModifier GetAttributeModifierFromMultimap(@Nonnull Multimap<Attribute, AttributeModifier> attributeModifiers,@Nonnull Attribute attribute)
    {
        AttributeModifier result = null;
        try
        {
            result = attributeModifiers.get(attribute).stream().findFirst().get();
        }
        catch (Exception ingored) { }

        return result;
    }

    public static float GetValueFromAttributeModifierMap(@Nonnull Multimap<Attribute, AttributeModifier> attributeModifiers,@Nonnull Attribute attribute)
    {
        return (float)GetAttributeModifierFromMultimap(attributeModifiers, attribute).getAmount();
    }

    public static float GetValueFromAttributeModifierMap(@Nonnull ItemStack itemStack,@Nonnull Attribute attribute)
    {
        return GetValueFromAttributeModifierMap(itemStack.getAttributeModifiers(ModTags.GetEquipmentSlotOf(itemStack)[0]), attribute);
    }




    public static <T, E> T GetKeyByValue(Map<T, E> map, E value)
    {
        for (Map.Entry<T, E> entry : map.entrySet())
        {
            if (entry.getValue().equals(value))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public static <T, E> T GetKeyByIndex(Map<T, E> map, int index)
    {
        int cpt = 0;
        for (Map.Entry<T, E> entry : map.entrySet())
        {
            if (cpt == index)
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public static boolean ArrayContainsValue(Object[] arr, Object value)
    {
        for(Object obj : arr)
        {
            if(obj.equals(value))
            {
                return true;
            }
        }
        return false;
    }

}
