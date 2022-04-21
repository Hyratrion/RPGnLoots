package com.hyratrion.rpgnloots.event;

import com.hyratrion.rpgnloots.RPGNLOOT;
import com.hyratrion.rpgnloots.event.loot.LootGemToolFromMobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = RPGNLOOT.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event)
    {
        event.getRegistry().registerAll(
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_blaze")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_cave_spider")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_creeper")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_drowned")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_elder_guardian")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_ender_dragon")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_enderman")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_endermite")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_evoker")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_ghast")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_guardian")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_hoglin")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_husk")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_illusioner")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_magma_cube")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_phantom")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_piglin")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_piglin_brute")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_pillager")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_ravager")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_shulker")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_silverfish")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_skeleton")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_skeleton_horse")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_slime")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_spider")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_stray")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_vex")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_vindicator")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_witch")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_wither")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_wither_skeleton")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_zoglin")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_zombie")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_zombie_horse")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_zombie_villager")),
                new LootGemToolFromMobs.Serializer().setRegistryName
                        (new ResourceLocation(RPGNLOOT.MOD_ID,"loot_gems_zombified_piglin"))
        );
    }

}
