package com.hyratrion.rpgnloots.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab RPGNLOOTS_TAB = new CreativeModeTab("rpgnlootstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GEM_CHANCE_LVL_4.get());
        }
    };
}
