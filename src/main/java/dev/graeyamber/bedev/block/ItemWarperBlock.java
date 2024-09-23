package dev.graeyamber.bedev.block;

import dev.graeyamber.bedev.block.entity.ItemWarperBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ItemWarperBlock extends Block implements EntityBlock {
    public ItemWarperBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ItemWarperBlockEntity(blockPos, blockState);
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

    /// useItemOn to load items in the entity
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ItemWarperBlockEntity blockEntity) {
            ItemStack itemstack = player.getItemInHand(hand);

            if (!level.isClientSide && blockEntity.placeItem(player, player.getAbilities().instabuild ? itemstack.copy() : itemstack)) {
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.CONSUME;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
