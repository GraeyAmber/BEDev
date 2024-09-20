package net.graeyamber.bedev.datagen.provider;

import net.graeyamber.bedev.BEDev;
import net.graeyamber.bedev.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BEDev.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //..basicItem(ItemRegistry.ITEMNAME.get());
    }
}
