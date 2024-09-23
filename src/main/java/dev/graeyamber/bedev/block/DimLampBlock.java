package dev.graeyamber.bedev.block;

import dev.graeyamber.bedev.block.entity.util.ITickableBlockEntity;
import dev.graeyamber.bedev.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

public class DimLampBlock extends Block implements EntityBlock {

    // property for Blockstate and model
    public static final BooleanProperty LIT;

    public DimLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(LIT, false));
    }

    @javax.annotation.Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(LIT, false);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        //..return new DimLampBlockEntity(blockPos, blockState);
        return BlockEntityRegistry.DIM_LAMP_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    /// using the interface to allow ticking, keep logic in the block entity class
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return ITickableBlockEntity.getTickerHelper(level);
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LIT});
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
