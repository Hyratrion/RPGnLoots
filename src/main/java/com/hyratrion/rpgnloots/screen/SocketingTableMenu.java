package com.hyratrion.rpgnloots.screen;

import com.hyratrion.rpgnloots.block.ModBlocks;
import com.hyratrion.rpgnloots.block.entity.SocketingTableBlockEntity;
import com.hyratrion.rpgnloots.screen.slot.ModResultSlot;
import net.minecraft.commands.arguments.SlotArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
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

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.hyratrion.rpgnloots.event.ModEvents.MOUSE_PRIMARY_CLICKED;

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
        this.internal = new ItemStackHandler(6);
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
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 19, 16) {

        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 19, 50) {
        }));
        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 76, 33)
        {
  /*          @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }
            @Override
            public boolean mayPickup(Player stack)
            {
                return true;
            }*/
        }));
        this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 135, 9)
        {
 /*           @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }
            @Override
            public boolean mayPickup(Player stack)
            {
                return true;
            }*/
        }));
        this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 141, 33)
        {
/*            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }
            @Override
            public boolean mayPickup(Player stack)
            {
                return true;
            }*/
        }));
        this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 135, 57)
        {
 /*           @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }
            @Override
            public boolean mayPickup(Player stack)
            {
                return true;
            }*/
        }));
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
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
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);

        System.out.println("'Affichage -slot-" + slot.index);

        if (slot != null && slot.hasItem()) {

            System.out.println("'Dans if -slot-" + slot.index);

            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 3) {
                if (!this.moveItemStackTo(itemstack1, 3, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                if (index < 3 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 3 + 27, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(itemstack1, 3, 3 + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
            System.out.println("----------------> Ontake");
      /*      if (slot.equals(customSlots.get(2)) && MOUSE_PRIMARY_CLICKED)
            {
                System.out.println("Slot2 + clic gauche");
                new ItemStack(itemstack1)
            }
  //          customSlots.get(2).getItem().*/
        }
        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
        boolean flag = false;
        int i = p_38905_;
        if (p_38907_) {
            i = p_38906_ - 1;
        }
        if (p_38904_.isStackable()) {
            while (!p_38904_.isEmpty()) {
                if (p_38907_) {
                    if (i < p_38905_) {
                        break;
                    }
                } else if (i >= p_38906_) {
                    break;
                }
                Slot slot = this.slots.get(i);
                ItemStack itemstack = slot.getItem();
                if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(p_38904_, itemstack)) {
                    int j = itemstack.getCount() + p_38904_.getCount();
                    int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
                    if (j <= maxSize) {
                        p_38904_.setCount(0);
                        itemstack.setCount(j);
                        slot.set(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        p_38904_.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.set(itemstack);
                        flag = true;
                    }
                }
                if (p_38907_) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        if (!p_38904_.isEmpty()) {
            if (p_38907_) {
                i = p_38906_ - 1;
            } else {
                i = p_38905_;
            }
            while (true) {
                if (p_38907_) {
                    if (i < p_38905_) {
                        break;
                    }
                } else if (i >= p_38906_) {
                    break;
                }
                Slot slot1 = this.slots.get(i);
                ItemStack itemstack1 = slot1.getItem();
                if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
                    if (p_38904_.getCount() > slot1.getMaxStackSize()) {
                        slot1.set(p_38904_.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.set(p_38904_.split(p_38904_.getCount()));
                    }
                    slot1.setChanged();
                    flag = true;
                    break;
                }
                if (p_38907_) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        return flag;
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
                }
            }
        }
    }

/*    @Override
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
    }*/

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

/*    private void addPlayerInventory(Inventory playerInventory) {
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
    }*/


    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }
}