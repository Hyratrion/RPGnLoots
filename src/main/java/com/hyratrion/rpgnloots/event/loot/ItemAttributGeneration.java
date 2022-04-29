package com.hyratrion.rpgnloots.event.loot;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.hyratrion.rpgnloots.event.loot.CustomAttributes.*;

public class ItemAttributGeneration
{
    static Random rand = new Random();
    static int gemSlot = 0;

    //region Tableaux des items par tiers
    /**
     * Tableau des items tier 1
     */
    static Item[] itemTier1 = new Item[]{
            Items.TURTLE_HELMET,
            Items.LEATHER_HELMET,
            Items.LEATHER_CHESTPLATE,
            Items.LEATHER_LEGGINGS,
            Items.LEATHER_BOOTS,
            Items.BOW,
            Items.CROSSBOW,
            Items.WOODEN_SWORD,
            Items.WOODEN_AXE,
            Items.WOODEN_HOE,
            Items.WOODEN_PICKAXE,
            Items.WOODEN_SHOVEL,
            Items.FISHING_ROD
    };
    /**
     * Tableau des items tier 2
     */
    static Item[] itemTier2 = new Item[]{
            Items.IRON_HELMET,
            Items.IRON_CHESTPLATE,
            Items.IRON_LEGGINGS,
            Items.IRON_BOOTS,
            Items.CHAINMAIL_HELMET,
            Items.CHAINMAIL_CHESTPLATE,
            Items.CHAINMAIL_LEGGINGS,
            Items.CHAINMAIL_BOOTS,
            Items.SHIELD,
            Items.IRON_SWORD,
            Items.IRON_AXE,
            Items.IRON_HOE,
            Items.IRON_PICKAXE,
            Items.IRON_SHOVEL,
            Items.SHEARS

    };
    /**
     * Tableau des items tier 3
     */
    static Item[] itemTier3 = new Item[]{
            Items.GOLDEN_HELMET,
            Items.GOLDEN_CHESTPLATE,
            Items.GOLDEN_LEGGINGS,
            Items.GOLDEN_BOOTS,
            Items.GOLDEN_SWORD,
            Items.GOLDEN_AXE,
            Items.GOLDEN_HOE,
            Items.GOLDEN_PICKAXE,
            Items.GOLDEN_SHOVEL,
            Items.TRIDENT
    };
    /**
     * Tableau des items tier 4
     */
    static Item[] itemTier4 = new Item[]{
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Items.ELYTRA,
            Items.DIAMOND_SWORD,
            Items.DIAMOND_AXE,
            Items.DIAMOND_HOE,
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_SHOVEL
    };
    /**
     * Tableau des items tier 5
     */
    static Item[] itemTier5 = new Item[]{
            Items.NETHERITE_HELMET,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS,
            Items.NETHERITE_SWORD,
            Items.NETHERITE_AXE,
            Items.NETHERITE_HOE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_SHOVEL
    };
    /**
     * Tableau contenant les tableaux des itemsTier
     */
    public static Item[][] lesItemsTier = {
            itemTier1,
            itemTier2,
            itemTier3,
            itemTier4,
            itemTier5
    };
    //endregion
    //region Tableaux des attributs par type d'items
    /**
     * Liste des attributs pour les items de type Sword
     */
    static final Attribute[] attributsItemSword = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Helmet
     */
    static final Attribute[] attributsItemHelmet = new Attribute[]{
            CustomAttributes.REINFORCED.get(),
            Attributes.ARMOR,
            Attributes.ARMOR_TOUGHNESS,
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.REFLECT_DAMAGE_PERCENT.get(),
            CustomAttributes.REFLECT_DAMAGE_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get(),
            CustomAttributes.DODGE.get()
    };
    /**
     * Liste des attributs pour les items de type Chestplate
     */
    static final Attribute[] attributsItemChestplate = new Attribute[]{
            CustomAttributes.REINFORCED.get(),
            Attributes.ARMOR,
            Attributes.ARMOR_TOUGHNESS,
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.REFLECT_DAMAGE_PERCENT.get(),
            CustomAttributes.REFLECT_DAMAGE_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get(),
            CustomAttributes.DODGE.get()
    };
    /**
     * Liste des attributs pour les items de type Leggings
     */
    static final Attribute[] attributsItemLeggings = new Attribute[]{
            CustomAttributes.REINFORCED.get(),
            Attributes.ARMOR,
            Attributes.ARMOR_TOUGHNESS,
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.REFLECT_DAMAGE_PERCENT.get(),
            CustomAttributes.REFLECT_DAMAGE_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get(),
            CustomAttributes.DODGE.get()
    };
    /**
     * Liste des attributs pour les items de type Boots
     */
    static final Attribute[] attributsItemBoots = new Attribute[]{
            CustomAttributes.REINFORCED.get(),
            Attributes.ARMOR,
            Attributes.ARMOR_TOUGHNESS,
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.REFLECT_DAMAGE_PERCENT.get(),
            CustomAttributes.REFLECT_DAMAGE_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get(),
            CustomAttributes.DODGE.get()
    };
    /**
     * Liste des attributs pour les items de type Elytra
     */
    static final Attribute[] attributsItemElytra = new Attribute[]{
            CustomAttributes.REINFORCED.get(),
            Attributes.ARMOR,
            Attributes.ARMOR_TOUGHNESS,
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.REFLECT_DAMAGE_PERCENT.get(),
            CustomAttributes.REFLECT_DAMAGE_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get(),
            CustomAttributes.DODGE.get()
    };
    /**
     * Liste des attributs pour les items de type Pickaxe
     */
    static final Attribute[] attributsItemPickaxe = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Axe
     */
    static final Attribute[] attributsItemAxe = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Shovel
     */
    static final Attribute[] attributsItemShovel = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Hoe
     */
    static final Attribute[] attributsItemHoe = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Bow
     */
    static final Attribute[] attributsItemBow = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Crossbow
     */
    static final Attribute[] attributsItemCrossbow = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Shears
     */
    static final Attribute[] attributsItemShears = new Attribute[]{
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type FishingRod
     */
    static final Attribute[] attributsItemFishingRod = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.CRITICAL_CHANCE.get(),
            CustomAttributes.CRITICAL_DAMAGE.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Trident
     */
    static final Attribute[] attributsItemTrident = new Attribute[]{
            Attributes.ATTACK_DAMAGE,
            Attributes.ATTACK_SPEED,
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.LIFE_LEECH_PERCENT.get(),
            CustomAttributes.LIFE_LEECH_RAW.get(),
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type Shield
     */
    static final Attribute[] attributsItemShield = new Attribute[]{
            CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    //endregion

    /**
     * Permet de retourner un item aléatoire en fonction du tier envoyé
     * @param tier le tier de l'item de vant etre looté
     * @return l'item récupérer aléatoirement
     */
    public static ItemStack GenerateItemAndAttributes(int tier){
        Item[] itemsLootablesParLeTier = new Item[0];
        for(int k = 0; k < tier; k++){
            itemsLootablesParLeTier = (Item[])ArrayUtils.add(itemsLootablesParLeTier,lesItemsTier[k]);
        }
        int nombreAleatoire = rand.nextInt(itemsLootablesParLeTier.length);
        Item itemLooted = itemsLootablesParLeTier[nombreAleatoire];

        //détermination de l'instance de l'item pour générer la liste des attributs
        List<Attribute> attributsTypeItem = new ArrayList<>();
        if(itemLooted instanceof SwordItem){
            attributsTypeItem = Arrays.asList(attributsItemSword);
        } else if (itemLooted instanceof PickaxeItem){
            attributsTypeItem = Arrays.asList(attributsItemPickaxe);
        } else if(itemLooted instanceof AxeItem){
            attributsTypeItem = Arrays.asList(attributsItemAxe);
        } else if (itemLooted instanceof ShovelItem){
            attributsTypeItem = Arrays.asList(attributsItemShovel);
        } else if (itemLooted instanceof HoeItem){
            attributsTypeItem = Arrays.asList(attributsItemHoe);
        } else if (itemLooted instanceof ArmorItem armorItem){
            EquipmentSlot slot = armorItem.getSlot();
            if(slot == EquipmentSlot.HEAD){
                attributsTypeItem = Arrays.asList(attributsItemHelmet);
            } else if (slot == EquipmentSlot.CHEST)
            {
                attributsTypeItem = Arrays.asList(attributsItemChestplate);
            } else if (slot == EquipmentSlot.LEGS)
            {
                attributsTypeItem = Arrays.asList(attributsItemLeggings);
            }else{
                attributsTypeItem = Arrays.asList(attributsItemBoots);
            }
        } else if (itemLooted instanceof ElytraItem){
            attributsTypeItem = Arrays.asList(attributsItemElytra);
        } else if (itemLooted instanceof ShearsItem){
            attributsTypeItem = Arrays.asList(attributsItemShears);
        } else if (itemLooted instanceof ShieldItem){
            attributsTypeItem = Arrays.asList(attributsItemShield);
        } else if (itemLooted instanceof FishingRodItem){
            attributsTypeItem = Arrays.asList(attributsItemFishingRod);
        } else if (itemLooted instanceof BowItem){
            attributsTypeItem = Arrays.asList(attributsItemBow);
        } else if (itemLooted instanceof CrossbowItem){
            attributsTypeItem = Arrays.asList(attributsItemCrossbow);
        } else if (itemLooted instanceof TridentItem){
            attributsTypeItem = Arrays.asList(attributsItemTrident);
        }

        System.err.println("taille liste attribut généré" + attributsTypeItem.size());
        //Crée l'objet d'item à partir de l'item
        ItemStack itemStack = new ItemStack(itemLooted, 1);
        //Bouble sur le nombre d'attribute modifier a ajouter
        for(int k = 0; k < tier; k++){
            nombreAleatoire = rand.nextInt(attributsTypeItem.size());
            Attribute attribute = attributsTypeItem.get(nombreAleatoire);
            attributsTypeItem.remove(nombreAleatoire);
            itemStack = AddAttributesIntoItem(attribute, tier, itemLooted,itemStack);
        }
        //Affectation des gam_Slot en fonction du tier
        if(tier > 3){
            if(tier > 4){
                itemStack.addAttributeModifier(
                        GEM_SLOT.get(),
                        new AttributeModifier(GEM_SLOT_ID, "modifier rpgnloots", gemSlot + 2, AttributeModifier.Operation.ADDITION),
                        itemLooted.getEquipmentSlot(itemStack)
                );
            }else{
                itemStack.addAttributeModifier(
                        GEM_SLOT.get(),
                        new AttributeModifier(GEM_SLOT_ID, "modifier rpgnloots", gemSlot + 1, AttributeModifier.Operation.ADDITION),
                        itemLooted.getEquipmentSlot(itemStack)
                );
            }
        }
        gemSlot = 0;
        return itemStack;
    }

    /**
     * Permet d'ajouter un attribut sur un item
     * @param attribute attribut à ajouter
     * @param tier le tier de l'item qui defini la valeur de modification de l'attribut
     * @param itemLooted item qui est looté
     * @param leItemStack l'objet item de l'item looté
     * @return l'objet item avec le nouvel attribut ajouté
     */
    public static ItemStack AddAttributesIntoItem(Attribute attribute, int tier, Item itemLooted, ItemStack leItemStack){
        float value;
        if(attribute == Attributes.ATTACK_DAMAGE){
            value = (((TieredItem) itemLooted).getTier().getAttackDamageBonus() + 3) * (rand.nextFloat(CustomAttributes.getValuesTierAttackDamage[tier - 1], CustomAttributes.getValuesTierAttackDamage[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            return leItemStack;
        } else if (attribute == ATTACK_SPEED)
        {
            value = (((TieredItem) itemLooted).getTier().getSpeed()) * (rand.nextFloat(CustomAttributes.getValuesTierAttackSpeed[tier - 1], CustomAttributes.getValuesTierAttackSpeed[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            return leItemStack;
        } else if (attribute == ARMOR)
        {
            value = (((ArmorItem) itemLooted).getDefense()) * (rand.nextFloat(CustomAttributes.getValuesTierArmor[tier - 1], CustomAttributes.getValuesTierArmor[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            return leItemStack;
        } else if (attribute == ARMOR_TOUGHNESS)
        {
            value = (((ArmorItem) itemLooted).getToughness()) * (rand.nextFloat(CustomAttributes.getValuesTierArmorToughness[tier - 1], CustomAttributes.getValuesTierArmorToughness[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            return leItemStack;
        } else if (attribute == KNOCKBACK_RESISTANCE)
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierKnockbackResistance[tier - 1], CustomAttributes.getValuesTierKnockbackResistance[tier]) / 100);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            return leItemStack;
        } else if (attribute == LIFE_LEECH_PERCENT.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierLifeLeechPercent[tier - 1], CustomAttributes.getValuesTierLifeLeechPercent[tier]) / 100);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(LIFE_LEECH_PERCENT_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            return leItemStack;
        } else if (attribute == LIFE_LEECH_RAW.get())
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierLifeLeechRaw[tier - 1], CustomAttributes.getValuesTierLifeLeechRaw[tier]);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(LIFE_LEECH_RAW_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            return leItemStack;
        } else if (attribute == REFLECT_DAMAGE_PERCENT.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierReflectDamagePercent[tier - 1], CustomAttributes.getValuesTierReflectDamagePercent[tier]) / 100);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(REFLECT_DAMAGE_PERCENT_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            return leItemStack;
        } else if (attribute == REFLECT_DAMAGE_RAW.get())
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierReflectDamageRaw[tier - 1], CustomAttributes.getValuesTierReflectDamageRaw[tier]);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(REFLECT_DAMAGE_RAW_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            return leItemStack;
        } else if (attribute == CRITICAL_CHANCE.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierCriticalChance[tier - 1], CustomAttributes.getValuesTierCriticalChance[tier]) / 100);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(CRITICAL_CHANCE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            return leItemStack;
        } else if (attribute == CRITICAL_DAMAGE.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierCriticalDamage[tier - 1], CustomAttributes.getValuesTierCriticalDamage[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(CRITICAL_DAMAGE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            return leItemStack;
        } else if (attribute == GEM_LVL_INCREASE.get())
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierGemLevelIncrease[tier - 1], CustomAttributes.getValuesTierGemLevelIncrease[tier]);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(GEM_LVL_INCREASE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    itemLooted.getEquipmentSlot(leItemStack)
            );
            return leItemStack;
        } else if (attribute == MORE_GEM_SLOT.get())
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierMoreGemSlot[tier - 1], CustomAttributes.getValuesTierMoreGemSlot[tier]);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(MORE_GEM_SLOT_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    itemLooted.getEquipmentSlot(leItemStack)
            );
            gemSlot += 1;
            return leItemStack;
        } else if (attribute == DODGE.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierDodge[tier - 1], CustomAttributes.getValuesTierDodge[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(DODGE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            return leItemStack;
        }
        return leItemStack;
    }



}
