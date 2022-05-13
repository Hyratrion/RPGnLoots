package com.hyratrion.rpgnloots.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModResultContainer implements Container
{
    private final NonNullList<ItemStack> itemStacks;
    private final int inputContainerSize;

    public ModResultContainer(int countResultcontainer, int inputContainer) {
        inputContainerSize = inputContainer;
        this.itemStacks = NonNullList.withSize(countResultcontainer + inputContainerSize, ItemStack.EMPTY);
    }

    public ModResultContainer(int countResultcontainer, Container inputContainer) {
        inputContainerSize = inputContainer.getContainerSize();
        this.itemStacks = NonNullList.withSize(countResultcontainer + inputContainerSize, ItemStack.EMPTY);
    }

    public ModResultContainer(ItemStack... itemStacks) {
        inputContainerSize = 0;
        this.itemStacks = NonNullList.of(ItemStack.EMPTY, itemStacks);
    }

    @Override
    public int getContainerSize() {
        return itemStacks.size();
    }

    public int countWithSimpleContainer(boolean isEmpty)
    {
        int result = 0;

        for(int i = 0; i < getContainerSize() - inputContainerSize; i++)
        {
            if(isEmpty == this.getItemWithSimpleContainer(i).isEmpty())
            {
                result++;
            }
        }

        return result;
    }


    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.itemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.itemStacks.get(index);
    }
    public ItemStack getItemWithSimpleContainer(int index) {
        return this.itemStacks.get(index  + inputContainerSize);
    }

    @Override
    public ItemStack removeItem(int p_40149_, int p_40150_) {
        return ContainerHelper.takeItem(this.itemStacks, p_40149_);
    }
    public ItemStack removeItemWithSimpleContainer(int p_40149_, int p_40150_) {
        return ContainerHelper.takeItem(this.itemStacks, p_40149_ + inputContainerSize);
    }


    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.itemStacks, index);
    }
    public ItemStack removeItemNoUpdateWithSimpleContainer(int index)
    {
        return ContainerHelper.takeItem(this.itemStacks, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.itemStacks.set(index, itemStack);
    }

    public void setItemWithSimpleContainer(int index, ItemStack itemStack) {
        this.itemStacks.set(index + inputContainerSize, itemStack);
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        this.itemStacks.clear();
    }

    public void EmptyResult()
    {
        for(int i = 0; i < getContainerSize() - inputContainerSize; i++)
        {
            this.setItemWithSimpleContainer(i, ItemStack.EMPTY);
        }
    }
}
