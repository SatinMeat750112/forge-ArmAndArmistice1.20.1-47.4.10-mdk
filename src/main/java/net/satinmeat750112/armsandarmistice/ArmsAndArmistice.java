package net.satinmeat750112.armsandarmistice;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.satinmeat750112.armsandarmistice.entity.ModEntities;
import net.satinmeat750112.armsandarmistice.entity.client.Sdkfz751Renderer;
import net.satinmeat750112.armsandarmistice.item.ModCreativeModTabs;
import net.satinmeat750112.armsandarmistice.item.Moditems;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;


@Mod(ArmsAndArmistice.MODID)
public class ArmsAndArmistice
{

    public static final String MODID = "arms_and_armistice";

    private static final Logger LOGGER = LogUtils.getLogger();

    public ArmsAndArmistice(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModEntities.register(modEventBus);

        ModCreativeModTabs.register(modEventBus);


        Moditems.register(modEventBus);






        modEventBus.addListener(this::commonSetup);

        GeckoLib.initialize();


        MinecraftForge.EVENT_BUS.register(this);


        modEventBus.addListener(this::addCreative);



    }

    private void commonSetup(final FMLCommonSetupEvent event) {



    }

        //CREATIVE MODE TAB HERE

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(Moditems.Steel_Ingot);
            event.accept(Moditems.Brass_Ingot);
            event.accept(Moditems.Chromium_Ingot);
            event.accept(Moditems.Aluminium_Ingot);
            event.accept(Moditems.Unprocessed_Rubber);
            event.accept(Moditems.Processed_Rubber);
        }

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.SDKFZ_751.get(), Sdkfz751Renderer::new);
        }
    }
}
