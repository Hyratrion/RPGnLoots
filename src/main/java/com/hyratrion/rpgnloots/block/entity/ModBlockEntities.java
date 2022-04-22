package com.hyratrion.rpgnloots.block.entity;

import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, RPGNLOOT.MOD_ID);

    public static final RegistryObject<BlockEntityType<SocketingTableBlockEntity>> SOCKETING_TABLE =
            BLOCK_ENTITIES.register("socketing_table", () ->
                    BlockEntityType.Builder.of(SocketingTableBlockEntity::new,
                            ModBlocks.SOCKETING_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
