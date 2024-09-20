package net.graeyamber.bedev.registry;

import net.graeyamber.bedev.BEDev;
import net.graeyamber.bedev.block.entity.ManualBurnerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
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
}
