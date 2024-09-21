package net.graeyamber.bedev.block;

import net.graeyamber.bedev.block.entity.ManualBurnerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ManualBurnerBlock extends RotatedPillarBlock implements EntityBlock {
    public ManualBurnerBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ManualBurnerBlockEntity(blockPos, blockState);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(AXIS, Direction.Axis.Y);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof ManualBurnerBlockEntity blockEntity) {
                var posAbove = pos.above();
                var blockAbove = level.getBlockState(posAbove).getBlock();
                if (blockAbove.equals(Blocks.AIR) || blockAbove.equals(Blocks.CAVE_AIR) || blockAbove.equals(Blocks.VOID_AIR)) {
                    level.setBlock(posAbove, Blocks.FIRE.defaultBlockState(), 3);
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide());
            }
        }

        // return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        return ItemInteractionResult.CONSUME;
    }
}
