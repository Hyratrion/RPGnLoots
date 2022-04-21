package com.hyratrion.rpgnloots.event.loot;

import com.google.common.collect.Multimap;
import com.google.gson.JsonObject;
import com.hyratrion.rpgnloots.item.ModItems;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static com.hyratrion.rpgnloots.event.loot.CustomAttributes.*;

public class LootGemToolFromMobs extends LootModifier {
    private Item addition;
    private final float chanceLootGemTier1;
    private final float chanceLootStuffTier1;
    private final float chanceLootStuffTier2;
    private final float chanceLootStuffTier3;
    private final float chanceLootStuffTier4;
    private final float chanceLootStuff;


    protected LootGemToolFromMobs(LootItemCondition[] conditionsIn, float chanceLootGemTier1) {
        super(conditionsIn);

        //ce code
        ModItems.createArrayGems();
        //en mettant 5
        //cela veux dire que l'on a ces valeurs : 0, 1, 2, 3, 4
        //pour loot l'item tier 1
        this.chanceLootGemTier1 = chanceLootGemTier1;
        this.chanceLootStuffTier1 = 20;
        this.chanceLootStuffTier2 = this.chanceLootStuffTier1 + 20;
        this.chanceLootStuffTier3 = this.chanceLootStuffTier2 + 20;
        this.chanceLootStuffTier4 = this.chanceLootStuffTier3 + 20;
        this.chanceLootStuff = this.chanceLootStuffTier4 + 20;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
        //

        Random rand = new Random();
        float chanceValue = rand.nextFloat(100);

        //0, 1, 2, 3, 4 < 5
        if(chanceValue < chanceLootGemTier1)
        {
            int item_index = rand.nextInt(ModItems.GEM_LVL_1.length);
            this.addition = ModItems.GEM_LVL_1[item_index];
        }
        else //donc supérieur a chanceLootGemTier1
        {
            int item_index = rand.nextInt(ModItems.GEM_LVL_0.length);
            this.addition = ModItems.GEM_LVL_0[item_index];
        }

        chanceValue = rand.nextFloat(100);

        if(chanceValue < chanceLootStuff) {
            Item stuff;
            //choix du tier
            //(pour le moment juste de l'épée
            if (chanceValue < chanceLootStuffTier1) {
                stuff = Items.NETHERITE_CHESTPLATE;
            } else if (chanceValue < chanceLootStuffTier2) {
                stuff = Items.IRON_CHESTPLATE;
            } else if (chanceValue < chanceLootStuffTier3) {
                stuff = Items.IRON_SWORD;
            } else if (chanceValue < chanceLootStuffTier4) {
                stuff = Items.DIAMOND_SWORD;
            } else {
                stuff = Items.NETHERITE_SWORD;
            }


            //ajout de modifier

            ItemStack itemStackStuff = new ItemStack(stuff, 1);

            if (stuff instanceof ArmorItem armorItem)
            {
                float armorMultiple = rand.nextFloat(5);
                itemStackStuff.addAttributeModifier(
                        Attributes.ARMOR,
                        new AttributeModifier("modifier rpgnloots", (armorItem.getDefense()) * (1 + armorMultiple), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
                float toughnessMultiple = rand.nextFloat(5);
                itemStackStuff.addAttributeModifier(
                        Attributes.ARMOR_TOUGHNESS,
                        new AttributeModifier("modifier rpgnloots", (armorItem.getToughness()) * (1 + toughnessMultiple), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
                itemStackStuff.addAttributeModifier(
                        Attributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier("modifier rpgnloots", rand.nextFloat(0.001f, 0.5f), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
                itemStackStuff.addAttributeModifier(
                        CustomAttributes.DODGE.get(),
                        new AttributeModifier(DODGE_ID, "modifier rpgnloots", rand.nextFloat(0.1f, 11), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
                itemStackStuff.addAttributeModifier(
                        CustomAttributes.REFLECT_DAMAGE_PERCENT.get(),
                        new AttributeModifier(REFLECT_DAMAGE_PERCENT_ID, "modifier rpgnloots", rand.nextFloat(1, 11), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
                itemStackStuff.addAttributeModifier(
                        CustomAttributes.REFLECT_DAMAGE_RAW.get(),
                        new AttributeModifier(REFLECT_DAMAGE_RAW_ID, "modifier rpgnloots", rand.nextFloat(0.1f, 2), AttributeModifier.Operation.ADDITION),
                        armorItem.getSlot()
                );
            }
            else if (stuff instanceof SwordItem swordItem)
            {
                int damageMultiple = rand.nextInt(5);
                itemStackStuff.addAttributeModifier(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier("modifier rpgnloots", (3 + swordItem.getTier().getAttackDamageBonus()) * (1 + damageMultiple), AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
                itemStackStuff.addAttributeModifier(
                        Attributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier("modifier rpgnloots", rand.nextFloat(0.001f, 0.2f), AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
            /*
            float speedValue = rand.nextFloat(0, 4);
            itemStackSword.addAttributeModifier (
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier("modifier rpgnloots", speedValue, AttributeModifier.Operation.ADDITION),
                    EquipmentSlot.MAINHAND
            );*/

                itemStackStuff.addAttributeModifier(
                        CustomAttributes.CRITICAL_CHANCE.get(),
                        new AttributeModifier(CRITICAL_CHANCE_ID, "modifier rpgnloots", rand.nextInt(1, 101), AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
                itemStackStuff.addAttributeModifier(
                        CustomAttributes.CRITICAL_DAMAGE.get(),
                        new AttributeModifier(CRITICAL_DAMAGE_ID, "modifier rpgnloots", rand.nextInt(1, 101), AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
                itemStackStuff.addAttributeModifier(
                        CustomAttributes.LIFE_LEECH_PERCENT.get(),
                        new AttributeModifier(LIFE_LEECH_PERCENT_ID, "modifier rpgnloots", rand.nextInt(1, 101), AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
                itemStackStuff.addAttributeModifier(
                        CustomAttributes.LIFE_LEECH_RAW.get(),
                        new AttributeModifier(LIFE_LEECH_RAW_ID, "modifier rpgnloots", rand.nextFloat(0.1f, 1), AttributeModifier.Operation.ADDITION),
                        EquipmentSlot.MAINHAND
                );
            }

            //durability

            try
            {
                //System.out.println("---- Makotache ----- base durability " + itemStackStuff.getMaxDamage());
                int durability = rand.nextInt(1,4) * itemStackStuff.getMaxDamage();


                Field field = Item.class.getDeclaredField("maxDamage");
                field.setAccessible(true);
                field.setInt(stuff, durability);

                //System.out.println("---- Makotache ----- durability mis a jour => " + itemStackStuff.getMaxDamage());
            }
            catch (Exception ingored) { }



            /*Multimap<Attribute, AttributeModifier> List_modifiers = itemStackSword.getAttributeModifiers(EquipmentSlot.MAINHAND);

            if(List_modifiers.containsKey(CustomAttributes.LIFE_LEECH_RAW.get()))
                itemStackSword.getDescriptionId();
                itemStackSword.name */

            itemStackStuff.getTooltipImage();
            //SWORD.getDescription().add new TranslatableComponent("this.getDescriptionId()");


            generatedLoot.add(itemStackStuff);
        }

        generatedLoot.add(new ItemStack(addition, 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<LootGemToolFromMobs> {

        @Override
        public LootGemToolFromMobs read(ResourceLocation name, JsonObject object,
                                        LootItemCondition[] conditionsIn) {
            float chanceLootTier1 = GsonHelper.getAsFloat(object, "chanceLootGemTier1");
            return new LootGemToolFromMobs(conditionsIn, chanceLootTier1);
        }

        @Override
        public JsonObject write(LootGemToolFromMobs instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("chanceLootGemTier1",instance.chanceLootGemTier1);
            return json;
        }
    }

}
/*public class ExampleModifier extends LootModifier {

    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, RPGLOOT.MOD_ID);

    public static final RegistryObject<Serializer> GEMME_DEGAT_CRITIQUE = GLM.register("loot_gemme_degat_critique", ExampleModifier.Serializer::new);
    /*private int montantToConvert;*//*
    private Item itemReward;

    public ExampleModifier(LootItemCondition[] conditionsIn, int montant, Item addition) {
        super(conditionsIn);
        /*montantToConvert=montant;*//*
        itemReward=addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // Modify the loot and return the new drops
        generatedLoot.add(new ItemStack(itemReward/*, montantToConvert*//*));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ExampleModifier> {

        @Override
        public ExampleModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            /*int montant = GsonHelper.getAsInt(object, "montant");*//*
            Item gemme_degat_critique = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new ExampleModifier(conditionsIn,/*, montant*//* gemme_degat_critique);
        }

        @Override
        public JsonObject write(ExampleModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            /*json.addProperty("montant", instance.montantToConvert);*//*
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.itemReward).toString());
            return json;
        }
    }
}*/