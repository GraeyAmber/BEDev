package dev.graeyamber.bedev;

import dev.graeyamber.bedev.client.renderer.WaterTankBERend;
import dev.graeyamber.bedev.registry.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(BEDev.MODID)
public class BEDev
{
    public static final String MODID = "bedev";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BEDev(IEventBus modEventBus, ModContainer modContainer)
    {
        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        CreativeTabRegistry.CREATIVE_TABS.register(modEventBus);

        /// VERY IMPORTANT TO add this to register our custom capabilities
        modEventBus.addListener(CapabilityRegistry::addCapabilities);

        // modEventBus.addListener(this::registerRenderers);

        /// USED BY RENDERERS - @SubscribeEvent
        //x NeoForge.EVENT_BUS.register(this);
    }

    //. private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    //.     event.registerBlockEntityRenderer(BlockEntityRegistry.MILK_CANISTER_BLOCK_ENTITY.get(), MilkCanisterBlockRenderer::new);
    //. }

    //x @SubscribeEvent
    //x public void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
//x
    //x     //: // Entities
    //x     //: event.registerEntityRenderer(EntityInit.EXAMPLE_ENTITY.get(), ExampleEntityRenderer::new);
    //x     //: event.registerEntityRenderer(EntityInit.EXAMPLE_ANIMATED_ENTITY.get(), ExampleAnimatedEntityRenderer::new);
//x
    //x     LOGGER.info("zzzHELLOzzz");
    //x     // Block Entities
    //x     event.registerBlockEntityRenderer(BlockEntityRegistry.WATER_TANK_BLOCK_ENTITY.get(), WaterTankBERend::new);
    //x }
}
