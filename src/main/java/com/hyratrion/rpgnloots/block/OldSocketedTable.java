package com.hyratrion.rpgnloots.block;

import com.hyratrion.rpgnloots.inventory.OldSocketingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class OldSocketedTable extends AnvilBlock
{
    private static final Component CONTAINER_TITLE = new TranslatableComponent("block.rpgloots.socketing_table");
    public OldSocketedTable(BlockBehaviour.Properties p_48777_) {
        super(p_48777_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public InteractionResult use(BlockState p_48804_, Level p_48805_, BlockPos p_48806_, Player p_48807_, InteractionHand p_48808_, BlockHitResult p_48809_) {
        if (p_48805_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            p_48807_.openMenu(p_48804_.getMenuProvider(p_48805_, p_48806_));
            p_48807_.awardStat(Stats.INTERACT_WITH_ANVIL);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    public MenuProvider getMenuProvider(BlockState p_48821_, Level p_48822_, BlockPos p_48823_) {
        return new SimpleMenuProvider((p_48785_, p_48786_, p_48787_) -> {
            return new OldSocketingTableMenu(p_48785_, p_48786_, ContainerLevelAccess.create(p_48822_, p_48823_));
        }, CONTAINER_TITLE);
    }
}
