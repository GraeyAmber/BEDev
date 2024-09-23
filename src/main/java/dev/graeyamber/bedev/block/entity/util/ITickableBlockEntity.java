package dev.graeyamber.bedev.block.entity.util;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

/*
* Implement this interface in blockentities that need a ticker
* */

public interface ITickableBlockEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level) {
        return getTickerHelper(level, false);
    }

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level, boolean allowClient) {
        if(level.isClientSide() && !allowClient)
            return null;
        return (level0, pos0, state0, blockEntity) -> ((ITickableBlockEntity)blockEntity).tick();
    }
}
