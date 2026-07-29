package net.satinmeat750112.armsandarmistice.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;
import net.satinmeat750112.armsandarmistice.entity.ModEntities;
import net.satinmeat750112.armsandarmistice.entity.custom.Sdkfz751Entity;

@Mod.EventBusSubscriber(modid = ArmsAndArmistice.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    // --- MOD BUS: Registration and Startup ---
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SDKFZ_751.get(), Sdkfz751Entity.setAttributes());
    }

    @Mod.EventBusSubscriber(modid = ArmsAndArmistice.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusEvents {
    }
}