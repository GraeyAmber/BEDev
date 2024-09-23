package dev.graeyamber.bedev.datagen.provider;

import dev.graeyamber.bedev.registry.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(BlockRegistry.GNEISS_BLOCK.get());
        dropSelf(BlockRegistry.GNEISS_BRICKS_BLOCK.get());
        dropSelf(BlockRegistry.GNEISS_SMOOTH_BLOCK.get());
        dropSelf(BlockRegistry.GNEISS_TILES_BLOCK.get());

        dropSelf(BlockRegistry.MANUAL_BURNER_BLOCK.get());

        dropSelf(BlockRegistry.DIM_LAMP_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
