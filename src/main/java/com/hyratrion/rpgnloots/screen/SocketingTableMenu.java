package com.hyratrion.rpgnloots.screen;

import com.hyratrion.rpgnloots.block.ModBlocks;
import com.hyratrion.rpgnloots.block.entity.SocketingTableBlockEntity;
import com.hyratrion.rpgnloots.screen.slot.ModResultSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SocketingTableMenu extends AbstractContainerMenu implements Supplier<Map<Integer,Slot>> {
//    private final SocketingTableBlockEntity blockEntity;
    private final Level level;
    public final static HashMap<String, Object> guistate = new HashMap<>();
//    public final Level world;
    public final Player player;
    public int x, y, z;
    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;

    private final Container inputContainer = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            SocketingTableMenu.this.slotsChanged(this);
            super.setChanged();
        }
    };
    private final ResultContainer outputContainer = new ResultContainer() {
        @Override
        public void setChanged() {
            SocketingTableMenu.this.slotsChanged(this);
            super.setChanged();
        }
    };

    public SocketingTableMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(ModMenuTypes.SOCKETING_TABLE_MENU_TYPE, id);

        System.out.println("Test ouverture gui SocketingTableMenu --> 1");

        this.player = inv.player;
        this.level = inv.player.level;
        this.internal = new ItemStackHandler(3);
        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }
        if (pos != null) {
            if (extraData.readableBytes() == 1) { // bound to item
                byte hand = extraData.readByte();
                ItemStack itemstack;
                if (hand == 0)
                    itemstack = this.player.getMainHandItem();
                else
                    itemstack = this.player.getOffhandItem();
                itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                    this.internal = capability;
                    this.bound = true;
                });
            } else if (extraData.readableBytes() > 1) {
                extraData.readByte(); // drop padding
                Entity entity = level.getEntity(extraData.readVarInt());
                if (entity != null)
                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                        this.internal = capability;
                        this.bound = true;
                    });
            } else { // might be bound to block
                BlockEntity ent = inv.player != null ? inv.player.level.getBlockEntity(pos) : null;
                if (ent != null) {
                    ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                        this.internal = capability;
                        this.bound = true;
                    });
                }
            }
        }
        this.addSlot(new Slot(this.inputContainer, 0, 19, 16));
        this.addSlot(new Slot(this.inputContainer, 1, 19, 50));
        this.addSlot(new Slot(this.outputContainer, 2, 76, 33));
        this.addSlot(new Slot(this.outputContainer, 3, 135, 9));
        this.addSlot(new Slot(this.outputContainer, 4, 141, 33));
        this.addSlot(new Slot(this.outputContainer, 5, 135, 57));
 /*       this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 19, 16) {
        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 19, 50) {
        }));
        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 76, 33) {
        }));
        this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 135, 9) {
        }));
        this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 141, 33) {
        }));
        this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 135, 57) {
        }));
        for (int si = 0; si < 6; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));*/
        System.out.println("Test ouverture gui SocketingTableMenu --> 2");
    }

 /*   public SocketingTableMenu(int p_39008_, Inventory inv, ContainerLevelAccess p_39010_) {
        super(ModMenuTypes.SOCKETING_TABLE_MENU_TYPE, p_39008_, inv, p_39010_);
        checkContainerSize(inv, 4);
        this.level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

     //   this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new Slot(this.inputContainer, 0, 19, 16));
            this.addSlot(new Slot(this.inputContainer, 1, 19, 50));
            this.addSlot(new Slot(this.outputContainer, 2, 76, 33));
            this.addSlot(new Slot(this.outputContainer, 3, 135, 9));
            this.addSlot(new Slot(this.outputContainer, 4, 141, 33));
            this.addSlot(new Slot(this.outputContainer, 5, 135, 57));
    //    });
    }*/
  /*  public SocketingTableMenu(int windowId, Inventory inv, FriendlyByteBuf extraData) {
        this(windowId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public SocketingTableMenu(int windowId, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.SOCKETING_TABLE_MENU.get(), windowId);
        checkContainerSize(inv, 4);
        blockEntity = ((SocketingTableBlockEntity) entity);
        this.level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 19, 16));
            this.addSlot(new SlotItemHandler(handler, 1, 19, 50));
            this.addSlot(new ModResultSlot(handler, 2, 76, 33));
            this.addSlot(new ModResultSlot(handler, 3, 135, 9));
            this.addSlot(new ModResultSlot(handler, 4, 141, 33));
            this.addSlot(new ModResultSlot(handler, 5, 135, 57));
        });
    }*/

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 6;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }

/*
    @Override
    protected void onTake(Player p_150601_, ItemStack p_150602_) {

    }

    @Override
    protected boolean isValidBlock(BlockState p_39788_) {
        return false;
    }

    @Override
    public void createResult() {

    }*/
/*
    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.SOCKETING_TABLE.get());
    }*/

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    @Override
    public Map<Integer, Slot> get() {
        return null;
    }
}