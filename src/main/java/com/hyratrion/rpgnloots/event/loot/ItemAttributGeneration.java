package com.hyratrion.rpgnloots.event.loot;

import com.google.common.base.Equivalence;
import com.google.common.collect.Multimap;
import com.hyratrion.rpgnloots.util.ModStats;
import com.hyratrion.rpgnloots.util.ModTags;
import net.minecraft.nbt.StringTag;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.util.*;

import static com.hyratrion.rpgnloots.event.loot.CustomAttributes.*;
import static net.minecraft.world.item.Tiers.*;

public class ItemAttributGeneration
{
    static Random rand = new Random();
    //static int gemSlot = 0;

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
//            Items.BOW,
//            Items.CROSSBOW,
            Items.WOODEN_SWORD,
            Items.WOODEN_AXE,
            Items.WOODEN_HOE,
            Items.WOODEN_PICKAXE,
            Items.WOODEN_SHOVEL,
            //Items.FISHING_ROD
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
            //Items.SHEARS

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
            Items.GOLDEN_SHOVEL//,
 //           Items.TRIDENT
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
//            CustomAttributes.REINFORCED.get(),
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
//            CustomAttributes.REINFORCED.get(),
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
//            CustomAttributes.REINFORCED.get(),
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
 //           CustomAttributes.REINFORCED.get(),
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
 //           CustomAttributes.REINFORCED.get(),
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
 //           CustomAttributes.REINFORCED.get(),
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
  //          CustomAttributes.REINFORCED.get(),
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
  //          CustomAttributes.REINFORCED.get(),
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
  //          CustomAttributes.REINFORCED.get(),
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
   //         CustomAttributes.REINFORCED.get(),
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
   //         CustomAttributes.REINFORCED.get(),
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
 //           CustomAttributes.REINFORCED.get(),
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
   //         CustomAttributes.REINFORCED.get(),
            Attributes.KNOCKBACK_RESISTANCE,
            CustomAttributes.GEM_LVL_INCREASE.get(),
            CustomAttributes.MORE_GEM_SLOT.get()
    };
    /**
     * Liste des attributs pour les items de type FishingRod
     */
    static final Attribute[] attributsItemFishingRod = new Attribute[]{
    //        CustomAttributes.REINFORCED.get(),
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
  //          CustomAttributes.REINFORCED.get(),
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
    //        CustomAttributes.REINFORCED.get(),
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
        List<Item> itemsLootablesParLeTier = new ArrayList<>();
        for(int k = 0; k < tier; k++){
            itemsLootablesParLeTier.addAll(Arrays.stream(lesItemsTier[k]).toList());
        }
        int nombreAleatoire = rand.nextInt(itemsLootablesParLeTier.size());
        Item itemLooted = itemsLootablesParLeTier.get(nombreAleatoire);

        //détermination de l'instance de l'item pour générer la liste des attributs
        List<Attribute> attributsTypeItem = new ArrayList<>();
        if(itemLooted instanceof SwordItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemSword));
        } else if (itemLooted instanceof PickaxeItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemPickaxe));
        } else if(itemLooted instanceof AxeItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemAxe));
        } else if (itemLooted instanceof ShovelItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemShovel));
        } else if (itemLooted instanceof HoeItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemHoe));
        } else if (itemLooted instanceof ArmorItem armorItem){
            EquipmentSlot slot = armorItem.getSlot();
            if(slot == EquipmentSlot.HEAD){
                attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemHelmet));
            } else if (slot == EquipmentSlot.CHEST)
            {
                attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemChestplate));
            } else if (slot == EquipmentSlot.LEGS)
            {
                attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemLeggings));
            }else{
                attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemBoots));
            }
        } else if (itemLooted instanceof ElytraItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemElytra));
        } else if (itemLooted instanceof ShearsItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemShears));
        } else if (itemLooted instanceof ShieldItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemShield));
        } else if (itemLooted instanceof FishingRodItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemFishingRod));
        } else if (itemLooted instanceof BowItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemBow));
        } else if (itemLooted instanceof CrossbowItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemCrossbow));
        } else if (itemLooted instanceof TridentItem){
            attributsTypeItem = new ArrayList<>(Arrays.asList(attributsItemTrident));
        }

        ItemStack itemStack = new ItemStack(itemLooted, 1);

        int gemSlot = 0;
        //Boucle sur le nombre d'attribut modifier à ajouter
        for(int k = 0; k < tier; k++)
        {
            if(attributsTypeItem.size() <= 0)
            {
                break;
            }
            nombreAleatoire = rand.nextInt(attributsTypeItem.size());
            Attribute attribute = attributsTypeItem.get(nombreAleatoire);

            /*Débug
            System.out.println("_________________________attributsTypeItem.size(): " + attributsTypeItem.size());
            System.out.println("_________________________attribute.getRegistryName(): " + attribute.getRegistryName());

             */
            attributsTypeItem.remove(nombreAleatoire);
            Object[] result = AddAttributesIntoItem(attribute, tier, itemLooted,itemStack);
            itemStack = (ItemStack)result[0];
            gemSlot += (int)result[1];
        }

        //Crée l'objet d'item à partir de l'item
        if(itemLooted instanceof ArmorItem armorItem)
        {
            Multimap<Attribute, AttributeModifier> attributeAttributeModifiers = itemStack.getAttributeModifiers(armorItem.getSlot());
            if(!attributeAttributeModifiers.containsKey(ARMOR))
            {
                itemStack.addAttributeModifier(
                        ARMOR,
                        new AttributeModifier("modifier rpgnloots", armorItem.getDefense(), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
            }
            if(armorItem.getToughness() > 0 && !attributeAttributeModifiers.containsKey(ARMOR_TOUGHNESS))
            {
                itemStack.addAttributeModifier(
                        ARMOR_TOUGHNESS,
                        new AttributeModifier("modifier rpgnloots", armorItem.getToughness(), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
            }
            if (armorItem.getMaterial().getKnockbackResistance() > 0 && !attributeAttributeModifiers.containsKey(KNOCKBACK_RESISTANCE))
            {
                itemStack.addAttributeModifier(
                        KNOCKBACK_RESISTANCE,
                        new AttributeModifier("modifier rpgnloots", armorItem.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
            }

        }
        else if (itemLooted instanceof TieredItem tieredItem)
        {
            Multimap<Attribute, AttributeModifier> attributeAttributeModifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            if(!attributeAttributeModifiers.containsKey(ATTACK_DAMAGE))
            {
                itemStack.addAttributeModifier(
                        ATTACK_DAMAGE,
                        new AttributeModifier("modifier rpgnloots", tieredItem.getTier().getAttackDamageBonus() + 3, AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
            }

            if(!attributeAttributeModifiers.containsKey(ATTACK_SPEED))
            {
                float value_attack_speed = 0;


                if(itemLooted instanceof PickaxeItem)
                {
                    value_attack_speed = -2.8f;
                }
                else if (itemLooted instanceof SwordItem)
                {
                    value_attack_speed =  -2.4f;
                }
                else if (itemLooted instanceof ShovelItem)
                {
                    value_attack_speed = 3f;
                }
                else if (itemLooted instanceof AxeItem axeItem)
                {
                    if(axeItem.getTier() == WOOD)
                    {
                        value_attack_speed = -3.2f;
                    }
                    else if (axeItem.getTier() == STONE)
                    {
                        value_attack_speed = -3f;
                    }
                    else if (axeItem.getTier() == GOLD)
                    {
                        value_attack_speed = -3f;
                    }
                    else if (axeItem.getTier() == IRON)
                    {
                        value_attack_speed = -3.1f;
                    }
                    else if (axeItem.getTier() == DIAMOND)
                    {
                        value_attack_speed = -3f;
                    }
                    else if (axeItem.getTier() == NETHERITE)
                    {
                        value_attack_speed = -3f;
                    }
                }
                else if (itemLooted instanceof HoeItem hoeItem)
                {
                    if(hoeItem.getTier() == WOOD)
                    {
                        value_attack_speed = -3f;
                    }
                    else if (hoeItem.getTier() == STONE)
                    {
                        value_attack_speed = -2f;
                    }
                    else if (hoeItem.getTier() == GOLD)
                    {
                        value_attack_speed = -3f;
                    }
                    else if (hoeItem.getTier() == IRON)
                    {
                        value_attack_speed = -1f;
                    }
                    else if (hoeItem.getTier() == DIAMOND)
                    {
                        value_attack_speed = 0f;
                    }
                    else if (hoeItem.getTier() == NETHERITE)
                    {
                        value_attack_speed = 0f;
                    }
                }

                itemStack.addAttributeModifier(
                        ATTACK_SPEED,
                        new AttributeModifier("modifier rpgnloots", value_attack_speed, AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
            }

        }

        //Ajout des emplacements des gemslot supplémentaires
        if(tier > 2)
        {
            if (tier > 4)
            {
                gemSlot += 2;
            } else
            {
                gemSlot += 1;
            }
        }
        //Ajout des gemslot sur l'item
        if(gemSlot > 0){

            itemStack.addAttributeModifier(
                    GEM_SLOT.get(),
                    new AttributeModifier(GEM_SLOT_ID, "modifier rpgnloots", gemSlot, AttributeModifier.Operation.ADDITION),
                    itemLooted.getEquipmentSlot(itemStack)
            );
            for(int i = 0; i < gemSlot; i++)
            {
                ModTags.AddGemSlot(itemStack, ModTags.DEFAULT_TAG_VALUE);
            }
        }

        itemStack.addTagElement(ModTags.RPGNLOOT_MODIFIER, StringTag.valueOf(tier + ""));

        //Debug
        //System.out.println("_________________________itemStack.getTag(): " + itemStack.getTag());


        return itemStack;
    }

    /**
     * Permet d'ajouter un attribut sur un item
     * @param attribute attribut à ajouter
     * @param tier le tier de l'item qui defini la valeur de modification de l'attribut
     * @param itemLooted item qui est looté
     * @param leItemStack l'objet item de l'item looté
     * @return un tableau d'objet avec l'item stack et le nombre de gemslot à ajouter
     */
    public static Object[] AddAttributesIntoItem(Attribute attribute, int tier, Item itemLooted, ItemStack leItemStack)
    {
        float value = 0;
        int gemSlot = 0;
        Object[] result = new Object[2];

        UUID uuid = null;

        if(attribute == Attributes.ATTACK_DAMAGE)
        {
            //value = (((TieredItem) itemLooted).getTier().getAttackDamageBonus() + 3) * (rand.nextFloat(CustomAttributes.getValuesTierAttackDamage[tier - 1], CustomAttributes.getValuesTierAttackDamage[tier]) / 100 + 1);
            value = (((TieredItem) itemLooted).getTier().getAttackDamageBonus() + 3) * GetValueFromTier(getValuesTierAttackDamage, tier, true);

        }
        else if (attribute == ATTACK_SPEED)
        {
            //value = (((TieredItem) itemLooted).getTier().getSpeed()) * (rand.nextFloat(CustomAttributes.getValuesTierAttackSpeed[tier - 1], CustomAttributes.getValuesTierAttackSpeed[tier]) / 100 + 1);
            value = (((TieredItem) itemLooted).getTier().getSpeed()) * GetValueFromTier(getValuesTierAttackSpeed, tier, true);
        }
        else if (attribute == ARMOR)
        {
            //value = (rand.nextFloat(CustomAttributes.getValuesTierArmor[tier - 1], CustomAttributes.getValuesTierArmor[tier]) / 100 + 1);
            value = GetValueFromTier(getValuesTierArmor, tier, true);

            if(itemLooted instanceof ArmorItem armorItem && armorItem.getDefense() != 0)
            {
                value = (armorItem.getDefense() * (value + 1));
            }
        }
        else if (attribute == ARMOR_TOUGHNESS)
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierArmorToughness[tier - 1], CustomAttributes.getValuesTierArmorToughness[tier]) / 100;
            //value = GetValueFromTier(getValuesTierArmorToughness, tier, true); on ne doit pas mettre le "+ 1"

            if(itemLooted instanceof ArmorItem armorItem && armorItem.getToughness() != 0)
            {
                value = (armorItem.getToughness() * (value + 1));
            }
        }
        else if (attribute == KNOCKBACK_RESISTANCE)
        {
            //value = (rand.nextFloat(CustomAttributes.getValuesTierKnockbackResistance[tier - 1], CustomAttributes.getValuesTierKnockbackResistance[tier]));
            value = GetValueFromTier(getValuesTierKnockbackResistance, tier, false);
        }
        else if (attribute == LIFE_LEECH_PERCENT.get())
        {
            //value = (rand.nextFloat(CustomAttributes.getValuesTierLifeLeechPercent[tier - 1], CustomAttributes.getValuesTierLifeLeechPercent[tier]));
            value = GetValueFromTier(getValuesTierLifeLeechPercent, tier, false);
            uuid = LIFE_LEECH_PERCENT_ID;
        }
        else if (attribute == LIFE_LEECH_RAW.get())
        {
            //value = rand.nextFloat(CustomAttributes.getValuesTierLifeLeechRaw[tier - 1], CustomAttributes.getValuesTierLifeLeechRaw[tier]);
            value = GetValueFromTier(getValuesTierLifeLeechRaw, tier, false);
            uuid = LIFE_LEECH_RAW_ID;
        }
        else if (attribute == REFLECT_DAMAGE_PERCENT.get())
        {
            //value = (rand.nextFloat(CustomAttributes.getValuesTierReflectDamagePercent[tier - 1], CustomAttributes.getValuesTierReflectDamagePercent[tier]));
            value = GetValueFromTier(getValuesTierReflectDamagePercent, tier, false);
            uuid = REFLECT_DAMAGE_PERCENT_ID;
        }
        else if (attribute == REFLECT_DAMAGE_RAW.get())
        {
            //value = rand.nextFloat(CustomAttributes.getValuesTierReflectDamageRaw[tier - 1], CustomAttributes.getValuesTierReflectDamageRaw[tier]);
            value = GetValueFromTier(getValuesTierReflectDamageRaw, tier, false);
            uuid = REFLECT_DAMAGE_RAW_ID;
        }
        else if (attribute == CRITICAL_CHANCE.get())
        {
            //value = (rand.nextFloat(CustomAttributes.getValuesTierCriticalChance[tier - 1], CustomAttributes.getValuesTierCriticalChance[tier]));
            value = GetValueFromTier(getValuesTierCriticalChance, tier, false);
            uuid =CRITICAL_CHANCE_ID;
        }
        else if (attribute == CRITICAL_DAMAGE.get())
        {
            //value = (rand.nextFloat(CustomAttributes.getValuesTierCriticalDamage[tier - 1], CustomAttributes.getValuesTierCriticalDamage[tier]));
            value = GetValueFromTier(getValuesTierCriticalDamage, tier, false);
            uuid = CRITICAL_DAMAGE_ID;
        }
        else if (attribute == GEM_LVL_INCREASE.get())
        {
            //value = rand.nextFloat(CustomAttributes.getValuesTierGemLevelIncrease[tier - 1], CustomAttributes.getValuesTierGemLevelIncrease[tier]);
            value = 1;
            uuid = GEM_LVL_INCREASE_ID;
        }
        else if (attribute == MORE_GEM_SLOT.get())
        {
            //value = rand.nextFloat(CustomAttributes.getValuesTierMoreGemSlot[tier - 1], CustomAttributes.getValuesTierMoreGemSlot[tier]);
            value = 1;
            uuid = MORE_GEM_SLOT_ID;
            gemSlot = 1;
        }
        else if (attribute == DODGE.get())
        {
            value = GetValueFromTier(getValuesTierDodge, tier, false);//rand.nextFloat(CustomAttributes.getValuesTierDodge[tier - 1], CustomAttributes.getValuesTierDodge[tier]);
            uuid = DODGE_ID;
        }
        else if (attribute == REINFORCED.get())
        {
            value = GetValueFromTier(getValuesTierReinforced, tier, true);// (rand.nextFloat(getValuesTierReinforced[tier - 1], getValuesTierReinforced[tier]) / 100 + 1);
            uuid = REINFORCED_ID;
        }

        EquipmentSlot[] equipmentSlots = ModTags.GetEquipmentSlotOf(leItemStack);

        //attribute modifier CUSTOM
        if(uuid != null)
        {
            for (EquipmentSlot equipmentSlot : equipmentSlots)
            {
                leItemStack.addAttributeModifier(
                        attribute,
                        new AttributeModifier(uuid, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                        equipmentSlot
                );
            }
        }
        else // VRAIS attribute modifier
        {
            for (EquipmentSlot equipmentSlot : equipmentSlots)
            {
                leItemStack.addAttributeModifier(
                        attribute,
                        new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                        equipmentSlot
                );
            }
        }

        result[0] = leItemStack;
        result[1] = gemSlot;
        return result;
    }

    private static float GetValueFromTier(float[] values, int tier, boolean divideByCent)
    {
        if(divideByCent)
        {
            return rand.nextFloat(values[tier - 1], values[tier]) / 100 + 1;
        }
        else
        {
            return rand.nextFloat(values[tier - 1], values[tier]);
        }
    }



}
