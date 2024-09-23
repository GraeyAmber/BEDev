package dev.graeyamber.bedev.block;

import dev.graeyamber.bedev.block.entity.WaterTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class WaterTankBlock extends Block implements EntityBlock {

    public WaterTankBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WaterTankBlockEntity(blockPos, blockState);
    }


    /// add a ticker to perform custom behavior
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        } else {
            /// on Server side, call the tickServer for our BE
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof WaterTankBlockEntity be) {
                    be.tickServer();
                }
            };
        }
    }

    /// useItemOn to load items in the entity
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WaterTankBlockEntity blockEntity) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (itemstack.getItem().equals(Items.WATER_BUCKET)) {
                if (!level.isClientSide && blockEntity.fillTank(player, new FluidStack(Fluids.WATER, 1000))) {
                    return ItemInteractionResult.SUCCESS;
                }
            }
            return ItemInteractionResult.CONSUME;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
