package com.hyratrion.rpgnloots.event;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import com.hyratrion.rpgnloots.item.Gems;
import com.hyratrion.rpgnloots.item.ModItems;
import com.hyratrion.rpgnloots.util.ModTags;
import com.hyratrion.rpgnloots.util.StaticClass;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Method;
import java.util.*;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

@Mod.EventBusSubscriber(modid = RPGNLOOT.MOD_ID)
public class ModEventToolTips
{
    @SubscribeEvent
    public static void onItemTooltipEvent(ItemTooltipEvent event)
    {
        if (event.getPlayer() != null && event.getPlayer().level.isClientSide())
        {
            //System.out.println("----- Makotache ----- onItemTooltipEvent => event.getEntity() != null");
            ItemStack itemStack = event.getItemStack();

            if(ModTags.ItemSupportedByMod(itemStack))
            {
                //System.out.println("----- Makotache ----- onItemTooltipEvent => ItemSupportedByMod");
                Style loreStyle = Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE).withItalic(true);

                Player player = event.getPlayer();
                TooltipFlag tooltipFlag = event.getFlags();
                List<Component> listToolTip = event.getToolTip();

                listToolTip.clear();

                ChatFormatting color = null;

                switch (Gems.GetTier(itemStack))
                {
                    case 1 :
                        color = ChatFormatting.GREEN;
                        break;

                    case 2 :
                        color = ChatFormatting.BLUE;
                        break;

                    case 3 :
                        color = ChatFormatting.LIGHT_PURPLE;
                        break;

                    case 4 :
                        color = ChatFormatting.GOLD;
                        break;

                    case 5 :
                        color = ChatFormatting.DARK_RED;
                        break;
                }

                MutableComponent mutablecomponent = (new TextComponent("")).append(itemStack.getHoverName()).withStyle(color).withStyle(ChatFormatting.ITALIC);
                if (itemStack.hasCustomHoverName()) {
                    mutablecomponent.withStyle(ChatFormatting.ITALIC);
                }

                listToolTip.add(mutablecomponent);
                if (!tooltipFlag.isAdvanced() && !itemStack.hasCustomHoverName() && itemStack.is(Items.FILLED_MAP)) {
                    Integer integer = MapItem.getMapId(itemStack);
                    if (integer != null) {
                        listToolTip.add((new TextComponent("#" + integer)).withStyle(ChatFormatting.GRAY));
                    }
                }

                int j = getHideFlags(itemStack);
                if (shouldShowInTooltip(j, ItemStack.TooltipPart.ADDITIONAL)) {
                    itemStack.getItem().appendHoverText(itemStack, player == null ? null : player.level, listToolTip, tooltipFlag);
                }

                if (itemStack.hasTag()) {
                    if (shouldShowInTooltip(j, ItemStack.TooltipPart.ENCHANTMENTS)) {
                        itemStack.appendEnchantmentNames(listToolTip, itemStack.getEnchantmentTags());
                    }

                    if (itemStack.getTag().contains("display", 10)) {
                        CompoundTag compoundtag = itemStack.getTag().getCompound("display");
                        if (shouldShowInTooltip(j, ItemStack.TooltipPart.DYE) && compoundtag.contains("color", 99)) {
                            if (tooltipFlag.isAdvanced()) {
                                listToolTip.add((new TranslatableComponent("item.color", String.format("#%06X", compoundtag.getInt("color")))).withStyle(ChatFormatting.GRAY));
                            } else {
                                listToolTip.add((new TranslatableComponent("item.dyed")).withStyle(new ChatFormatting[]{ChatFormatting.GRAY, ChatFormatting.ITALIC}));
                            }
                        }

                        if (compoundtag.getTagType("Lore") == 9) {
                            ListTag listtag = compoundtag.getList("Lore", 8);

                            for(int i = 0; i < listtag.size(); ++i) {
                                String s = listtag.getString(i);

                                try {
                                    MutableComponent mutablecomponent1 = Component.Serializer.fromJson(s);
                                    if (mutablecomponent1 != null) {
                                        listToolTip.add(ComponentUtils.mergeStyles(mutablecomponent1, loreStyle));
                                    }
                                } catch (Exception exception) {
                                    compoundtag.remove("Lore");
                                }
                            }
                        }
                    }
                }

                if (shouldShowInTooltip(j, ItemStack.TooltipPart.MODIFIERS))
                {

                    //System.out.println("----- Makotache ----- onItemTooltipEvent.AttributeModifier => start");
                    EquipmentSlot[] equipmentSlots;

                    if(itemStack.getItem().equals(Items.SHIELD))
                    {
                        equipmentSlots = new EquipmentSlot[]{EquipmentSlot.MAINHAND};
                    }
                    else
                    {
                        equipmentSlots = StaticClass.GetEquipmentSlotOf(itemStack);
                    }
                    //EquipmentSlot[] equipmentSlots = ModTags.GetEquipmentSlotOf(itemStack);
                    for(int i =0; i < equipmentSlots.length; i++)
                    {
                        listToolTip.add(TextComponent.EMPTY);
                        listToolTip.add((new TranslatableComponent("item.modifiers." + equipmentSlots[i].getName())).withStyle(ChatFormatting.GRAY));

                        //System.out.println("----- Makotache ----- onItemTooltipEvent.for => equipmentSlots");

                        Multimap<Attribute, AttributeModifier> attributeModifierMaps = itemStack.getAttributeModifiers(equipmentSlots[i]);
                        boolean gemLevelIncrease = attributeModifierMaps.containsKey(CustomAttributes.GEM_LVL_INCREASE.get());


                        Map<String, Component> unSortedAttributeModifierMaps = new HashMap<>();
                        for(Map.Entry<Attribute, AttributeModifier> entry : attributeModifierMaps.entries())
                        {
                            if (player != null)
                            {
                                AttributeModifier attributeModifier = entry.getValue();
                                Attribute attribute = entry.getKey();
                                boolean customToolTip = false;
                                boolean showPercent = false;
                                boolean modifierPlus = true;
                                ChatFormatting chatFormatting = ChatFormatting.BLUE;

                                double value = (float)attributeModifier.getAmount();


                                if (attribute.equals(Attributes.ATTACK_DAMAGE))
                                {
                                    value += player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);

                                    value += EnchantmentHelper.getDamageBonus(itemStack, MobType.UNDEFINED);


                                    modifierPlus = false;
                                    chatFormatting = ChatFormatting.RED;
                                }
                                else if (attribute.equals(Attributes.ATTACK_SPEED))
                                {
                                    value += player.getAttributeBaseValue(Attributes.ATTACK_SPEED);

                                    modifierPlus = false;
                                    chatFormatting = ChatFormatting.GRAY;
                                }
                                else if(attribute.equals(CustomAttributes.CRITICAL_CHANCE.get()))
                                {
                                    showPercent = true;
                                    chatFormatting = ChatFormatting.YELLOW;
                                }
                                else if(attribute.equals(CustomAttributes.CRITICAL_DAMAGE.get()))
                                {
                                    showPercent = true;
                                    chatFormatting = ChatFormatting.GOLD;
                                }
                                else if(attribute.equals(Attributes.MAX_HEALTH))
                                {
                                    chatFormatting = ChatFormatting.LIGHT_PURPLE;
                                }
                                else if(attribute.equals(CustomAttributes.GEM_SLOT.get()))
                                {
                                    if(itemStack.hasTag() && itemStack.getTag().contains(ModItems.GEM_TYPE))
                                    {
                                        int moreGem = 0;
                                        if(attributeModifierMaps.containsKey(CustomAttributes.MORE_GEM_SLOT.get()))
                                        {
                                            moreGem = (int)StaticClass.GetValueFromAttributeModifierMap(attributeModifierMaps, CustomAttributes.MORE_GEM_SLOT.get());
                                        }
                                        value -= moreGem;
                                        String value_str = (int)value + "";
                                        if(moreGem > 0)
                                        {
                                            value_str += "(+"+moreGem+")";
                                        }
                                        Map.Entry<String, Component> componentToolTip = createComponentToolTip(false, entry, value_str, false, chatFormatting);
                                        unSortedAttributeModifierMaps.put(componentToolTip.getKey(), componentToolTip.getValue());

                                        String gemText = componentToolTip.getKey();

                                        chatFormatting = ChatFormatting.GRAY;

                                        Item[] allGemsEquiped = Gems.GetGems(itemStack);

                                        //on doit appuyer sur shift pou plus de détail
                                        if(Screen.hasShiftDown())
                                        {
                                            for (int u = 0; u < allGemsEquiped.length; u++)
                                            {
                                                int ub = u+1;
                                                Item item = allGemsEquiped[u];

                                                if(item == null)
                                                {
                                                    componentToolTip = createComponentToolTipGem(gemText + ub, "", "attribute.name.rpgnloots.empty", chatFormatting);
                                                }
                                                else
                                                {
                                                    showPercent = Gems.GemIsType(item, ModTags.Items.GEM_PERCENT);

                                                    componentToolTip = createComponentToolTipGem(gemText + ub, Gems.GetGemValue(item), item, showPercent, chatFormatting, gemLevelIncrease);
                                                }
                                                unSortedAttributeModifierMaps.put(componentToolTip.getKey(), componentToolTip.getValue());
                                            }
                                        }

                                        customToolTip = true;
                                    }

                                }
                                else if(attribute.equals(CustomAttributes.DODGE.get()))
                                {
                                    showPercent = true;
                                }
                                else if(attribute.equals(CustomAttributes.LIFE_LEECH_PERCENT.get()))
                                {
                                    showPercent = true;
                                }
                                else if(attribute.equals(CustomAttributes.REFLECT_DAMAGE_PERCENT.get()))
                                {
                                    showPercent = true;
                                }

                                double finalValue;
                                if (attributeModifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributeModifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                                    if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
                                        finalValue = value * 10.0D;
                                    } else {
                                        finalValue = value;
                                    }
                                } else {
                                    finalValue = value * 100.0D;
                                }

                                if(!customToolTip)
                                {
                                    Map.Entry<String, Component> componentToolTip = createComponentToolTip(modifierPlus, entry, finalValue, showPercent, chatFormatting);
                                    unSortedAttributeModifierMaps.put(componentToolTip.getKey(), componentToolTip.getValue());
                                }
                            }
                        }
                        Map<String, Component> sortedAttributeModifierMaps = new TreeMap<>(unSortedAttributeModifierMaps);

                        sortedAttributeModifierMaps.forEach((k, v) -> listToolTip.add(v));
                    }
                    //System.out.println("----- Makotache ----- onItemTooltipEvent.AttributeModifier => end");

                }

