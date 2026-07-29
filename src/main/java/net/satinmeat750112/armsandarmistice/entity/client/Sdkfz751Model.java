package net.satinmeat750112.armsandarmistice.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;
import net.satinmeat750112.armsandarmistice.entity.custom.Sdkfz751Entity;
import software.bernie.geckolib.model.GeoModel;

public class Sdkfz751Model extends GeoModel<Sdkfz751Entity> {

    @Override
    public ResourceLocation getModelResource(Sdkfz751Entity animatable) {
        return new ResourceLocation(ArmsAndArmistice.MODID, "geo/sdkfz751.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sdkfz751Entity animatable) {
        return new ResourceLocation(ArmsAndArmistice.MODID, "textures/entity/sdkfz751.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sdkfz751Entity animatable) {
        return new ResourceLocation(ArmsAndArmistice.MODID, "animations/sdkfz751.animation.json");
    }
}
