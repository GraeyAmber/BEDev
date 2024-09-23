package dev.graeyamber.bedev.registry;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityRegistry {

    public static void addCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockEntityRegistry.ITEM_WARPER_BLOCK_ENTITY.get(), (o, direction) -> o.getItemHandler());

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, BlockEntityRegistry.WATER_TANK_BLOCK_ENTITY.get(), (be, ctx) -> be.getTank());
    }


}