                if (itemStack.hasTag()) {
                    if (shouldShowInTooltip(j, ItemStack.TooltipPart.UNBREAKABLE) && itemStack.getTag().getBoolean("Unbreakable")) {
                        listToolTip.add((new TranslatableComponent("item.unbreakable")).withStyle(ChatFormatting.BLUE));
                    }

                    if (shouldShowInTooltip(j, ItemStack.TooltipPart.CAN_DESTROY) && itemStack.getTag().contains("CanDestroy", 9)) {
                        ListTag listtag1 = itemStack.getTag().getList("CanDestroy", 8);
                        if (!listtag1.isEmpty()) {
                            listToolTip.add(TextComponent.EMPTY);
                            listToolTip.add((new TranslatableComponent("item.canBreak")).withStyle(ChatFormatting.GRAY));

                            for(int k = 0; k < listtag1.size(); ++k) {
                                try
                                {
                                    Class[] cArg = new Class[1];
                                    cArg[0] = Component.class;
                                    Method method = ItemStack.class.getDeclaredMethod("expandBlockState", cArg);
                                    method.setAccessible(true);
                                    listToolTip.addAll((Collection<? extends Component>) method.invoke(null, listtag1.getString(k)));

                                    //listToolTip.addAll(ItemStack.expandBlockState(listtag1.getString(k)));
                                }
                                catch (Exception ex)
                                {
                                    //System.out.println("----- Makotache ----- onItemTooltipEvent.CAN_DESTROY");
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }

                if (tooltipFlag.isAdvanced()) {
                    if (itemStack.isDamaged()) {
                        listToolTip.add(new TranslatableComponent("item.durability", itemStack.getMaxDamage() - itemStack.getDamageValue(), itemStack.getMaxDamage()));
                    }

                    listToolTip.add((new TextComponent(Registry.ITEM.getKey(itemStack.getItem()).toString())).withStyle(ChatFormatting.DARK_GRAY));
                    if (itemStack.hasTag()) {
                        listToolTip.add((new TranslatableComponent("item.nbt_tags", itemStack.getTag().getAllKeys().size())).withStyle(ChatFormatting.DARK_GRAY));
                    }
                }
            }
        }
    }

    //methode ItemStack pour toolTips
    private static Map.Entry<String, Component> createComponentToolTip(boolean modifierPlus, Map.Entry<Attribute, AttributeModifier> entry, double value, boolean showPercent, ChatFormatting chatFormatting)
    {
        AttributeModifier attributeModifier = entry.getValue();
        Attribute attribute = entry.getKey();
        TranslatableComponent name = new TranslatableComponent(entry.getKey().getDescriptionId());

        String modifierPlusStr = modifierPlus ? "attribute.modifier.plus." : "attribute.modifier.equals.";
        String showPercentStr = showPercent ? "%" : "";


        Component component = (new TranslatableComponent(modifierPlusStr + attributeModifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(value) + showPercentStr, name)).withStyle(chatFormatting);

        if(!modifierPlus)
        {
            component = new TextComponent(" ").append(component);
        }

        String str = Language.getInstance().getOrDefault(name.getKey());
        if(attribute.equals(CustomAttributes.GEM_SLOT.get()))
        {
            str += "0";
        }

        return Map.entry(str, component);
    }

    private static Map.Entry<String, Component> createComponentToolTip(boolean modifierPlus, Map.Entry<Attribute, AttributeModifier> entry, String value, boolean showPercent, ChatFormatting chatFormatting)
    {
        AttributeModifier attributeModifier = entry.getValue();
        Attribute attribute = entry.getKey();
        TranslatableComponent name = new TranslatableComponent(entry.getKey().getDescriptionId());

        String modifierPlusStr = modifierPlus ? "attribute.modifier.plus." : "attribute.modifier.equals.";
        String showPercentStr = showPercent ? "%" : "";


        Component component = (new TranslatableComponent(modifierPlusStr + attributeModifier.getOperation().toValue(), value + showPercentStr, name)).withStyle(chatFormatting);

        if(!modifierPlus)
        {
            component = new TextComponent(" ").append(component);
        }

        String str = Language.getInstance().getOrDefault(name.getKey());
        if(attribute.equals(CustomAttributes.GEM_SLOT.get()))
        {
            str += "0";
        }

        return Map.entry(str, component);
    }

    private static Map.Entry<String, Component> createComponentToolTipGem(String pos, String value, String id, ChatFormatting chatFormatting)
    {
        //value += value.length() > 0 ?  " " : "";
        //pert de faire un affichage plus court du text des gems
        Language langInstance = Language.getInstance();
        String name_str = langInstance .getOrDefault(id);
        String gem_of_str = langInstance .getOrDefault("tooltip.rpgnloots.gem_of");
        String level_str = langInstance .getOrDefault("tooltip.rpgnloots.gem_level");
        String level_shortcut_str = langInstance .getOrDefault("tooltip.rpgnloots.gem_level_shortcut");

        TextComponent name_comp = new TextComponent(name_str.replace(gem_of_str, "").replace(level_str, level_shortcut_str));

        //préparation du composant pour l'affichage
        Component component = (new TextComponent("  <" + value).withStyle(chatFormatting)).append((name_comp).append(">"));

        return Map.entry(pos + name_str, component);
    }

    private static Map.Entry<String, Component> createComponentToolTipGem(String pos, float value, Item item, boolean showPercent, ChatFormatting chatFormatting, boolean gemLevelIncrease)
    {
        String text = ATTRIBUTE_MODIFIER_FORMAT.format(value);

        text += showPercent ? "% " : " ";

        if(gemLevelIncrease)
        {
            float valueGemLevelIncrease = Gems.GetGemValue(item, Gems.GetGemLevel(item) + 1) - Gems.GetGemValue(item, Gems.GetGemLevel(item));
            text += "(+" + ATTRIBUTE_MODIFIER_FORMAT.format(valueGemLevelIncrease) + ") ";
        }

        return createComponentToolTipGem(pos, text, item.getDescriptionId(), chatFormatting);
    }

    private static boolean shouldShowInTooltip(int p_41627_, ItemStack.TooltipPart p_41628_) {
        return (p_41627_ & p_41628_.getMask()) == 0;
    }

    private static int getHideFlags(ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getTag().contains("HideFlags", 99) ? itemStack.getTag().getInt("HideFlags") : itemStack.getItem().getDefaultTooltipHideFlags(itemStack);
    }

}
