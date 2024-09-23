package dev.graeyamber.bedev.registry;

import dev.graeyamber.bedev.BEDev;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BEDev.MODID);


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BEDEV_MAIN_TAB = CREATIVE_TABS.register("bedev_main_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.bedev_main"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemRegistry.GNEISS_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for(var item : ItemRegistry.ITEMS.getEntries()) {
                    output.accept(item.get());
                }
            }).build());
}
