package com.hyratrion.rpgnloots.event.loot;

import com.hyratrion.rpgnloots.RPGNLOOT;
import net.minecraft.core.Registry;

import java.util.UUID;


import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.RegistryObject;

public class CustomAttributes extends Attributes
{
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, RPGNLOOT.MOD_ID);

    public static final UUID CRITICAL_CHANCE_ID = UUID.fromString("3EEF1993-4C00-468B-9766-89914E9B13BD");
    public static final UUID CRITICAL_DAMAGE_ID = UUID.fromString("C50EB615-37BC-4161-B8F9-51FE5DB48BBE");
    public static final UUID LIFE_LEECH_RAW_ID = UUID.fromString("E4DC069C-C745-4794-89BD-F56C33A943DA");
    public static final UUID LIFE_LEECH_PERCENT_ID = UUID.fromString("DB869A47-9B08-4287-A4AE-4DEFADD3373E");
    public static final UUID GEM_LVL_INCREASE_ID = UUID.fromString("32093793-DC96-47D2-83C4-C3F216E854FC");
    public static final UUID MORE_GEM_SLOT_ID = UUID.fromString("814C27AB-D808-42CF-AF2E-54BD8CD6D4D3");
    public static final UUID REFLECT_DAMAGE_RAW_ID = UUID.fromString("FB0D8918-95A8-463A-828D-544CD6974661");
    public static final UUID REFLECT_DAMAGE_PERCENT_ID = UUID.fromString("2C2FB8D0-B723-49EE-AE5F-ACF76361686B");
    public static final UUID DODGE_ID = UUID.fromString("EB9D70D1-4081-45CD-8F94-5AB7FCF0A539");

    public static final RegistryObject<Attribute> CRITICAL_CHANCE = ATTRIBUTES.register("rpgnloots.critical_chance", () -> new RangedAttribute("attribute.name.rpgnloots.critical_chance", 10.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> CRITICAL_DAMAGE = ATTRIBUTES.register("rpgnloots.critical_damage", () -> new RangedAttribute("attribute.name.rpgnloots.critical_damage", 2.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> LIFE_LEECH_RAW = ATTRIBUTES.register("rpgnloots.life_leech_raw", () -> new RangedAttribute("attribute.name.rpgnloots.life_leech_raw", 10.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> LIFE_LEECH_PERCENT = ATTRIBUTES.register("rpgnloots.life_leech_percent", () -> new RangedAttribute("attribute.name.rpgnloots.life_leech_percent", 10.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> GEM_LVL_INCREASE = ATTRIBUTES.register("rpgnloots.gem_lvl_increase", () -> new RangedAttribute("attribute.name.rpgnloots.gem_lvl_increase", 10.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> MORE_GEM_SLOT = ATTRIBUTES.register("rpgnloots.more_gem_slot", () -> new RangedAttribute("attribute.name.rpgnloots.more_gem_slot", 2.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> REFLECT_DAMAGE_RAW = ATTRIBUTES.register("rpgnloots.reflect_damage_raw", () -> new RangedAttribute("attribute.name.rpgnloots.reflect_damage_raw", 10.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> REFLECT_DAMAGE_PERCENT = ATTRIBUTES.register("rpgnloots.reflect_damage_percent", () -> new RangedAttribute("attribute.name.rpgnloots.reflect_damage_percent", 10.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> DODGE = ATTRIBUTES.register("rpgnloots.dodge", () -> new RangedAttribute("attribute.name.rpgnloots.dodge", 10.0D, 1.0D, 1024.0D));


    public static void register(IEventBus eventBus)
    {
        ATTRIBUTES.register(eventBus);
    }
}
