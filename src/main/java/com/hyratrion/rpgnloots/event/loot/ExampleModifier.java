package com.hyratrion.rpgnloots.event.loot;

import com.google.common.collect.ImmutableMultimap;
import com.google.gson.JsonObject;
import com.hyratrion.rpgnloots.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ExampleModifier extends LootModifier {
    private Item addition;
    private final float chanceLootGemTier1;
    private final float chanceLootStuffTier1;
    private final float chanceLootStuffTier2;
    private final float chanceLootStuffTier3;
    private final float chanceLootStuffTier4;
    private final float chanceLootStuff;


    protected ExampleModifier(LootItemCondition[] conditionsIn, float chanceLootGemTier1) {
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
            Item SWORD;
            if (chanceValue < chanceLootStuffTier1) {
                SWORD = Items.WOODEN_SWORD;
            }
            else if (chanceValue < chanceLootStuffTier2) {
                SWORD = Items.STONE_SWORD;
            }
            else if (chanceValue < chanceLootStuffTier3) {
                SWORD = Items.IRON_SWORD;
            }
            else if (chanceValue < chanceLootStuffTier4) {
                SWORD = Items.DIAMOND_SWORD;
            }
            else {
                SWORD = Items.NETHERITE_SWORD;
            }

            generatedLoot.add(new ItemStack(SWORD, 1));
        }

        generatedLoot.add(new ItemStack(addition, 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ExampleModifier> {

        @Override
        public ExampleModifier read(ResourceLocation name, JsonObject object,
                                    LootItemCondition[] conditionsIn) {
            float chanceLootTier1 = GsonHelper.getAsFloat(object, "chanceLootGemTier1");
            return new ExampleModifier(conditionsIn, chanceLootTier1);
        }

        @Override
        public JsonObject write(ExampleModifier instance) {
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