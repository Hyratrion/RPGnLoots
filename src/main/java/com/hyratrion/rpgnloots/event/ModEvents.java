package com.hyratrion.rpgnloots.event;

import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.CustomAttributes;
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
                float criticalChanceBase = (float)attributeModifiers.get(CustomAttributes.CRITICAL_CHANCE.get()) .stream().findFirst().get().getAmount();

                //récupération de la valeur de chance d'appliquer un critique
                float criticalChanceRNG = rand.nextFloat( 100 );

                //on check notre chance de faire un critique
                if(criticalChanceRNG < criticalChanceBase)
                {
                    //System.out.println("----- Makotache ----- crit EXISTANT");

                    //récupération du multiplicateur des dégâts crtitique de minecraft Vanilla
                    float criticalDamage = event.getOldDamageModifier();
                    //System.out.println("----- Makotache ----- crit base " + degatsCritique);

                    //SI on possède "CustomAttributes.CRITICAL_DAMAGE"
                    if(attributeModifiers.containsKey(CustomAttributes.CRITICAL_DAMAGE.get()))
                    {
                        //on ajoute plus de dégâts critique
                        criticalDamage += (float)attributeModifiers.get(CustomAttributes.CRITICAL_DAMAGE.get()).stream().findFirst().get().getAmount() / 100;
                        //System.out.println("----- Makotache ----- nouveau crit  " + degatsCritique);
                    }

                    //on change la valeur des dégâts
                    event.setDamageModifier(criticalDamage);
                    event.setResult(Event.Result.ALLOW);

                    //System.out.println("----- Makotache ----- Degats suivant => " + degatsCritique);
                }
                /*else
                {
                    //System.out.println("----- Makotache ----- AUCUN critique");
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
            //si l'entité qui inflige des dégâts est un joueur
            if (event.getEntity() instanceof Player player)
            {
                //on récupère tous les equipement dans les slots d'armure du joueur
                Iterable<ItemStack> itemStacks = player.getArmorSlots();
                float amountDodge = 0;


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
                            amountDodge += (float)attributeModifiers.get(CustomAttributes.DODGE.get()).stream().findFirst().get().getAmount();
                        }

                        boolean dodgeDone = false;
                        //si on a un montant d'esquive
                        if (amountDodge > 0)
                        {
                            //System.out.println("amout dodge -->" + amountDodge);
                            //on test notre chance d'esquive
                            float chanceDodge = rand.nextInt(100);
                            if (chanceDodge < amountDodge)
                            {
                                dodgeDone = true;
                                event.setCanceled(true);
                            }
                        }
                        if (!dodgeDone && attributeModifiers.containsKey(CustomAttributes.REFLECT_DAMAGE_PERCENT.get()))
                        {
                            float amountReflectedDamagePercent = (float)attributeModifiers.get(CustomAttributes.REFLECT_DAMAGE_PERCENT.get()).stream().findFirst().get().getAmount() / 100;

                            Entity entity = event.getSource().getDirectEntity();

                            amountReflectedDamagePercent = amountReflectedDamagePercent * event.getAmount();

                            entity.hurt(DamageSource.playerAttack(player),amountReflectedDamagePercent);

                        }
                        if (!dodgeDone && attributeModifiers.containsKey(CustomAttributes.REFLECT_DAMAGE_RAW.get()))
                        {
                            float amountReflectedDamagePercent = (float) attributeModifiers.get(CustomAttributes.REFLECT_DAMAGE_RAW.get()).stream().findFirst().get().getAmount();

                            Entity entity = event.getSource().getDirectEntity();

                            entity.hurt(DamageSource.playerAttack(player), amountReflectedDamagePercent);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamageEvent(LivingDamageEvent event)
    {
        if (!event.getEntity().level.isClientSide())
        {
            if (event.getSource().getDirectEntity() instanceof Player player)
            {
                ItemStack itemStack = player.getMainHandItem();

                Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                System.out.println("----- Makotache ----- bobo du mob => " +event.getAmount());

                if(attributeModifiers.containsKey(CustomAttributes.LIFE_LEECH_PERCENT.get()))
                {
                    float amountLifeLeechPercent = (float)attributeModifiers.get(CustomAttributes.LIFE_LEECH_PERCENT.get()).stream().findFirst().get().getAmount() / 100;

                    System.out.println("----- Makotache ----- pourcentage de vole de vie => " + amountLifeLeechPercent);

                    amountLifeLeechPercent = amountLifeLeechPercent * event.getAmount();

                    player.heal(amountLifeLeechPercent);

                    System.out.println("----- Makotache ----- vie regen => " + amountLifeLeechPercent);
                }

            }

        }
        if (!event.getEntity().level.isClientSide())
        {
            if (event.getSource().getDirectEntity() instanceof Player player)
            {
                ItemStack itemStack = player.getMainHandItem();

                Multimap<Attribute, AttributeModifier> attributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                System.out.println("----- Makotache ----- bobo du mob => " +event.getAmount());

                Component displayname = itemStack.getDisplayName();
                System.out.println("----- Makotache ----- displayname => " + displayname);
                String DescriptionId = itemStack.getDescriptionId();
                System.out.println("----- Makotache ----- DescriptionId => " + DescriptionId);

                if(attributeModifiers.containsKey(CustomAttributes.LIFE_LEECH_RAW.get()))
                {
                    float amountLifeLeechraw = (float)attributeModifiers.get(CustomAttributes.LIFE_LEECH_RAW.get()).stream().findFirst().get().getAmount();

                    System.out.println("----- Makotache ----- pourcentage de vole de vie => " + amountLifeLeechraw);

                    player.heal(amountLifeLeechraw);

                    System.out.println("----- Makotache ----- vie regen => " + amountLifeLeechraw);
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