package com.hyratrion.rpgnloots.screen;

import com.hyratrion.rpgnloots.RPGNLOOT;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModMenuTypes {
    private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
    public static final MenuType<SocketingTableMenu> SOCKETING_TABLE_MENU_TYPE = register("socketing_table_menu", (id, inv, extraData) -> new SocketingTableMenu(id, inv, extraData));

    private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
        MenuType<T> menuType = new MenuType<T>(containerFactory);
        menuType.setRegistryName(registryname);
        REGISTRY.add(menuType);
        return menuType;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
    }



 /*   public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, RPGNLOOT.MOD_ID);

    public static final RegistryObject<MenuType<SocketingTableMenu>> SOCKETING_TABLE_MENU =
            registerMenuType("socketing_table_menu", SocketingTableMenu::new );


  //  private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
  //                                                                                                String name) {
  //      return MENUS.register(name, () -> IForgeMenuType.create(factory));
  //  }
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, MenuType.MenuSupplier<T> menu)
    {
        return MENUS.register(name, () -> new MenuType<>(menu));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
    */
}