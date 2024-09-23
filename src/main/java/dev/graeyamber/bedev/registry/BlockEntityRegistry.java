package dev.graeyamber.bedev.registry;

import dev.graeyamber.bedev.BEDev;
import dev.graeyamber.bedev.block.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityRegistry {

    /// registry, like blocks, items etc, needs to be called in main class
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BEDev.MODID);


    /// standard block entity for a block
    public static final Supplier<BlockEntityType<ManualBurnerBlockEntity>> MANUAL_BURNER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("manual_burner_block_entity", () -> BlockEntityType.Builder.of(ManualBurnerBlockEntity::new,
                    BlockRegistry.MANUAL_BURNER_BLOCK.get()).build(null));


    /// ticking blockentity
    public static final Supplier<BlockEntityType<DimLampBlockEntity>> DIM_LAMP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dim_lamp_block_entity", () -> BlockEntityType.Builder.of(DimLampBlockEntity::new,
                    BlockRegistry.DIM_LAMP_BLOCK.get()).build(null));


    /// ticking + inventory blockentity
    public static final Supplier<BlockEntityType<ItemWarperBlockEntity>> ITEM_WARPER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("item_warper_block_entity", () -> BlockEntityType.Builder.of(ItemWarperBlockEntity::new,
                    BlockRegistry.ITEM_WARPER_BLOCK.get()).build(null));

    /// WIP MENU with buttons + ticking
    public static final Supplier<BlockEntityType<RedstoneClockBlockEntity>> REDSTONE_CLOCK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("redstone_clock_block_entity", () -> BlockEntityType.Builder.of(RedstoneClockBlockEntity::new,
                    BlockRegistry.REDSTONE_CLOCK_BLOCK.get()).build(null));

    /// energy cap + ticking + item rendering
    /*
    public static final Supplier<BlockEntityType<?>> COAL_GENERATOR =
            BLOCK_ENTITIES.register("coal_generator_block_entity", () -> BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new,
                    BlockRegistry.COAL_GENERATOR_BLOCK.get()).build(null));

     */

    public static final Supplier<BlockEntityType<WaterTankBlockEntity>> WATER_TANK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("water_tank_block_entity", () -> BlockEntityType.Builder.of(WaterTankBlockEntity::new,
                    BlockRegistry.WATER_TANK_BLOCK.get()).build(null));
}
