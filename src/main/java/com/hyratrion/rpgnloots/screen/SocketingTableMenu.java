package com.hyratrion.rpgnloots.screen;

import com.hyratrion.rpgnloots.inventory.ModResultContainer;
import com.hyratrion.rpgnloots.item.Gems;
import com.hyratrion.rpgnloots.item.ModItems;
import com.hyratrion.rpgnloots.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SocketingTableMenu extends AbstractContainerMenu implements Supplier<Map<Integer,Slot>>
{
    private final Level level;
    public final Player player;
    public int x, y, z;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;
    private ItemPos pliersPos = ItemPos.NONE;
    private ItemPos gemPos = ItemPos.NONE;
    private boolean readForNewCraft = true;
    private boolean craftStart = false;


    private final Container inputContainer = new SimpleContainer(2)
    {
        @Override
        public void setChanged() {
            super.setChanged();
            SocketingTableMenu.this.slotsChanged(this);
        }
    };
    private final ModResultContainer outputContainer = new ModResultContainer(4, 2)
    {
        @Override
        public void setChanged() {
            super.setChanged();
            //SocketingTableMenu.this.slotsChanged(this);
        }
    };

    public SocketingTableMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(ModMenuTypes.SOCKETING_TABLE_MENU_TYPE, id);

        //System.out.println("Test ouverture gui SocketingTableMenu --> 1");

        this.player = inv.player;
        this.level = inv.player.level;
        //this.internal = new ItemStackHandler(6);

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
                    //this.internal = capability;
                    this.bound = true;
                });
            } else if (extraData.readableBytes() > 1) {
                extraData.readByte(); // drop padding
                Entity entity = level.getEntity(extraData.readVarInt());
                if (entity != null)
                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                        //this.internal = capability;
                        this.bound = true;
                    });
            } else { // might be bound to block
                BlockEntity ent = inv.player != null ? inv.player.level.getBlockEntity(pos) : null;
                if (ent != null) {
                    ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
                        //this.internal = capability;
                        this.bound = true;
                    });
                }
            }
        }

        this.customSlots.put(0, this.addSlot(new Slot(inputContainer, 0, 19, 16)
        {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return readForNewCraft;
            }
            @Override
            public void onTake(Player player, ItemStack itemStack) {
                SocketingTableMenu.this.onTake(player, itemStack, 0);
            }
        }));
        this.customSlots.put(1, this.addSlot(new Slot(inputContainer, 1, 19, 50)
        {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return readForNewCraft;
            }
            @Override
            public void onTake(Player player, ItemStack itemStack) {
                SocketingTableMenu.this.onTake(player, itemStack, 1);
            }
        }));
        this.customSlots.put(2, this.addSlot(new Slot(outputContainer, 2, 76, 33)
        {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return SocketingTableMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void onTake(Player player, ItemStack itemStack) {
                SocketingTableMenu.this.onTake(player, itemStack, 2);
            }
        }));
        this.customSlots.put(3, this.addSlot(new Slot(outputContainer, 3, 135, 9)
        {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return SocketingTableMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void onTake(Player player, ItemStack itemStack) {
                SocketingTableMenu.this.onTake(player, itemStack, 3);
            }
        }));
        this.customSlots.put(4, this.addSlot(new Slot(outputContainer, 4, 141, 33)
        {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return SocketingTableMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void onTake(Player player, ItemStack itemStack) {
                SocketingTableMenu.this.onTake(player, itemStack, 4);
            }
        }));
        this.customSlots.put(5, this.addSlot(new Slot(outputContainer, 5, 135, 57)
        {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return SocketingTableMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void onTake(Player player, ItemStack itemStack) {
                SocketingTableMenu.this.onTake(player, itemStack, 5);
            }
        }));




        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
        //System.out.println("Test ouverture gui SocketingTableMenu --> 2");
    }

    public void createResult() {
        ItemStack itemStack_0 = this.inputContainer.getItem(0);
        ItemStack itemStack_1 = this.inputContainer.getItem(1);


        ItemStack itemStackResult_0 = this.outputContainer.getItemWithSimpleContainer(0);
        ItemStack itemStackResult_1 = this.outputContainer.getItemWithSimpleContainer(1);
        ItemStack itemStackResult_2 = this.outputContainer.getItemWithSimpleContainer(2);
        ItemStack itemStackResult_3 = this.outputContainer.getItemWithSimpleContainer(3);

        boolean allResultEmpty = itemStackResult_0.isEmpty() && itemStackResult_1.isEmpty() && itemStackResult_2.isEmpty() && itemStackResult_3.isEmpty();

        pliersPos = itemStack_0.getItem().equals(ModItems.PLIERS.get()) ? ItemPos.SLOT_0 : ItemPos.NONE;
        pliersPos = itemStack_1.getItem().equals(ModItems.PLIERS.get()) ? ItemPos.SLOT_1 : pliersPos;


        //aucun des slot de resultat ne doit être remplis
        if(allResultEmpty)
        {
            Item gemToAdd = null;

            if(ModTags.ItemSupportedByMod(itemStack_0) && (Gems.isGem(itemStack_1.getItem()) || pliersPos == ItemPos.SLOT_1))
            {
                itemStackResult_0 = itemStack_0.copy();
                if(pliersPos == ItemPos.NONE)
                {
                    gemToAdd = itemStack_1.getItem();
                    gemPos = ItemPos.SLOT_1;
                }
            }
            else if(ModTags.ItemSupportedByMod(itemStack_1) && (Gems.isGem(itemStack_0.getItem()) || pliersPos == ItemPos.SLOT_0))
            {
                itemStackResult_0 = itemStack_1.copy();
                if(pliersPos == ItemPos.NONE)
                {
                    gemToAdd = itemStack_0.getItem();
                    gemPos = ItemPos.SLOT_0;
                }
            }
            else
            {
                gemPos = ItemPos.NONE;
            }


            if(Gems.HasGemSlot(itemStackResult_0))
            {

                int emptyGemSlot = Gems.CountGem(itemStackResult_0, true);
                //System.out.println("---- Makotache ---- emptyGemSlot => " + emptyGemSlot);

                int filledGemSlot = Gems.CountGem(itemStackResult_0, false);
                //System.out.println("---- Makotache ---- filledGemSlot => " + filledGemSlot);


                boolean doChanges = false;

                if(pliersPos != ItemPos.NONE && filledGemSlot > 0)
                {
                    Item[] allGemsEquiped = Gems.GetGems(itemStackResult_0);

                    for(int i = 0; i < filledGemSlot; i++)
                    {
                        this.outputContainer.setItemWithSimpleContainer(i + 1, new ItemStack(allGemsEquiped[i], 1));
                    }

                    Gems.ReplaceAllGemSlotByEmpty(itemStackResult_0);
                    doChanges = true;
                }
                else if(pliersPos == ItemPos.NONE && emptyGemSlot > 0)
                {
                    Gems.ReplaceFirstEmptyGemSlot(itemStackResult_0, gemToAdd);
                    doChanges = true;
                }

                if(doChanges)
                {
                    this.outputContainer.setItemWithSimpleContainer(0, itemStackResult_0);
                    this.broadcastChanges();
                }
            }
        }
    }



    protected void onTake(Player player, ItemStack itemStack, int index)
    {
        boolean isInput = index == 0 || index == 1;
        //System.out.println("--- Makotache -- index => " + index);
        //System.out.println("--- Makotache -- itemStack.getItem().getRegistryName().getPath() => " + itemStack.getItem().getRegistryName().getPath());
        //System.out.println("--- Makotache -- itemStack.getItem() => " + itemStack.getItem());

        //System.out.println("this.inputContainer.getItem(1) => " + this.inputContainer.getItem(1));


        if(isInput && (readForNewCraft || !craftStart))
        {
            //System.out.println("isInput");
            craftStart = false;
            outputContainer.EmptyResult();
        }
        else if(gemPos != ItemPos.NONE)
        {
            //System.out.println("gemPos");

            int gemIndex = gemPos == ItemPos.SLOT_0 ? 0 : 1;
            ItemStack gemItem = this.inputContainer.getItem(gemIndex);

            if (!gemItem.isEmpty() && gemItem.getCount() > 1) {
                gemItem.shrink(1);
            }
            else
            {
                this.inputContainer.setItem(gemIndex, ItemStack.EMPTY);
            }

            this.inputContainer.setItem(gemIndex == 0 ? 1 : 0, ItemStack.EMPTY);
        }
        else if(pliersPos != ItemPos.NONE)
        {
            craftStart = true;
            //System.out.println("pliersPos");

            //voir pliers qui disparait en shit click sur gem
            if(this.inputContainer.getItem(0).getItem().equals(ModItems.PLIERS.get()))
            {
                this.inputContainer.setItem(1, ItemStack.EMPTY);
            }
            else
            {
                this.inputContainer.setItem(0, ItemStack.EMPTY);
            }

            //this.inputContainer.setItem(pliersPos == ItemPos.SLOT_1 ? 0 : 1, ItemStack.EMPTY);
        }

        //System.out.println("this.inputContainer.getItem(1) => " + this.inputContainer.getItem(1));

        readForNewCraft = outputContainer.countWithSimpleContainer(false) == 0;

    }


    @Override
    public void slotsChanged(Container container)
    {
        super.slotsChanged(container);
        if (container == this.inputContainer)
        {
            this.createResult();
        }
    }

    protected boolean mayPickup(Player p_39023_, boolean p_39024_) {
        return (p_39023_.getAbilities().instabuild || p_39024_);
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 5) {
                if (!this.moveItemStackTo(itemstack1, 5, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 5, false)) {
                if (index < 5 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 5 + 27, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(itemstack1, 5, 5 + 27, false)) {
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
            slot.onTake(player, itemstack1);
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
        if (!bound && playerIn instanceof ServerPlayer serverPlayer)
        {
            for (int i = 0; i < inputContainer.getContainerSize(); ++i)
            {
                ItemStack itemStack = inputContainer.getItem(i).copy();
                inputContainer.setItem(i, ItemStack.EMPTY);

                if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected())
                {
                    playerIn.drop(itemStack, false);
                }
                else
                {
                    playerIn.getInventory().placeItemBackInInventory(itemStack);
                }
            }

        }
    }



    @Override
    public Map<Integer, Slot> get()
    {
        return customSlots;
    }

    private enum ItemPos
    {
        SLOT_0,
        SLOT_1,
        NONE
    }
}

