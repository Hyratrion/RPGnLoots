package com.hyratrion.rpgnloots.recipe;

import com.hyratrion.rpgnloots.RPGNLOOT;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RPGNLOOT.MOD_ID);

    //public static final RegistryObject<RecipeSerializer<GemIncrustationRecipe>> GEM_INCRUSTATION_SERIALIZER =
    //        SERIALIZERS.register("gem_incrustation", () -> GemIncrustationRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        //Registry.register(Registry.RECIPE_TYPE, GemIncrustationRecipe.Type.ID, GemIncrustationRecipe.Type.INSTANCE);
    }
}