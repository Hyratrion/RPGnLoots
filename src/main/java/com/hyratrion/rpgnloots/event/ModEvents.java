package com.hyratrion.rpgnloots.event;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
import com.hyratrion.rpgnloots.item.ModItems;
import com.hyratrion.rpgnloots.util.ModTags;
import com.hyratrion.rpgnloots.util.StaticClass;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

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
                System.out.println("----- Makotache ----- itemStack.getTag().toString() => " + itemStack.getTag().toString());
            }*/

            //on vérifie si "attributeModifiers" contiens
            //"Attributes.ATTACK_DAMAGE" ET "CustomAttributes.CRITICAL_CHANCE"
            if(attributeModifiers.containsKey(Attributes.ATTACK_DAMAGE) &&
                    attributeModifiers.containsKey(CustomAttributes.CRITICAL_CHANCE.get()))
            {
                //récupération des chances de critiques
                float criticalChanceBase = StaticClass.GetValueFromAttributeModifier(attributeModifiers, CustomAttributes.CRITICAL_CHANCE.get());
                //System.out.println("----- Makotache ----- base crit chance " + criticalChanceBase);


                String[] allGemsEquiped = ModTags.GetGemTags(itemStack);
                //System.out.println("----- Makotache ----- itemStack.getTag() => "+ itemStack.getTag());
                System.out.println("----- Makotache ----- allGemsEquiped.length => "+ allGemsEquiped.length);

                if(ModTags.HaveGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_CRITICAL_CHANCE))
                {
                    criticalChanceBase += ModTags.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_CRITICAL_CHANCE);
                    System.out.println("----- Makotache ----- base +gem crit chance " + criticalChanceBase);
                }
                //récupération de la valeur de chance d'appliquer un critique
                float criticalChanceRNG = rand.nextFloat( 100 );

                //on check notre chance de faire un critique
                if(criticalChanceRNG < criticalChanceBase)
                {
                    //System.out.println("----- Makotache ----- crit EXISTANT");

                    //récupération du multiplicateur des dégâts crtitique de minecraft Vanilla
                    float criticalDamage = event.getOldDamageModifier();
                    //System.out.println("----- Makotache ----- crit base " + criticalDamage);

                    if(ModTags.HaveGemOfType(allGemsEquiped, ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE))
                    {
                        criticalDamage += ModTags.GetGemTotalValueOfType(itemStack, ModTags.Items.GEM_TYPE_CRITICAL_DAMAGE) / 100;
                        //System.out.println("----- Makotache ----- base +gem crit damage " + criticalDamage);
                    }

                    //SI on possède "CustomAttributes.CRITICAL_DAMAGE"
                    if(attributeModifiers.containsKey(CustomAttributes.CRITICAL_DAMAGE.get()))
                    {
                        //on ajoute plus de dégâts critique
                        criticalDamage += StaticClass.GetValueFromAttributeModifier(attributeModifiers, CustomAttributes.CRITICAL_DAMAGE.get()) / 100;
                        //System.out.println("----- Makotache ----- nouveau crit  " + criticalDamage);
                    }

                    //on change la valeur des dégâts
                    event.setDamageModifier(criticalDamage);
                    event.setResult(Event.Result.ALLOW);

                    //System.out.println("----- Makotache ----- Degats suivant => " + criticalDamage);
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

                //réucpération de l'entitié qui INFLIGE les dégats
                Entity entity = event.getSource().getDirectEntity();

                boolean dodgeDone = false;
                float amountDodge = 0;

                //esquive
                for (ItemStack itemStack : itemStacks)
                {
                    //si l'equipement d'amure est bien une armure et pas un slot vide
                    if (itemStack.getItem() instanceof ArmorItem armorItem)
                    {
                        //System.out.println("Get equipement slot --> " + armorItem.getSlot());
                        //récupération des attributeModifiers de l'item en question
                        Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(armorItem.getSlot());

                        //si attributeModifiers contient "CustomAttributes.DODGE.get())"
                        if (attributeModifiers.containsKey(CustomAttributes.DODGE.get()))
                        {
                            //on réucpère le montant d'esquive
                            amountDodge += StaticClass.GetValueFromAttributeModifier(attributeModifiers, CustomAttributes.DODGE.get());
                        }
                    }
                }

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
                                amountReflectedDamagePercent += StaticClass.GetValueFromAttributeModifier(attributeModifiers, CustomAttributes.REFLECT_DAMAGE_PERCENT.get()) / 100;
                            }

                            //BRUTE
                            //si l'armure possède "CustomAttributes.REFLECT_DAMAGE_RAW"
                            if (attributeModifiers.containsKey(CustomAttributes.REFLECT_DAMAGE_RAW.get()))
                            {
                                amountReflectedDamageRaw += StaticClass.GetValueFromAttributeModifier(attributeModifiers, CustomAttributes.REFLECT_DAMAGE_RAW.get());
                            }
                        }
                    }

                    //si on a un renvoie de dégâts
                    if(amountReflectedDamagePercent > 0 || amountReflectedDamageRaw > 0)
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
                ItemStack itemStack = player.getMainHandItem();

                Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                //System.out.println("----- Makotache ----- bobo du mob => " +event.getAmount());

                //vol de vie
                if(attributeModifiers.containsKey(CustomAttributes.LIFE_LEECH_PERCENT.get()))
                {
                    float amountLifeLeechPercent = (float)attributeModifiers.get(CustomAttributes.LIFE_LEECH_PERCENT.get()).stream().findFirst().get().getAmount() / 100;

                    //System.out.println("----- Makotache ----- pourcentage de vole de vie => " + amountLifeLeechPercent);

                    amountLifeLeechPercent = amountLifeLeechPercent * event.getAmount();

                    player.heal(amountLifeLeechPercent);

                    //System.out.println("----- Makotache ----- vie regen => " + amountLifeLeechPercent);
                }

            }

        }
        if (!event.getEntity().level.isClientSide())
        {
            if (event.getSource().getDirectEntity() instanceof Player player)
            {
                ItemStack itemStack = player.getMainHandItem();

                Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                //System.out.println("----- Makotache ----- bobo du mob => " +event.getAmount());

                Component displayname = itemStack.getDisplayName();
                //System.out.println("----- Makotache ----- displayname => " + displayname);
                String DescriptionId = itemStack.getDescriptionId();
                //System.out.println("----- Makotache ----- DescriptionId => " + DescriptionId);

                if(attributeModifiers.containsKey(CustomAttributes.LIFE_LEECH_RAW.get()))
                {
                    float amountLifeLeechraw = (float)attributeModifiers.get(CustomAttributes.LIFE_LEECH_RAW.get()).stream().findFirst().get().getAmount();

                    //System.out.println("----- Makotache ----- pourcentage de vole de vie => " + amountLifeLeechraw);

                    player.heal(amountLifeLeechraw);

                    //System.out.println("----- Makotache ----- vie regen => " + amountLifeLeechraw);
                }

            }

        }
    }
    /* degats infligé a une entité
    * class event => inflige degats, moment precis, methode
    *
    * AttackEntityEvent => avant, start attack(), attack()
    * LivingAttackEvent => avant, montant sans prendre en compte les resistances et crit de base, attack()
    * CriticalHitEvent => avant, calcule crit, attack
    * LivingDamageEvent => avant, montant en prenant en compte les resistances et crit de base, attack()
    *
    *
    * autre ?
    * LivingHurtEvent => avant, ?, actuallyHurt()
     *
     * */

    //LivingAttackEvent
}