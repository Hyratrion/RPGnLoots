package com.hyratrion.rpgnloots.util;

import com.hyratrion.rpgnloots.RPGLOOT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
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


    public static class Items {
        public static final TagKey<Item> test = forgeTag("gems/gems_lvl_4");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RPGLOOT.MOD_ID, name));
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
