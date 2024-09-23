package dev.graeyamber.bedev.datagen.provider;

import dev.graeyamber.bedev.BEDev;
import dev.graeyamber.bedev.registry.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BEDev.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BlockRegistry.GNEISS_BLOCK);
        blockWithItem(BlockRegistry.GNEISS_BRICKS_BLOCK);
        blockWithItem(BlockRegistry.GNEISS_SMOOTH_BLOCK);
        blockWithItem(BlockRegistry.GNEISS_TILES_BLOCK);

        axisBlock(BlockRegistry.MANUAL_BURNER_BLOCK.get());

        blockWithItem(BlockRegistry.DIM_LAMP_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
