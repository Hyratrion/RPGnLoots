package com.hyratrion.rpgnloots.block;

import com.hyratrion.rpgnloots.RPGNLOOT;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RPGNLOOT.MOD_ID);

    public static final RegistryObject<Block> SOCKETING_TABLE = BLOCKS.register("socketing_table", SocketingTableBlock::new);


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
