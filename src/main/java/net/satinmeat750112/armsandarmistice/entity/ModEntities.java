package net.satinmeat750112.armsandarmistice.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;
import net.satinmeat750112.armsandarmistice.entity.custom.Sdkfz751Entity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArmsAndArmistice.MODID);

    public static final RegistryObject<EntityType<Sdkfz751Entity>> SDKFZ_751 =
            ENTITY_TYPES.register("sdkfz_751",
                    () -> EntityType.Builder.of(Sdkfz751Entity::new, MobCategory.MISC)
                            .sized(0.6f, 1.95f)
                            .build(new ResourceLocation(ArmsAndArmistice.MODID, "sdkfz_751").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}