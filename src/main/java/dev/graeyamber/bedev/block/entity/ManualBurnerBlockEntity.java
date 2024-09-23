package dev.graeyamber.bedev.block.entity;

import dev.graeyamber.bedev.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/*
* tier 0
* this block entity doesn't really need to exist, since the fire is handled by the block
* */

public class ManualBurnerBlockEntity extends BlockEntity {
    public ManualBurnerBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.MANUAL_BURNER_BLOCK_ENTITY.get(), pos, blockState);
    }
}
