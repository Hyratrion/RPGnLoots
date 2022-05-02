package com.hyratrion.rpgnloots.event.loot;

import com.google.common.base.Equivalence;
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

        //Crée l'objet d'item à partir de l'item
        ItemStack itemStack = new ItemStack(itemLooted, 1);
        if(itemLooted instanceof ArmorItem armorItem){
            itemStack.addAttributeModifier(
                    ARMOR,
                    new AttributeModifier("modifier rpgnloots", armorItem.getDefense(), AttributeModifier.Operation.ADDITION),
                    armorItem.getSlot()
            );
            if(armorItem.getToughness() > 0){
                itemStack.addAttributeModifier(
                        ARMOR_TOUGHNESS,
                        new AttributeModifier("modifier rpgnloots", armorItem.getToughness(), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
            }
            if (armorItem.getMaterial().getKnockbackResistance() > 0){
                itemStack.addAttributeModifier(
                        KNOCKBACK_RESISTANCE,
                        new AttributeModifier("modifier rpgnloots", armorItem.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
            }

        }
        else if (itemLooted instanceof TieredItem tieredItem)
        {
            itemStack.addAttributeModifier(
                    ATTACK_DAMAGE,
                    new AttributeModifier("modifier rpgnloots", tieredItem.getTier().getAttackDamageBonus() + 3, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            
            if(itemLooted instanceof PickaxeItem){
                itemStack.addAttributeModifier(
                        ATTACK_SPEED,
                        new AttributeModifier("modifier rpgnloots", -2.8f, AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
            }
            else if (itemLooted instanceof SwordItem){
                itemStack.addAttributeModifier(
                    ATTACK_SPEED,
                    new AttributeModifier("modifier rpgnloots", -2.4f, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            } else if (itemLooted instanceof ShovelItem)
            {
                itemStack.addAttributeModifier(
                        ATTACK_SPEED,
                        new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
            } else if (itemLooted instanceof AxeItem axeItem)
            {
                if(axeItem.getTier() == WOOD){
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3.2f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (axeItem.getTier() == STONE)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (axeItem.getTier() == GOLD)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (axeItem.getTier() == IRON)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3.1f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (axeItem.getTier() == DIAMOND)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (axeItem.getTier() == NETHERITE)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                }
            } else if (itemLooted instanceof HoeItem hoeItem)
            {
                if(hoeItem.getTier() == WOOD){
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (hoeItem.getTier() == STONE)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -2f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (hoeItem.getTier() == GOLD)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -3f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (hoeItem.getTier() == IRON)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", -1f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                }

                else if (hoeItem.getTier() == DIAMOND)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", 0f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                } else if (hoeItem.getTier() == NETHERITE)
                {
                    itemStack.addAttributeModifier(
                            ATTACK_SPEED,
                            new AttributeModifier("modifier rpgnloots", 0f, AttributeModifier.Operation.ADDITION),
                            EquipmentSlot.MAINHAND
                    );
                }


            }
            
        }
        int gemSlot = 0;
        //Boucle sur le nombre d'attribut modifier à ajouter
        for(int k = 0; k < tier; k++){
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
                ModTags.AddGemTag(itemStack, ModTags.DEFAULT_TAG_VALUE);
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
    public static Object[] AddAttributesIntoItem(Attribute attribute, int tier, Item itemLooted, ItemStack leItemStack){
        float value;
        int gemSlot = 0;
        Object[] result = new Object[2];
        if(attribute == Attributes.ATTACK_DAMAGE){
            value = (((TieredItem) itemLooted).getTier().getAttackDamageBonus() + 3) * (rand.nextFloat(CustomAttributes.getValuesTierAttackDamage[tier - 1], CustomAttributes.getValuesTierAttackDamage[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
        } else if (attribute == ATTACK_SPEED)
        {
            value = (((TieredItem) itemLooted).getTier().getSpeed()) * (rand.nextFloat(CustomAttributes.getValuesTierAttackSpeed[tier - 1], CustomAttributes.getValuesTierAttackSpeed[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            
        } else if (attribute == ARMOR)
        {
            value = (((ArmorItem) itemLooted).getDefense()) * (rand.nextFloat(CustomAttributes.getValuesTierArmor[tier - 1], CustomAttributes.getValuesTierArmor[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            
        } else if (attribute == ARMOR_TOUGHNESS)
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierArmorToughness[tier - 1], CustomAttributes.getValuesTierArmorToughness[tier]) / 100;
            if(((ArmorItem) itemLooted).getToughness() != 0)
            {
                value = (((ArmorItem) itemLooted).getToughness() * (value + 1));
            }
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            
        } else if (attribute == KNOCKBACK_RESISTANCE)
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierKnockbackResistance[tier - 1], CustomAttributes.getValuesTierKnockbackResistance[tier]));
            EquipmentSlot[] temp = ModTags.GetEquipmentSlotOf(leItemStack);
            for (EquipmentSlot slot : temp)
            {
                leItemStack.addAttributeModifier(
                        attribute,
                        new AttributeModifier("modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                        slot
                );
            }

        } else if (attribute == LIFE_LEECH_PERCENT.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierLifeLeechPercent[tier - 1], CustomAttributes.getValuesTierLifeLeechPercent[tier]) / 100);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(LIFE_LEECH_PERCENT_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            
        } else if (attribute == LIFE_LEECH_RAW.get())
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierLifeLeechRaw[tier - 1], CustomAttributes.getValuesTierLifeLeechRaw[tier]);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(LIFE_LEECH_RAW_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
            
        } else if (attribute == REFLECT_DAMAGE_PERCENT.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierReflectDamagePercent[tier - 1], CustomAttributes.getValuesTierReflectDamagePercent[tier]) / 100);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(REFLECT_DAMAGE_PERCENT_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            
        } else if (attribute == REFLECT_DAMAGE_RAW.get())
        {
            value = rand.nextFloat(CustomAttributes.getValuesTierReflectDamageRaw[tier - 1], CustomAttributes.getValuesTierReflectDamageRaw[tier]);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(REFLECT_DAMAGE_RAW_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            
        } else if (attribute == CRITICAL_CHANCE.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierCriticalChance[tier - 1], CustomAttributes.getValuesTierCriticalChance[tier]));
            EquipmentSlot[] temp = ModTags.GetEquipmentSlotOf(leItemStack);
            for (EquipmentSlot slot : temp)
            {
                leItemStack.addAttributeModifier(
                        attribute,
                        new AttributeModifier(CRITICAL_CHANCE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                        slot
                );
            }
            
        } else if (attribute == CRITICAL_DAMAGE.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierCriticalDamage[tier - 1], CustomAttributes.getValuesTierCriticalDamage[tier]));

            EquipmentSlot[] temp = ModTags.GetEquipmentSlotOf(leItemStack);
            for (EquipmentSlot slot : temp)
            {
                leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(CRITICAL_DAMAGE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    slot
                );
            }

        } else if (attribute == GEM_LVL_INCREASE.get())
        {
            //value = rand.nextFloat(CustomAttributes.getValuesTierGemLevelIncrease[tier - 1], CustomAttributes.getValuesTierGemLevelIncrease[tier]);
            value = 1;
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(GEM_LVL_INCREASE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    itemLooted.getEquipmentSlot(leItemStack)
            );
            
        } else if (attribute == MORE_GEM_SLOT.get())
        {
            //value = rand.nextFloat(CustomAttributes.getValuesTierMoreGemSlot[tier - 1], CustomAttributes.getValuesTierMoreGemSlot[tier]);
            value = 1;
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(MORE_GEM_SLOT_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    itemLooted.getEquipmentSlot(leItemStack)
            );
            gemSlot = 1;
            
        } else if (attribute == DODGE.get())
        {
            value = (rand.nextFloat(CustomAttributes.getValuesTierDodge[tier - 1], CustomAttributes.getValuesTierDodge[tier]));
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(DODGE_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    ((ArmorItem) itemLooted).getSlot()
            );
            
        }else if (attribute == REINFORCED.get()){
            value = (rand.nextFloat(getValuesTierReinforced[tier - 1], CustomAttributes.getValuesTierReinforced[tier]) / 100 + 1);
            leItemStack.addAttributeModifier(
                    attribute,
                    new AttributeModifier(REINFORCED_ID, "modifier rpgnloots", value, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );
        }
        result[0] = leItemStack;
        result[1] = gemSlot;
        return result;
    }



}
