package com.hyratrion.rpgnloots.event;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.lang.reflect.Method;
import java.util.*;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

@Mod.EventBusSubscriber(modid = RPGNLOOT.MOD_ID)
public class ModEvents
{
    private static Random rand = new Random();

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event)
    {
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event)
    {
        if(!event.getOriginal().getLevel().isClientSide())
        {
            event.getPlayer().getPersistentData().putIntArray(RPGNLOOT.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(RPGNLOOT.MOD_ID + "homepos"));
        }
    }

    //event de critique
    @SubscribeEvent
    public static void onCriticalHitEvent(CriticalHitEvent event)
    {
        if(!event.getEntity().level.isClientSide())
        {
            //récupération de l'item dans main du joueur
            ItemStack itemStack = event.getPlayer().getMainHandItem();

            //récupération des attributeModifiers de l'item
            Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            //debug
            /*if(attributeModifiers.containsKey(CustomAttributes.ATTACK_DAMAGE))
            {
                float degatBase = (float)attributeModifiers.get(CustomAttributes.ATTACK_DAMAGE).stream().findFirst().get().getAmount();
                System.out.println("----- Makotache ----- Degats precedant => " + degatBase);
                //System.out.println("----- Makotache ----- itemStack.getTag().toString() => " + itemStack.getTag().toString());
            }*/

            float criticalChanceArmor = getValueAttributeOfArmor(CustomAttributes.CRITICAL_CHANCE.get(), event.getPlayer());
            float criticalDamage = event.getOldDamageModifier();

            //on vérifie si "attributeModifiers" contiens
            //"Attributes.ATTACK_DAMAGE" ET "CustomAttributes.CRITICAL_CHANCE"
            /*System.out.println("attributeModifiers.containsKey(Attributes.ATTACK_DAMAGE)" + attributeModifiers.containsKey(Attributes.ATTACK_DAMAGE));
            System.out.println("attributeModifiers.containsKey(Attributes.CRITICAL_CHANCE)" + attributeModifiers.containsKey(CustomAttributes.CRITICAL_CHANCE.get()));
            System.out.println("criticalChanceArmor" + criticalChanceArmor);*/
            if(attributeModifiers.containsKey(Attributes.ATTACK_DAMAGE) &&
                    (attributeModifiers.containsKey(CustomAttributes.CRITICAL_CHANCE.get()) || criticalChanceArmor > 0 || criticalDamage != 1))
            {
                float criticalChanceBase = 0;
                //récupération des chances de critiques
                if(attributeModifiers.containsKey(CustomAttributes.CRITICAL_CHANCE.get()))
                {
                    criticalChanceBase = StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.CRITICAL_CHANCE.get());
                }
                System.out.println("----- Makotache ----- base crit chance " + criticalChanceBase);
                criticalChanceBase += criticalChanceArmor;
                System.out.println("----- Makotache ----- base crit chance +armor " + criticalChanceBase);

                String[] allGemsEquiped = null;
                if(ModTags.HaveGem(itemStack))
                {
                    allGemsEquiped = ModTags.GetGemTags(itemStack);
                    //System.out.println("----- Makotache ----- itemStack.getTag() => "+ itemStack.getTag());
                    //System.out.println("----- Makotache ----- allGemsEquiped.length => "+ allGemsEquiped.length);

                    if(ModTags.HaveGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_CRITICAL_CHANCE))
                    {
                        criticalChanceBase += ModTags.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_CRITICAL_CHANCE);
                        //System.out.println("----- Makotache ----- base +gem crit chance " + criticalChanceBase);
                    }
                }

                //récupération de la valeur de chance d'appliquer un critique
                float criticalChanceRNG = rand.nextFloat(100);


                //on check notre chance de faire un critique
                if(criticalChanceRNG < criticalChanceBase || criticalChanceBase >= 100 || criticalDamage != 1)
                {
                    int multiplicator = 1;
                    if(criticalChanceBase >= 100)
                    {
                        String critChanceStr = String.valueOf(criticalChanceBase);
                        int posComma = critChanceStr.indexOf(".");//par ce que 94 % sure de micro
                        posComma = posComma != -1 ? posComma - 2 : 1;
                        multiplicator += Integer.valueOf(critChanceStr.substring(0, posComma));
                        System.out.println("----- Makotache ----- avant multiplicator => " + multiplicator);

                        criticalChanceRNG = rand.nextFloat(100);
                        if(criticalChanceRNG < criticalChanceBase - 100 * (multiplicator -1))
                        {
                            multiplicator += 1;
                        }
                    }
                    System.out.println("----- Makotache ----- apres multiplicator => " + multiplicator);

                    System.out.println("----- Makotache ----- crit EXISTANT");

                    float criticalDamageArmor = getValueAttributeOfArmor(CustomAttributes.CRITICAL_DAMAGE.get(), event.getPlayer()) / 100;

                    //récupération du multiplicateur des dégâts crtitique de minecraft Vanilla
                    System.out.println("----- Makotache ----- crit base " + criticalDamage);
                    criticalDamage += criticalDamageArmor;
                    System.out.println("----- Makotache ----- crit base +armor " + criticalDamage);

                    if(ModTags.HaveGem(itemStack))
                    {
                        if(ModTags.HaveGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE))
                        {
                            criticalDamage += ModTags.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE) / 100;
                            //System.out.println("----- Makotache ----- base +gem crit damage " + criticalDamage);
                        }
                    }

                    //SI on possède "CustomAttributes.CRITICAL_DAMAGE"
                    if(attributeModifiers.containsKey(CustomAttributes.CRITICAL_DAMAGE.get()))
                    {
                        //on ajoute plus de dégâts critique
                        criticalDamage += StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.CRITICAL_DAMAGE.get()) / 100;
                    }
                    System.out.println("----- Makotache ----- nouveau crit  " + criticalDamage);

                    criticalDamage *= multiplicator;

                    //on change la valeur des dégâts
                    event.setDamageModifier(criticalDamage);
                    event.setResult(Event.Result.ALLOW);

                    System.out.println("----- Makotache ----- Degats multiplicateur => " + criticalDamage);
                }
                /*else
                {
                    System.out.println("----- Makotache ----- AUCUN critique");
                }*/
            }
        }
    }

    //Event quand une enitité prend des dégats
    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event)
    {


        if (!event.getEntity().level.isClientSide())
        {
            //si l'entité qui SUBIT des dégâts est un joueur
            if (event.getEntity() instanceof Player player)
            {
                //on récupère tous les equipement dans les slots d'armure du joueur
                Iterable<ItemStack> itemStacks = player.getArmorSlots();


                boolean dodgeDone = false;

                //esquive
                float amountDodge = getValueAttributeOfArmor(CustomAttributes.DODGE.get(), player);

                //si on a une chance de faire une equive
                if (amountDodge > 0)
                {
                    //System.out.println("amout dodge -->" + amountDodge);
                    //on test notre chance d'esquive
                    float chanceDodge = rand.nextInt(100);
                    if (chanceDodge < amountDodge)
                    {
                        dodgeDone = true;
                        //si on esquive alors on ne prend pas les dégats
                        event.setCanceled(true);
                    }
                }

                //si on a pas fais d'esquive
                if(!dodgeDone)
                {
                    float amountReflectedDamagePercent = 0;
                    float amountReflectedDamageRaw = 0;

                    //renvoie de degats
                    for (ItemStack itemStack : itemStacks)
                    {
                        //si l'equipement d'amure est bien une armure et pas un slot vide
                        if (itemStack.getItem() instanceof ArmorItem armorItem)
                        {
                            //System.out.println("Get equipement slot --> " + armorItem.getSlot());
                            //récupération des attributeModifiers de l'item en question
                            Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(armorItem.getSlot());

                            //POURCENTAGE

                            //si l'armure possède "CustomAttributes.REFLECT_DAMAGE_PERCENT"
                            if (attributeModifiers.containsKey(CustomAttributes.REFLECT_DAMAGE_PERCENT.get()))
                            {
                                //récupérationd de la valeurs de renvoie de dégâts
                                amountReflectedDamagePercent += StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.REFLECT_DAMAGE_PERCENT.get()) / 100;
                            }

                            //BRUTE
                            //si l'armure possède "CustomAttributes.REFLECT_DAMAGE_RAW"
                            if (attributeModifiers.containsKey(CustomAttributes.REFLECT_DAMAGE_RAW.get()))
                            {
                                amountReflectedDamageRaw += StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.REFLECT_DAMAGE_RAW.get());
                            }
                        }
                    }

                    //réucpération de l'entitié qui INFLIGE les dégats
                    Entity entity = event.getSource().getDirectEntity();


                    //si on a un renvoie de dégâts
                    if(entity != null && (amountReflectedDamagePercent > 0 || amountReflectedDamageRaw > 0))
                    {
                        //calcule du montant de dégâs a infligé en pourcentage
                        float damgeToReflect = amountReflectedDamagePercent * event.getAmount();

                        //on ajotue le montant brute de dégâts
                        damgeToReflect += amountReflectedDamageRaw;

                        //on inflige les dégâts
                        entity.hurt(DamageSource.playerAttack(player), damgeToReflect);
                    }
                }
            }
        }
    }

    //Event juste avnt entité prend des degats
    //après le calcule le nombre de degats en fonction des resistance
    @SubscribeEvent
    public static void onLivingDamageEvent(LivingDamageEvent event)
    {
        if (!event.getEntity().level.isClientSide())
        {
            if (event.getSource().getDirectEntity() instanceof Player player)
            {
                //vol de vie POURCENTAGE
                ItemStack itemStack = player.getMainHandItem();

                Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                System.out.println("----- Makotache ----- bobo du mob => " +event.getAmount());

                //vol de vie
                if(attributeModifiers.containsKey(CustomAttributes.LIFE_LEECH_PERCENT.get()))
                {
                    float amountLifeLeechPercent = StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.LIFE_LEECH_PERCENT.get()) / 100;

                    //System.out.println("----- Makotache ----- pourcentage de vole de vie => " + amountLifeLeechPercent);

                    amountLifeLeechPercent = amountLifeLeechPercent * event.getAmount();

                    player.heal(amountLifeLeechPercent);

                    //System.out.println("----- Makotache ----- vie regen => " + amountLifeLeechPercent);
                }


                //vol de vie BRUTE

                //System.out.println("----- Makotache ----- bobo du mob => " +event.getAmount());

                Component displayname = itemStack.getDisplayName();
                //System.out.println("----- Makotache ----- displayname => " + displayname);
                String DescriptionId = itemStack.getDescriptionId();
                //System.out.println("----- Makotache ----- DescriptionId => " + DescriptionId);

                if(attributeModifiers.containsKey(CustomAttributes.LIFE_LEECH_RAW.get()))
                {
                    float amountLifeLeechraw = StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.LIFE_LEECH_RAW.get());

                    //System.out.println("----- Makotache ----- pourcentage de vole de vie => " + amountLifeLeechraw);

                    player.heal(amountLifeLeechraw);

                    //System.out.println("----- Makotache ----- vie regen => " + amountLifeLeechraw);
                }
            }
        }
    }




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

                switch (ModTags.GetTier(itemStack))
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
                        equipmentSlots = ModTags.GetEquipmentSlotOf(itemStack);
                    }
                    //EquipmentSlot[] equipmentSlots = ModTags.GetEquipmentSlotOf(itemStack);
                    for(int i =0; i < equipmentSlots.length; i++)
                    {
                        listToolTip.add(TextComponent.EMPTY);
                        listToolTip.add((new TranslatableComponent("item.modifiers." + equipmentSlots[i].getName())).withStyle(ChatFormatting.GRAY));

                        //System.out.println("----- Makotache ----- onItemTooltipEvent.for => equipmentSlots");

                        Multimap<Attribute, AttributeModifier> attributeModifierMaps = itemStack.getAttributeModifiers(equipmentSlots[i]);


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
                                    chatFormatting = ChatFormatting.GOLD;//ModColor.RED_ORANGE;
                                }
                                else if(attribute.equals(Attributes.MAX_HEALTH))
                                {
                                    chatFormatting = ChatFormatting.LIGHT_PURPLE;
                                }
                                else if(attribute.equals(CustomAttributes.GEM_SLOT.get()))
                                {
                                    if(itemStack.hasTag() && itemStack.getTag().contains(ModItems.GEM_TYPE))
                                    {
                                        Map.Entry<String, Component> componentToolTip = createComponentToolTip(modifierPlus, entry, value, showPercent, chatFormatting);
                                        unSortedAttributeModifierMaps.put(componentToolTip.getKey(), componentToolTip.getValue());

                                        String gemText = componentToolTip.getKey();

                                        chatFormatting = ChatFormatting.GRAY;

                                        Item[] allGemsEquiped = ModTags.GetGems(itemStack);

                                        //on doit appuyer sur shift pou plus de détail
                                        if(Screen.hasShiftDown())
                                        {
                                            for (int u = 0; u < allGemsEquiped.length; u++)
                                            {
                                                int ub = u+1;
                                                Item item = allGemsEquiped[u];

                                                if(item == null)
                                                {
                                                    componentToolTip = createComponentToolTipGem(gemText + ub, "", "attribute.name.rpgnloots.empty", showPercent, chatFormatting);
                                                }
                                                else
                                                {
                                                    componentToolTip = createComponentToolTipGem(gemText + ub, String.valueOf(ModTags.GetGemValue(item)), item, showPercent, chatFormatting);
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
                                    System.out.println("----- Makotache ----- onItemTooltipEvent.CAN_DESTROY");
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

    /*@SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(TestCaveUpdateModMenus.GUITEST_1, Guitest1Screen::new);
        });
    }*/



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

    private static Map.Entry<String, Component> createComponentToolTipGem(String pos, String value, String name_str, boolean showPercent, ChatFormatting chatFormatting)
    {
        value += value.length() > 0 ?  " " : "";
        String showPercentStr = showPercent ? "%" : "";

        Component component = (new TextComponent("  <" + value + showPercentStr).withStyle(chatFormatting)).append((new TranslatableComponent(name_str)).append(">"));

        String str = Language.getInstance().getOrDefault(name_str);
        return Map.entry(pos + str, component);
    }

    private static Map.Entry<String, Component> createComponentToolTipGem(String pos, String value, Item item, boolean showPercent, ChatFormatting chatFormatting)
    {
        return createComponentToolTipGem(pos, value, item.getDescriptionId(), showPercent, chatFormatting);
    }

    private static boolean shouldShowInTooltip(int p_41627_, ItemStack.TooltipPart p_41628_) {
        return (p_41627_ & p_41628_.getMask()) == 0;
    }

    private static int getHideFlags(ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getTag().contains("HideFlags", 99) ? itemStack.getTag().getInt("HideFlags") : itemStack.getItem().getDefaultTooltipHideFlags(itemStack);
    }


    private static float getValueAttributeOfArmor(Attribute attribute, Player player)
    {
        float result = 0;

        Iterable<ItemStack> itemStacks = player.getArmorSlots();

        for (ItemStack itemStack : itemStacks)
        {
            //si l'equipement d'amure est bien une armure et pas un slot vide
            if (itemStack.getItem() instanceof ArmorItem armorItem)
            {
                //récupération des attributeModifiers de l'item en question
                Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(armorItem.getSlot());

                //si attributeModifiers contient "CustomAttributes.DODGE.get())"
                if (attributeModifiers.containsKey(attribute))
                {
                    //on réucpère le montant d'esquive
                    result += StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, attribute);
                }
            }
        }

        return result;
    }

}