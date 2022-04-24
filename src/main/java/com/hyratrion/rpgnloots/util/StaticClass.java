package com.hyratrion.rpgnloots.util;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public final class StaticClass
{
    public static float GetValueFromAttributeModifier(Multimap<Attribute, AttributeModifier> attributeModifiers, Attribute attribute)
    {
        return (float)attributeModifiers.get(attribute).stream().findFirst().get().getAmount();
    }

    public static float GetValueFromAttributeModifier(ItemStack itemStack, Attribute attribute)
    {
        return GetValueFromAttributeModifier(itemStack.getAttributeModifiers(ModTags.GetEquipmentSlotOf(itemStack)[0]), attribute);
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
