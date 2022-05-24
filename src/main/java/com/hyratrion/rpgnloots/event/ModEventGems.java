package com.hyratrion.rpgnloots.event;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import com.hyratrion.rpgnloots.item.Gems;
import com.hyratrion.rpgnloots.util.ModTags;
import com.hyratrion.rpgnloots.util.StaticClass;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.*;

@Mod.EventBusSubscriber(modid = RPGNLOOT.MOD_ID)
public class ModEventGems
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
            Player player = event.getPlayer();
            //récupération de l'item dans main du joueur
            ItemStack itemStack = player.getMainHandItem();

            //récupération des attributeModifiers de l'item
            Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            boolean gemLevelIncrease = attributeModifiers.containsKey(CustomAttributes.GEM_LVL_INCREASE.get());

            //System.out.println("-- RPG&Loots -- player name => " + player.getDisplayName().getString());

            //chance critique

            float criticalChanceAttributeArmor = getValueAttributeOfArmor(CustomAttributes.CRITICAL_CHANCE.get(), player);
            //System.out.println("- RPG&Loots - crit chance attribute armor => " + criticalChanceAttributeArmor);

            float criticalChanceGemArmor = getValueGemOfArmor(ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, player);
            //System.out.println("- RPG&Loots - crit chance gem armor => " + criticalChanceGemArmor);

            float criticalChance = criticalChanceAttributeArmor + criticalChanceGemArmor;

            //récupération des chances de critiques des attributs de l'arme
            if(attributeModifiers.containsKey(CustomAttributes.CRITICAL_CHANCE.get()))
            {
                float criticalChanceAttributeWeapon = StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.CRITICAL_CHANCE.get());
                //System.out.println("- RPG&Loots - crit chance attribute weapon => " + criticalChanceAttributeWeapon);
                criticalChance += criticalChanceAttributeWeapon;
            }


            //récupération des chances de critiques des gems de l'arme
            String[] allGemsEquiped = null;
            if(Gems.HasGemSlot(itemStack))
            {
                allGemsEquiped = Gems.GetGemTags(itemStack);

                if(Gems.HasGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_CRITICAL_CHANCE))
                {
                    float criticalChanceGemWeapon = Gems.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_CRITICAL_CHANCE, gemLevelIncrease);
                    //System.out.println("- RPG&Loots - crit chance gem weapon => " + criticalChanceGemWeapon);
                    criticalChance += criticalChanceGemWeapon;
                }
            }

            //degats critique

            //récupération de la valeur de chance d'appliquer un critique
            float criticalChanceRNG = rand.nextFloat(100);

            float criticalDamage = event.getOldDamageModifier() - 1;

            float valueToReturnForDamage;

            System.out.println("- RPG&Loots - total crit chance => " + criticalChance);
            //on check notre chance de faire un critique
            if(criticalChanceRNG < criticalChance || criticalDamage > 0)
            {
                System.out.println("- RPG&Loots - crit fait" );
                int multiplicator = 1;
                if(criticalChance >= 100)
                {
                    String critChanceStr = String.valueOf(criticalChance);
                    int posComma = critChanceStr.indexOf(".");//par ce que 94 % sure de micro
                    posComma = posComma != -1 ? posComma - 2 : 1;
                    multiplicator += Integer.valueOf(critChanceStr.substring(0, posComma)) - 1; // ajoute +1 au multiplicateur critique par centaine
                    //multiplicator += (Integer.valueOf(critChanceStr.substring(0, posComma)) / 2); // ajoute +0.5 au multiplicateur critique par centaine
                    System.out.println("- RPG&Loots - before multiplicator => " + multiplicator);

                    criticalChanceRNG = rand.nextFloat(100);
                    System.out.println("- RPG&Loots - criticalChanceRNG => " + criticalChanceRNG);

                    float calc = criticalChance - 100 * multiplicator;
                    System.out.println("- RPG&Loots - calc => " + calc);
                    if(criticalChanceRNG < calc)
                    {
                        multiplicator += 1; // ajoute +1 au multiplicateur critique
                        //multiplicator += 0.5f; // ajoute +0.5 au multiplicateur critique
                    }
                }
               System.out.println("- RPG&Loots - after multiplicator => " + multiplicator);


                float criticalDamageAttributeArmor = getValueAttributeOfArmor(CustomAttributes.CRITICAL_DAMAGE.get(), player) / 100;
               //System.out.println("- RPG&Loots - crit damage attribute armor => " + criticalDamageAttributeArmor);
                criticalDamage += criticalDamageAttributeArmor;

                float criticalDamageGemArmor = getValueGemOfArmor(ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, player) / 100;
               //System.out.println("- RPG&Loots - crit damage gem armor => " + criticalDamageGemArmor);
                criticalDamage += criticalDamageGemArmor;


                //récupération des degats de critiques des attributes de l'arme
                if(attributeModifiers.containsKey(CustomAttributes.CRITICAL_DAMAGE.get()))
                {
                    //on ajoute plus de dégâts critique
                    float criticalDamgeWeapon = StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, CustomAttributes.CRITICAL_DAMAGE.get()) / 100;
                   //System.out.println("- RPG&Loots - crit damage attribute weapon => " + criticalDamgeWeapon);
                    criticalDamage += criticalDamgeWeapon;
                }


                //récupération des degats de critiques des gems de l'arme
                if(Gems.HasGemSlot(itemStack) && Gems.HasGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE))
                {
                    float criticalDamageGem = Gems.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE, gemLevelIncrease) / 100;
                   //System.out.println("- RPG&Loots - crit damage gem weapon => " + criticalDamageGem);
                    criticalDamage += criticalDamageGem;
                }

                System.out.println("- RPG&Loots - critical Damage => " + criticalDamage);

                //cacule final ou casi final du multiplicateur
                valueToReturnForDamage = criticalDamage * multiplicator + 1;
            }
            else
            {
                valueToReturnForDamage = 1;
                //System.out.println("----- Makotache ----- AUCUN critique");
            }

            //récupération des degats des gems de l'arme
            if(Gems.HasGemSlot(itemStack) && Gems.HasGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_DAMAGE))
            {
                //calcul du nombre de dégats a infligé en plus fonction de la gem de degats
                //on doit utiliser le//Systeme de calcule des degats de minecraft vanilla pour savoir PAR combien on devra multiplier notre multiplicateur final
                float baseDamage = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);

                float f2 = player.getAttackStrengthScale(0.5F);
                baseDamage *= 0.2F + f2 * f2 * 0.8F;
                //System.out.println("- RPG&Loots - baseDamage => " + baseDamage);

                //ce n'est que maintenant que l'on met la valeur de notre degats
                float damageGem = Gems.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_DAMAGE, gemLevelIncrease) / 100;
                //System.out.println("- RPG&Loots - damage gem weapon => " + damageGem);

                float realBaseDamage = baseDamage * (damageGem + 1);
                //System.out.println("- RPG&Loots - realBaseDamage => " + realBaseDamage);

                float finalDamage = realBaseDamage * valueToReturnForDamage;
                //System.out.println("- RPG&Loots - finalDamage => " + finalDamage);

                valueToReturnForDamage = finalDamage / baseDamage;
            }

            if(valueToReturnForDamage != 1)
            {
                //on change la valeur des dégâts
                event.setDamageModifier(valueToReturnForDamage);
                event.setResult(Event.Result.ALLOW);
                //System.out.println("- RPG&Loots - valueToReturnForDamage => " + valueToReturnForDamage);
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

                System.out.println("-- RPG&Loots -- bobo of " + event.getEntity().getType().getRegistryName().getPath() + " => " + event.getAmount());

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

                //Component displayname = itemStack.getDisplayName();
                //System.out.println("----- Makotache ----- displayname => " + displayname);
                //String DescriptionId = itemStack.getDescriptionId();
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


    private static float getValueAttributeOfArmor(Attribute attribute, Player player)
    {
        float result = 0;

        ItemStack offHand = player.getOffhandItem();

        if(!offHand.isEmpty())
        {
            Multimap<Attribute, AttributeModifier> attributeModifiers = offHand.getAttributeModifiers(EquipmentSlot.OFFHAND);

            //si attributeModifiers contient "CustomAttributes.DODGE.get())"
            if (attributeModifiers.containsKey(attribute))
            {
                //on réucpère le montant d'esquive
                result += StaticClass.GetValueFromAttributeModifierMap(attributeModifiers, attribute);
            }
        }

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

    private static float getValueGemOfArmor(TagKey<Item> gemType, Player player)
    {
        float result = 0;

        ItemStack offHand = player.getOffhandItem();


        if(!offHand.isEmpty() && Gems.HasGemSlot(offHand))
        {
            String[] allGemsEquiped = Gems.GetGemTags(offHand);


            if(Gems.HasGemOfType(allGemsEquiped, gemType))
            {
                Multimap<Attribute, AttributeModifier> attributeModifiers = offHand.getAttributeModifiers(EquipmentSlot.OFFHAND);
                boolean gemLevelIncrease = attributeModifiers.containsKey(CustomAttributes.GEM_LVL_INCREASE.get());

                result += Gems.GetGemTotalValueOfType(offHand, gemType, gemLevelIncrease);
            }
        }

        Iterable<ItemStack> itemStacks = player.getArmorSlots();

        for (ItemStack itemStack : itemStacks)
        {
            //si l'equipement d'amure est bien une armure et pas un slot vide
            if (itemStack.getItem() instanceof ArmorItem armorItem)
            {
                if(Gems.HasGemSlot(itemStack))
                {
                    String[] allGemsEquiped = Gems.GetGemTags(itemStack);

                    if(Gems.HasGemOfType(allGemsEquiped, gemType))
                    {
                        Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(armorItem.getSlot());
                        boolean gemLevelIncrease = attributeModifiers.containsKey(CustomAttributes.GEM_LVL_INCREASE.get());

                        result += Gems.GetGemTotalValueOfType(itemStack, gemType, gemLevelIncrease);
                    }
                }
            }
        }

        return result;
    }

}