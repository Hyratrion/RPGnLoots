package com.hyratrion.rpgnloots.block;

import com.hyratrion.rpgnloots.screen.SocketingTableMenu;
import com.hyratrion.rpgnloots.util.ModStats;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SocketingTableBlock extends FallingBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final Component CONTAINER_TITLE = new TranslatableComponent("block.rpgnloots.socketing_table");

    public SocketingTableBlock() {
        //HEAVY_METAL, MaterialColor.METAL
        super(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1f, 10f).sound(SoundType.ANVIL));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(0, 0, 2, 7, 1, 8),
            Block.box(1, 10, 3, 15, 11, 13),
            Block.box(1, 12, 3, 15, 13, 13),
            Block.box(0, 11, 2, 16, 12, 14),
            Block.box(7, 13, 10, 8, 14, 11),
            Block.box(11, 13, 9, 12, 14, 10),
            Block.box(11, 13, 6, 12, 14, 7),
            Block.box(12, 13, 8, 13, 14, 9),
            Block.box(12, 13, 7, 13, 14, 8),
            Block.box(3, 13, 7, 4, 14, 8),
            Block.box(3, 13, 8, 4, 14, 9),
            Block.box(8, 13, 10, 9, 14, 11),
            Block.box(8, 13, 5, 9, 14, 6),
            Block.box(7, 13, 5, 8, 14, 6),
            Block.box(2, 13, 4, 3, 14, 5),
            Block.box(13, 13, 4, 14, 14, 5),
            Block.box(13, 13, 11, 14, 14, 12),
            Block.box(2, 13, 11, 3, 14, 12),
            Block.box(4, 13, 9, 5, 14, 10),
            Block.box(4, 13, 6, 5, 14, 7),
            Block.box(0, 0, 8, 7, 1, 14),
            Block.box(1, 1, 8, 6, 2, 13),
            Block.box(2, 2, 8, 5, 10, 12),
            Block.box(11, 2, 8, 14, 10, 12),
            Block.box(10, 1, 8, 15, 2, 13),
            Block.box(9, 0, 8, 16, 1, 14),
            Block.box(11, 2, 4, 14, 10, 8),
            Block.box(10, 1, 3, 15, 2, 8),
            Block.box(9, 0, 2, 16, 1, 8),
            Block.box(2, 2, 4, 5, 10, 8),
            Block.box(1, 1, 3, 6, 2, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(8, 0, 0, 14, 1, 7),
            Block.box(3, 10, 1, 13, 11, 15),
            Block.box(3, 12, 1, 13, 13, 15),
            Block.box(2, 11, 0, 14, 12, 16),
            Block.box(5, 13, 7, 6, 14, 8),
            Block.box(6, 13, 11, 7, 14, 12),
            Block.box(9, 13, 11, 10, 14, 12),
            Block.box(7, 13, 12, 8, 14, 13),
            Block.box(8, 13, 12, 9, 14, 13),
            Block.box(8, 13, 3, 9, 14, 4),
            Block.box(7, 13, 3, 8, 14, 4),
            Block.box(5, 13, 8, 6, 14, 9),
            Block.box(10, 13, 8, 11, 14, 9),
            Block.box(10, 13, 7, 11, 14, 8),
            Block.box(11, 13, 2, 12, 14, 3),
            Block.box(11, 13, 13, 12, 14, 14),
            Block.box(4, 13, 13, 5, 14, 14),
            Block.box(4, 13, 2, 5, 14, 3),
            Block.box(6, 13, 4, 7, 14, 5),
            Block.box(9, 13, 4, 10, 14, 5),
            Block.box(2, 0, 0, 8, 1, 7),
            Block.box(3, 1, 1, 8, 2, 6),
            Block.box(4, 2, 2, 8, 10, 5),
            Block.box(4, 2, 11, 8, 10, 14),
            Block.box(3, 1, 10, 8, 2, 15),
            Block.box(2, 0, 9, 8, 1, 16),
            Block.box(8, 2, 11, 12, 10, 14),
            Block.box(8, 1, 10, 13, 2, 15),
            Block.box(8, 0, 9, 14, 1, 16),
            Block.box(8, 2, 2, 12, 10, 5),
            Block.box(8, 1, 1, 13, 2, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(0, 0, 2, 7, 1, 8),
            Block.box(1, 10, 3, 15, 11, 13),
            Block.box(1, 12, 3, 15, 13, 13),
            Block.box(0, 11, 2, 16, 12, 14),
            Block.box(7, 13, 10, 8, 14, 11),
            Block.box(11, 13, 9, 12, 14, 10),
            Block.box(11, 13, 6, 12, 14, 7),
            Block.box(12, 13, 8, 13, 14, 9),
            Block.box(12, 13, 7, 13, 14, 8),
            Block.box(3, 13, 7, 4, 14, 8),
            Block.box(3, 13, 8, 4, 14, 9),
            Block.box(8, 13, 10, 9, 14, 11),
            Block.box(8, 13, 5, 9, 14, 6),
            Block.box(7, 13, 5, 8, 14, 6),
            Block.box(2, 13, 4, 3, 14, 5),
            Block.box(13, 13, 4, 14, 14, 5),
            Block.box(13, 13, 11, 14, 14, 12),
            Block.box(2, 13, 11, 3, 14, 12),
            Block.box(4, 13, 9, 5, 14, 10),
            Block.box(4, 13, 6, 5, 14, 7),
            Block.box(0, 0, 8, 7, 1, 14),
            Block.box(1, 1, 8, 6, 2, 13),
            Block.box(2, 2, 8, 5, 10, 12),
            Block.box(11, 2, 8, 14, 10, 12),
            Block.box(10, 1, 8, 15, 2, 13),
            Block.box(9, 0, 8, 16, 1, 14),
            Block.box(11, 2, 4, 14, 10, 8),
            Block.box(10, 1, 3, 15, 2, 8),
            Block.box(9, 0, 2, 16, 1, 8),
            Block.box(2, 2, 4, 5, 10, 8),
            Block.box(1, 1, 3, 6, 2, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(2, 0, 9, 8, 1, 16),
            Block.box(3, 10, 1, 13, 11, 15),
            Block.box(3, 12, 1, 13, 13, 15),
            Block.box(2, 11, 0, 14, 12, 16),
            Block.box(10, 13, 8, 11, 14, 9),
            Block.box(9, 13, 4, 10, 14, 5),
            Block.box(6, 13, 4, 7, 14, 5),
            Block.box(8, 13, 3, 9, 14, 4),
            Block.box(7, 13, 3, 8, 14, 4),
            Block.box(7, 13, 12, 8, 14, 13),
            Block.box(8, 13, 12, 9, 14, 13),
            Block.box(10, 13, 7, 11, 14, 8),
            Block.box(5, 13, 7, 6, 14, 8),
            Block.box(5, 13, 8, 6, 14, 9),
            Block.box(4, 13, 13, 5, 14, 14),
            Block.box(4, 13, 2, 5, 14, 3),
            Block.box(11, 13, 2, 12, 14, 3),
            Block.box(11, 13, 13, 12, 14, 14),
            Block.box(9, 13, 11, 10, 14, 12),
            Block.box(6, 13, 11, 7, 14, 12),
            Block.box(8, 0, 9, 14, 1, 16),
            Block.box(8, 1, 10, 13, 2, 15),
            Block.box(8, 2, 11, 12, 10, 14),
            Block.box(8, 2, 2, 12, 10, 5),
            Block.box(8, 1, 1, 13, 2, 6),
            Block.box(8, 0, 0, 14, 1, 7),
            Block.box(4, 2, 2, 8, 10, 5),
            Block.box(3, 1, 1, 8, 2, 6),
            Block.box(2, 0, 0, 8, 1, 7),
            Block.box(4, 2, 11, 8, 10, 14),
            Block.box(3, 1, 10, 8, 2, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
            return tieredItem.getTier().getLevel() >= 1;
        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_E;
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit)
    {
        super.use(state, level, pos, player, hand, hit);
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openGui(serverPlayer, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return CONTAINER_TITLE;
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new SocketingTableMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            }, pos);
        }
        System.out.println("Test ouverture gui SocketingTableMenu --> 3");
        return InteractionResult.SUCCESS;
    }

}

