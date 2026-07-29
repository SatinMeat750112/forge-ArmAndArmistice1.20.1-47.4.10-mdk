package net.satinmeat750112.armsandarmistice.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.satinmeat750112.armsandarmistice.entity.custom.Sdkfz751Entity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Sdkfz751Renderer extends GeoEntityRenderer<Sdkfz751Entity> {

    public Sdkfz751Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Sdkfz751Model());
    }
}

