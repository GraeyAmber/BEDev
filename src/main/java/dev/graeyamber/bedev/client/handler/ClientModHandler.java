package dev.graeyamber.bedev.client.handler;

import dev.graeyamber.bedev.BEDev;
import dev.graeyamber.bedev.client.renderer.WaterTankBERend;
import dev.graeyamber.bedev.registry.BlockEntityRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

// @Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@EventBusSubscriber(modid = BEDev.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {

    //: @SubscribeEvent
    //: public static void clientSetup(FMLClientSetupEvent event) {
    //:     event.enqueueWork(() -> {
    //:         MenuScreens.register(MenuInit.EXAMPLE_MENU.get(), ExampleMenuScreen::new);
    //:     });
    //: }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //: // Entities
        //: event.registerEntityRenderer(EntityInit.EXAMPLE_ENTITY.get(), ExampleEntityRenderer::new);
        //: event.registerEntityRenderer(EntityInit.EXAMPLE_ANIMATED_ENTITY.get(), ExampleAnimatedEntityRenderer::new);
        // Block Entities

        BEDev.LOGGER.info("123_hello");
        event.registerBlockEntityRenderer(BlockEntityRegistry.WATER_TANK_BLOCK_ENTITY.get(), WaterTankBERend::new);
    }
}
