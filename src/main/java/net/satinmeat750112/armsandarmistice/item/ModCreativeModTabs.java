package net.satinmeat750112.armsandarmistice.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArmsAndArmistice.MODID);

    public static final RegistryObject<CreativeModeTab> Arms_And_Armistice_Tab = CREATIVE_MODE_TABS.register("arms_and_armistice_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.Steel_Ingot.get()))
                    .title(Component.translatable("creativetab.arms_and_armistice_tab"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(Moditems.Steel_Ingot.get());
                        output.accept(Moditems.Brass_Ingot.get());
                        output.accept(Moditems.Aluminium_Ingot.get());
                        output.accept(Moditems.Chromium_Ingot.get());
                        output.accept(Moditems.Unprocessed_Rubber.get());
                        output.accept(Moditems.Processed_Rubber.get());

                        
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
