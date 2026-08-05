package net.satinmeat750112.armsandarmistice.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;
import net.satinmeat750112.armsandarmistice.entity.custom.Sdkfz751Entity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class Sdkfz751Model extends GeoModel<Sdkfz751Entity> {

    @Override
    public ResourceLocation getModelResource(Sdkfz751Entity animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArmsAndArmistice.MODID, "geo/sdkfz751.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sdkfz751Entity animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArmsAndArmistice.MODID, "textures/entity/sdkfz751.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sdkfz751Entity animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArmsAndArmistice.MODID, "animations/sdkfz751.animation.json");
    }

    @Override
    public void setCustomAnimations(Sdkfz751Entity animatable, long instanceId, AnimationState<Sdkfz751Entity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        float targetRotation = animatable.getClientSteer() * 0.6F;

        CoreGeoBone leftBone = this.getAnimationProcessor().getBone("left");
        if (leftBone != null) {
            leftBone.setRotY(targetRotation);
        }

        CoreGeoBone rightBone = this.getAnimationProcessor().getBone("right");
        if (rightBone != null) {
            rightBone.setRotY(targetRotation);
        }
    }
}
