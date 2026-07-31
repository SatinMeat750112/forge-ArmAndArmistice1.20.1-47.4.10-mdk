package net.satinmeat750112.armsandarmistice.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArmsAndArmistice.MODID);


    public static final RegistryObject<Item> Steel_Ingot = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Brass_Ingot = ITEMS.register("brass_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Chromium_Ingot = ITEMS.register("chromium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Aluminium_Ingot = ITEMS.register("aluminium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Unprocessed_Rubber = ITEMS.register("unprocessed_rubber",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> Processed_Rubber = ITEMS.register("processed_rubber",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
