package com.hyratrion.rpgnloots.item;

import com.hyratrion.rpgnloots.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GemDictionary
{
    //static
    public static GemDictionary AllValues;

    public static void registerGem()
    {
        AllValues = new GemDictionary();
    }

    private Map<TagKey<Item>, Map<Integer, Float>> dict_type_level = new HashMap<>();
    //private Map<Integer, Float> dict_level_value = new HashMap<>();

    //class
    private GemDictionary()
    {
        //chance
        add(ModTags.Items.GEM_TYPE_CHANCE, 1, 1);
        add(ModTags.Items.GEM_TYPE_CHANCE, 2, 2);
        add(ModTags.Items.GEM_TYPE_CHANCE, 3, 3);
        add(ModTags.Items.GEM_TYPE_CHANCE, 4, 4);
        add(ModTags.Items.GEM_TYPE_CHANCE, 5, 5);
        add(ModTags.Items.GEM_TYPE_CHANCE, 6, 6);

        //chance de critique
        add(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, 1, 15);
        add(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, 2, 30);
        add(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, 3, 45);
        add(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, 4, 70);
        add(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, 5, 100);
        add(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, 6, 150);

        //degats critique
        add(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, 1, 15);
        add(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, 2, 30);
        add(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, 3, 45);
        add(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, 4, 70);
        add(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, 5, 100);
        add(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, 6, 150);

        //damage
        add(ModTags.Items.GEM_TYPE_DAMAGE, 1, 15);
        add(ModTags.Items.GEM_TYPE_DAMAGE, 2, 30);
        add(ModTags.Items.GEM_TYPE_DAMAGE, 3, 45);
        add(ModTags.Items.GEM_TYPE_DAMAGE, 4, 70);
        add(ModTags.Items.GEM_TYPE_DAMAGE, 5, 100);
        add(ModTags.Items.GEM_TYPE_DAMAGE, 6, 150);

        //max life
        add(ModTags.Items.GEM_TYPE_MAX_LIFE, 1, 1);
        add(ModTags.Items.GEM_TYPE_MAX_LIFE, 2, 2);
        add(ModTags.Items.GEM_TYPE_MAX_LIFE, 3, 3);
        add(ModTags.Items.GEM_TYPE_MAX_LIFE, 4, 4);
        add(ModTags.Items.GEM_TYPE_MAX_LIFE, 5, 5);
        add(ModTags.Items.GEM_TYPE_MAX_LIFE, 6, 6);

        //reparation
        add(ModTags.Items.GEM_TYPE_REPARATION, 1, 1);
        add(ModTags.Items.GEM_TYPE_REPARATION, 2, 2);
        add(ModTags.Items.GEM_TYPE_REPARATION, 3, 3);
        add(ModTags.Items.GEM_TYPE_REPARATION, 4, 4);
        add(ModTags.Items.GEM_TYPE_REPARATION, 5, 5);
        add(ModTags.Items.GEM_TYPE_REPARATION, 6, 6);

        //shield
        add(ModTags.Items.GEM_TYPE_SHIELD, 1, 1);
        add(ModTags.Items.GEM_TYPE_SHIELD, 2, 2);
        add(ModTags.Items.GEM_TYPE_SHIELD, 3, 3);
        add(ModTags.Items.GEM_TYPE_SHIELD, 4, 4);
        add(ModTags.Items.GEM_TYPE_SHIELD, 5, 5);
        add(ModTags.Items.GEM_TYPE_SHIELD, 6, 6);

        //speed
        add(ModTags.Items.GEM_TYPE_SPEED, 1, 15);
        add(ModTags.Items.GEM_TYPE_SPEED, 2, 30);
        add(ModTags.Items.GEM_TYPE_SPEED, 3, 45);
        add(ModTags.Items.GEM_TYPE_SPEED, 4, 70);
        add(ModTags.Items.GEM_TYPE_SPEED, 5, 100);
        add(ModTags.Items.GEM_TYPE_SPEED, 6, 150);
    }

    private boolean add(TagKey<Item> type, int level, float value)
    {
        Map<Integer, Float> dict_level_value;

        if(dict_type_level.containsKey(type))
        {
            dict_level_value = dict_type_level.get(type);
            if(dict_level_value.containsKey(level))
            {
                return false;
            }
        }
        else
        {
            dict_level_value = new HashMap<>();
        }

        dict_level_value.put(level, value);
        dict_type_level.put(type, dict_level_value);

        return true;
    }

    public float getValue(TagKey<Item> type, int level)
    {
        float result = 0;
        Map<Integer, Float> dict_level_value;

        if(dict_type_level.containsKey(type))
        {
            dict_level_value = dict_type_level.get(type);
            if(dict_level_value.containsKey(level))
            {
                return dict_level_value.get(level);
            }
        }
        return result;
    }

    public float getValue(Item gem)
    {
        TagKey<Item> type = Gems.GetGemType(gem);
        int level = Gems.GetGemLevel(gem);

        float result = 0;
        Map<Integer, Float> dict_level_value;

        if(dict_type_level.containsKey(type))
        {
            dict_level_value = dict_type_level.get(type);
            if(dict_level_value.containsKey(level))
            {
                return dict_level_value.get(level);
            }
        }
        return result;
    }
}
