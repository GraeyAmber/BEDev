package dev.graeyamber.bedev.registry;

import dev.graeyamber.bedev.BEDev;
import dev.graeyamber.bedev.block.*;
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

    /// block for ticking block entity
    public static final DeferredBlock<DimLampBlock> DIM_LAMP_BLOCK  = BLOCKS.register("dim_lamp_block", () -> new DimLampBlock(GNEISS_BLOCK.get().properties()));

    /// block with synced inventory + ticking
    public static final DeferredBlock<ItemWarperBlock> ITEM_WARPER_BLOCK  = BLOCKS.register("item_warper_block", () -> new ItemWarperBlock(GNEISS_BLOCK.get().properties()));

    /// WIP block with menu + ticking
    public static final DeferredBlock<RedstoneClockBlock> REDSTONE_CLOCK_BLOCK  = BLOCKS.register("redstone_clock_block", () -> new RedstoneClockBlock(GNEISS_BLOCK.get().properties()));

    /// energy + item + ticking + item rendering
    //: public static final DeferredBlock<?> COAL_GENRATOR_BLOCK  = BLOCKS.register("coal_generator_block", () -> new CoalGeneratorBlock(GNEISS_BLOCK.get().properties()));

    public static final DeferredBlock<?> WATER_TANK_BLOCK = BLOCKS.register("water_tank_block", () -> new WaterTankBlock(GNEISS_BLOCK.get().properties()));
}
