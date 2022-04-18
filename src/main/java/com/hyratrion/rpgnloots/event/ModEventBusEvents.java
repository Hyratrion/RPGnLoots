package com.hyratrion.rpgnloots.event;

import com.hyratrion.rpgnloots.RPGLOOT;
import com.hyratrion.rpgnloots.event.loot.ExampleModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = RPGLOOT.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_blaze")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_cave_spider")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_creeper")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_drowned")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_elder_guardian")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_ender_dragon")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_enderman")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_endermite")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_evoker")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_ghast")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_guardian")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_hoglin")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_husk")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_illusioner")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_magma_cube")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_phantom")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_piglin")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_piglin_brute")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_pillager")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_ravager")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_shulker")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_silverfish")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_skeleton")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_skeleton_horse")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_slime")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_spider")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_stray")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_vex")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_vindicator")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_witch")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_wither")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_wither_skeleton")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_zoglin")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_zombie")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_zombie_horse")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_zombie_villager")),
                new ExampleModifier.Serializer().setRegistryName
                        (new ResourceLocation(RPGLOOT.MOD_ID,"loot_gems_zombified_piglin"))
        );
    }


}
