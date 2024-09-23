package dev.graeyamber.bedev.datagen.provider;

import dev.graeyamber.bedev.BEDev;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BEDev.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // basicItem(ItemRegistry.MANUAL_BURNER_BLOCK_ITEM.get());
    }
}
