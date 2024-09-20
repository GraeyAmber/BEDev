package net.graeyamber.bedev.registry;

import net.graeyamber.bedev.BEDev;
import net.graeyamber.bedev.block.ManualBurnerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BEDev.MODID);


    public static final DeferredBlock<Block> GNEISS_BLOCK = BLOCKS.register("gneiss_block", () -> new Block(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> GNEISS_BRICKS_BLOCK = BLOCKS.register("gneiss_bricks_block", () -> new Block(GNEISS_BLOCK.get().properties()));
    public static final DeferredBlock<Block> GNEISS_SMOOTH_BLOCK = BLOCKS.register("gneiss_smooth_block", () -> new Block(GNEISS_BLOCK.get().properties()));
    public static final DeferredBlock<Block> GNEISS_TILES_BLOCK  = BLOCKS.register("gneiss_tiles_block" , () -> new Block(GNEISS_BLOCK.get().properties()));

    /// block that will store a blockentity
    public static final DeferredBlock<ManualBurnerBlock> MANUAL_BURNER_BLOCK  = BLOCKS.register("manual_burner_block", () -> new ManualBurnerBlock(GNEISS_BLOCK.get().properties()));
}
