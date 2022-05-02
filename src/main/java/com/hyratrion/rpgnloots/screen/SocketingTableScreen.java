package com.hyratrion.rpgnloots.screen;

import com.hyratrion.rpgnloots.event.ModEvents;
import com.hyratrion.rpgnloots.item.ModItems;
import com.hyratrion.rpgnloots.util.ModTags;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import org.antlr.v4.runtime.atn.SemanticContext;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Map;

import static com.hyratrion.rpgnloots.util.ModTags.*;

public class SocketingTableScreen extends AbstractContainerScreen<SocketingTableMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RPGNLOOT.MOD_ID, "textures/gui/socketing_table_gui.png");

    public SocketingTableScreen(SocketingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        SocketingTableMenu socketingTableMenu = this.menu;

//        Map<Integer, Slot> customSlots = socketingTableMenu.get();
        NonNullList<Slot> slots = socketingTableMenu.slots;
        Slot slot0 = slots.get(0);
        Slot slot1 = slots.get(1);
        Slot slot2 = slots.get(2);
        Slot slot3 = slots.get(3);
        Slot slot4 = slots.get(4);
        Slot slot5 = slots.get(5);
/*
        Slot slot0 = customSlots.get(0);
        Slot slot1 = customSlots.get(1);
        Slot slot2 = customSlots.get(2);
        Slot slot3 = customSlots.get(3);
        Slot slot4 = customSlots.get(4);
        Slot slot5 = customSlots.get(5);
   */
        Item itemSlot0 = slot0.getItem().getItem();
        ItemStack itemStackSlot0 = slot0.getItem();
        Item itemSlot1 = slot1.getItem().getItem();
        ItemStack itemStackSlot1 = slot1.getItem();
        Item itemSlot2;// = slot2.getItem().getItem();
        ItemStack itemStackSlot2 = null;

        if (ModTags.HaveGem(itemStackSlot0) && isGem(itemSlot1))
        {
 //           System.out.println("(itemTypeIsCorrect(itemSlot0) && isGem(itemSlot1)");

            itemStackSlot2 = itemStackSlot0.copy();

 //           System.out.println(itemStackSlot2.getTag());

            boolean truc = ReplaceFirstEmptyGemTag(itemStackSlot2, itemSlot1);

 //           System.out.println(truc);

 //           customSlots.get(2).onTake(ItemStack itemStackSlot0);

        }
        else if (ModTags.HaveGem(itemStackSlot1) && isGem(itemSlot0))
        {
  //          System.out.println("(itemTypeIsCorrect(itemSlot1) && isGem(itemSlot0)");

            itemStackSlot2 = itemStackSlot1.copy();



  //          System.out.println(itemStackSlot2.getTag());

            boolean truc = ReplaceFirstEmptyGemTag(itemStackSlot2, itemSlot0);

  //          System.out.println(truc);
        }
        else
        {
            itemStackSlot2 = ItemStack.EMPTY;
 //           System.out.println("----------------------> ERREUR");
        }
        socketingTableMenu.setRemoteSlot(2,itemStackSlot2);

  /*  if (ModEvents.MOUSE_PRIMARY_CLICKED)
    {
        if (slot2.hasItem() && this.hoveredSlot==slot2 && socketingTableMenu.getCarried().isEmpty())
        {
            socketingTableMenu.setCarried(itemStackSlot2);
    //        slot2.onTake(, itemStackSlot2);
            slot0.set(ItemStack.EMPTY);
            slot1.set(ItemStack.EMPTY);
            slot2.set(ItemStack.EMPTY);
    //        return this.hoveredSlot.index;
        }
        else if (this.hoveredSlot.getItem().isEmpty())
        {
            this.hoveredSlot.set(socketingTableMenu.getCarried().copy());
            System.out.println("set(socketingTableMenu.getCarried().copy()" + this.hoveredSlot.getItem().getDescriptionId());

        }

        System.out.println("ITEM PRESENT ---------->" + itemStackSlot2);
        if (this.hoveredSlot.doClic)
        {
            itemStackSlot2.getItem().
        }
    }*/

/*        if (ModTags.HaveGem(itemStackSlot0) && itemStackSlot1Slot1())
        {
            System.out.println("(itemTypeIsCorrect(itemSlot0) && isGem(itemSlot1)");

            slot2.set(itemStackSlot0);
            itemStackSlot2 = slot2.getItem();

            System.out.println(itemStackSlot2.getTag());



            System.out.println(truc);
        }*/
    }


    private boolean isGem(Item gem) {
        return ModItems.GEMS_VALUES.containsKey(gem);
    }


/*    private static String StringValue( Tag tag)
    {
        return tag.toString().replace("\"", "");//.replace("'", "");
    }

    private boolean isGemType( ItemStack itemStack,  Item gem)
    {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains(ModItems.GEM_TYPE))
        {
            return false;
        }

        ListTag listtag = (ListTag)tag.get(ModItems.GEM_TYPE);
        String gemTag = GetGemTag(gem);
        boolean result = false;

        for(int i = listtag.size()-1; i >= 0; i--)
        {
            String value = StringValue(listtag.getCompound(i).get("Name"));

            if (value.equals(gemTag))
            {
                result = true;
            }
        }

        return result;
    }*/

}