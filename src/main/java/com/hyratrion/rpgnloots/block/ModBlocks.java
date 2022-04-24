package com.hyratrion.rpgnloots.block;

import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.block.custom.SocketingTableBlock;
import com.hyratrion.rpgnloots.item.ModCreativeModeTab;
import com.hyratrion.rpgnloots.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RPGNLOOT.MOD_ID);

    public static final RegistryObject<Block> SOCKETING_TABLE = registerBlock("socketing_table",
            () -> new SocketingTableBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).strength(5.0f).requiresCorrectToolForDrops().sound(SoundType.ANVIL).noOcclusion()),
            ModCreativeModeTab.RPGNLOOTS_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock (String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return  toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
