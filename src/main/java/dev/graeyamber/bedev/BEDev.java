package dev.graeyamber.bedev;

import dev.graeyamber.bedev.registry.*;
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
    }

    //. private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    //.     event.registerBlockEntityRenderer(BlockEntityRegistry.MILK_CANISTER_BLOCK_ENTITY.get(), MilkCanisterBlockRenderer::new);
    //. }
}
