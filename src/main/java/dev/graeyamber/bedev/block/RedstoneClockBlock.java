package dev.graeyamber.bedev.block;

import dev.graeyamber.bedev.block.entity.ItemWarperBlockEntity;
import dev.graeyamber.bedev.block.entity.RedstoneClockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class RedstoneClockBlock extends Block implements EntityBlock {
    public RedstoneClockBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RedstoneClockBlockEntity(blockPos, blockState);
    }


    /// add a ticker to perform custom behavior
    @javax.annotation.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        } else {
            /// on Server side, call the tickServer for our BE
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof ItemWarperBlockEntity be) {
                    be.tickServer();
                }
            };
        }
    }

    /// useWithoutItem to open GUI screen
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof RedstoneClockBlockEntity blockEntity) {
                this.openScreen(level, pos, player);
                // could probably call openMenu directly, this implementation was taken from the lectern
            }

            return InteractionResult.CONSUME;
        }
    }

    private void openScreen(Level level, BlockPos pos, Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof RedstoneClockBlockEntity blockEntity) {
            player.openMenu(blockEntity);
        }

    }
}
